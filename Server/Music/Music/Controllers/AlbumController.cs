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
    public class AlbumController : Controller
    {
        private MusicEntities db = new MusicEntities();

        // GET: Album
        public ActionResult Index()
        {
            return View(db.Albums.ToList());
        }

        // GET: Album/Details/5
        public ActionResult Details(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Album album = db.Albums.Find(id);
            if (album == null)
            {
                return HttpNotFound();
            }
            return View(album);
        }

        // GET: Album/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Album/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,Name,DateIssue,Detail,ImagePath,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Album album)
        {
            if (ModelState.IsValid)
            {
                db.Albums.Add(album);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(album);
        }

        // GET: Album/Edit/5
        public ActionResult Edit(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Album album = db.Albums.Find(id);
            if (album == null)
            {
                return HttpNotFound();
            }
            return View(album);
        }

        // POST: Album/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,Name,DateIssue,Detail,ImagePath,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Album album)
        {
            if (ModelState.IsValid)
            {
                db.Entry(album).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(album);
        }

        // GET: Album/Delete/5
        public ActionResult Delete(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Album album = db.Albums.Find(id);
            if (album == null)
            {
                return HttpNotFound();
            }
            return View(album);
        }

        // POST: Album/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(long id)
        {
            Album album = db.Albums.Find(id);
            db.Albums.Remove(album);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        //Get Album Json
        public ActionResult GetAlbum()
        {
            var entities = db.Albums;
            var VietNam = entities.Where(x => x.CustomInt1 == 1).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Detail });
            var AuMy = entities.Where(x => x.CustomInt1 == 2).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Detail });
            var ChauA = entities.Where(x => x.CustomInt1 == 3).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Detail });
            var KhongLoi = entities.Where(x => x.CustomInt1 == 4).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Detail });
            return Json(new { VietNam, AuMy, ChauA, KhongLoi }, JsonRequestBehavior.AllowGet);
        }
        public ActionResult searchAlbum(string cat, int page)
        {
            cat = cat.Replace("%20", " ");
            IQueryable<Album> entities = db.Albums;
            var result = entities.Where(x => x.CustomString1.ToLower().Contains(cat.ToLower())).OrderBy(x => x.ID).Skip(9 * page).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Detail });
            return Json(result, JsonRequestBehavior.AllowGet);
        }
        //Search Ablum Action
        public ActionResult Search(string album)
        {
            IQueryable<Album> matches = db.Albums;

            if (!string.IsNullOrEmpty(album))
            {
                matches = matches.Where(x => x.Name.ToLower().Contains(album.ToLower()));
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
