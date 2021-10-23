package com.example.eighthlessonnew.model;

import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.eighthlessonnew.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CardSourceLocalImpl implements CardSource {

    private List<CardData> dataSource;

    private Resources resources;

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteCardData(int position) {  // удалить карточку
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData newCardData) {  // обновить карточку
        dataSource.set(position,newCardData);
    }

    @Override
    public void addCardData(CardData newCardData) {   //   добавить карточку
        dataSource.add(newCardData);
    }

    @Override
    public void clearCardData() {   // очистить всё
        dataSource.clear();
    }



    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    public CardSourceLocalImpl(Resources resources){
        dataSource = new ArrayList<>();
        this.resources = resources;
    }



     public CardSourceLocalImpl init(CardSourceResponse cardSourceResponse){

        String[] titles = resources.getStringArray(R.array.titles);
        String[] description = resources.getStringArray(R.array.description);
         TypedArray typedArray = resources.obtainTypedArray(R.array.pictures);
         int[] pictures = new int[typedArray.length()];

         for (int i = 0; i <typedArray.length(); i++){
             pictures[i] = typedArray.getResourceId(i,-1);
         }
         for (int i = 0; i <titles.length; i++){
             dataSource.add(new CardData(titles[i],description[i],pictures[i],false, Calendar.getInstance().getTime()));

         }
         if(cardSourceResponse!=null){
             cardSourceResponse.initialized(this);
         }
         return this;
     }


}
