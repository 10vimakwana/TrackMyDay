package com.project.trackmydayapp.adapter;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.project.trackmydayapp.activity.user.fragment.ViewRecipeFragment;
import com.project.trackmydayapp.activity.user.fragment.ViewStepFragment;

import java.util.ArrayList;
import java.util.List;

public  class SectionsPagerAdapter extends FragmentPagerAdapter {

    Context context;
    Bundle bundle;

    public SectionsPagerAdapter(Context context, FragmentManager manager,String date) {
        super(manager);
        this.context = context;
        bundle = new Bundle();
        bundle.putString("date",date);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ViewRecipeFragment recipeFragment = new ViewRecipeFragment();
                recipeFragment.setArguments(bundle);
                return recipeFragment;

            case 1:
                ViewStepFragment viewStepFragment = new ViewStepFragment();
                viewStepFragment.setArguments(bundle);
                return viewStepFragment;


            default:
                return new ViewRecipeFragment();

        }

    }

    @Override
    public int getCount() {
        return 2;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Recipes.";
            case 1:
                return "Activity";

        }
        return null;
    }
}
