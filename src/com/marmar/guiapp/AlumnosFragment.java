package com.marmar.guiapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.marmar.guiapp.db.DataBaseHelper;
import com.marmar.guiapp.db.Idurl;

public class AlumnosFragment extends Fragment {

	private UsersAdapter ua;

	public AlumnosFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DataBaseHelper myDbHelper = new DataBaseHelper(getActivity());
		try {
			myDbHelper.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Idurl> things = myDbHelper.getAlumnoOrDocenteURLs("al");

		 ua = new UsersAdapter(getActivity(), (ArrayList<Idurl>)things);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_alumnos, container,
				false);

		final ListView lv = (ListView) rootView.findViewById(R.id.listView_alumnos);
		lv.setAdapter(ua);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ListAdapter la = lv.getAdapter();
				Idurl i = (Idurl)la.getItem(position);
				String url = i.getUrl_name();
				
				final Intent browserIntent 
					= new Intent(Intent.ACTION_VIEW,
						Uri.parse(url));
				browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getActivity().startActivity(browserIntent);
			}
		});

		return rootView;
	}

}

class UsersAdapter extends ArrayAdapter<Idurl> {
    public UsersAdapter(Context context, ArrayList<Idurl> users) {
       super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
    	Idurl user = getItem(position);    
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_url, parent, false);
       }
       // Lookup view for data population
       TextView tvName = (TextView) convertView.findViewById(R.id.urlDes);
       TextView tvHome = (TextView) convertView.findViewById(R.id.url);
       // Populate the data into the template view using the data object
       tvName.setText(user.getDescripcion_url());
       tvHome.setText(user.getUrl_name());
       // Return the completed view to render on screen
       return convertView;
   }
}
