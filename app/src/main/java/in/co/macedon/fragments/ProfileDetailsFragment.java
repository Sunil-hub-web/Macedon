package in.co.macedon.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import in.co.macedon.R;
import in.co.macedon.activities.DashBoard;
import in.co.macedon.activities.Login;
import in.co.macedon.extras.SessionManager;

public class ProfileDetailsFragment extends Fragment {

    TextView logout_txt, userprofile_txt, nav_Subscriptions, nev_CompletedSession, wallet_txt,showAllDate;
    SessionManager sessionManager;
    ImageView image_back;
    // variable for our bar chart
    BarChart barChart;
    // variable for our bar data.
    BarData barData;
    // variable for our bar data set.
    BarDataSet barDataSet1,barDataSet2,barDataSet3,barDataSet4,barDataSet5,barDataSet6,barDataSet7;
    // array list for storing entries.
    ArrayList<BarEntry> barEntriesArrayList1;
    ArrayList<BarEntry> barEntriesArrayList2;
    ArrayList<BarEntry> barEntriesArrayList3;
    ArrayList<BarEntry> barEntriesArrayList4;
    ArrayList<BarEntry> barEntriesArrayList5;
    ArrayList<BarEntry> barEntriesArrayList6;
    ArrayList<BarEntry> barEntriesArrayList7;
    ImageView editProfile;

    List<String> xValues = Arrays.asList("Monday","Tuesday","Wednesday","Tursday","Friday","Saturday","Sunday");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        logout_txt = view.findViewById(R.id.logout_txt);
        userprofile_txt = view.findViewById(R.id.userprofile_txt);
        nav_Subscriptions = view.findViewById(R.id.nav_Subscriptions);
        wallet_txt = view.findViewById(R.id.wallet_txt);
        nev_CompletedSession = view.findViewById(R.id.nev_CompletedSession);
        image_back = view.findViewById(R.id.image_back);
        editProfile = view.findViewById(R.id.editProfile);
        showAllDate = view.findViewById(R.id.showAllDate);
        DashBoard.header.setVisibility(View.GONE);

        // initializing variable for bar chart.
        barChart = view.findViewById(R.id.idBarChart);

