package com.example.apnicare.Upload_Prescription;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.apnicare.R;
import com.example.apnicare.ViewPagerAdapt;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class why_prescription extends BottomSheetDialogFragment {
    ImageButton backbtn ,nextbtn;
    Button btnupload;
    TextView[] dots;
    ViewPager msliderviewpager;
    TabWidget mDotLayout;
    Button skip;
    ViewPagerAdapt viewPagerAdapt;
    BottomSheetBehavior bottomSheetBehavior;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_why_prescription, container, false);
        nextbtn = view.findViewById(R.id.nextbtn);
        backbtn = view.findViewById(R.id.backbtn);
        skip = view.findViewById(R.id.skip);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getitem(0) > 0){

                    msliderviewpager.setCurrentItem(getitem(-1),true);
                }

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getitem(0) < 3)
                    msliderviewpager.setCurrentItem(getitem(1),true);

                else {

                }

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mDotLayout=view.findViewById(R.id.dots);
        msliderviewpager = view.findViewById(R.id.Sliderpager);
        viewPagerAdapt = new ViewPagerAdapt(getContext());
        msliderviewpager.setAdapter(viewPagerAdapt);
        return view;

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpindicator(int position){

        dots = new TextView[4];
        mDotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.active,getContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.black,getContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0){

                backbtn.setVisibility(View.VISIBLE);

            }else {

                backbtn.setVisibility(View.INVISIBLE);

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



    private int getitem(int i){

        return msliderviewpager.getCurrentItem() + i;
    };

}