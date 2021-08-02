
package com.example.phoenixproject.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.phoenixproject.R;


public class profileFragment extends Fragment {
    public profileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        return root;
    }
}