        sessionManager = new SessionManager(getContext());

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DashBoard.locationlayout.setVisibility(View.VISIBLE);
                DashBoard.cart.setVisibility(View.GONE);
                DashBoard.fl.removeAllViews();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                UserProfileDetails userProfileDetails = new UserProfileDetails();
                ft.replace(R.id.nav_host_fragment, userProfileDetails);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        nav_Subscriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DashBoard.locationlayout.setVisibility(View.VISIBLE);
                DashBoard.cart.setVisibility(View.GONE);
                DashBoard.fl.removeAllViews();
                DashBoard.userName.setText("Subscriptions");

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Subscriptions subscriptions = new Subscriptions();
                ft.replace(R.id.nav_host_fragment, subscriptions);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        nev_CompletedSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DashBoard.locationlayout.setVisibility(View.VISIBLE);
                DashBoard.cart.setVisibility(View.GONE);
                DashBoard.img_Search.setVisibility(View.VISIBLE);
                DashBoard.userName.setText("Completed Session");
                DashBoard.fl.removeAllViews();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                CompletedSession completedSession = new CompletedSession();
                ft.replace(R.id.nav_host_fragment, completedSession);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        wallet_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DashBoard.locationlayout.setVisibility(View.VISIBLE);
                DashBoard.cart.setVisibility(View.GONE);
                DashBoard.img_Search.setVisibility(View.VISIBLE);
                DashBoard.userName.setText("Completed Session");
                DashBoard.fl.removeAllViews();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Wallet_Fragment wallet_fragment = new Wallet_Fragment();
                ft.replace(R.id.nav_host_fragment, wallet_fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        logout_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Logout!!")
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(getActivity().getApplicationContext(), Login.class);
                                startActivity(i);

                                sessionManager.logoutUser();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                ft.replace(R.id.nav_host_fragment, homeFragment);
                ft.addToBackStack(null);
                ft.commit();

                DashBoard.navView.setSelectedItemId(R.id.navigation_home);

            }
        });


        barEntriesArrayList1 = new ArrayList<>();
        barEntriesArrayList1.add(new BarEntry(0,45f));
        barEntriesArrayList1.add(new BarEntry(1,65f));
        barEntriesArrayList1.add(new BarEntry(2,80f));
        barEntriesArrayList1.add(new BarEntry(3,38f));
        barEntriesArrayList1.add(new BarEntry(4,46f));
        barEntriesArrayList1.add(new BarEntry(5,46f));
        barEntriesArrayList1.add(new BarEntry(6,46f));

       /* barEntriesArrayList2 = new ArrayList<>();
        barEntriesArrayList2.add(new BarEntry(0,45f));
        barEntriesArrayList2.add(new BarEntry(1,65f));
        barEntriesArrayList2.add(new BarEntry(2,80f));
        barEntriesArrayList2.add(new BarEntry(3,38f));
        barEntriesArrayList2.add(new BarEntry(4,46f));
        barEntriesArrayList2.add(new BarEntry(5,46f));
        barEntriesArrayList2.add(new BarEntry(6,46f));

        barEntriesArrayList3 = new ArrayList<>();
        barEntriesArrayList3.add(new BarEntry(0,45f));
        barEntriesArrayList3.add(new BarEntry(1,65f));
        barEntriesArrayList3.add(new BarEntry(2,80f));
        barEntriesArrayList3.add(new BarEntry(3,38f));
        barEntriesArrayList3.add(new BarEntry(4,46f));
        barEntriesArrayList3.add(new BarEntry(5,46f));
        barEntriesArrayList3.add(new BarEntry(6,46f));

        barEntriesArrayList4 = new ArrayList<>();
        barEntriesArrayList4.add(new BarEntry(0,45f));
        barEntriesArrayList4.add(new BarEntry(1,65f));
        barEntriesArrayList4.add(new BarEntry(2,80f));
        barEntriesArrayList4.add(new BarEntry(3,38f));
        barEntriesArrayList4.add(new BarEntry(4,46f));
        barEntriesArrayList4.add(new BarEntry(5,46f));
        barEntriesArrayList4.add(new BarEntry(6,46f));

        barEntriesArrayList5 = new ArrayList<>();
        barEntriesArrayList5.add(new BarEntry(0,45f));
        barEntriesArrayList5.add(new BarEntry(1,65f));
        barEntriesArrayList5.add(new BarEntry(2,80f));
        barEntriesArrayList5.add(new BarEntry(3,38f));
        barEntriesArrayList5.add(new BarEntry(4,46f));
        barEntriesArrayList5.add(new BarEntry(5,46f));
        barEntriesArrayList5.add(new BarEntry(6,46f));

        barEntriesArrayList6 = new ArrayList<>();
        barEntriesArrayList6.add(new BarEntry(0,45f));
        barEntriesArrayList6.add(new BarEntry(1,65f));
        barEntriesArrayList6.add(new BarEntry(2,80f));
        barEntriesArrayList6.add(new BarEntry(3,38f));
        barEntriesArrayList6.add(new BarEntry(4,46f));
        barEntriesArrayList6.add(new BarEntry(5,46f));
        barEntriesArrayList6.add(new BarEntry(6,46f));

        barEntriesArrayList7 = new ArrayList<>();
        barEntriesArrayList7.add(new BarEntry(0,45f));
        barEntriesArrayList7.add(new BarEntry(1,65f));
        barEntriesArrayList7.add(new BarEntry(2,80f));
        barEntriesArrayList7.add(new BarEntry(3,38f));
        barEntriesArrayList7.add(new BarEntry(4,46f));
        barEntriesArrayList7.add(new BarEntry(5,46f));
        barEntriesArrayList7.add(new BarEntry(6,46f));*/

        barDataSet1 = new BarDataSet(barEntriesArrayList1, "Macedon");
        barDataSet1.setColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        barDataSet1.setDrawValues(false);
        barDataSet1.setValueTextColor(Color.BLACK);
        barDataSet1.setValueTextSize(10f);

        /*barDataSet2 = new BarDataSet(barEntriesArrayList2,"Tuesday");
        barDataSet2.setColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        barDataSet2.setDrawValues(false);
        barDataSet2.setValueTextColor(Color.BLACK);
        barDataSet2.setValueTextSize(10f);

        barDataSet3 = new BarDataSet(barEntriesArrayList3,"Wednesday");
        barDataSet3.setColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        barDataSet3.setDrawValues(false);
        barDataSet3.setValueTextColor(Color.BLACK);
        barDataSet3.setValueTextSize(10f);

        barDataSet4 = new BarDataSet(barEntriesArrayList4,"Tursday");
        barDataSet4.setColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        barDataSet4.setDrawValues(false);
        barDataSet4.setValueTextColor(Color.BLACK);
        barDataSet4.setValueTextSize(10f);

        barDataSet5 = new BarDataSet(barEntriesArrayList5,"Friday");
        barDataSet5.setColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        barDataSet5.setDrawValues(false);
        barDataSet5.setValueTextColor(Color.BLACK);
        barDataSet5.setValueTextSize(10f);

        barDataSet6 = new BarDataSet(barEntriesArrayList6,"Saturday");
        barDataSet6.setColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        barDataSet6.setDrawValues(false);
        barDataSet6.setValueTextColor(Color.BLACK);
        barDataSet6.setValueTextSize(10f);

        barDataSet7 = new BarDataSet(barEntriesArrayList7,"Sunday");
        barDataSet7.setColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        barDataSet7.setDrawValues(false);
        barDataSet7.setValueTextColor(Color.BLACK);
        barDataSet7.setValueTextSize(10f);*/


        barData = new BarData(barDataSet1);
        barChart.setData(barData);
        barData.setBarWidth(0.30f);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMaximum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(1);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        barChart.setDragEnabled(true);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.animateY(5000);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false); // disable grid lines for the XAxis
        barChart.getAxisLeft().setDrawGridLines(false); // disable grid lines for the left YAxis
        barChart.getAxisRight().setDrawGridLines(false); // disable grid lines for the right YAxis
        barChart.invalidate();
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);
       // barChart.getXAxis().setCenterAxisLabels(true);

        showAllDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });

        return view;
    }

    private void showDialog() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        TextView showdate = dialog.findViewById(R.id.showdate);
        DatePicker datePicker1 = dialog.findViewById(R.id.datePicker1);
        int day = datePicker1.getDayOfMonth();
        int month = datePicker1.getMonth() + 1;
        int year = datePicker1.getYear();
        showdate.setText(year+"-"+day+"-"+month);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker1.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    showdate.setText(year+"-"+dayOfMonth+"-"+monthOfYear);
                }
            });
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
            Date d = new Date(year, month, day);
            String strDate = dateFormatter.format(d);
        }


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}
