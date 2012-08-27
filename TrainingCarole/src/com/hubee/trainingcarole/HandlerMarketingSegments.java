package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerMarketingSegments {
	
	private static final String TABLE_MarketingSegments = "'0202_MarketingSegments'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Dimension = "Dimension";
	private static final int NUM_COL_Dimension = 1;
	private static final String COL_Segment = "Segment";
	private static final int NUM_COL_Segment = 2;
	
	
	// Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	  public HandlerMarketingSegments (Context context){
			// prend en entrée une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertMarketingSegments(MarketingSegments marketingSegments){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Dimension,marketingSegments.Dimension);
			values.put(COL_Segment,marketingSegments.Segment);
		
			//on insère l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_MarketingSegments, null, values);
		}
	  
	  public int updateMarketingSegments(String  segment, MarketingSegments marketingsegments){
			//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Dimension,marketingsegments.Dimension);
			values.put(COL_Segment,marketingsegments.Segment);
			return database.update(TABLE_MarketingSegments, values,  COL_Segment+ " LIKE \"" + segment +"\"" , null);
		}
	  
	  public int deleteMarketingSegments(String segment){
			//Suppression d'un user de la BDD grâce à l'ID
			return database.delete(TABLE_MarketingSegments,  COL_Segment+ " LIKE \"" + segment +"\"", null);
		}
	  
	  public MarketingSegments getMarketingSegmentsWithSegment(String  segment){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_MarketingSegments, new String[] {COL_ID,COL_Dimension,COL_Segment}, COL_Segment + " LIKE \"" + segment +"\"", null, null, null, null);
			return cursorToMarketingSegments(c);
			
		}
	  
	//Cette méthode permet de convertir un cursor en un UserAccounts
		private MarketingSegments cursorToMarketingSegments(Cursor c){
			//si aucun élément n'a été retourné dans la requête, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un UserAccounts
			MarketingSegments marketingSegments = new MarketingSegments();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			marketingSegments .id=(c.getInt(NUM_COL_ID));
			marketingSegments .Dimension=(c.getInt(NUM_COL_Dimension));
			marketingSegments .Segment=(c.getString(NUM_COL_Segment));
			

			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return marketingSegments;
		}
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}

	  

}
