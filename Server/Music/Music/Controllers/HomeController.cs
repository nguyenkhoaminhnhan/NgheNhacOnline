using Music.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;

namespace Music.Controllers
{
    public class HomeController : Controller
    {
        private MusicEntities db = new MusicEntities();
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
        public ActionResult Search(string search) 
        {
            if(search!= null)
            {
                search.Trim().ToLower();
                string searchAscii = convertToUnSign3(search);
            
                IQueryable<Song> song = db.Songs;
                var Song = song.Where(x => x.Name.ToLower().Contains(search)
                    ||x.Name.ToLower().Contains(searchAscii)).OrderByDescending(x => x.ID).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, Album = x.Album.Name, Singer = x.Singer.Name, x.SourcePath, View = x.CustomInt3 });

                IQueryable<Singer> singer = db.Singers;
                var Singer = singer.Where(x => x.Name.ToLower().Contains(search)
                    || x.Name.ToLower().Contains(searchAscii)).OrderByDescending(x => x.ID).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });

                IQueryable<Album> album = db.Albums;
                var Album = album.Where(x => x.Name.ToLower().Contains(search)
                    || x.Name.ToLower().Contains(searchAscii)).OrderByDescending(x => x.ID).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, x.Detail, Singer = x.CustomString2 });

                IQueryable<Category> cat = db.Categories;
                var Cat = cat.Where(x => x.Name.ToLower().Contains(search)
                    || x.Name.ToLower().Contains(searchAscii)).Take(10).Select(x => new { x.ID, x.Name, x.Detail, x.ImagePath });

                return Json(new { Song, Singer, Album, Cat }, JsonRequestBehavior.AllowGet);
            }
            return Json(new { }, JsonRequestBehavior.AllowGet);
        }

        public string convertToUnSign3(string s)
        {
            Regex regex = new Regex("\\p{IsCombiningDiacriticalMarks}+");
            string temp = s.Normalize(NormalizationForm.FormD);
            return regex.Replace(temp, String.Empty).Replace('\u0111', 'd').Replace('\u0110', 'D');
        }  
    }
}