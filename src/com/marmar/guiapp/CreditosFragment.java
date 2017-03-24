package com.marmar.guiapp;

import com.marmar.guiapp.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreditosFragment extends Fragment {
	
	public CreditosFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_creditos, container, false);
         
        return rootView;
    }
}
