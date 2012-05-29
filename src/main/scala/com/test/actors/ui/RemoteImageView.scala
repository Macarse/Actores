package com.test.actors.ui

import android.widget.ImageView
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.test.actors.PhotosLoader
import com.test.actors.PhotosLoader._
import scala.actors.Actor
import android.graphics.Bitmap

class RemoteImageView(context: Context, attrs: AttributeSet)
  extends ImageView(context, attrs) with Actor {

  private var mUrl: String = _

  def this(context: Context) = this(context, null)

  start()

  def setUrl(url: String) {
    mUrl = url
    Log.d("RemoteImageView", "setting url: " + url + " from " + Thread.currentThread)
    PhotosLoader ! LoadImage(url)
  }

  override def act() {
    receive {
      case ImageLoaded(photoToLoad: String, bitmap: Bitmap) => {
        Log.d("RemoteImageView", "setting ImageBitmap from " + Thread.currentThread)
        setImageBitmap(bitmap)
      }
    }
  }
}