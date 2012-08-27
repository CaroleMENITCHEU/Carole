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
			// prend en entr�e une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertGoogleAppCategories(GoogleAppCategories GoogleAppCat){
			//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Category,GoogleAppCat.Category);
			values.put(COL_Popularity,GoogleAppCat.Popularity);
			
		
			//on ins�re l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_GoogleAppCategories, null, values);
		}
	  
	  public int updateGoogleAppCategories(String  category, GoogleAppCategories googleapp){
			//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Category,googleapp.Category);
			values.put(COL_Popularity,googleapp.Popularity);
			return database.update(TABLE_GoogleAppCategories, values,  COL_Category+ " LIKE \"" + category +"\"" , null);
		}
		
	  public int deleteGoogleAppCategories(String category){
			//Suppression d'un user de la BDD gr�ce � la category
			return database.delete(TABLE_GoogleAppCategories,  COL_Category+ " LIKE \"" + category  +"\"", null);
		}
	  
	  public GoogleAppCategories getGoogleAppCategoriesWithcategory(String category){
			//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
			Cursor c =database.query(TABLE_GoogleAppCategories, new String[] {COL_ID,COL_Category,COL_Popularity}, COL_Category + " LIKE \"" + category +"\"", null, null, null, null);
			return cursorToGoogleAppCategories(c);
			
		}
	  
	//Cette m�thode permet de convertir un cursor en un UserAccounts
		private GoogleAppCategories cursorToGoogleAppCategories(Cursor c){
			//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier �l�ment
			c.moveToFirst();
			//On cr�� un UserAccounts
			GoogleAppCategories googleApp= new GoogleAppCategories();
			//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
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
