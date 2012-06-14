package com.test.actors;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.test.actors.ui.RemoteImageView;

public class MainActivityJava extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    final RemoteImageView riv = (RemoteImageView)findViewById(R.id.remote_image_view);
    riv.setUrl("http://www.h-online.com/imgs/43/5/5/4/4/8/6/a484cda3f9839fe6.png");

    riv.setBackgroundColor(Color.RED);
  }
}
