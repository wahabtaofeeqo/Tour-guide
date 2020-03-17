package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        link = getIntent().getStringExtra("link");

        if (link != null) {
            YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);
            playerView.initialize(Utils.API_KEY, this);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(link);
        //youTubePlayer.setFullscreen(true);
        //youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);
    }

    private void playVideo() {

//        if (videoView == null)
//            return;
//
//        Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.guide);
//        Log.i("URI", uri.toString());
//        videoView.setVideoURI(uri);
//        videoView.start();
    }

    private void pauseVideo() {
//        if (videoView.canPause())
//            videoView.pause();
    }
}
