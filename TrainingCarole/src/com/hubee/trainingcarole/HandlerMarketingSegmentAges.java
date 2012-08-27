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
			// prend en entr�e une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertMarketingSegmentAges(MarketingSegmentAges marketingSegmentAges){
			//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Age,marketingSegmentAges.Age);
			
		
			//on ins�re l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_MarketingSegmentAges, null, values);
		}
	  
	  public int updateMarketingSegmentAges(String age,MarketingSegmentAges marketingSegmentAges){
			//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Age,marketingSegmentAges.Age);
			return database.update(TABLE_MarketingSegmentAges, values,  COL_Age+ " LIKE \"" + age +"\"" , null);
		}
	  
	  public int deleteMarketingSegmentAges(String age){
			//Suppression d'un user de la BDD gr�ce � l'ID
			return database.delete(TABLE_MarketingSegmentAges,  COL_Age+ " LIKE \"" + age +"\"", null);
		}
	  
	  
	  public MarketingSegmentAges getMarketingSegmentAgesWithAge(String  age){
			//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
			Cursor c =database.query(TABLE_MarketingSegmentAges, new String[] {COL_ID,COL_Age}, COL_Age+ " LIKE \"" + age +"\"", null, null, null, null);
			return cursorToMarketingSegmentAges(c);
			
		}
	  
	//Cette m�thode permet de convertir un cursor en un UserAccounts
		private MarketingSegmentAges cursorToMarketingSegmentAges(Cursor c){
			//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier �l�ment
			c.moveToFirst();
			//On cr�� un UserAccounts
			MarketingSegmentAges marketingSegmentAges = new MarketingSegmentAges();
			//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
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
