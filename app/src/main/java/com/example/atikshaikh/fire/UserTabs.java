package com.example.atikshaikh.fire;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class UserTabs extends AppCompatActivity implements UserTimeTableFrag.OnFragmentInteractionListener,
UserSyllabusFrag.OnFragmentInteractionListener, UserAttendanceFrag.OnFragmentInteractionListener{
    TabLayout tabLayout;
    ViewPager viewPager;
    String usertype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout2);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("TimeTable"));
        tabLayout.addTab(tabLayout.newTab().setText("Syllabus"));
        tabLayout.addTab(tabLayout.newTab().setText("Attendance"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //User Toast
       // usertype = getIntent().getExtras().getString("Type");
        Toast.makeText(getApplicationContext(),"Usertype is " + usertype,Toast.LENGTH_LONG).show();

       /* FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction t = manager.beginTransaction();
        final UserSyllabusFrag userFragment2 = new UserSyllabusFrag();
        Bundle bundle = new Bundle();
        bundle.putString("Type", usertype);
        userFragment2.setArguments(bundle);
        t.add(R.id.UserSyllabusFrag, userFragment2);
        t.commit();

        UserSyllabusFrag fragment2 = new UserSyllabusFrag();
        fragment2.setArguments(savedInstanceState);
*/
        final UserTabsAdapter adapter = new UserTabsAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
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
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
