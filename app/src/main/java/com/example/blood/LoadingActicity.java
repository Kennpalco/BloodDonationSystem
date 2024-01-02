package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.content.Intent;
import android.os.AsyncTask;
public class LoadingActicity extends AppCompatActivity {
    private ProgressBar loadingProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_acticity);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        // Simulate work using AsyncTask
        new ProgressTask().execute();
    }
    private class ProgressTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Show the progress bar
            loadingProgressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Simulate work here
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50);
                    // Publish progress
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // Update the progress bar
            loadingProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Hide the progress bar and start MainActivity
            loadingProgressBar.setVisibility(ProgressBar.INVISIBLE);

            Intent intent = new Intent(LoadingActicity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
        }
    }
}
