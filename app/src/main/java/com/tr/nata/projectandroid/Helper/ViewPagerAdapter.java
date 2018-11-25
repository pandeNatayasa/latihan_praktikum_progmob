package com.tr.nata.projectandroid.Helper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tr.nata.projectandroid.fragment.FragmentProfilleDataJasa;
import com.tr.nata.projectandroid.fragment.FragmentProfilleDetail;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> FragmentListTitle = new ArrayList<>();

    int mNoOfTabs;

    public ViewPagerAdapter(FragmentManager fm,int NumberOfTabs) {

        super(fm);
        this.mNoOfTabs=NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

//        return fragmentList.get(i);
        switch (position)
        {
            case 0:
                FragmentProfilleDataJasa fragmentProfilleDataJasa = new FragmentProfilleDataJasa();
                return fragmentProfilleDataJasa;
            case 1:
                FragmentProfilleDetail fragmentProfilleDetail = new FragmentProfilleDetail();
                return fragmentProfilleDetail;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
//        return fragmentList.size();
        return mNoOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
//
//    public void AddFragment(Fragment fragment,String title){
//        fragmentList.add(fragment);
//        FragmentListTitle.add(title);
//    }
}
