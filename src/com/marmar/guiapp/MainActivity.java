package com.marmar.guiapp;

import com.marmar.guiapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.marmar.guiapp.adapter.NavDrawerListAdapter;
import com.marmar.guiapp.db.DataBaseHelper;
import com.marmar.guiapp.db.Idurl;
import com.marmar.guiapp.model.NavDrawerItem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		// Docentes
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		// Alumnos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));
		// Proposito
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1), true, "22"));
		// Creditos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		// Salir
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(5, -1), true, "50+"));

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}

		// try to change the action bar color
		ActionBar actionBar;
		actionBar = getActionBar();
		ColorDrawable colorDrawable = new ColorDrawable(
				Color.parseColor("#1865af"));
		actionBar.setBackgroundDrawable(colorDrawable);
		// actionBar.

		int titleId = getResources().getIdentifier("action_bar_title", "id",
				"android");
		TextView abTitle = (TextView) findViewById(titleId);
		abTitle.setTextColor(Color.WHITE);
	}

	/**
	 * Slide menu item click listener
	 * 
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new DocentesFragment();
			break;
		case 2:
			fragment = new AlumnosFragment();
			break;
		case 3:
			fragment = new PropositoFragment();
			break;
		case 4:
			fragment = new CreditosFragment();
			break;
		case 5:
//			System.exit(0);
			fragment = new AgradecimientosFragment();
			// fragment = new WhatsHotFragment();
			break;
		case 6:
			fragment = new DocentesListaFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			if (position == 6) {
				position = 1;
			}
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void filtrarEnAlumnos(View v) {
		EditText filtro = (EditText) this
				.findViewById(R.id.editText_filtro_en_alumnos);

		if (filtro == null) {
			return;
		}

		if (filtro.getText() == null) {
			return;
		}

		if (filtro.getText().toString() == null) {
			return;
		}

		if (filtro.getText().toString().isEmpty()) {
			fillList(R.id.listView_alumnos, "al", null, false);
			return;
		}

		fillList(R.id.listView_alumnos, "al", filtro.getText().toString(), true);
	}

	public void validarLoginDocente(View v) {

		EditText clave = (EditText) this
				.findViewById(R.id.editText_clave_login);

		if (clave == null) {
			return;
		}

		if (clave.getText() == null) {
			return;
		}

		if (clave.getText().toString() == null) {
			return;
		}

		if (clave.getText().toString().isEmpty()) {
			return;
		}

		DataBaseHelper myDbHelper = new DataBaseHelper(this);
		
		try {
			myDbHelper.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean exito = myDbHelper.validarCredenciales(clave.getText()
				.toString());
		if (exito) {
			// ir a fragment de docentes
			// TODO
			displayView(6);
		} else {
			Toast.makeText(this, "Credenciales Invalidas", Toast.LENGTH_LONG)
					.show();
		}

	}

	public void filtrarEnDocentes(View v) {
		EditText filtro = (EditText) this
				.findViewById(R.id.editText_filtro_en_docentes);
		if (filtro == null) {
			return;
		}

		if (filtro.getText() == null) {
			return;
		}

		if (filtro.getText().toString() == null) {
			return;
		}

		if (filtro.getText().toString().isEmpty()) {
			fillList(R.id.listView_docentes, "do", null, false);
			return;
		}

		fillList(R.id.listView_docentes, "do", filtro.getText().toString(), true);
		
	}

	public void fillList(int rid, String usuario, String filtro, boolean filter) {
		DataBaseHelper myDbHelper = new DataBaseHelper(this);

		try {
			myDbHelper.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UsersAdapter ua;
		if (filter) {
			List<Idurl> things = myDbHelper.getFiltradoAlumnoOrDocenteURLs(
					usuario, filtro);
			ua = new UsersAdapter(this, (ArrayList<Idurl>) things);
		} else {
			List<Idurl> things = myDbHelper.getAlumnoOrDocenteURLs(usuario);
			ua = new UsersAdapter(this, (ArrayList<Idurl>) things);
		}

		final Activity activity = this;
		final ListView lv = (ListView) this.findViewById(rid);
		lv.setAdapter(ua);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ListAdapter la = lv.getAdapter();
				Idurl i = (Idurl) la.getItem(position);
				String url = i.getUrl_name();

				final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(url));
				browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				activity.startActivity(browserIntent);
			}
		});
	}
}
