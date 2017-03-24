package com.marmar.guiapp;

import com.marmar.guiapp.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PropositoFragment extends Fragment {
	
	public PropositoFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_proposito, container, false);
         
        return rootView;
    }
}
