package com.marmar.guiapp.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.marmar.guiapp/databases/";

	private static String DB_NAME = "mydb.db";

	private SQLiteDatabase myDataBase;

	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DataBaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// the public helper methods to access and get content from the database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd
	// be easy
	// to you to create adapters for your views.

//	public List<Docente> getAllDocentes() {
//		List<Docente> docenteList = new ArrayList<Docente>();
//		// Select All Query
//		String selectQuery = "SELECT  * FROM docentes";
//
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//
//		// looping through all rows and adding to list
//		if (cursor.moveToFirst()) {
//			do {
//				Docente doc = new Docente(
//						Integer.parseInt(cursor.getString(0)),
//						cursor.getString(1), cursor.getString(2),
//						cursor.getString(3));
//				// Adding contact to list
//				docenteList.add(doc);
//			} while (cursor.moveToNext());
//		}
//
//		// return contact list
//		return docenteList;
//	}

	// public Docente getDocente(int id) {
	// SQLiteDatabase db = this.getReadableDatabase();
	//
	// Cursor cursor = db.rawQuery("SELECT * FROM docentes where ", null);
	// if (cursor != null)
	// cursor.moveToFirst();
	//
	// Docente doc = new Docente(Integer.parseInt(cursor.getString(0)),
	// cursor.getString(1), cursor.getString(2), cursor.getString(3));
	// // return contact
	// return doc;
	// }

//	public int getDocenteCount() {
//		String countQuery = "SELECT  * FROM docentes";
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		cursor.close();
//
//		// return count
//		return cursor.getCount();
//	}

	public boolean validarCredenciales(String clave) {
		SQLiteDatabase db = this.getReadableDatabase();
		String s = "SELECT * FROM docentes d WHERE " 
				 + "d.cve_acceso='" + clave + "'";

		Cursor cursor = db.rawQuery(s, null);
		
		if (cursor.moveToFirst()) {
			return true;
		}
		return false;
	}

	public List<Idurl> getAllURLs() {
		List<Idurl> urlList = new ArrayList<Idurl>();
		// Select All Query
		String selectQuery = "SELECT  * FROM idurl";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Idurl url = new Idurl(Integer.parseInt(cursor.getString(0)),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4));
				// Adding contact to list
				urlList.add(url);
			} while (cursor.moveToNext());
		}

		// return contact list
		return urlList;
	}

	public List<Idurl> getAlumnoOrDocenteURLs(String acceso) {
		List<Idurl> urlList = new ArrayList<Idurl>();
		// Select All Query
		String selectQuery = "SELECT  * FROM idurl where acceso='" + acceso + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Idurl url = new Idurl(Integer.parseInt(cursor.getString(0)),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4));
				// Adding contact to list
				urlList.add(url);
			} while (cursor.moveToNext());
		}

		// return contact list
		return urlList;
	}

	public List<Idurl> getFiltradoAlumnoOrDocenteURLs(String acceso,
			String filtro) {
		List<Idurl> urlList = new ArrayList<Idurl>();
		// Select All Query
		String selectQuery = "SELECT  * FROM idurl where acceso='" + acceso
				+ "' and (" + "descipcion_url LIKE '%" + filtro
				+ "%' or url_name LIKE '%" + filtro + "%')";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Idurl url = new Idurl(Integer.parseInt(cursor.getString(0)),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4));
				// Adding contact to list
				urlList.add(url);
			} while (cursor.moveToNext());
		}

		// return contact list
		return urlList;
	}
}
