package com.example.eighthlessonnew.view;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eighthlessonnew.MainActivity;
import com.example.eighthlessonnew.Navigation;
import com.example.eighthlessonnew.R;
import com.example.eighthlessonnew.model.CardData;
import com.example.eighthlessonnew.model.CardSource;
import com.example.eighthlessonnew.model.CardSourceLocalImpl;
import com.example.eighthlessonnew.model.CardSourceResponse;
import com.example.eighthlessonnew.model.CardsSourceRemoteImpl;
import com.example.eighthlessonnew.observe.Observer;
import com.example.eighthlessonnew.observe.Publisher;

public class NoteFragment extends Fragment implements MyOnClickListener{// имплемент майКликЛисенер  реализует его поведение

    private CardSource data;
    private NoteAdapter adapter;
    private RecyclerView recyclerView;
    private Navigation navigation;
    private Publisher publisher;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity)context;
        navigation = activity.getNavigation();
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        super.onDetach();
    }

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        setHasOptionsMenu(true); // включает меню в фрагменте

       View view = inflater.inflate(R.layout.note_fragment,container,false);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        initRecyclerView(data, recyclerView);
        if(false) {
            data = new CardSourceLocalImpl(getResources()).init(new CardSourceResponse() {
                @Override
                public void initialized(CardSource cardSource) {

                }
            });
        }else {
            data = new CardsSourceRemoteImpl().init(new CardSourceResponse() {
                @Override
                public void initialized(CardSource cardSource) {
                    adapter.notifyDataSetChanged();
                }
            });
        }
        adapter.setDataSource(data);
        return view;
    }
//--------------------------------------------------------------------------------------------
    private void initRecyclerView(CardSource data, RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
                                                                                            // переиспользование
        adapter = new NoteAdapter(this);
        adapter.setOnMyOnClickListener(this);  // установка адаптера
        recyclerView.setAdapter(adapter);
//----------------------------------------------------------------------------------------------------
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setChangeDuration(1000);                            // анимация плавного исчезания- появления-обновления
        defaultItemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(defaultItemAnimator);
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
                navigation.addFragment(CardUpdateFragment.newInstance(),true);
                publisher.subscribe(new Observer() {
                    @Override
                    public void updateState(CardData cardData) {
                        data.addCardData(cardData);
                        adapter.notifyItemInserted(data.size()-1);
                    }
                });

                return true;
            case R.id.action_clear:
                data.clearCardData();
                adapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
//--------------------------------------------------------------------------------------------------------
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);                                                    // меню выскакивающая по нажатию на картинку
        requireActivity().getMenuInflater().inflate(R.menu.card_menu,menu);
    }
//--------------------------------------------------------------------------------------------------------


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuContextClickPosition();
        switch (item.getItemId()){
            case R.id.action_update:
                navigation.addFragment(CardUpdateFragment.newInstance(data.getCardData(position)),true);
                publisher.subscribe(new Observer() {
                    @Override
                    public void updateState(CardData cardData) {
                        data.updateCardData(position,cardData);
                        adapter.notifyItemInserted(position);
                    }
                });

                return true;
            case R.id.action_delete:
                data.deleteCardData(position);
                adapter.notifyItemRemoved(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
