package in.co.macedon.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import in.co.macedon.R;
import in.co.macedon.activities.DashBoard;

public class FAQSFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.faqsfragment,container,false);

        DashBoard.header.setVisibility(View.GONE);
        DashBoard.header1.setVisibility(View.VISIBLE);

        return view;
    }
}
