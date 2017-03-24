package com.marmar.guiapp;

import java.util.ArrayList;
import java.util.List;

import com.marmar.guiapp.R;
import com.marmar.guiapp.db.DataBaseHelper;
import com.marmar.guiapp.db.Idurl;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DocentesListaFragment extends Fragment {

	private UsersAdapter ua;

	public DocentesListaFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DataBaseHelper myDbHelper = new DataBaseHelper(getActivity());
		List<Idurl> things = myDbHelper.getAlumnoOrDocenteURLs("do");
		ua = new UsersAdapter(getActivity(), (ArrayList<Idurl>) things);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_docentes_lista,
				container, false);
		final ListView lv = (ListView) rootView
				.findViewById(R.id.listView_docentes);
		lv.setAdapter(ua);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ListAdapter la = lv.getAdapter();
				Idurl i = (Idurl) la.getItem(position);
				String url = i.getUrl_name();

				final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(url));
				browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivity(browserIntent);
			}
		});

		return rootView;
	}
}
