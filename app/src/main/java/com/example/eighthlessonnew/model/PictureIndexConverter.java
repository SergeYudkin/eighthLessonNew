package com.example.eighthlessonnew.model;

import com.example.eighthlessonnew.R;

import java.util.Random;

public class PictureIndexConverter {

    private static Random rnd = new Random();
    private static Object syncObj = new Object();

    private static int[] picIndex = {R.drawable.dom,
            R.drawable.dvor,
            R.drawable.auto,
            R.drawable.remont,
            R.drawable.work,
            R.drawable.granit,
            R.drawable.wife,


    };

    public static int randomPictureIndex(){
        synchronized (syncObj){
            return rnd.nextInt(picIndex.length);
        }
    }

    public static int getPictureByIndex(int index){
        if (index < 0 || index >= picIndex.length){
            index = 0;
        }
        return picIndex[index];
    }

    public static int getIndexByPicture(int picture){
        for (int i = 0; i < picIndex.length; i++){
            if(picIndex[i] == picture){
                return i;
            }
        }
        return 0;
    }


}
