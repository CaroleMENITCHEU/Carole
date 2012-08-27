package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerLocalAppCategories {
	
	private static final String TABLE_LocalAppCategories = "'0505_LocalAppCategories'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Country = "Country";
	private static final int NUM_COL_Country = 1;
	private static final String COL_Category= "Category";
	private static final int NUM_COL_Category = 2;
	private static final String COL_rank = "rank";
	private static final int NUM_COL_rank = 3;
	
	// Database fields
		  public SQLiteDatabase database;
		  public DataBaseHelper dbHelper;
		  public HandlerLocalAppCategories (Context context){
				// prend en entr�e une BD ouverte
				dbHelper = new DataBaseHelper(context);
			
			}
			
		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
			  }

		  public long insertLocalAppCategories(LocalAppCategories localAppCat){
				//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(COL_Country,localAppCat.Country);
				values.put(COL_Category,localAppCat.Category);
				values.put(COL_rank,localAppCat.rank);
				
			
				//on ins�re l'objet dans la BDD via le ContentValues
				return database.insert(TABLE_LocalAppCategories, null, values);
			}
		  
		  public int updateLocalAppCategories(String  country, LocalAppCategories localapp){
				//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
				//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
				ContentValues values = new ContentValues();
				values.put(COL_Country,localapp.Country);
				values.put(COL_Category,localapp.Category);
				values.put(COL_rank,localapp.rank);
				return database.update(TABLE_LocalAppCategories, values,  COL_Country+ " LIKE \"" + country +"\"" , null);
			}
		  
		  public int deleteLocalAppCategories(String country){
				//Suppression d'un user de la BDD gr�ce � la category
				return database.delete(TABLE_LocalAppCategories,  COL_Category+ " LIKE \"" + country  +"\"", null);
			}
		  
		  public LocalAppCategories getLocalAppCategoriesWithcountry(String country){
				//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
				Cursor c =database.query(TABLE_LocalAppCategories, new String[] {COL_ID,COL_Country,COL_Category,COL_rank}, COL_Country + " LIKE \"" + country +"\"", null, null, null, null);
				return cursorToLocalAppCategories(c);
				
			}
		  
		//Cette m�thode permet de convertir un cursor en un UserAccounts
			private LocalAppCategories cursorToLocalAppCategories(Cursor c){
				//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier �l�ment
				c.moveToFirst();
				//On cr�� un UserAccounts
				LocalAppCategories localApp= new LocalAppCategories();
				//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
				localApp.id=(c.getInt(NUM_COL_ID));
				localApp.Country=(c.getString(NUM_COL_Country));
				localApp.Category=(c.getString(NUM_COL_Category));
				localApp.rank=(c.getInt(NUM_COL_rank));

				//On ferme le cursor
				c.close();
		 
				//On retourne le livre
				return localApp;
			}
		
		public void close()
		{
			  if(database != null)
		   		    database.close();
		}

			
}
