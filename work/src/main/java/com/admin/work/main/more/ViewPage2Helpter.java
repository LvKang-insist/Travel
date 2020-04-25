package com.admin.work.main.more;

import androidx.viewpager2.widget.ViewPager2;

import net.lucode.hackware.magicindicator.MagicIndicator;

class ViewPage2Helpter {
    public static void bind(MagicIndicator magicIndicator, ViewPager2 viewPager2) {
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });

    }
}
