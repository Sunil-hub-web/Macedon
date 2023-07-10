package in.co.macedon.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import in.co.macedon.R;
import in.co.macedon.activities.DashBoard;
import in.co.macedon.activities.Login;
import in.co.macedon.extras.SessionManager;

public class ProfileDetailsFragment extends Fragment {

    TextView logout_txt, userprofile_txt, nav_Subscriptions, nev_CompletedSession, wallet_txt;
    SessionManager sessionManager;

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

        sessionManager = new SessionManager(getContext());

        userprofile_txt.setOnClickListener(new View.OnClickListener() {
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


        return view;
    }
}
