package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayActivity extends AppCompatActivity {
    PlayerView playerView;
    ExoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String pass = intent.getStringExtra("pass");
        String movie_id = intent.getStringExtra("movieId");
        playerView = findViewById(R.id.video_view);
        streamRequest(user, pass, movie_id);
        //MediaItem mediaItem = MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
    }

    protected void streamRequest(String user, String pass, String movieID){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject postData = new JSONObject();
        try {
            postData.put("user", user);
            postData.put("pass", pass);
            postData.put("videoId", movieID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://34.175.83.209:8080/stream/", postData, response -> {
            try {
                String videoURL = response.getString("URL");
                System.out.println(videoURL);
                player  = new ExoPlayer.Builder(getApplicationContext()).build();
                playerView.setPlayer(player);
                MediaItem mediaItem = MediaItem.fromUri(videoURL);
                player.setMediaItem(mediaItem);
                player.prepare();
                startPlayer(player);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> System.out.println(error.toString()));
        requestQueue.add(jsonObjectRequest);
    }

    public static void startPlayer(ExoPlayer exoPlayer) {
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(true);
        }
    }
    @Override
    public void onStop(){
        super.onStop();
        playerView.setPlayer(null);
        releasePlayer();
    }

    private void releasePlayer(){
        if(player != null){
            player.release();
        }
    }
}