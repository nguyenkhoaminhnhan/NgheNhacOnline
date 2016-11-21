﻿using System;
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
    public class SongController : Controller
    {
        private MusicEntities db = new MusicEntities();

        // GET: Song
        public ActionResult Index()
        {
            var songs = db.Songs.Include(s => s.Album).Include(s => s.Musician).Include(s => s.Singer);
            return View(songs.ToList());
        }

        // GET: Song/Details/5
        public ActionResult Details(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Song song = db.Songs.Find(id);
            if (song == null)
            {
                return HttpNotFound();
            }
            return View(song);
        }

        // GET: Song/Create
        public ActionResult Create()
        {
            ViewBag.Album_ID = new SelectList(db.Albums, "ID", "Name");
            ViewBag.Musician_ID = new SelectList(db.Musicians, "ID", "Name");
            ViewBag.Singer_ID = new SelectList(db.Singers, "ID", "Name");
            return View();
        }

        // POST: Song/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,Name,Album_ID,Singer_ID,Musician_ID,ImagePath,SourcePath,Year,Format,BitRate,Tag,Size,Length,Rating,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Song song)
        {
            if (ModelState.IsValid)
            {
                db.Songs.Add(song);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.Album_ID = new SelectList(db.Albums, "ID", "Name", song.Album_ID);
            ViewBag.Musician_ID = new SelectList(db.Musicians, "ID", "Name", song.Musician_ID);
            ViewBag.Singer_ID = new SelectList(db.Singers, "ID", "Name", song.Singer_ID);
            return View(song);
        }

        // GET: Song/Edit/5
        public ActionResult Edit(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Song song = db.Songs.Find(id);
            if (song == null)
            {
                return HttpNotFound();
            }
            ViewBag.Album_ID = new SelectList(db.Albums, "ID", "Name", song.Album_ID);
            ViewBag.Musician_ID = new SelectList(db.Musicians, "ID", "Name", song.Musician_ID);
            ViewBag.Singer_ID = new SelectList(db.Singers, "ID", "Name", song.Singer_ID);
            return View(song);
        }

        // POST: Song/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,Name,Album_ID,Singer_ID,Musician_ID,ImagePath,SourcePath,Year,Format,BitRate,Tag,Size,Length,Rating,CustomString1,CustomString2,CustomString3,CustomString4,CustomString5,CustomInt1,CustomInt2,CustomInt3,CustomInt4,CustomInt5,CustomBool1,CustomBool2,CustomBool3,CustomBool4,CustomBool5")] Song song)
        {
            if (ModelState.IsValid)
            {
                db.Entry(song).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.Album_ID = new SelectList(db.Albums, "ID", "Name", song.Album_ID);
            ViewBag.Musician_ID = new SelectList(db.Musicians, "ID", "Name", song.Musician_ID);
            ViewBag.Singer_ID = new SelectList(db.Singers, "ID", "Name", song.Singer_ID);
            return View(song);
        }

        // GET: Song/Delete/5
        public ActionResult Delete(long? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Song song = db.Songs.Find(id);
            if (song == null)
            {
                return HttpNotFound();
            }
            return View(song);
        }

        // POST: Song/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(long id)
        {
            Song song = db.Songs.Find(id);
            db.Songs.Remove(song);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        //Get Song Json
        public ActionResult GetSong(long? ID)
        {
            var entities = db.Songs;
            if (ID != null)
            {
                var result = entities.Where(x => x.ID == ID).Select(x => new { x.ID, x.Name, x.ImagePath, x.SourcePath, Album = x.Album.Name, Musician = x.Musician.Name, Singer = x.Singer.Name, x.Year, x.Format, x.BitRate, x.Tag, x.Rating });
                return Json(result, JsonRequestBehavior.AllowGet);
            }
            var allResult = entities.Select(x => new { x.ID, x.Name, x.ImagePath, x.SourcePath, Album = x.Album.Name, Musician = x.Musician.Name, Singer=x.Singer.Name, x.Year, x.Format, x.BitRate, x.Tag, x.Rating });
            return Json(allResult, JsonRequestBehavior.AllowGet);
        }

        //
        public ActionResult GetSongList(long? id, string name, string album, int? page, int? size)
        {            
            IQueryable<Song> matches = db.Songs;
            if (id != null)
            {
                matches = matches.Where(x => x.ID == id.Value);
            }
            if (!string.IsNullOrEmpty(name))
            {
                matches = matches.Where(x => x.Name.ToLower().Contains(name.ToLower()));
            }
            if (!string.IsNullOrEmpty(album))
            {
                matches = matches.Where(x => x.Album.Name.ToLower().Contains(album.ToLower()));
            }
            if (page != null && size != null)
            {
                matches = matches.OrderBy(x => x.ID).Skip(page.Value * size.Value).Take(size.Value);
            }
            var result = matches.Select(x => new { x.ID, x.Name, x.ImagePath, x.SourcePath, Album = x.Album.Name, Musician = x.Musician.Name, Singer = x.Singer.Name, x.Year, x.Format, x.BitRate, x.Tag, x.Rating });
            return Json(result, JsonRequestBehavior.AllowGet);
        }


        public ActionResult GetShowCase()
        {
            IQueryable<Song> entities = db.Songs;
            var showCase = entities.OrderByDescending(x => x.ID).Take(5).Select(x => new { x.ID, x.Name, x.ImagePath, Album = x.Album.Name, Singer = x.Singer.Name, x.Year, x.Format, x.BitRate, x.Tag, x.Rating });

            var hotSong = entities.OrderByDescending(x => x.Rating).Take(15).Select(x => new { x.ID, x.Name, x.ImagePath, Album = x.Album.Name, Singer = x.Singer.Name, x.Year, x.Format, x.BitRate, x.Tag, x.Rating });

            var hotSongWeek = entities.OrderByDescending(x => x.CustomInt1).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, Album = x.Album.Name, Singer = x.Singer.Name, x.Year, x.Format, x.BitRate, x.Tag, x.Rating });

            var hotSongMonth = entities.OrderByDescending(x => x.CustomInt2).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, Album = x.Album.Name, Singer = x.Singer.Name, x.Year, x.Format, x.BitRate, x.Tag, x.Rating });

            return Json(new { showCase, hotSong = hotSong, hotSongWeek, hotSongMonth }, JsonRequestBehavior.AllowGet);

        }

        //Search Song Action        
        public ActionResult Search(string search_query)
        {
            IQueryable<Song> matches = db.Songs.Include(s => s.Album).Include(s => s.Musician).Include(s => s.Singer);

            if (!string.IsNullOrEmpty(search_query))
            {
                matches = matches.Where(x => x.Name.ToLower().Contains(search_query.ToLower()));
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