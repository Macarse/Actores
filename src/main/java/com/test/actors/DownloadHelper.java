package com.test.actors;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadHelper {

  public static Bitmap downloadFile(String imgUrl) {
    Bitmap bitmap = null;
    URL url = null;

    try {
      url = new URL(imgUrl);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    try {
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoInput(true);
      conn.connect();

      InputStream is = conn.getInputStream();
      bitmap = BitmapFactory.decodeStream(is);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return bitmap;
  }
}
