package com.rizovictor.video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.MediaController
import androidx.viewbinding.ViewBinding
import com.rizovictor.video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediacontroller = MediaController(this)
        var tipoVideo:Int =0
        mediacontroller.setAnchorView(binding.videoView)
        var contenido:Uri
        var onlineContenido: Uri
        val arregloVideo: IntArray = intArrayOf(R.raw.video1, R.raw.video2, R.raw.video3)



        binding.btnPlay.setOnClickListener{

            if (binding.textInput.text.toString() == ""){
                contenido=Uri.parse("android.resource://$packageName/${arregloVideo[tipoVideo]}")
                binding.videoView.setMediaController(mediacontroller)
                binding.videoView.setVideoURI(contenido)   //Para videos locales
                binding.videoView.requestFocus()
                binding.videoView.start()

            }else{
                onlineContenido= Uri.parse(binding.textInput.text.toString())
                binding.videoView.setMediaController(mediacontroller)
                binding.videoView.setVideoURI(onlineContenido) //Para videos online
                binding.videoView.requestFocus()
                binding.videoView.start()
            }
                binding.textInput.text = null
        }

        binding.btnStop.setOnClickListener {
            binding.videoView.stopPlayback()
        }

        val videos = resources.getStringArray(R.array.videos)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tipoVideo = position
            }
        }
    }
}