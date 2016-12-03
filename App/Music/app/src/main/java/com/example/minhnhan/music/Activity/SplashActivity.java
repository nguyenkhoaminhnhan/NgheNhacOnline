package com.example.minhnhan.music.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.minhnhan.music.Model.Async.AsyncHomePage;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Page.HomePage;
import com.example.minhnhan.music.R;
import com.example.minhnhan.music.Utils.Utils;

import static com.example.minhnhan.music.Utils.Constants.GET_SHOW_CASE;

public class SplashActivity extends AppCompatActivity {

    private static final int INTERNET_CONNECTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // get showcase list from server
        //check Internet connection
        checkInternet();
    }

    private void checkInternet() {
        if (Utils.isNetworkAvailable(this)) {
            startApp();
        } else {
            //Show message about Internet problem and allow user config connection
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Xin vui lòng kết nối Internet!");
            dlgAlert.setTitle("Kết nối Internet thất bại!");
            dlgAlert.setPositiveButton("Cấu hình", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //
                    startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), INTERNET_CONNECTION);
                }
            });
            dlgAlert.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dlgAlert.create().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTERNET_CONNECTION) {
            if (!Utils.isNetworkAvailable(this)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // waiting for connection in 10s
                        boolean hasConnection;
                        int count = 0;
                        do {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                            hasConnection = Utils.isNetworkAvailable(SplashActivity.this);
                        } while (!hasConnection && count++ < 5);
                        // app is being continued ...
                        final boolean finalResult = hasConnection;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (finalResult) {
                                    startApp();
                                } else {
                                    showExitDialog("Kết nối thất bại", "Xin vui lòng kết nối GPS!");
                                }
                            }
                        });
                    }
                }).start();
            } else {
                startApp();
            }
        }
    }

    private void showExitDialog(String title, String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("Cấu hình", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dlgAlert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        dlgAlert.create().show();
    }

    private void startApp() {

        //get bank list
        String linkBank = GET_SHOW_CASE;
        AsyncHomePage getSongData = new AsyncHomePage(new AsyncListener() {
            @Override
            public void onAsyncComplete() {
                HomePage homePage = DataManager.getInstance().getHomeDetail();
                if (homePage != null
                        && homePage.getShowCase().size() > 0
                        &&homePage.getHotSong().size() >0
                        &&homePage.getHotSongWeek().size() >0
                        &&homePage.getHotSongMonth().size() >0) {
                    //Go to Map activity
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    showExitDialog("Thông báo", "Tải dữ liệu thất bại xin vui lòng chạy lại ứng dụng.");
                }
            }
        });
        getSongData.execute(linkBank);
    }
}