package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerAppStatus {
	private static final String TABLE_AppStatus = "'0502_AppStatus'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Status = "Status";
	private static final int NUM_COL_Status = 1;
	private static final String COL_Affinity = "Affinity";
	private static final int NUM_COL_Affinity = 2;
	
	// Database fields
		  public SQLiteDatabase database;
		  public DataBaseHelper dbHelper;
		  public HandlerAppStatus (Context context){
				// prend en entrée une BD ouverte
				dbHelper = new DataBaseHelper(context);
			
			}
			
		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
			  }
	
			public long insertAppStatus(AppStatus appstatus){
				//Création d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(COL_Status,appstatus.Status);
				values.put(COL_Affinity,appstatus.Affinity);
			
				//on insère l'objet dans la BDD via le ContentValues
				return database.insert(TABLE_AppStatus, null, values);
			}
			
			public int updateAppStatus(String  status, AppStatus appstatus){
				//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
				//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
				ContentValues values = new ContentValues();
				values.put(COL_Status,appstatus.Status);
				values.put(COL_Affinity,appstatus.Affinity);
				return database.update(TABLE_AppStatus, values,  COL_Status+ " LIKE \"" + status +"\"" , null);
			}
		 
			public int deleteAppStatus(String status){
				//Suppression d'un user de la BDD grâce à l'ID
				return database.delete(TABLE_AppStatus,  COL_Status+ " LIKE \"" + status +"\"", null);
			}
			
			public AppStatus getAppStatusWithStatus(String  status){
				//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
				Cursor c =database.query(TABLE_AppStatus, new String[] {COL_ID, COL_Status,COL_Affinity}, COL_Status+ " LIKE \"" + status +"\"", null, null, null, null);
				return cursorToAppStatus(c);
			}

			//Cette méthode permet de convertir un cursor en un UserAccounts
			private AppStatus cursorToAppStatus(Cursor c){
				//si aucun élément n'a été retourné dans la requête, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier élément
				c.moveToFirst();
				//On créé un UserAccounts
				AppStatus appstatus = new AppStatus();
				//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
				appstatus.id=(c.getInt(NUM_COL_ID));
				appstatus.Status=(c.getString(NUM_COL_Status));
				appstatus.Affinity=(c.getInt(NUM_COL_Affinity));
				

				//On ferme le cursor
				c.close();
		 
				//On retourne le livre
				return appstatus;
			}
		
		public void close()
		{
			  if(database != null)
		   		    database.close();
		}
	 
}
