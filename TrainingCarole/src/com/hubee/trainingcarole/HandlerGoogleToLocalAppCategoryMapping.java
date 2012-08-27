package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerGoogleToLocalAppCategoryMapping {
	private static final String TABLE_GoogleToLocalAppCategoryMapping = "'0506_GoogleToLocalAppCategoryMapping'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Local = "Local";
	private static final int NUM_COL_Local = 1;
	private static final String COL_Google= "Google";
	private static final int NUM_COL_Google = 2;
	
	// Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	  public HandlerGoogleToLocalAppCategoryMapping (Context context){
			// prend en entrée une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertGoogleToLocalAppCategoryMapping(GoogleToLocalAppCategoryMapping googletolocal){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Local,googletolocal.Local);
			values.put(COL_Google,googletolocal.Google);
			
			//on insère l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_GoogleToLocalAppCategoryMapping , null, values);
		}
	  
	  public int updateGoogleToLocalAppCategoryMapping(int google, GoogleToLocalAppCategoryMapping googletolocal){
			//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Local,googletolocal.Local);
			values.put(COL_Google,googletolocal.Google);
			return database.update(TABLE_GoogleToLocalAppCategoryMapping, values,  COL_Google+ " =" + google  , null);
		}
	  
	  public int deleteGoogleToLocalAppCategoryMapping(int google){
			//Suppression d'un user de la BDD grâce à la category
			return database.delete(TABLE_GoogleToLocalAppCategoryMapping,  COL_Google+ " =" + google, null);
		}

	  public GoogleToLocalAppCategoryMapping getGoogleToLocalAppCategoryMappingWithgoogle(int google){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_GoogleToLocalAppCategoryMapping, new String[] {COL_ID,COL_Local,COL_Google}, COL_Google+ "=" + google +"\"", null, null, null, null);
			return cursorToGoogleToLocalAppCategoryMapping(c);
			
		}
	  
	//Cette méthode permet de convertir un cursor en un UserAccounts
		private GoogleToLocalAppCategoryMapping cursorToGoogleToLocalAppCategoryMapping(Cursor c){
			//si aucun élément n'a été retourné dans la requête, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un UserAccounts
			GoogleToLocalAppCategoryMapping googletolocal= new GoogleToLocalAppCategoryMapping();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			googletolocal.id=(c.getInt(NUM_COL_ID));
			googletolocal.Local=(c.getInt(NUM_COL_Local));
			googletolocal.Google=(c.getInt(NUM_COL_Google));
			

			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return googletolocal;
		}
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}

}
