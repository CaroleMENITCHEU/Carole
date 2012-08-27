package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerAppSegmentRanking {
	
	private static final String TABLE_AppSegmentRanking = "'0600_AppSegmentRanking'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_App= "App";
	private static final int NUM_COL_App = 1;
	private static final String COL_Segment= "Segment";
	private static final int NUM_COL_Segment = 2;
	private static final String COL_Rank = "Rank";
	private static final int NUM_COL_Rank = 3;
	
	// Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	  public HandlerAppSegmentRanking (Context context){
			// prend en entrée une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertAppSegmentRanking(AppSegmentRanking Appsegmentranking){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_App,Appsegmentranking.App);
			values.put(COL_Segment,Appsegmentranking.Segment);
			values.put(COL_Rank,Appsegmentranking.Rank);
			
		
			//on insère l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_AppSegmentRanking, null, values);
		}
	  
	  public int updateAppSegmentRanking(int app, AppSegmentRanking Appsegmentranking){
			//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
			ContentValues values = new ContentValues();
			values.put(COL_App,Appsegmentranking.App);
			values.put(COL_Segment,Appsegmentranking.Segment);
			values.put(COL_Rank,Appsegmentranking.Rank);
			return database.update(TABLE_AppSegmentRanking, values,  COL_App+ "=" + app, null);
		}
	  
	  public int deleteAppSegmentRanking(int app){
			//Suppression d'un user de la BDD grâce à la category
			return database.delete(TABLE_AppSegmentRanking,  COL_App+ "=" + app, null);
		}
	  
	  public AppSegmentRanking getAppSegmentRankingWithapp(int app){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_AppSegmentRanking, new String[] {COL_ID,COL_App,COL_Segment,COL_Rank}, COL_App + "=" + app, null, null, null, null);
			return cursorToAppSegmentRanking(c);
			
		}
	  
	//Cette méthode permet de convertir un cursor en un UserAccounts
		private AppSegmentRanking cursorToAppSegmentRanking(Cursor c){
			//si aucun élément n'a été retourné dans la requête, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un UserAccounts
			AppSegmentRanking Appsegmentranking= new AppSegmentRanking();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			Appsegmentranking.id=(c.getInt(NUM_COL_ID));
			Appsegmentranking.App=(c.getInt(NUM_COL_App));
			Appsegmentranking.Segment=(c.getInt(NUM_COL_Segment));
			Appsegmentranking.Rank=(c.getInt(NUM_COL_Rank));

			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return Appsegmentranking;
		}
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}
	  

}
