package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerMarketingSegmentOccupations {
	
	private static final String TABLE_MarketingSegmentOccupations = "'0204_MarketingSegmentOccupations'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Occupation = "Occupation";
	private static final int NUM_COL_Occupation = 1;
	
	
	// Database fields
		  public SQLiteDatabase database;
		  public DataBaseHelper dbHelper;
		  public HandlerMarketingSegmentOccupations (Context context){
				// prend en entr�e une BD ouverte
				dbHelper = new DataBaseHelper(context);
			
			}
			
		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
			  }
		  
		  public long insertMarketingSegmentOccupations(MarketingSegmentOccupations marketingsegmenoccupation){
				//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(COL_Occupation,marketingsegmenoccupation.Occupation);
			
				//on ins�re l'objet dans la BDD via le ContentValues
				return database.insert(TABLE_MarketingSegmentOccupations, null, values);
			}
		  
		  public int updateMarketingSegmentOccupations(String  occupation, MarketingSegmentOccupations marketingsegmentoccupation){
				//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
				//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
				ContentValues values = new ContentValues();
				values.put(COL_Occupation,marketingsegmentoccupation.Occupation);
				return database.update(TABLE_MarketingSegmentOccupations, values,  COL_Occupation+ " LIKE \"" + occupation +"\"" , null);
			}
		  
		  public int deleteMarketingSegmentOccupations(String occupation){
				//Suppression d'un user de la BDD gr�ce � l'ID
				return database.delete(TABLE_MarketingSegmentOccupations,  COL_Occupation+ " LIKE \"" + occupation +"\"", null);
			}
			
		  public MarketingSegmentOccupations getMarketingSegmentOccupationsWithOccupation(String  occupation){
				//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
				Cursor c =database.query(TABLE_MarketingSegmentOccupations, new String[] {COL_ID,COL_Occupation}, COL_Occupation + " LIKE \"" + occupation +"\"", null, null, null, null);
				return cursorToMarketingSegmentOccupations(c);
				
			}
		  
		//Cette m�thode permet de convertir un cursor en un UserAccounts
			private MarketingSegmentOccupations cursorToMarketingSegmentOccupations(Cursor c){
				//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier �l�ment
				c.moveToFirst();
				//On cr�� un UserAccounts
				MarketingSegmentOccupations marketingSegmentOccupations = new MarketingSegmentOccupations();
				//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
				marketingSegmentOccupations.id=(c.getInt(NUM_COL_ID));
				marketingSegmentOccupations.Occupation=(c.getString(NUM_COL_Occupation));
				

				//On ferme le cursor
				c.close();
		 
				//On retourne le livre
				return marketingSegmentOccupations;
			}
		
		public void close()
		{
			  if(database != null)
		   		    database.close();
		}



}
