package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerAppAffinity {
	private static final String TABLE_AppAffinity = "'0503_AppAffinity'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Affinity = "Affinity";
	private static final int NUM_COL_Affinity = 1;
	

	// Database fields
		  public SQLiteDatabase database;
		  public DataBaseHelper dbHelper;
		  public HandlerAppAffinity (Context context){
				// prend en entr�e une BD ouverte
				dbHelper = new DataBaseHelper(context);
			
			}
			
		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
			  }
		  
		  public long insertAppAffinity(AppAffinity appaffinity){
				//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(COL_Affinity,appaffinity.Affinity);
			
				//on ins�re l'objet dans la BDD via le ContentValues
				return database.insert(TABLE_AppAffinity, null, values);
			}
			
			public int updateAppAffinity(String  aff, AppAffinity appaffinity){
				//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
				//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
				ContentValues values = new ContentValues();
				values.put(COL_Affinity,appaffinity.Affinity);
				return database.update(TABLE_AppAffinity, values,  COL_Affinity+ " LIKE \"" + aff +"\"" , null);
			}
			
			public int deleteAppAffinity(String aff){
				//Suppression d'un user de la BDD gr�ce � l'ID
				return database.delete(TABLE_AppAffinity,  COL_Affinity+ " LIKE \"" + aff +"\"", null);
			}
			
			public AppAffinity getAppAffinityWithAffinity(String  aff){
				//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
				Cursor c =database.query(TABLE_AppAffinity, new String[] {COL_ID,COL_Affinity}, COL_Affinity + " LIKE \"" + aff +"\"", null, null, null, null);
				return cursorToAppAffinity(c);
				
			}

			//Cette m�thode permet de convertir un cursor en un UserAccounts
			private AppAffinity cursorToAppAffinity(Cursor c){
				//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier �l�ment
				c.moveToFirst();
				//On cr�� un UserAccounts
				AppAffinity appaffinity = new AppAffinity();
				//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
				appaffinity.id=(c.getInt(NUM_COL_ID));
				appaffinity.Affinity=(c.getString(NUM_COL_Affinity));
				

				//On ferme le cursor
				c.close();
		 
				//On retourne le livre
				return appaffinity;
			}
		
		public void close()
		{
			  if(database != null)
		   		    database.close();
		}
	
}
