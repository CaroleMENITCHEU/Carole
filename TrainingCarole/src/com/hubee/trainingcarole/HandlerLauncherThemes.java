package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerLauncherThemes {
	
	private static final String TABLE_LauncherThemes = "'0403_LauncherThemes'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Theme= "Theme";
	private static final int NUM_COL_Theme = 1;
	
	// Database fields
		  public SQLiteDatabase database;
		  public DataBaseHelper dbHelper;
		  public HandlerLauncherThemes (Context context){
				// prend en entrée une BD ouverte
				dbHelper = new DataBaseHelper(context);
			
			}
			
		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
			  }
		  
		  public long insertLauncherThemes(LauncherThemes launcherthemes){
				//Création d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(COL_Theme,launcherthemes.Theme);
				
				//on insère l'objet dans la BDD via le ContentValues
				return database.insert(TABLE_LauncherThemes, null, values);
			}
		  
		  public int updateLauncherThemes(int theme, LauncherThemes launcherthemes){
				//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
				//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
				ContentValues values = new ContentValues();
				values.put(COL_Theme,launcherthemes.Theme);
				return database.update(TABLE_LauncherThemes, values,  COL_Theme+ "=" + theme , null);
			}
		  
		  public int deleteLauncherThemes(int theme){
				//Suppression d'un user de la BDD grâce à la category
				return database.delete(TABLE_LauncherThemes,  COL_Theme+ "=" + theme , null);
			}
		  
		  public LauncherThemes getLauncherThemes(int theme){
				//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
				Cursor c =database.query(TABLE_LauncherThemes, new String[] {COL_ID,COL_Theme}, COL_Theme + "="+ theme, null, null, null, null);
				return cursorToLauncherThemes(c);
				
			}
		  
		//Cette méthode permet de convertir un cursor en un UserAccounts
			private LauncherThemes cursorToLauncherThemes(Cursor c){
				//si aucun élément n'a été retourné dans la requête, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier élément
				c.moveToFirst();
				//On créé un UserAccounts
				LauncherThemes launcherthemes= new LauncherThemes();
				//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
				launcherthemes.id=(c.getInt(NUM_COL_ID));
				launcherthemes.Theme=(c.getInt(NUM_COL_Theme));
				
                //On ferme le cursor
				c.close();
		 
				//On retourne le livre
				return launcherthemes;
			}
		
		public void close()
		{
			  if(database != null)
		   		    database.close();
		}
		  

}
