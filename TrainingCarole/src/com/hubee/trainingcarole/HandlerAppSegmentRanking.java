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
			// prend en entr�e une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public long insertAppSegmentRanking(AppSegmentRanking Appsegmentranking){
			//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_App,Appsegmentranking.App);
			values.put(COL_Segment,Appsegmentranking.Segment);
			values.put(COL_Rank,Appsegmentranking.Rank);
			
		
			//on ins�re l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_AppSegmentRanking, null, values);
		}
	  
	  public int updateAppSegmentRanking(int app, AppSegmentRanking Appsegmentranking){
			//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
			ContentValues values = new ContentValues();
			values.put(COL_App,Appsegmentranking.App);
			values.put(COL_Segment,Appsegmentranking.Segment);
			values.put(COL_Rank,Appsegmentranking.Rank);
			return database.update(TABLE_AppSegmentRanking, values,  COL_App+ "=" + app, null);
		}
	  
	  public int deleteAppSegmentRanking(int app){
			//Suppression d'un user de la BDD gr�ce � la category
			return database.delete(TABLE_AppSegmentRanking,  COL_App+ "=" + app, null);
		}
	  
	  public AppSegmentRanking getAppSegmentRankingWithapp(int app){
			//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
			Cursor c =database.query(TABLE_AppSegmentRanking, new String[] {COL_ID,COL_App,COL_Segment,COL_Rank}, COL_App + "=" + app, null, null, null, null);
			return cursorToAppSegmentRanking(c);
			
		}
	  
	//Cette m�thode permet de convertir un cursor en un UserAccounts
		private AppSegmentRanking cursorToAppSegmentRanking(Cursor c){
			//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier �l�ment
			c.moveToFirst();
			//On cr�� un UserAccounts
			AppSegmentRanking Appsegmentranking= new AppSegmentRanking();
			//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
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
