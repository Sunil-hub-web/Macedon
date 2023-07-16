package in.co.macedon.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import in.co.macedon.R;
import in.co.macedon.extras.SessionManager;
import in.co.macedon.fragments.AboutFragment;
import in.co.macedon.fragments.CompletedSession;
import in.co.macedon.fragments.ContactUsFragment;
import in.co.macedon.fragments.FAQSFragment;
import in.co.macedon.fragments.GymMembership;
import in.co.macedon.fragments.HelpFragment;
import in.co.macedon.fragments.HomeFragment;
import in.co.macedon.fragments.MyOrder;
import in.co.macedon.fragments.PrivacyPolicyFragment;
import in.co.macedon.fragments.ProfileDetailsFragment;
import in.co.macedon.fragments.ReviewRating_Fragment;
import in.co.macedon.fragments.ScanFragment;
import in.co.macedon.fragments.ShopFragment;
import in.co.macedon.fragments.Subscriptions;
import in.co.macedon.fragments.TermsConditionsFragment;
import in.co.macedon.fragments.UserProfileDetails;

public class DashBoard extends AppCompatActivity {

    BottomNavigationView navView;
    public static FrameLayout fl;
    public static ImageView usericon, cart,img_Search;
    public static TextView logout_txt, userprofile_txt,nav_Subscriptions,nav_Home,
            nev_CompletedSession,userName,nav_GymMembership,userNamedet;
    public static TextView address_txt;
    public static RelativeLayout networkConnection,locationlayout,header;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    String not_updated;
    Button restartapp;

    Double latitude,longitude;
    GoogleMap mMap;
    String name,mobileNo,image,userid,addressDetails;
    SessionManager sessionManager;
    private int FINE_LOCATION_ACCESS_REQUEST_CODE = 10001;
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;

    HomeFragment test;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);

        navView = findViewById(R.id.nav_view);
        fl = findViewById(R.id.nav_host_fragment);
        usericon = findViewById(R.id.usericon);
        locationlayout = findViewById(R.id.locationlayout);
        cart = findViewById(R.id.cart);
        header = findViewById(R.id.header);
        address_txt = findViewById(R.id.locationtext);
        networkConnection = findViewById(R.id.networkConnection);
        restartapp = findViewById(R.id.restartapp);
        img_Search = findViewById(R.id.img_Search);
        userName = findViewById(R.id.userName);


        name = sessionManager.getUserName();
        userName.setText("Hi, " + name);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //Write Function To enable gps
            locationPermission();
            enableUserLocation();

        } else {
            //GPS is already On then
            getLocation();
        }

