package com.example.atikshaikh.fire;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UserTabsAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    public UserTabsAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                UserTimeTableFrag userFragment1 = new UserTimeTableFrag();
               /* Bundle bundle = new Bundle();
                bundle.putString("Type",);
                userFragment1.setArguments(bundle);
                */return userFragment1;
            case 1:
                UserSyllabusFrag userSyllabusFrag = new UserSyllabusFrag();
                return userSyllabusFrag;
            case 2:
                UserAttendanceFrag userAttendanceFrag = new UserAttendanceFrag();
                return userAttendanceFrag;
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
