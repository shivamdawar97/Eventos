package com.example.shivam97.eventos;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import java.io.ByteArrayOutputStream;

public class Eventos extends Application {

    public static RequestQueue queue;
    public static String userID;
    public static String BASE_URL=" https://eventos-igi.000webhostapp.com";

    public static Eventos EVENTOS;

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
    }

    public static String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        Eventos.userID = userID;
        SharedPreferences preferences=getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("userID",userID);
        editor.apply();
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


}
