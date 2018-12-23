package com.rahimlis.templates.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.rahimlis.templates.R;
import com.rahimlis.templates.adapters.SectionsPagerAdapter;
import com.rahimlis.templates.fragments.BaseFragment;
import com.rahimlis.templates.vendor.Factory;

import java.util.ArrayList;
import java.util.List;

public abstract class TabBaseActivity extends BaseActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    protected SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    protected ViewPager mViewPager;
    protected final List<BaseFragment> fragments = new ArrayList<>();
    protected final List<String> titles = new ArrayList<>();
    protected TabLayout tabLayout;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager = findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = findViewById(R.id.tabs);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(mViewPager);
    }

    protected View getTabViewAt(int index) {
        if (tabLayout == null || tabLayout.getChildAt(0) == null)
            return null;

        return ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(index);
    }


    protected abstract int getContentView();

    public void addFragment(@NonNull BaseFragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
        mSectionsPagerAdapter.notifyDataSetChanged();
    }


    public void addFragment(@NonNull Class<? extends BaseFragment> fragment, @NonNull String title, @LayoutRes int resId) {
        addFragment(Factory.newInstanceOf(fragment, resId), title);
    }

    public void addFragment(@NonNull Class<? extends BaseFragment> fragment, @StringRes int titleRes, @LayoutRes int resId) {
        addFragment(Factory.newInstanceOf(fragment, resId), getString(titleRes));
    }

}
