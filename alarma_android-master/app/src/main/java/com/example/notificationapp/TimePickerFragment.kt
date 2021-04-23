package com.example.notificationapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import android.text.format.DateFormat;
import java.util.*


class TimePickerFragment : DialogFragment() {

    private var listener: TimePickerDialog.OnTimeSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, listener, hour, minute, DateFormat.is24HourFormat(activity))
    }

    companion object {
        fun newInstance(listener: TimePickerDialog.OnTimeSetListener): TimePickerFragment {
            val fragment = TimePickerFragment()
            fragment.listener = listener
            return fragment
        }
    }
}