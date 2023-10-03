package com.example.buoi7;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CalculateSumAsyncTask extends AsyncTask<Void, Integer, Long> {
    private ProgressBar pbDemo;
    private TextView tvDemo;
    private long sum;
    public CalculateSumAsyncTask(ProgressBar pbDemo, TextView tvDemo) {
        this.pbDemo = pbDemo;
        this.tvDemo = tvDemo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pbDemo.setMax(3000000);
        pbDemo.setProgress(0);
    }

    @Override
    protected Long doInBackground(Void... voids) {
        for (int i = 1; i <= 3000000; i++) {
            sum += i;
            if (i % 1000 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
        }
        return sum;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int input = values[0];
        double run = ((double) input/10);
        tvDemo.setText("Running..." + run + "%");
        pbDemo.setProgress(input);
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
        pbDemo.setProgress(View.GONE);
        tvDemo.setText("Done");
    }
}
