package com.example.eighthlessonnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;

import com.example.eighthlessonnew.observe.Publisher;
import com.example.eighthlessonnew.view.NoteFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {


    private Publisher publisher = new Publisher();



    private Navigation navigation;


    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = new Navigation(getSupportFragmentManager());
        initToolbar();
        navigation.addFragment(NoteFragment.newInstance(),false);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }
 //--------------------------------------------------------------------
    @Override
    public void onBackStackChanged() {
       if (getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);   // стрелочка "вернуться"
        }else {
           getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       }
    }
 //------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu,menu);   // меню в активити в тулбаре будет доступно всегда
        return super.onCreateOptionsMenu(menu);
    }
//-----------------------------------------------------------------

    private void  initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    public Navigation getNavigation() {
        return navigation;
    }


}