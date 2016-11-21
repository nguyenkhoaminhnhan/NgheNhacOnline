using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Music.Models;

namespace Music.Controllers
{
    public class Song_CategoryController : Controller
    {
        private MusicEntities db = new MusicEntities();

        // GET: Song_Category
        public ActionResult Index()
        {
            var song_Category = db.Song_Category.Include(s => s.Category).Include(s => s.Song);
            return View(song_Category.ToList());
        }

        // GET: Song_Category/Details/5
        public ActionResult Details(long? song_id, long? cat_id)
        {
            if (song_id == null || cat_id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Song_Category song_Category = db.Song_Category.Find(song_id, cat_id);
            if (song_Category == null)
            {
                return HttpNotFound();
            }
            return View(song_Category);
        }

        // GET: Song_Category/Create
        public ActionResult Create()
        {
            ViewBag.Category_ID = new SelectList(db.Categories, "ID", "Name");
            ViewBag.Song_ID = new SelectList(db.Songs, "ID", "Name");
            return View();
        }

        // POST: Song_Category/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Song_ID,Category_ID,Detail")] Song_Category song_Category)
        {
            if (ModelState.IsValid)
            {
                db.Song_Category.Add(song_Category);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.Category_ID = new SelectList(db.Categories, "ID", "Name", song_Category.Category_ID);
            ViewBag.Song_ID = new SelectList(db.Songs, "ID", "Name", song_Category.Song_ID);
            return View(song_Category);
        }

        // GET: Song_Category/Edit/5
        public ActionResult Edit(long? song_id, long? cat_id)
        {
            if (song_id == null || cat_id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Song_Category song_Category = db.Song_Category.Find(song_id, cat_id);
            if (song_Category == null)
            {
                return HttpNotFound();
            }
            ViewBag.Category_ID = new SelectList(db.Categories, "ID", "Name", song_Category.Category_ID);
            ViewBag.Song_ID = new SelectList(db.Songs, "ID", "Name", song_Category.Song_ID);
            return View(song_Category);
        }

        // POST: Song_Category/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Song_ID,Category_ID,Detail")] Song_Category song_Category)
        {
            if (ModelState.IsValid)
            {
                db.Entry(song_Category).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.Category_ID = new SelectList(db.Categories, "ID", "Name", song_Category.Category_ID);
            ViewBag.Song_ID = new SelectList(db.Songs, "ID", "Name", song_Category.Song_ID);
            return View(song_Category);
        }

        // GET: Song_Category/Delete/5
        public ActionResult Delete(long? song_id, long? cat_id)
        {
            if (song_id == null || cat_id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Song_Category song_Category = db.Song_Category.Find(song_id, cat_id);
            if (song_Category == null)
            {
                return HttpNotFound();
            }
            return View(song_Category);
        }

        // POST: Song_Category/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(long? song_id, long? cat_id)
        {
            if (song_id == null || cat_id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Song_Category song_Category = db.Song_Category.Find(song_id, cat_id);
            db.Song_Category.Remove(song_Category);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        //Get S-Cat Json
        public ActionResult GetSCat(long? song_id, long? cat_id)
        {
            var entities = db.Song_Category;
            
            if (song_id != null && cat_id != null)
            {
                var result = entities.Where(x => x.Song_ID == song_id && x.Category_ID == cat_id).Select(x => new { x.Song_ID, x.Category_ID, x.Detail });
                return Json(result, JsonRequestBehavior.AllowGet);
            }
            else if(song_id != null)
            {
                var result = entities.Where(x => x.Song_ID == song_id).Select(x => new { x.Song_ID, x.Category_ID, x.Detail });
                return Json(result, JsonRequestBehavior.AllowGet);
            }
            else if(cat_id != null)
            {
                var result = entities.Where(x => x.Category_ID == cat_id).Select(x => new { x.Song_ID, x.Category_ID, x.Detail });
                return Json(result, JsonRequestBehavior.AllowGet);
            }

            var allResult = entities.Select(x => new { x.Song_ID, x.Category_ID, x.Detail });
            return Json(allResult, JsonRequestBehavior.AllowGet);
        }

        ////Search Song - Category Action
        public ActionResult Search(string song_name, string cat_name)
        {
            IQueryable<Song_Category> matches = db.Song_Category.Include(s => s.Category).Include(s => s.Song);

            if (!string.IsNullOrEmpty(song_name) && !string.IsNullOrEmpty(cat_name))
            {
                matches = matches.Where(x => x.Song.Name.ToLower().Contains(song_name.ToLower()) 
                    && x.Category.Name.ToLower().Contains(song_name.ToLower()));
            }
            else if (!string.IsNullOrEmpty(song_name))
            {
                matches = matches.Where(x => x.Song.Name.ToLower().Contains(song_name.ToLower()));
            }
            else if (!string.IsNullOrEmpty(cat_name))
            {
                matches = matches.Where(x => x.Category.Name.ToLower().Contains(cat_name.ToLower()));
            }
            return View("Index", matches);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
