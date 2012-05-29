package com.test.actors

import scala.actors.Actor
import android.util.Log

object DownloadActor extends Actor {

  case class DownloadBitmap(url: String)

  start()
  override def act() {
    receive {
      case DownloadBitmap(url) => {
        Log.d("com.test.actors", "DownloadBitmap with " + url + " from " + Thread.currentThread);
        reply(DownloadHelper.downloadFile(url))
        Log.d("com.test.actors", "DownloadBitmap finished!");
      }
    }
  }

}