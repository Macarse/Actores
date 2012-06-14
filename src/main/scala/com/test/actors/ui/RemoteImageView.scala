package com.test.actors.ui

import android.widget.ImageView
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.test.actors.PhotosLoader
import scala.actors._
import Actor._
import android.graphics.Bitmap
import com.test.actors.PhotosLoader.ImageLoaded
import com.test.actors.PhotosLoader.LoadImage

class RemoteImageView(context: Context, attrs: AttributeSet)
  extends ImageView(context, attrs) {

  private var mUrl: String = _

  def this(context: Context) = this(context, null)

  def setUrl(url: String) {
    mUrl = url

    val a = actor {
      Log.d("RemoteImageView", "setting url: " + url + " from " + Thread.currentThread)
      PhotosLoader ! LoadImage(mUrl)
      loop {
        react {
          case ImageLoaded(photoToLoad, bitmap) => {
            Log.d("RemoteImageView", "setting ImageBitmap from " + Thread.currentThread)
            this.post(new Runnable() {
              def run() {
                setImageBitmap(bitmap)
              }
            });
          }
        }
      }
    }
  }
}