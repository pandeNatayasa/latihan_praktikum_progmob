package com.tr.nata.projectandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tr.nata.projectandroid.Helper.ViewPagerAdapter;
import com.tr.nata.projectandroid.R;
import com.tr.nata.projectandroid.TryPerofilleActivity;

public class FragmentFavorite extends Fragment {

    private AppBarLayout appBarLayout;
    ImageView favorite_to_profille;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_favorite, container, false);

        favorite_to_profille = view.findViewById(R.id.img_favorite_to_profille);

        favorite_to_profille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), TryPerofilleActivity.class);
                startActivity(intent);
//                getActivity().finish();
            }
        });

        return view;
    }
}
