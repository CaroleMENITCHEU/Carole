package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerMarketingSegmentAges {
	
	private static final String TABLE_MarketingSegmentAges  = "'0203_MarketingSegmentAges'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Age = "Age";
	private static final int NUM_COL_Age = 1;
	
	// Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	  public HandlerMarketingSegmentAges (Context context){
			// prend en entrée une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertMarketingSegmentAges(MarketingSegmentAges marketingSegmentAges){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Age,marketingSegmentAges.Age);
			
		
			//on insère l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_MarketingSegmentAges, null, values);
		}
	  
	  public int updateMarketingSegmentAges(String age,MarketingSegmentAges marketingSegmentAges){
			//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Age,marketingSegmentAges.Age);
			return database.update(TABLE_MarketingSegmentAges, values,  COL_Age+ " LIKE \"" + age +"\"" , null);
		}
	  
	  public int deleteMarketingSegmentAges(String age){
			//Suppression d'un user de la BDD grâce à l'ID
			return database.delete(TABLE_MarketingSegmentAges,  COL_Age+ " LIKE \"" + age +"\"", null);
		}
	  
	  
	  public MarketingSegmentAges getMarketingSegmentAgesWithAge(String  age){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_MarketingSegmentAges, new String[] {COL_ID,COL_Age}, COL_Age+ " LIKE \"" + age +"\"", null, null, null, null);
			return cursorToMarketingSegmentAges(c);
			
		}
	  
	//Cette méthode permet de convertir un cursor en un UserAccounts
		private MarketingSegmentAges cursorToMarketingSegmentAges(Cursor c){
			//si aucun élément n'a été retourné dans la requête, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un UserAccounts
			MarketingSegmentAges marketingSegmentAges = new MarketingSegmentAges();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			marketingSegmentAges.id=(c.getInt(NUM_COL_ID));
			marketingSegmentAges.Age=(c.getString(NUM_COL_Age));
			
			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return marketingSegmentAges;
		}
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}


}
