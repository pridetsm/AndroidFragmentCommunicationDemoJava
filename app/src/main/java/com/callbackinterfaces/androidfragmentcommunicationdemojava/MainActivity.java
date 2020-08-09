package com.callbackinterfaces.androidfragmentcommunicationdemojava;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFirstFragTextSentListener, SecondFragment.OnSecondFragTextSentListener {
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private FragmentManager fm;
   private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        fm=getSupportFragmentManager();
        firstFragment=new FirstFragment();
        secondFragment=new SecondFragment();
        fm.beginTransaction().replace(R.id.fragmentContainer,firstFragment)
        .commit();
    }

    @Override
    public void onFirstFragTextSent(String text) {
        Bundle bundle=new Bundle();
        bundle.putString("RECEIVED",text);
        secondFragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.fragmentContainer,secondFragment)
                .commit();
    }
    @Override
    public void onSecondFragTextSent(String text) {
        Bundle bundle=new Bundle();
        bundle.putString("RECEIVED",text);
        firstFragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.fragmentContainer,firstFragment)
                .commit();
    }
    public void setActionBarTitle(String string) {
        getSupportActionBar().setTitle(string);
    }
}
