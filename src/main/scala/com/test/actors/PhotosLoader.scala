package com.test.actors

import scala.actors.Actor
import android.util.Log
import android.graphics.Bitmap
import CacheActor._
import DownloadActor._

object PhotosLoader extends Actor {
  start()

  case class LoadImage(photoToLoad: String)
  case class ImageLoaded(photoToLoad:String, bitmap: Bitmap)

  override def act() {
    loop {
      react {
        case LoadImage(photoToLoad) => {
          Log.d("com.test.actors", "LoadImage with photoToLoad " + photoToLoad + " from " + Thread.currentThread)
          val bitmapFromCache = CacheActor !? Contains(photoToLoad)

          if ( bitmapFromCache != None ) {
            sender ! ImageLoaded(photoToLoad, bitmapFromCache.asInstanceOf[Bitmap])
          } else {
            val bitmapDownloaded = DownloadActor !? DownloadBitmap(photoToLoad)
            if ( bitmapDownloaded != null ) {
              CacheActor ! Put(photoToLoad, bitmapDownloaded.asInstanceOf[Bitmap])
              sender ! ImageLoaded(photoToLoad, bitmapDownloaded.asInstanceOf[Bitmap])
            }
          }
        }
      }
    }
  }
}
