package com.example.myzone.theshow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myzone.R;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment>mmFragmentTitleList FragmentList = new ArrayList<>();
    private final List<String> = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
    try {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorPrimary);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        });

    } catch (Exception e) {
        // if Bitmap fetch fails, fallback to primary colors
        Log.e(TAG, "onCreate: failed to create bitmap from background", e.fillInStackTrace());
        collapsingToolbarLayout.setContentScrimColor(
                ContextCompat.getColor(this, R.color.colorPrimary)
        );
        collapsingToolbarLayout.setStatusBarScrimColor(
                ContextCompat.getColor(this, R.color.colorPrimary)
        );
    }
}