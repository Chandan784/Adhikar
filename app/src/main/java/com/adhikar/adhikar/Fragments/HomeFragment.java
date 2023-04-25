package com.adhikar.adhikar.Fragments;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.adhikar.adhikar.Adapter.SliderAdapter;
import com.adhikar.adhikar.Modal.NetworkChangeReceiver;
import com.adhikar.adhikar.Modal.SliderModel;
import com.adhikar.adhikar.R;
import com.adhikar.adhikar.SubDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    ViewPager mViewPager;

    // images array

   private CardView cv_krusi,cv_edu,cv_health,cv_busi,cv_bhataa,cv_home,cv_finance,cv_bima,cv_doc;
    // Creating Object of ViewPagerAdapter
    SliderAdapter sliderAdapter;
    private BroadcastReceiver MyReceiver = null;
    //...
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_home, container, false);

//Testing

        mViewPager = (ViewPager)view.findViewById(R.id.viewPagerMain);
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scroll);
        scrollView.setFillViewport (true);

        cv_edu= view.findViewById(R.id.cv_edu);
        cv_health= view.findViewById(R.id.cv_health);
        cv_krusi= view.findViewById(R.id.cv_krusi);
        cv_busi= view.findViewById(R.id.cv_business);
        cv_bhataa= view.findViewById(R.id.cv_bhataa);
        cv_home= view.findViewById(R.id.cv_home);
        cv_finance= view.findViewById(R.id.cv_finance);
        cv_bima= view.findViewById(R.id.cv_bima);
        cv_doc= view.findViewById(R.id.cv_doc);
       MyReceiver = new NetworkChangeReceiver();

       broadcastIntent();

// testing


        cv_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","edu");
                intent.putExtra("title","ଶିକ୍ଷ୍ୟା ଯୋଜନା ଗୁଡିକ");

                startActivity(intent);
            }
        });
        cv_health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","health");
                intent.putExtra("title","ସ୍ୱାସ୍ଥ୍ୟ ଯୋଜନା ଗୁଡିକ");

                startActivity(intent);
            }
        });
        cv_krusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","krusi");
                intent.putExtra("title","କୃଷି ଯୋଜନା ଗୁଡିକ");
 ,jhWKFHSRLJG
                        WR[IG]RWOG]WROG]rwk,h;ermnef,mnv kdsjvusd09g83489yohkf;ljhm;lfdj
                startActivity(intent);
            }
        });
        cv_busi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","business");
                intent.putExtra("title","ବ୍ୟବସାୟ ଯୋଜନା ଗୁଡିକ");

                startActivity(intent);
            }
        });
        cv_bhataa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","bhataa");
                intent.putExtra("title","ଭତ୍ତା ଯୋଜନା ଗୁଡିକ");

                startActivity(intent);
            }
        });
        cv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","home");
                intent.putExtra("title","ବାସସ୍ଥାନ ଯୋଜନା ଗୁଡିକ ");

                startActivity(intent);
            }
        });
        cv_finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","finance");
                intent.putExtra("title","ଆର୍ଥିକ ଯୋଜନା ଗୁଡିକ");

                startActivity(intent);
            }
        });
        cv_bima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","bima");
                intent.putExtra("title","ବୀମା ଯୋଜନା ଗୁଡିକ");

                startActivity(intent);
            }
        });


        cv_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SubDetailActivity.class);
                intent.putExtra("type","doc");
                intent.putExtra("title","ଦସ୍ତାବିଜ ଯୋଜନା ଗୁଡିକ");

                startActivity(intent);
            }
        });

        // Initializing the ViewPagerAdapter


        // Adding the Adapter to the ViewPager






       return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("https://educhandan.com/adhikar_audio/Adhikar.png"));
        sliderModelFakeList.add(new SliderModel("https://educhandan.com/adhikar_audio/Adhikar.png"));
        sliderModelFakeList.add(new SliderModel("https://educhandan.com/adhikar_audio/Adhikar.png"));
        sliderAdapter = new SliderAdapter(sliderModelFakeList);
        mViewPager.setAdapter(sliderAdapter);

        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == sliderModelFakeList.size()) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }


    public void broadcastIntent() {
        getContext().registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


}