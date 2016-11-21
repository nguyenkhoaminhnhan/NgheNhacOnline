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
    public class MusicianController : Controller
    {
        private MusicEntities db = new MusicEntities();

        // GET: Musician
        public ActionResult Index()
        {
            return View(db.Musicians.ToList());
        }

        // GET: Musician/Details/5
        public ActionResult Details(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Musician musician = db.Musicians.Find(id);
            if (musician == null)
            {
                return HttpNotFound();
            }
            return View(musician);
        }

        // GET: Musician/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Musician/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,Name,Birthday,Nationality,Detail,ImagePath,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Musician musician)
        {
            if (ModelState.IsValid)
            {
                db.Musicians.Add(musician);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(musician);
        }

        // GET: Musician/Edit/5
        public ActionResult Edit(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Musician musician = db.Musicians.Find(id);
            if (musician == null)
            {
                return HttpNotFound();
            }
            return View(musician);
        }

        // POST: Musician/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,Name,Birthday,Nationality,Detail,ImagePath,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Musician musician)
        {
            if (ModelState.IsValid)
            {
                db.Entry(musician).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(musician);
        }

        // GET: Musician/Delete/5
        public ActionResult Delete(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Musician musician = db.Musicians.Find(id);
            if (musician == null)
            {
                return HttpNotFound();
            }
            return View(musician);
        }

        // POST: Musician/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(long id)
        {
            Musician musician = db.Musicians.Find(id);
            db.Musicians.Remove(musician);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        //Get Musician Json
        public ActionResult GetMusician(long? ID)
        {
            var entities = db.Musicians;
            if (ID != null)
            {
                var result = entities.Where(x => x.ID == ID).Select(x => new { x.ID, x.Name, x.Birthday,x.Nationality, x.Detail, x.ImagePath });
                return Json(result, JsonRequestBehavior.AllowGet);
            }
            var allResult = entities.Select(x => new { x.ID, x.Name, x.Birthday, x.Nationality, x.Detail, x.ImagePath });
            return Json(allResult, JsonRequestBehavior.AllowGet);
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
