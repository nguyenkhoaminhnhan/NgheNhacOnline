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
    public class CategoryController : Controller
    {
        private MusicEntities db = new MusicEntities();

        // GET: Category
        public ActionResult Index()
        {
            return View(db.Categories.ToList());
        }

        // GET: Category/Details/5
        public ActionResult Details(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Category category = db.Categories.Find(id);
            if (category == null)
            {
                return HttpNotFound();
            }
            return View(category);
        }

        // GET: Category/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Category/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,Name,Detail,ImagePath,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Category category)
        {
            if (ModelState.IsValid)
            {
                db.Categories.Add(category);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(category);
        }

        // GET: Category/Edit/5
        public ActionResult Edit(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Category category = db.Categories.Find(id);
            if (category == null)
            {
                return HttpNotFound();
            }
            return View(category);
        }

        // POST: Category/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,Name,Detail,ImagePath,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Category category)
        {
            if (ModelState.IsValid)
            {
                db.Entry(category).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(category);
        }

        // GET: Category/Delete/5
        public ActionResult Delete(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Category category = db.Categories.Find(id);
            if (category == null)
            {
                return HttpNotFound();
            }
            return View(category);
        }

        // POST: Category/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(long id)
        {
            Category category = db.Categories.Find(id);
            db.Categories.Remove(category);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        //Get Category Json
        public ActionResult GetCat(long? ID)
        {
            var entities = db.Categories;
            if(ID != null)
            {
                var result = entities.Where(x => x.ID == ID).Select(x => new { x.ID, x.Name, x.Detail, x.ImagePath });
                return Json(result, JsonRequestBehavior.AllowGet);
            }
            var allResult = entities.Select(x => new { x.ID, x.Name, x.Detail, x.ImagePath });
            return Json(allResult, JsonRequestBehavior.AllowGet);
        }

        //Search Category Action
        public ActionResult Search(string cat_name)
        {
            IQueryable<Category> matches = db.Categories;

            if (!string.IsNullOrEmpty(cat_name))
            {
                matches = matches.Where(x => x.Name.ToLower().Contains(cat_name.ToLower()));
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
