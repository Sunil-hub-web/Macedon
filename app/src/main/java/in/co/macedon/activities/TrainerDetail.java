package in.co.macedon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import in.co.macedon.R;
import in.co.macedon.adapters.TimeslotAdapter;
import in.co.macedon.models.CenterTimeingSlot;
import in.co.macedon.models.TrainerImageGetSet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TrainerDetail extends AppCompatActivity {

    private int currentPage = 0;
    ViewPager viewPager;
    TabLayout tabview;
    private ArrayList<TrainerImageGetSet> urls = new ArrayList<TrainerImageGetSet>();
    private ArrayList<CenterTimeingSlot> timeslots = new ArrayList<CenterTimeingSlot>();
    TextView regularprice, chooseslot, datetxt;
    RecyclerView timeslotsrecycler;
    DatePickerDialog datePickerDialog;
    int year, month, dayOfMonth, i;
    Calendar calendar;
    String endt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_detail);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
        viewPager = findViewById(R.id.viewPager);
        tabview = findViewById(R.id.tabview);
        regularprice = findViewById(R.id.regularprice);
        chooseslot = findViewById(R.id.chooseslot);

        regularprice.setPaintFlags(regularprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        chooseslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowConfrmPay();
            }
        });

        init();
    }

    Dialog dialogConfirm;
    public void ShowConfrmPay() {

        dialogConfirm = new Dialog(TrainerDetail.this);
        dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation2);
        dialogConfirm.setContentView(R.layout.timeslot_dialog_layout);
        dialogConfirm.setCancelable(true);
        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogConfirm.setCanceledOnTouchOutside(true);
        dialogConfirm.getWindow().setGravity(Gravity.LEFT);
        Window window = dialogConfirm.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        timeslots = new ArrayList<CenterTimeingSlot>();
       /* timeslots.add(new CenterTimeingSlot("14:45 - 15:45"));
        timeslots.add(new CenterTimeingSlot("16:00 - 17:00"));
        timeslots.add(new CenterTimeingSlot("17:15 - 18:15"));
        timeslots.add(new CenterTimeingSlot("18:30 - 19:30"));
        timeslots.add(new CenterTimeingSlot("19:45 - 20:45"));
        timeslots.add(new CenterTimeingSlot("21:00 - 22:00"));
        timeslots.add(new CenterTimeingSlot("22:15 - 23:15"));*/

        timeslotsrecycler = dialogConfirm.findViewById(R.id.timeslots);


        TimeslotAdapter adpater = new TimeslotAdapter(timeslots, TrainerDetail.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        timeslotsrecycler.setLayoutManager(gridLayoutManager);
        timeslotsrecycler.setItemAnimator(new DefaultItemAnimator());
        timeslotsrecycler.setAdapter(adpater);

        datetxt = dialogConfirm.findViewById(R.id.datetxt);
        TextView pickdate = dialogConfirm.findViewById(R.id.pickdate);
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(TrainerDetail.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                        edit_date.setText(year + "-" + (month + 1) + "-" + day);

                                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-d");
                                DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
                                endt = year + "-" + (month + 1) + "-" + day;
                                Log.d("sufifn", endt);
                                Date date = null;
                                try {
                                    date = inputFormat.parse(endt);
                                    String outputDateStr = outputFormat.format(date);


//                                fragHome_Date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    datetxt.setText("Choosen Date : "+outputDateStr);
                                    Log.d("sufifn", outputDateStr);

                                } catch (
                                        ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        TextView confirmbook = dialogConfirm.findViewById(R.id.confirmbook);
        confirmbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogConfirm.dismiss();
            }
        });



        dialogConfirm.show();

    }

    private void init() {

        urls = new ArrayList<TrainerImageGetSet>();

        urls.add(new TrainerImageGetSet(R.drawable.slid_a));
        urls.add(new TrainerImageGetSet(R.drawable.slid_d));
        urls.add(new TrainerImageGetSet(R.drawable.slid_b));
        urls.add(new TrainerImageGetSet(R.drawable.slid_c));

        MyCustomPagerAdapter myCustomPagerAdapter = new MyCustomPagerAdapter(getApplicationContext(), urls);
        viewPager.setAdapter(myCustomPagerAdapter);
        tabview.setupWithViewPager(viewPager, true);

        viewPager.setClipToPadding(false);
        viewPager.setPadding(70, 0, 70, 0);
        viewPager.setPageMargin(10);
//        setupAutoPager();

    }

    public class MyCustomPagerAdapter extends PagerAdapter {
        Context context;
        List<TrainerImageGetSet> bannerDatumList;
        LayoutInflater layoutInflater;

        public MyCustomPagerAdapter(Context context, List<TrainerImageGetSet> bannerDatumList) {
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
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
                }
            });
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    private void setupAutoPager() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (viewPager == null) {
                    return;
                }
                viewPager.setCurrentItem(currentPage, true);
                if (currentPage == urls.size()) {
                    currentPage = 0;
                } else {
                    ++currentPage;
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000);
    }
}
