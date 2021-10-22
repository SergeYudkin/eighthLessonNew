package com.example.eighthlessonnew.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eighthlessonnew.R;
import com.example.eighthlessonnew.model.CardSource;
import com.example.eighthlessonnew.model.CardSourceImpl;

public class NoteFragment extends Fragment implements MyOnClickListener{   // имплемент майКликЛисенер  реализует его поведение

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.note_fragment,container,false);
       CardSource data = new CardSourceImpl(getResources()).init();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        initRecyclerView(data, recyclerView);

        return view;
    }
//--------------------------------------------------------------------------------------------
    private void initRecyclerView(CardSource data, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
                                                                                            // переиспользование
        NoteAdapter noteAdapter = new NoteAdapter(data);
        noteAdapter.setOnMyOnClickListener(this);
        recyclerView.setAdapter(noteAdapter);
//----------------------------------------------------------------------------------------------------------------------------
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);   // добавление разделителя
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
//----------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onMyClick(View view, int position) {
        Toast.makeText(getContext(),"Тяжёлая обработка"+position,Toast.LENGTH_SHORT).show();

    }
}
