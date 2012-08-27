package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerGoogleAppCategories {
	private static final String TABLE_GoogleAppCategories = "'0504_GoogleAppCategories'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Category = "Category";
	private static final int NUM_COL_Category = 1;
	private static final String COL_Popularity = "Popularity";
	private static final int NUM_COL_Popularity = 2;

	// Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	  public  HandlerGoogleAppCategories (Context context){
			// prend en entrée une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertGoogleAppCategories(GoogleAppCategories GoogleAppCat){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Category,GoogleAppCat.Category);
			values.put(COL_Popularity,GoogleAppCat.Popularity);
			
		
			//on insère l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_GoogleAppCategories, null, values);
		}
	  
	  public int updateGoogleAppCategories(String  category, GoogleAppCategories googleapp){
			//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Category,googleapp.Category);
			values.put(COL_Popularity,googleapp.Popularity);
			return database.update(TABLE_GoogleAppCategories, values,  COL_Category+ " LIKE \"" + category +"\"" , null);
		}
		
	  public int deleteGoogleAppCategories(String category){
			//Suppression d'un user de la BDD grâce à la category
			return database.delete(TABLE_GoogleAppCategories,  COL_Category+ " LIKE \"" + category  +"\"", null);
		}
	  
	  public GoogleAppCategories getGoogleAppCategoriesWithcategory(String category){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_GoogleAppCategories, new String[] {COL_ID,COL_Category,COL_Popularity}, COL_Category + " LIKE \"" + category +"\"", null, null, null, null);
			return cursorToGoogleAppCategories(c);
			
		}
	  
	//Cette méthode permet de convertir un cursor en un UserAccounts
		private GoogleAppCategories cursorToGoogleAppCategories(Cursor c){
			//si aucun élément n'a été retourné dans la requête, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un UserAccounts
			GoogleAppCategories googleApp= new GoogleAppCategories();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			googleApp.id=(c.getInt(NUM_COL_ID));
			googleApp.Category=(c.getString(NUM_COL_Category));
			googleApp.Popularity=(c.getInt(NUM_COL_Popularity));

			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return googleApp;
		}
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}

		

}
