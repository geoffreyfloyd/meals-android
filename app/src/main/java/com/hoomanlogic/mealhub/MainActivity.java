package com.hoomanlogic.mealhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends FragmentActivity {
    MealHubPagerAdapter _Adapter;
    ViewPager _Pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _Adapter = new MealHubPagerAdapter(getSupportFragmentManager());
        _Pager = (ViewPager)findViewById(R.id.pager);
        _Pager.setAdapter(_Adapter);
        //_Pager.setCurrentItem(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

