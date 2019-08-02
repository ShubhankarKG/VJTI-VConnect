package com.example.inheritance;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public Fragment getItem(int position){
        switch(position){
            case 0 :
                return new MainActivity();
            case 1 :
                return new StudentInterfaceFrag();
            case 2 :
                return new AccountSettingFrag();
        }
        return null;
    }

    public int getCount(){
        return 3;
    }

}
