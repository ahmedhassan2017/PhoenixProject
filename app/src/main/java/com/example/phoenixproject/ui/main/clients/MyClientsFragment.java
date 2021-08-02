package com.example.phoenixproject.ui.main.clients;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phoenixproject.R;


public class MyClientsFragment extends Fragment {

    public MyClientsFragment() {

    }


    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_myclients, container, false);
        return root;
    }
}
