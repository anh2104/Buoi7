package com.example.buoi7;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LoadImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {
    ImageView imageView;
    private ProgressBar progressBar;


    public LoadImageAsyncTask(ImageView imageDemo,ProgressBar progressBar) {
        this.imageView = imageDemo;
        this.progressBar = progressBar;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int input = values[0];

//        ProgressBar progressBar;
//        progressBar = new ProgressBar();
        progressBar.setProgress(input);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        String imageUrl = strings[0];
        try {
            URL url = new URL(imageUrl);
            try {
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
       publishProgress(50);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        progressBar.setVisibility(View.GONE);
        if (bitmap != null){
           imageView.setImageBitmap(null);
           imageView.setImageBitmap(bitmap);
        }
    }
}
