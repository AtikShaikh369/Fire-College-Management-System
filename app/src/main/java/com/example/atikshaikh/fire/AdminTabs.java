package com.example.atikshaikh.fire;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.view.ViewPager;


import com.google.firebase.auth.FirebaseAuth;

public class AdminTabs extends AppCompatActivity implements AdminTimeTableFrag.OnFragmentInteractionListener,
        AdminSyllabusFrag.OnFragmentInteractionListener, AdminAttendanceFrag.OnFragmentInteractionListener, AdminAssignmentFrag.OnFragmentInteractionListener{
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("TimeTable"));
        tabLayout.addTab(tabLayout.newTab().setText("Syllabus"));
        tabLayout.addTab(tabLayout.newTab().setText("Attendance"));
       // tabLayout.addTab(tabLayout.newTab().setText("Assignment"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AdminTabsAdapter adapter = new AdminTabsAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        if (id == R.id.signout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AdminTabs.this, LogIn.class));
            Toast.makeText(getApplicationContext(), "Signed Out", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