//        Intent intent = getIntent();
//        not_updated = intent.getStringExtra("not_updated");

        intrenetCheck();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        locationlayout.setVisibility(View.VISIBLE);
                        cart.setVisibility(View.GONE);
                        userName.setText("Hi, " + name);
                        //fl.removeAllViews();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment(),"HomeFragment").commit();
                        break;

                    case R.id.navigation_dashboard:
                        locationlayout.setVisibility(View.GONE);
                        cart.setVisibility(View.VISIBLE);
                        img_Search.setVisibility(View.GONE);
                        //fl.removeAllViews();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new GymMembership()).commit();
                        break;

                    case R.id.navigation_notifications:
                        locationlayout.setVisibility(View.VISIBLE);
                        cart.setVisibility(View.GONE);
                        //fl.removeAllViews();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ScanFragment()).commit();
                        break;

                    case R.id.navigation_help:
                        locationlayout.setVisibility(View.VISIBLE);
                        cart.setVisibility(View.GONE);
                        //fl.removeAllViews();
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ProfileDetailsFragment()).commit();
                        break;

                }

                return true;
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
            }
        });

        usericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowConfrmPay();
            }
        });

        restartapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intrenetCheck();

            }
        });

        img_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), SearchPage.class);
                startActivity(i);
            }
        });

        address_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(DashBoard.this,DeliveryLocation.class);
                startActivity(intent1);
            }
        });

    }

    Dialog dialogConfirm;

    public void ShowConfrmPay() {

        dialogConfirm = new Dialog(DashBoard.this);
        dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialogConfirm.setContentView(R.layout.navigation_drawer_layout);
        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogConfirm.setCanceledOnTouchOutside(true);
        dialogConfirm.setCancelable(true);
        dialogConfirm.getWindow().setGravity(Gravity.LEFT);
        Window window = dialogConfirm.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

     //   logout_txt = dialogConfirm.findViewById(R.id.logout_txt);
        userprofile_txt = dialogConfirm.findViewById(R.id.userprofile_txt);
        nav_Subscriptions = dialogConfirm.findViewById(R.id.nav_Subscriptions);
       // nav_ItemOrder = dialogConfirm.findViewById(R.id.nav_ItemOrder);
        nev_CompletedSession = dialogConfirm.findViewById(R.id.nev_CompletedSession);
        nav_GymMembership = dialogConfirm.findViewById(R.id.nav_GymMembership);
        userNamedet = dialogConfirm.findViewById(R.id.userNamedet);
        nav_Home = dialogConfirm.findViewById(R.id.nav_Home);
        //address_txt = dialogConfirm.findViewById(R.id.address_txt);

        userNamedet.setText("Hi, " + name);

        nav_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationlayout.setVisibility(View.GONE);
                cart.setVisibility(View.GONE);
                img_Search.setVisibility(View.GONE);
                fl.removeAllViews();
                userName.setText("Contact Us");
                dialogConfirm.dismiss();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ContactUsFragment contactUsFragment = new ContactUsFragment();
                ft.replace(R.id.nav_host_fragment, contactUsFragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        userprofile_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationlayout.setVisibility(View.GONE);
                cart.setVisibility(View.GONE);
                img_Search.setVisibility(View.GONE);
                fl.removeAllViews();
                userName.setText("Privacy Policy");
                dialogConfirm.dismiss();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                PrivacyPolicyFragment userProfileDetails = new PrivacyPolicyFragment();
                ft.replace(R.id.nav_host_fragment, userProfileDetails);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        nav_Subscriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationlayout.setVisibility(View.GONE);
                cart.setVisibility(View.GONE);
                img_Search.setVisibility(View.GONE);
                fl.removeAllViews();
                userName.setText("About As");
                dialogConfirm.dismiss();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                AboutFragment subscriptions = new AboutFragment();
                ft.replace(R.id.nav_host_fragment, subscriptions);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

       /* nav_ItemOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationlayout.setVisibility(View.GONE);
                cart.setVisibility(View.VISIBLE);
                img_Search.setVisibility(View.GONE);

                fl.removeAllViews();
                dialogConfirm.dismiss();
                userName.setText("My Order");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                MyOrder myOrder = new MyOrder();
                ft.replace(R.id.nav_host_fragment, myOrder);
                ft.addToBackStack(null);
                ft.commit();

            }
        });*/

        nev_CompletedSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                locationlayout.setVisibility(View.GONE);
                cart.setVisibility(View.GONE);
                img_Search.setVisibility(View.GONE);
                fl.removeAllViews();
                dialogConfirm.dismiss();
                userName.setText("FAQS");

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                FAQSFragment completedSession = new FAQSFragment();
                ft.replace(R.id.nav_host_fragment, completedSession);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        nav_GymMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationlayout.setVisibility(View.GONE);
                cart.setVisibility(View.GONE);
                img_Search.setVisibility(View.GONE);
                userName.setText("Terms & Conditions");
                fl.removeAllViews();
                dialogConfirm.dismiss();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                TermsConditionsFragment gymMembership = new TermsConditionsFragment();
                ft.replace(R.id.nav_host_fragment, gymMembership);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        LinearLayout mainlayout = dialogConfirm.findViewById(R.id.mainlayout);
        mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogConfirm.dismiss();
            }
        });

        dialogConfirm.show();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        test = (HomeFragment) getSupportFragmentManager().findFragmentByTag("HomeFragment");

        if (test != null && test.isVisible()) {

            if (exit) {
                finish(); // finish activity
            } else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }, 4 * 1000);
            }
        }
        else {

          //  userNamedet.setText("Hi, " + name);
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment(),"HomeFragment").commit();

        }
    }

    public void intrenetCheck(){

        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()){

            networkConnection.setVisibility(View.VISIBLE);
            fl.setVisibility(View.GONE);
            header.setVisibility(View.GONE);
            navView.setVisibility(View.GONE);

            Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            networkConnection.setVisibility(View.GONE);

            locationlayout.setVisibility(View.VISIBLE);
            cart.setVisibility(View.GONE);
            //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment(),"HomeFragment").commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new ReviewRating_Fragment()).commit();

          /*  if (not_updated.equals("not_updated")) {

                //fl.removeAllViews();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                UserProfileDetails userProfileDetails = new UserProfileDetails();
                ft.replace(R.id.nav_host_fragment, userProfileDetails);
                ft.commit();

            }else if(not_updated.equals("updated")){

                locationlayout.setVisibility(View.VISIBLE);
                cart.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment(),"HomeFragment").commit();

            }else if(not_updated.equals("home")){

                locationlayout.setVisibility(View.VISIBLE);
                cart.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment(),"HomeFragment").commit();
            }*/


        }
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                //initialize location
                Location location = task.getResult();

                if (location != null) {

                    try {
                        //initialize geocoder
                        Geocoder geocoder = new Geocoder(DashBoard.this, Locale.getDefault());

                        //initialize AddressList
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        //set Latitude On Text View
                        latitude = addresses.get(0).getLatitude();

                        //set Longitude On Text View
                        longitude = addresses.get(0).getLongitude();

                        //set address On Text View
                        address_txt.setText(addresses.get(0).getLocality());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    private void enableUserLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            //Ask for permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                //We need to show user a dialog for displaying why the permission is needed and then ask for the permission...
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_LOCATION_ACCESS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //We have the permission
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                        (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            } else {
                //We do not have the permission..
            }
        }
    }

    public void locationPermission() {

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);

        builder.setMessage("Enable Your GPS Location").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
