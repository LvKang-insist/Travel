package com.admin.work.main.more;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

class SquVpTabAdapter extends FragmentStateAdapter {
    List<String> list;
    public SquVpTabAdapter(@NonNull FragmentManager fragmentManager,
                           @NonNull Lifecycle lifecycle,
                           List<String> list) {
        super(fragmentManager, lifecycle);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new TabDelegate();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
