package com.example.eighthlessonnew.model;



public interface CardSource {

    CardSource init(CardSourceResponse cardSourceResponse);

    CardData getCardData(int position);
    int size();
//---------------------------------------------------------------
    void deleteCardData(int position);
    void updateCardData(int position,CardData newCardData);  // удаление, обновление, добавление, очистить карточки
    void addCardData(CardData newCardData);
    void clearCardData();
//---------------------------------------------------------------


}
