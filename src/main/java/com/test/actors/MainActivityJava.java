package com.test.actors;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.test.actors.ui.RemoteImageView;

public class MainActivityJava extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final RemoteImageView riv = new RemoteImageView(this);
    riv.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        riv.setUrl("http://www.h-online.com/imgs/43/5/5/4/4/8/6/a484cda3f9839fe6.png");
      }
    });

    riv.setBackgroundColor(Color.RED);
    setContentView(riv);
  }
}
