package com.example.notificationapp

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class Worker (context: Context, workerParams: WorkerParameters) : Worker(context,workerParams) {
    override  fun doWork(): Result{
        try{
            val miVar = inputData.getInt("key1", 0)

            var builder = NotificationCompat.Builder(applicationContext, "100")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Titulo de notificacion")
                    .setContentText("Contenido")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(applicationContext)) {
                // notificationId is a unique int for each notification that you must define
                notify(1, builder.build())
            }
            return Result.success()
        } catch (ex:Exception){
            return Result.failure()
        }
    }
}