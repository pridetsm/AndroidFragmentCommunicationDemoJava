/*Written by:Pride Moyo ST (09/08/2020)*/
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
        //initializing our toolbar
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        //setting our toobar as our application's appbar
        setSupportActionBar(toolbar);
        
        fm=getSupportFragmentManager();
        //NB:This app is not meant to survive any configuration so just this simple assignment will do for fragments.
        firstFragment=new FirstFragment();
        secondFragment=new SecondFragment();
        fm.beginTransaction().replace(R.id.fragmentContainer,firstFragment)
        .commit();
    }
    //handling the onFirstFragTextSent event 
    @Override
    public void onFirstFragTextSent(String text) {
        //we create a bundle and attach our data to.
        Bundle bundle=new Bundle();
        bundle.putString("RECEIVED",text);
        //then we set that bundle as the arguments of our fragment
        secondFragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.fragmentContainer,secondFragment)
                .commit();
    }
    //handling the onSecondFragTextSent event
    @Override
    public void onSecondFragTextSent(String text) {
        Bundle bundle=new Bundle();
        bundle.putString("RECEIVED",text);
        firstFragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.fragmentContainer,firstFragment)
                .commit();
    }
    //this method is used by the fragments on navigation to show which fragment is currently resumed.
    public void setActionBarTitle(String string) {
        getSupportActionBar().setTitle(string);
    }
}
