package com.example.fragmentcommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    boolean mDualPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MasterFragment masterFragment = null;
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        if(frameLayout!=null){
            mDualPane = false;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            masterFragment = (MasterFragment) getSupportFragmentManager().findFragmentByTag("MASTER");
            if(masterFragment==null){
                masterFragment = new MasterFragment();
                ft.add(R.id.frameLayout, masterFragment, "MASTER");
            }

            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            if(detailFragment!=null){
                ft.remove(detailFragment);
            }
            ft.commit();
        }
        else{
            mDualPane = true;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            masterFragment = (MasterFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutMaster);
            if(masterFragment==null){
                masterFragment = new MasterFragment();
                ft.add(R.id.frameLayoutMaster, masterFragment);
            }

            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            if(detailFragment==null){
                detailFragment = new DetailFragment();
                ft.add(R.id.frameLayoutDetail, detailFragment);
            }
            ft.commit();
        }
        masterFragment.setOnMasterSelectedListener(countryName -> {
            sendCountryName(countryName);
        });

    }

    private void sendCountryName(String countryName) {
        DetailFragment detailFragment;
        if(mDualPane){
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frameLayoutDetail);
            detailFragment.showSelectedCountry(countryName);
        }
        else{
            detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(DetailFragment.KEY_COUNTRY_NAME, countryName);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, detailFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}