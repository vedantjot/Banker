package com.example.banker;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.banker.Fragments.AccountFragment;
import com.example.banker.Fragments.BillsFragment;
import com.example.banker.Fragments.InvestmentFragment;

public class PageAdapter extends FragmentStateAdapter {

    public PageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0: return new AccountFragment();
            case 1: return new InvestmentFragment();
            case 2: return new BillsFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
