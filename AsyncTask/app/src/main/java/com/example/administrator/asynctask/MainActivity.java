package com.example.administrator.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView textView;
    private ImageView imageView;
    private Button button;
    private String url = "http://noavatar.csdn.net/7/F/C/1_astro_gypsophila.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ImageDownloadTask().execute(url);
            }
        });


    }

    /**
     * String 为doInBackground方法参数,
     * Bitmap为执行结果,在onPostExecute的参数中,同时也是doInBackground的返回值,
     * Integer为onProgressUpdate方法的参数,如果不调用publishProgress()方法onProgressUpdate就不会被调用,
     *
     */
    class ImageDownloadTask extends AsyncTask<String,Integer,Bitmap>{

        private Bitmap bitmap=null;

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            if(url==null){
                return null;
            }
            try {
                URLConnection urlConnection = new URL(url).openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                Thread.sleep(2000L);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(1);//只有此方法调用了,onProgressUpdate才会被执行
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("开始下载..");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e(TAG,"onProgressUpdate执行了");
            super.onProgressUpdate(values);
        }
    }
}
