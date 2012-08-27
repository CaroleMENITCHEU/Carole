package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerMarketingSegmentsGender {
	private static final String TABLE_MarketingSegmentsGender = "'0205_MarketingSegmentsGender'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Sex = "Sex";
	private static final int NUM_COL_Sex = 1;
	
	// Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	  public HandlerMarketingSegmentsGender (Context context){
			// prend en entrée une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertMarketingSegmentsGender(MarketingSegmentsGender marketingsegmentsGender){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Sex,marketingsegmentsGender.Sex);
		
			//on insère l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_MarketingSegmentsGender, null, values);
		}
	  
	  public int updateMarketingSegmentsGender(String  sex, MarketingSegmentsGender marketingsegmentsGender){
			//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Sex,marketingsegmentsGender.Sex);
			return database.update(TABLE_MarketingSegmentsGender, values,  COL_Sex+ " LIKE \"" + sex +"\"" , null);
		}
	  
	  public int deleteMarketingSegmentsGender(String sex){
			//Suppression d'un user de la BDD grâce à l'ID
			return database.delete(TABLE_MarketingSegmentsGender,  COL_Sex+ " LIKE \"" + sex +"\"", null);
		}
		
	  
	  public MarketingSegmentsGender getMarketingSegmentsGenderWithSex(String  sex){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_MarketingSegmentsGender, new String[] {COL_ID,COL_Sex}, COL_Sex + " LIKE \"" + sex +"\"", null, null, null, null);
			return cursorToMarketingSegmentsGender(c);
			
		}
	  
	  
	//Cette méthode permet de convertir un cursor en un UserAccounts
		private MarketingSegmentsGender cursorToMarketingSegmentsGender(Cursor c){
			//si aucun élément n'a été retourné dans la requête, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un UserAccounts
			MarketingSegmentsGender marketingSegmentsGender = new MarketingSegmentsGender();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			marketingSegmentsGender.id=(c.getInt(NUM_COL_ID));
			marketingSegmentsGender.Sex=(c.getString(NUM_COL_Sex));
			

			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return marketingSegmentsGender;
		}
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}




}
