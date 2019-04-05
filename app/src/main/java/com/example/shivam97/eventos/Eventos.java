package com.example.shivam97.eventos;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.io.ByteArrayOutputStream;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Eventos extends Application {

    public static RequestQueue queue;
    public static String BASE_URL="http://eventos-igi.000webhostapp.com";
    public static MyNetworkRequest request;
    public static Eventos EVENTOS;
    public static SavedPreferences preferences;
    public static MyRoomDatabase database;

    private static AlertDialog progressDialog;


    public static Eventos getInstance(){
        if(EVENTOS !=null)
            return EVENTOS;

        else {
            EVENTOS =new Eventos();
            return EVENTOS;
        }
    }



    @Override
    public void onCreate() {
        super.onCreate();
        queue=Volley.newRequestQueue(this);
        EVENTOS =new Eventos();
        request=new MyNetworkRequest();
        preferences=new SavedPreferences(this);
        //  database=MyRoomDatabase.getDatabase(this);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://eventos-igi.000webhostapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Bitmap getCompressed(Bitmap bitmap){
        try {
            //Decode image size

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data1 = baos.toByteArray();

            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            // Toast.makeText(context,String.valueOf(data1.length),Toast.LENGTH_LONG).show();
            if( data1.length <500000) {

                return bitmap;
            }
            else
                BitmapFactory.decodeByteArray(data1,0,data1.length,o);
            //The new size we want to scale to
            final int REQUIRED_SIZE=200;

            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeByteArray(data1,0,data1.length, o2);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void showProgressDialog(Context context)

    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        ProgressBar progressBar=new ProgressBar(context);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(30,30));
        //View v= LayoutInflater.from(context).inflate(R.layout.global_progress,null);
        builder.setView(progressBar);

        progressDialog =builder.create();
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }

    public static void hideProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }
    }
}
