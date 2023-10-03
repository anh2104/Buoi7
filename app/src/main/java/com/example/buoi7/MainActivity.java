package com.example.buoi7;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ILoadProductsListener {
    private TextView tvDemo;
    private ImageView imgDemo;
    private ProgressBar pbDemo;
    private LinearLayout llloading;

    public static final String API_PRODUCTS = "https://dummyjson.com/products";
    public static final String IMAGE_DEMO = "https://i.dummyjson.com/data/products/1/1.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDemo = findViewById(R.id.tvDemo);
        imgDemo = findViewById(R.id.imgDemo);
        llloading = findViewById(R.id.llloading);
        pbDemo = findViewById(R.id.pbDemo);

        asyncTask();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ");

            }
        };

        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: " + Thread.currentThread().getName());
                tvDemo.setText("Hello 2023");
            }
        });
        thread.start();
        thread2.start();

        Handler handler = new Handler();
//        handler.post(runnable);
        handler.postDelayed(runnable,5000);
    }

    private void asyncTask() {
        LoadProductsAsyncTask loadProductsAsyncTask = new LoadProductsAsyncTask(API_PRODUCTS, tvDemo);
        loadProductsAsyncTask.setProductsListener(this);
        loadProductsAsyncTask.execute();
        CalculateSumAsyncTask calculateSumAsyncTask = new CalculateSumAsyncTask(pbDemo,tvDemo);
        calculateSumAsyncTask.execute();
//        LoadImageAsyncTask loadImageAsyncTask = new LoadImageAsyncTask(imgDemo){
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                super.onPostExecute(bitmap);
//                imgDemo.setImageBitmap(bitmap);
//            }
//        };
//        loadImageAsyncTask.execute(IMAGE_DEMO);

        llloading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadProductSuccess(ArrayList<ProductModel> productModels) {
        llloading.setVisibility(View.GONE);
    }

    @Override
    public void onLoadProductError(String message) {
        llloading.setVisibility(View.GONE);
    }
}