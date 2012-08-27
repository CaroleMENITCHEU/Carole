package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerAppSelection {

	private static final String TABLE_App_Selection = "'0500_AppSelection'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_App = "App";
	private static final int NUM_COL_App = 1;
	private static final String COL_Status = "Status";
	private static final int NUM_COL_Status=2;
	private static final String COL_User = "User";
	private static final int NUM_COL_User=3;
	private static final String COL_Added = "Added";
	private static final int NUM_COL_Added=4;
	private static final String COL_Updated = "Updated";
	private static final int NUM_COL_Updated=5;
	 // Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	
	public HandlerAppSelection(Context context){
		// prend en entrée une BD ouverte
		dbHelper = new DataBaseHelper(context);
	//	bdd=maBaseSQLite.myDataBase;
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }
	
	public long insertAppSelection(AppSelection appselection){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		
		values.put( COL_App, appselection.App);
		values.put(COL_Status , appselection.Status);
		values.put( COL_User, appselection.User);
		values.put(COL_Added, appselection.Added);
		values.put(COL_Updated, appselection.Updated);
		//on insère l'objet dans la BDD via le ContentValues
		return database.insert(TABLE_App_Selection, null, values);
	}
	
	public int updateAppSelection(int  app,AppSelection appselection){
		//La mise à jour d'un user dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
		ContentValues values = new ContentValues();

		values.put( COL_App, appselection.App);
		values.put(COL_Status , appselection.Status);
		values.put( COL_User, appselection.User);
		values.put(COL_Added, appselection.Added);
		values.put(COL_Updated, appselection.Updated);
		return database.update(TABLE_App_Selection, values,  COL_App+ " =" + app , null);
	}
 

	public int deleteAppSelectionWithApp(int  app){
		//Suppression d'un user de la BDD grâce à l'ID
		return database.delete(TABLE_App_Selection,  COL_App+ " =" + app, null);
	}

	public AppSelection getAppSelectionWithApp(int app){
		//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
		Cursor c =database.query(TABLE_App_Selection, new String[] {COL_ID, COL_App, COL_Status,COL_User,COL_Added,COL_Updated}, COL_App+ "=" + app +"\"", null, null, null, null);
		return cursorToAppSelection(c);
	}
	
	//Cette méthode permet de convertir un cursor en un UserAccounts
			private AppSelection cursorToAppSelection(Cursor c){
				//si aucun élément n'a été retourné dans la requête, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier élément
				c.moveToFirst();
				//On créé un AppSelection
				AppSelection appselection = new AppSelection();
				//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
				appselection.id=(c.getInt(NUM_COL_ID));
				appselection.App=(c.getInt(NUM_COL_App));
				appselection.Status=(c.getInt(NUM_COL_Status));
				appselection.User=(c.getInt(NUM_COL_User));
				appselection.Added=(c.getString(NUM_COL_Added));
				appselection.Updated=(c.getString(NUM_COL_Updated));


				//On ferme le cursor
				c.close();
		 
				//On retourne le livre
				return appselection;
			}
		
		public void close()
		{
			  if(database != null)
		   		    database.close();
		}
	 
}
