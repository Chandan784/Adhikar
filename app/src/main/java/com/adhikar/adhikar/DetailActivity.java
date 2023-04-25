package com.adhikar.adhikar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.adhikar.adhikar.Modal.SubDetailModal;

import java.io.IOException;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    ScrollView scrollView;
    ArrayList<String> data;
    MediaPlayer mediaPlayer;
    int position;
    TextView tv_title, tv_benifit, tv_process, tv_documents, tv_details, tv_criteria;
    ImageView im_play,im_pause;
    String title = "", benifit = "", process = "", details = "", documents = "", criteria = "",audio="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        scrollView = (ScrollView) findViewById(R.id.scroll);
        scrollView.setFillViewport(true);

        tv_title = findViewById(R.id.title);
        tv_benifit = findViewById(R.id.benifits);
        tv_process = findViewById(R.id.process);
        tv_documents = findViewById(R.id.documents);
        tv_details = findViewById(R.id.details);
        tv_criteria = findViewById(R.id.criteria);
        im_play = findViewById(R.id.im_play);
        im_pause = findViewById(R.id.im_pause);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        benifit = intent.getStringExtra("benifit");
        process = intent.getStringExtra("process");
        criteria = intent.getStringExtra("criteria");
        details = intent.getStringExtra("details");
        documents = intent.getStringExtra("document");
        audio = intent.getStringExtra("audio");


        tv_title.setText(title);
        tv_benifit.setText(benifit);
        tv_details.setText(details);
        tv_process.setText(process);
        tv_criteria.setText(criteria);
        tv_documents.setText(documents);


im_play.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        playAudio();

    }
});
im_pause.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


            // pausing the media player if media player
            // is playing we are calling below line to
            // stop our media player.
        if (mediaPlayer!=null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();

                // below line is to display a message
                // when media player is paused.
                Toast.makeText(DetailActivity.this, "Audio has been paused", Toast.LENGTH_SHORT).show();
                im_pause.setEnabled(false);
            } else {
                Toast.makeText(DetailActivity.this, "Audiois not playing", Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(DetailActivity.this, "Audiois not playing", Toast.LENGTH_SHORT).show();
        }
    }
});

    }

    private void playAudio() {

        String audioUrl = audio;

        // initializing media player
        mediaPlayer = new MediaPlayer();

        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer.setDataSource(audioUrl);
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // below line is use to display a toast message.
        Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show();
        im_pause.setEnabled(true);
    }

}