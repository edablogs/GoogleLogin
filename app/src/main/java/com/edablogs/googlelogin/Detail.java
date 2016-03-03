package com.edablogs.googlelogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Detail extends AppCompatActivity {

    private ImageView dp;
    private TextView name, email, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dp = (ImageView) findViewById(R.id.dp);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        gender = (TextView) findViewById(R.id.gender);

        Intent i = getIntent();
        final String i_name, i_email, i_gender, i_url;
        i_name = i.getStringExtra("p_name");
        i_email = i.getStringExtra("p_email");
        i_gender = i.getStringExtra("p_gender");
        i_url = i.getStringExtra("p_url");

        name.setText(i_name);
        email.setText(i_email);
        gender.setText(i_gender);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(i_url);
                    InputStream is = url.openConnection().getInputStream();
                    final Bitmap bmp = BitmapFactory.decodeStream(is);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dp.setImageBitmap(bmp);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
