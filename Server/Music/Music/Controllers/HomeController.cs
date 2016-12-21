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
        public ActionResult Search(string search, string type, int page) 
        {
            List<object> song = new List<object>();
            List<object> singer = new List<object>();
            List<object> cat = new List<object>();
            List<object> album = new List<object>();

            string searchAscii = convertToUnSign3(search);

            if ("all".Equals(type) || "song".Equals(type))
            {
                IQueryable<Song> songs = db.Songs;
                var temp = songs.Where(x => x.Name.ToLower().Contains(search)
                        || x.Name.ToLower().Contains(searchAscii)).Distinct().OrderBy(x => x.ID).Skip(page * 10).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, Album = x.Album.Name, Singer = x.Singer.Name, x.SourcePath, View = x.CustomInt3 });
                song.Add(temp);
            }
            if ("all".Equals(type) || "singer".Equals(type))
            {
                IQueryable<Singer> singers = db.Singers;
                var temp = singers.Where(x => x.Name.ToLower().Contains(search)
                        || x.Name.ToLower().Contains(searchAscii)).Distinct().OrderBy(x => x.ID).Skip(page * 10).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, x.Birthday, x.Nationality, x.Detail });
                singer.Add(temp);
            }
            if ("all".Equals(type) || "cat".Equals(type))
            {
                IQueryable<Category> cats = db.Categories;
                var temp = cats.Where(x => x.Name.ToLower().Contains(search)
                        || x.Name.ToLower().Contains(searchAscii)).Distinct().OrderBy(x => x.ID).Skip(page * 10).Take(10).Select(x => new { x.ID, x.Name, x.Detail, x.ImagePath });
                cat.Add(temp);
            }
            if ("all".Equals(type) || "album".Equals(type))
            {
                IQueryable<Album> albums = db.Albums;
                var temp = albums.Where(x => x.Name.ToLower().Contains(search)
                        || x.Name.ToLower().Contains(searchAscii)).Distinct().OrderBy(x => x.ID).Skip(page * 10).Take(10).Select(x => new { x.ID, x.Name, x.ImagePath, x.Detail, Singer = x.CustomString2 });
                album.Add(temp);
            }

            return Json(new { Song = song, Singer = singer, Cat = cat, Album = album }, JsonRequestBehavior.AllowGet);            
        }

        public string convertToUnSign3(string s)
        {
            Regex regex = new Regex("\\p{IsCombiningDiacriticalMarks}+");
            string temp = s.Normalize(NormalizationForm.FormD);
            return regex.Replace(temp, String.Empty).Replace('\u0111', 'd').Replace('\u0110', 'D');
        }  
    }
}