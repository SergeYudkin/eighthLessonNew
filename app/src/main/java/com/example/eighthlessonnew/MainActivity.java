package com.example.eighthlessonnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.eighthlessonnew.view.NoteFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, NoteFragment.newInstance())
                    .commit();
        }
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return toolbar;

    }

}