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
        public ActionResult getSinger()
        {

            IQueryable<Singer> entities = db.Singers;
            var VietNam = entities.Where(x => x.CustomInt1 == 1).OrderBy(x => x.ID).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
            var AuMy = entities.Where(x => x.CustomInt1 == 2).OrderBy(x => x.ID).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
            var ChauA = entities.Where(x => x.CustomInt1 == 3).OrderBy(x => x.ID).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
            var HoaTau = entities.Where(x => x.CustomInt1 == 4).OrderBy(x => x.ID).Take(9).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
            return Json(new { VietNam, AuMy, ChauA, HoaTau }, JsonRequestBehavior.AllowGet);
        }

        public ActionResult getSingerVN(int page) 
        {

            IQueryable<Singer> entities = db.Singers;
            var VietNam = entities.Where(x=> x.CustomInt1 ==1).OrderBy(x =>x.ID).Skip(12*page).Take(12).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday,x.Nationality, x.Detail});
            return Json(VietNam, JsonRequestBehavior.AllowGet);
        }
        public ActionResult getSingerAuMy(int page)
        {

            IQueryable<Singer> entities = db.Singers;
            var AuMy = entities.Where(x => x.CustomInt1 == 2).OrderBy(x => x.ID).Skip(12 * page).Take(12).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
            return Json(AuMy, JsonRequestBehavior.AllowGet);
        }
        public ActionResult getSingerChauA(int page)
        {

            IQueryable<Singer> entities = db.Singers;
            var ChauA = entities.Where(x => x.CustomInt1 == 3).OrderBy(x => x.ID).Skip(12 * page).Take(12).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
            return Json(ChauA, JsonRequestBehavior.AllowGet);
        }
        public ActionResult getSingerHoaTau(int page)
        {

            IQueryable<Singer> entities = db.Singers;
            var HoaTau = entities.Where(x => x.CustomInt1 == 4).OrderBy(x => x.ID).Skip(12 * page).Take(12).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
            return Json(HoaTau, JsonRequestBehavior.AllowGet);
        }

        public ActionResult searchSinger(string name, int page)
        {

            IQueryable<Singer> entities = db.Singers;
            var result = entities.Where(x => x.Name.ToLower().Contains(name.ToLower())).OrderBy(x => x.ID).Skip(10 * page).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
            return Json(result, JsonRequestBehavior.AllowGet);
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
