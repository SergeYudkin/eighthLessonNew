package com.example.eighthlessonnew.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eighthlessonnew.R;
import com.example.eighthlessonnew.model.CardData;
import com.example.eighthlessonnew.model.CardSource;
import com.example.eighthlessonnew.model.CardSourceImpl;

public class NoteFragment extends Fragment implements MyOnClickListener{// имплемент майКликЛисенер  реализует его поведение

    private CardSource data;
    private NoteAdapter adapter;
    private RecyclerView recyclerView;

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        setHasOptionsMenu(true); // включает меню в фрагменте

       View view = inflater.inflate(R.layout.note_fragment,container,false);
       data = new CardSourceImpl(getResources()).init();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        initRecyclerView(data, recyclerView);

        return view;
    }
//--------------------------------------------------------------------------------------------
    private void initRecyclerView(CardSource data, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
                                                                                            // переиспользование
        adapter = new NoteAdapter(data);
        adapter.setOnMyOnClickListener(this);  // установка адаптера
        recyclerView.setAdapter(adapter);
//----------------------------------------------------------------------------------------------------------------------------
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);   // добавление разделителя карточек
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
//----------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onMyClick(View view, int position) {
        Toast.makeText(getContext(),"Тяжёлая обработка"+position,Toast.LENGTH_SHORT).show();

    }
//-------------------------------------------------------------------------------------------
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu,@NonNull MenuInflater inflater) {  //   меню  в туллбаре
        inflater.inflate(R.menu.fragment_menu,menu);
    }
//--------------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                data.addCardData(new CardData("Новая"+data.size(),
                        "Описание"+data.size(),
                        R.drawable.auto,false));
                        adapter.notifyItemInserted(data.size()-1);   // notify - обновить
                        recyclerView.smoothScrollToPosition(data.size()-1);  // плавный скролл до последней позиции при добавлении новой карточки
                return true;
            case R.id.action_clear:
                data.clearCardData();
                adapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
