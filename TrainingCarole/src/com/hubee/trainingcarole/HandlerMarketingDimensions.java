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
				// prend en entr�e une BD ouverte
				dbHelper = new DataBaseHelper(context);
			
			}
			
		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
			  }
		  
		  public long insertMarketingDimensions(MarketingDimensions marketingdimension){
				//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(COL_Dimension,marketingdimension.Dimension);
				
			
				//on ins�re l'objet dans la BDD via le ContentValues
				return database.insert(TABLE_MarketingDimensions, null, values);
			}
		  
		  public int updateMarketingDimensions(String  dimension,MarketingDimensions marketingdimensions){
				//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
				//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
				ContentValues values = new ContentValues();
				values.put(COL_Dimension,marketingdimensions.Dimension);
				return database.update(TABLE_MarketingDimensions, values,  COL_Dimension+ " LIKE \"" + dimension +"\"" , null);
			}
		  
		  public int deleteMarketingDimensions(String dimension){
				//Suppression d'un user de la BDD gr�ce � l'ID
				return database.delete(TABLE_MarketingDimensions,  COL_Dimension+ " LIKE \"" + dimension +"\"", null);
			}
		  
		  public MarketingDimensions getMarketingDimensionsWithDimension(String  dimension){
				//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
				Cursor c =database.query(TABLE_MarketingDimensions, new String[] {COL_ID,COL_Dimension}, COL_Dimension + " LIKE \"" + dimension +"\"", null, null, null, null);
				return cursorToMarketingDimensions(c);
				
			}
		  
		//Cette m�thode permet de convertir un cursor en un UserAccounts
			private MarketingDimensions cursorToMarketingDimensions(Cursor c){
				//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier �l�ment
				c.moveToFirst();
				//On cr�� un UserAccounts
				MarketingDimensions marketingDimensions = new MarketingDimensions();
				//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
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
