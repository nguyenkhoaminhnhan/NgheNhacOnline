package com.example.minhnhan.music.Utils;

/**
 * Created by Minh Nhan on 11/20/2016.
 */

public class Constants {
    // url
    public static final String DOMAIN = "http://nghenhac.apphb.com/";
    public static final String GET_SHOW_CASE = DOMAIN + "song/getshowcase";

    // params
    public static final String Song_URL = "songUrl";
    public static final String SONGID = "songID";
    public static final String SONG_IMAGE = "songImage";

    public static final String TEST ="{\"showCase\":[{\"ID\":1033,\"Name\":\"Maps\",\"ImagePath\":null,\"Album\":\"V (Deluxe Version)\",\"Singer\":\"MAROON 5\",\"Year\":\"2015\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"Hot 2015\",\"Rating\":null},{\"ID\":1032,\"Name\":\"Leaving California\",\"ImagePath\":null,\"Album\":\"V (Deluxe Version)\",\"Singer\":\"MAROON 5\",\"Year\":\"2015\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1031,\"Name\":\"It Was Always You\",\"ImagePath\":null,\"Album\":\"V (Deluxe Version)\",\"Singer\":\"MAROON 5\",\"Year\":\"2015\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1030,\"Name\":\"In Your Pocket\",\"ImagePath\":null,\"Album\":\"V (Deluxe Version)\",\"Singer\":\"MAROON 5\",\"Year\":\"2015\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1029,\"Name\":\"Animals\",\"ImagePath\":null,\"Album\":\"V (Deluxe Version)\",\"Singer\":\"MAROON 5\",\"Year\":\"2015\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"Hot 2015\",\"Rating\":null}],\"hotSong\":[{\"ID\":1000,\"Name\":\"Lạ\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1001,\"Name\":\"Giữa 7 Tỷ Người\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1002,\"Name\":\"Điều Em Không Học Được\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1003,\"Name\":\"Lạ (Beat)\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1004,\"Name\":\"Giữa 7 Tỷ Người (Beat)\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1005,\"Name\":\"Anh Còn Nợ Em\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1006,\"Name\":\"Chạy Theo Lý Trí\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1007,\"Name\":\"Chợt Nhớ Về Anh\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1008,\"Name\":\"Có Khi Nào Rời Xa\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1009,\"Name\":\"Đành Như Thế Thôi\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New\",\"Rating\":null},{\"ID\":1010,\"Name\":\"Gửi Người Yêu Cũ\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1011,\"Name\":\"Khi Mình Yêu\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1012,\"Name\":\"Lặng Thầm Một Tình Yêu\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1013,\"Name\":\"Nếu Em Được Lựa Chọn\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1014,\"Name\":\"Ngày Mai Em Đi\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null}],\"hotSongWeek\":[{\"ID\":1000,\"Name\":\"Lạ\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1001,\"Name\":\"Giữa 7 Tỷ Người\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1002,\"Name\":\"Điều Em Không Học Được\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1003,\"Name\":\"Lạ (Beat)\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1004,\"Name\":\"Giữa 7 Tỷ Người (Beat)\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1005,\"Name\":\"Anh Còn Nợ Em\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1006,\"Name\":\"Chạy Theo Lý Trí\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1007,\"Name\":\"Chợt Nhớ Về Anh\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1008,\"Name\":\"Có Khi Nào Rời Xa\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1009,\"Name\":\"Đành Như Thế Thôi\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New\",\"Rating\":null}],\"hotSongMonth\":[{\"ID\":1000,\"Name\":\"Lạ\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1001,\"Name\":\"Giữa 7 Tỷ Người\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1002,\"Name\":\"Điều Em Không Học Được\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1003,\"Name\":\"Lạ (Beat)\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1004,\"Name\":\"Giữa 7 Tỷ Người (Beat)\",\"ImagePath\":null,\"Album\":\"Lạ\",\"Singer\":\"LYNA THÙY LINH\",\"Year\":\"2006\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":null,\"Rating\":null},{\"ID\":1005,\"Name\":\"Anh Còn Nợ Em\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1006,\"Name\":\"Chạy Theo Lý Trí\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1007,\"Name\":\"Chợt Nhớ Về Anh\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1008,\"Name\":\"Có Khi Nào Rời Xa\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New Việt Album 2016\",\"Rating\":null},{\"ID\":1009,\"Name\":\"Đành Như Thế Thôi\",\"ImagePath\":null,\"Album\":\"Gửi Người Yêu Cũ\",\"Singer\":\"Hồ Ngọc Hà\",\"Year\":\"2016\",\"Format\":\"mp3\",\"BitRate\":128,\"Tag\":\"New\",\"Rating\":null}]}";
}
