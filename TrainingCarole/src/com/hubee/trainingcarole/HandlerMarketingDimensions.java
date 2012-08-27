package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerMarketingDimensions {

	private static final String TABLE_MarketingDimensions = "'0201_MarketingDimensions'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Dimension = "Dimension";
	private static final int NUM_COL_Dimension = 1;
	
	// Database fields
		  public SQLiteDatabase database;
		  public DataBaseHelper dbHelper;
		  public HandlerMarketingDimensions (Context context){
				// prend en entrée une BD ouverte
				dbHelper = new DataBaseHelper(context);
			
			}
			
		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
			  }
		  
		  public long insertMarketingDimensions(MarketingDimensions marketingdimension){
				//Création d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(COL_Dimension,marketingdimension.Dimension);
				
			
				//on insère l'objet dans la BDD via le ContentValues
				return database.insert(TABLE_MarketingDimensions, null, values);
			}
		  
		  public int updateMarketingDimensions(String  dimension,MarketingDimensions marketingdimensions){
				//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
				//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
				ContentValues values = new ContentValues();
				values.put(COL_Dimension,marketingdimensions.Dimension);
				return database.update(TABLE_MarketingDimensions, values,  COL_Dimension+ " LIKE \"" + dimension +"\"" , null);
			}
		  
		  public int deleteMarketingDimensions(String dimension){
				//Suppression d'un user de la BDD grâce à l'ID
				return database.delete(TABLE_MarketingDimensions,  COL_Dimension+ " LIKE \"" + dimension +"\"", null);
			}
		  
		  public MarketingDimensions getMarketingDimensionsWithDimension(String  dimension){
				//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
				Cursor c =database.query(TABLE_MarketingDimensions, new String[] {COL_ID,COL_Dimension}, COL_Dimension + " LIKE \"" + dimension +"\"", null, null, null, null);
				return cursorToMarketingDimensions(c);
				
			}
		  
		//Cette méthode permet de convertir un cursor en un UserAccounts
			private MarketingDimensions cursorToMarketingDimensions(Cursor c){
				//si aucun élément n'a été retourné dans la requête, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier élément
				c.moveToFirst();
				//On créé un UserAccounts
				MarketingDimensions marketingDimensions = new MarketingDimensions();
				//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
				marketingDimensions.id=(c.getInt(NUM_COL_ID));
				marketingDimensions .Dimension=(c.getString(NUM_COL_Dimension));
				
				//On ferme le cursor
				c.close();
		 
				//On retourne le livre
				return marketingDimensions;
			}
		
		public void close()
		{
			  if(database != null)
		   		    database.close();
		}

		  

		  
	
}
