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
    public class SingerController : Controller
    {
        private MusicEntities db = new MusicEntities();

        // GET: Singer
        public ActionResult Index()
        {
            return View(db.Singers.ToList());
        }

        // GET: Singer/Details/5
        public ActionResult Details(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Singer singer = db.Singers.Find(id);
            if (singer == null)
            {
                return HttpNotFound();
            }
            return View(singer);
        }

        // GET: Singer/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Singer/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,Name,Birthday,Nationality,Detail,ImagePath,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Singer singer)
        {
            if (ModelState.IsValid)
            {
                db.Singers.Add(singer);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(singer);
        }

        // GET: Singer/Edit/5
        public ActionResult Edit(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Singer singer = db.Singers.Find(id);
            if (singer == null)
            {
                return HttpNotFound();
            }
            return View(singer);
        }

        // POST: Singer/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,Name,Birthday,Nationality,Detail,ImagePath,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Singer singer)
        {
            if (ModelState.IsValid)
            {
                db.Entry(singer).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(singer);
        }

        // GET: Singer/Delete/5
        public ActionResult Delete(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Singer singer = db.Singers.Find(id);
            if (singer == null)
            {
                return HttpNotFound();
            }
            return View(singer);
        }

        // POST: Singer/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(long id)
        {
            Singer singer = db.Singers.Find(id);
            db.Singers.Remove(singer);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        //Get Singer Json
        public ActionResult GetSinger(long? ID)
        {
            var entities = db.Singers;
            if (ID != null)
            {
                var result = entities.Where(x => x.ID == ID).Select(x => new { x.ID, x.Name, x.Birthday, x.Nationality, x.Detail, x.ImagePath });
                return Json(result, JsonRequestBehavior.AllowGet);
            }
            var allResult = entities.Select(x => new { x.ID, x.Name, x.Birthday, x.Nationality, x.Detail, x.ImagePath });
            return Json(allResult, JsonRequestBehavior.AllowGet);
        }

        //Search Singer Action        
        public ActionResult Search(string singer)
        {
            IQueryable<Singer> matches = db.Singers;

            if (!string.IsNullOrEmpty(singer))
            {
                matches = matches.Where(x => x.Name.ToLower().Contains(singer.ToLower()));
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
