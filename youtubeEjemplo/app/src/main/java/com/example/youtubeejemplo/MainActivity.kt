package com.example.youtubeejemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    val claveYoutube = "AIzaSyBsX7Up7R2e0_ZU7hJxWOKV2jwkuCDncmU"
    lateinit var youTubePlayerView: YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        youTubePlayerView = findViewById(R.id.youtube_view)
        youTubePlayerView.initialize(claveYoutube,this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if(!p2){
            p1?.cueVideo("2H2TB4kSEcg")//
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        if(p1!!.isUserRecoverableError){
            p1.getErrorDialog(this,1).show()
        }else{
            Toast.makeText(this,"Error al cargar el reproductor",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==1){
            getYouTubePlayerProvider().initialize(claveYoutube, this)
        }
    }

    fun getYouTubePlayerProvider(): YouTubePlayer.Provider{
        return  youTubePlayerView
    }
}