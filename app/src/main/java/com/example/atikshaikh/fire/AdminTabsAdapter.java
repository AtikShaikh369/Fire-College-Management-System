package com.example.atikshaikh.fire;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;

public class AdminTabsAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public AdminTabsAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AdminTimeTableFrag adminTimeTableFrag = new AdminTimeTableFrag();
                return adminTimeTableFrag;
            case 1:
                AdminSyllabusFrag adminSyllabusFrag = new AdminSyllabusFrag();
                return adminSyllabusFrag;
            case 2:
                AdminAttendanceFrag adminAttendanceFrag = new AdminAttendanceFrag();
                return adminAttendanceFrag;
          /*case 3:
                AdminAssignmentFrag adminAssignmentFrag = new AdminAssignmentFrag();
                return adminAssignmentFrag;*/
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}