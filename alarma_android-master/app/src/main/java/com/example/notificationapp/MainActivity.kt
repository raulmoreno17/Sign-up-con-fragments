package com.example.notificationapp

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.work.*
import java.sql.Time
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "100"
    private lateinit var time : Time
    private lateinit var date : Date

    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Titulo de notificacion"
            val descriptionText = "Contenido"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        var buttonSave = findViewById<Button>(R.id.saveButton)

        buttonSave.setOnClickListener{
                setPeriodicTimeRequest(date,time)
        }
    }
    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            findViewById<TextView>(R.id.pickedDateText).text = selectedDate
            date = Date(year,month,day)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun showTimePickerDialog(v: View) {
        val newFragment = TimePickerFragment.newInstance(TimePickerDialog.OnTimeSetListener() { _, hour, minute ->
            // +1 because January is zero
            val selectedTime = "$hour:$minute"
            findViewById<TextView>(R.id.PickedTimeText).text = selectedTime
            time = Time(hour,minute,Date().seconds)
        })

        newFragment.show(supportFragmentManager, "timePicker")
    }

    private fun setOneTimeRequest(){
        val wm: WorkManager = WorkManager.getInstance(applicationContext)
        val misdatos: Data = Data.Builder()
            .putInt("key1",45)
            .build()

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(Worker::class.java)
            .setConstraints(constraints)
            .setInputData(misdatos)
            .build()

        //WorkManager.getInstance(applicationContext)
        wm.enqueue(uploadRequest)
        wm.getWorkInfoByIdLiveData(uploadRequest.id).observe(this, Observer {
          //  findViewById<TextView>(R.id.texto1).text = it.state.name
        })
    }

    private fun setPeriodicTimeRequest(date:Date,time:Time){
        val today = Date()
        date.hours = time.hours
        date.minutes = time.minutes
        date.year = date.year-1900
        val timeLeft : Long = date.time - today.time

        val wm: WorkManager = WorkManager.getInstance(applicationContext)
        val misdatos: Data = Data.Builder()
                .putInt("key1",45)
                .build()

        val uploadRequest = PeriodicWorkRequest.Builder(Worker::class.java,timeLeft,TimeUnit.MILLISECONDS)
                .setInputData(misdatos)
                .build()
        Toast.makeText(this,timeLeft.toString(),Toast.LENGTH_LONG).show()
        wm.enqueue(uploadRequest)
        wm.getWorkInfoByIdLiveData(uploadRequest.id).observe(this, Observer {
        })

    }


}