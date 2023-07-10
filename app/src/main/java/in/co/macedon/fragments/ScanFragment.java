package in.co.macedon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import in.co.macedon.R;
import in.co.macedon.activities.ScannerClass;

public class ScanFragment extends Fragment {

    Button btn_ScannClass;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_scanner, container, false);

        btn_ScannClass = root.findViewById(R.id.btn_ScannClass);

        btn_ScannClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), ScannerClass.class));

            }
        });

        return root;
    }
}
