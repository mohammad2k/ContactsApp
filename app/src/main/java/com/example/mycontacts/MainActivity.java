package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mycontacts.Adapters.ViewPagerAdapter;
import com.example.mycontacts.fragments.FragmentCalls;
import com.example.mycontacts.fragments.FragmentContacts;
import com.example.mycontacts.fragments.FragmentFav;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int[] ICONS = {R.drawable.ic_call_with_24dp,R.drawable.ic_contacts_white_24dp,R.drawable.ic_star_white_24dp};
    private final int MY_PERMISSIONS_REQUEST_READ_CALL_LOG = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        askPermissions();

    }


    private void setViewPagerAdapter(){

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCalls(),"Call Logs");
        adapter.addFragment(new FragmentContacts(),"Contacts");
        adapter.addFragment(new FragmentFav(),"Favourites");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);

        for (int i=0 ;i<tabLayout.getTabCount();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }

    }


    private void askPermissions(){

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {


                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.READ_PHONE_NUMBERS,
                                Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_READ_CALL_LOG);


        }else {
            setViewPagerAdapter();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CALL_LOG: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    setViewPagerAdapter();

                } else {

                }
                return;
            }
        }
    }


    private void findViews(){
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
    }

}
