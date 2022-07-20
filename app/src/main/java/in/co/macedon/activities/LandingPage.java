package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import in.co.macedon.R;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.models.LandingImageGetSet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class LandingPage extends AppCompatActivity {

    ArrayList<LandingImageGetSet> imagearray = new ArrayList<LandingImageGetSet>();
    ViewPager viewPager;
    TabLayout tabview;
    TextView btn_skip, btn_next;
    int selectPage = 0;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);

        imagearray = new ArrayList<LandingImageGetSet>();
        imagearray.add(new LandingImageGetSet(R.drawable.landing_a));
        imagearray.add(new LandingImageGetSet(R.drawable.landing_b));
        imagearray.add(new LandingImageGetSet(R.drawable.landing_a));

        viewPager = findViewById(R.id.viewPager);
        tabview = findViewById(R.id.tabview);
        btn_skip = findViewById(R.id.btn_skip);
        btn_next = findViewById(R.id.btn_next);

        btn_next.setVisibility(View.INVISIBLE);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPage = position;
                if (position == 0) {
                    btn_skip.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.INVISIBLE);

                } else if (position == 1) {
                    btn_skip.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.INVISIBLE);

                } else if (position == 2) {
                    btn_skip.setVisibility(View.INVISIBLE);
                    btn_next.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        MyPagerAdapter myCustomPagerAdapter = new MyPagerAdapter(LandingPage.this, imagearray);
        viewPager.setAdapter(myCustomPagerAdapter);
        tabview.setupWithViewPager(viewPager, true);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* if(sessionManager.getUserID().equals("DEFAULT")){

                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);

                }else{

                    Intent i = new Intent(getApplicationContext(), DashBoard.class);
                    startActivity(i);
                }*/

                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* if(sessionManager.getUserID().equals("DEFAULT")){

                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);

                }else{

                    Intent i = new Intent(getApplicationContext(), DashBoard.class);
                    startActivity(i);
                }*/

                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
        
    }

    public class MyPagerAdapter extends PagerAdapter {
        Context context;
        List<LandingImageGetSet> bannerDatumList;
        LayoutInflater layoutInflater;

        public MyPagerAdapter(Context context, List<LandingImageGetSet> bannerDatumList) {
            this.context = context;
            this.bannerDatumList = bannerDatumList;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return bannerDatumList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = layoutInflater.inflate(R.layout.item_banner, container, false);
//            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Glide.with(context).load(
//                    APIClient.Base_URL + "/" +
                    bannerDatumList.get(position).getImage()).into(imageView);
            container.addView(itemView);
            //listening to image click

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }

    }

    @Override
    public void onBackPressed() {

        finish();
    }
}
