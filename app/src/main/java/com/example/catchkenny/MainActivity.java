package com.example.catchkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

   TextView time;
   TextView score;
   int tscore;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView12;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView[] imgArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     time=findViewById(R.id.timetext);
     score=findViewById(R.id.skortext);
     tscore=0;
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        imageView11=findViewById(R.id.imageView11);
        imageView12=findViewById(R.id.imageView12);

        imgArray=new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12};
        sakla();



     new CountDownTimer(30000, 1000) {
         @Override
         public void onTick(long millisUntilFinished) {
             time.setText("Süre:"+millisUntilFinished/1000);
         }

         @Override
         public void onFinish() {
             time.setText("Süre Doldu!");
             handler.removeCallbacks(runnable);
             for(ImageView image:imgArray){
                 image.setVisibility(View.INVISIBLE);
             }
             AlertDialog.Builder uyarı= new AlertDialog.Builder(MainActivity.this);
             uyarı.setTitle("Tekrar Oyna?");
             uyarı.setMessage("Yeniden Başlatmak İstediğinize Eminmisiniz? ");
             uyarı.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                        //restart
                     Intent intent=getIntent();
                     finish();
                     startActivity(intent);
                     //Yapılan işlemlerle mevcut sayfa yeniden yüklenmiş oldu


                 }
             });
             uyarı.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     Toast.makeText(getApplicationContext(), "Keyfiniz Bilir",Toast.LENGTH_SHORT).show();
                 }
             });
             uyarı.show();
         }
     }.start();
    }
    public void artır(View view){
        tscore+=10;
        score.setText("Skor: "+tscore);
    }
    public void sakla(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imgArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(12);
                imgArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);

            }

        };
        handler.post(runnable);


    }
}