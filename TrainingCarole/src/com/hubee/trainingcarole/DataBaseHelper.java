package com.hubee.trainingcarole;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper  extends SQLiteOpenHelper{
	 private static String DB_NAME = "HHLauncher07.db";
	

 //  public SQLiteDatabase myDataBase; 

   private final Context myContext;
   /**
    * Constructor
    * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
    * @param context
    */
   public DataBaseHelper(Context context) {

   	super(context, DB_NAME, null, 03);
       this.myContext = context;
     //  myDataBase =  SQLiteDatabase.openDatabase("/data/data/com.hubee.trainingcarole/databases/HHLauncher03.db", null, SQLiteDatabase.OPEN_READWRITE);
   }
   
   
   
   @Override
	public synchronized void close() {

   	    super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		//myDataBase =  SQLiteDatabase.openDatabase("/data/data/com.hubee.trainingcarole/databases/HHLauncher03.db", null, SQLiteDatabase.OPEN_READWRITE);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 
		 //SQLiteDatabase.openDatabase("/data/data/com.hubee.trainingcarole/databases/HHLauncher03.db", null, SQLiteDatabase.OPEN_READWRITE);	
	}

	/*public SQLiteDatabase getBDD() {
		// TODO Auto-generated method stub
		return  myDataBase;
	}*/

		

}
