package com.hubee.trainingcarole;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerTabletUsers {
	
	private static final String TABLE_TabletUsers  = "'0300_TabletUsers'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_ScreenName= "ScreenName";
	private static final int NUM_COL_ScreenName = 1;
	private static final String COL_Password = "Password";
	private static final int NUM_COL_Password = 2;
	private static final String COL_Age = "Age";
	private static final int NUM_COL_Age = 3;
	private static final String COL_Occupation = "Occupation";
	private static final int NUM_COL_Occupation = 4;
	private static final String COL_Theme = "Theme";
	private static final int NUM_COL_Theme = 5;
	private static final String COL_Network = "Network";
	private static final int NUM_COL_Network = 6;
	
	
	// Database fields
		  public SQLiteDatabase database;
		  public DataBaseHelper dbHelper;
		  public HandlerTabletUsers (Context context){
				// prend en entr�e une BD ouverte
				dbHelper = new DataBaseHelper(context);
			
			}
			
		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
			  }
		  
		  public long insertTabletUsers(TabletUsers tabletUsers){
				//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(COL_ScreenName,tabletUsers.ScreenName);
				values.put(COL_Password ,tabletUsers.Password);
				values.put(COL_Age,tabletUsers.Age);
				values.put(COL_Occupation,tabletUsers.Occupation);
				values.put(COL_Theme,tabletUsers.Theme);
				values.put(COL_Network,tabletUsers.Network);
				//values.put(COL_Theme,tabletUsers.Theme)
				
			
				//on ins�re l'objet dans la BDD via le ContentValues
				return database.insert(TABLE_TabletUsers, null, values);
			}
		  
		  public int updateTabletUsers(String screenName,TabletUsers tabletUsers){
				//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
				//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
				ContentValues values = new ContentValues();
				values.put(COL_ScreenName,tabletUsers.ScreenName);
				values.put(COL_Password ,tabletUsers.Password);
				values.put(COL_Age,tabletUsers.Age);
				values.put(COL_Occupation,tabletUsers.Occupation);
				return database.update(TABLE_TabletUsers, values,  COL_ScreenName+ " LIKE \"" + screenName +"\"" , null);
			}
		  
		  public int deleteTabletUsers(String screenName){
				//Suppression d'un user de la BDD gr�ce � l'ID
				return database.delete(TABLE_TabletUsers,  COL_Age+ " LIKE \"" + screenName +"\"", null);
			}
		  
		 /* public TabletUsers getTabletUsersWithScreenName(String  screenName){
				//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
				Cursor c =database.query(TABLE_TabletUsers, new String[] {COL_ID,COL_ScreenName,COL_Password,COL_Age,COL_Occupation}, COL_ScreenName+ " LIKE \"" + screenName +"\"", null, null, null, null);
				return cursorToTabletUsers(c);
				
			}*/
		  
		  public ArrayList<TabletUsers> getAllTabletUsers(){
				//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
				Cursor c =database.query(TABLE_TabletUsers, new String[] {COL_ID,COL_ScreenName,COL_Password,COL_Age,COL_Occupation}, null, null, null, null, null);
				return cursorToTabletUsers(c);
				
			}
		  
		//Cette m�thode permet de convertir un cursor en un UserAccounts
			private ArrayList<TabletUsers> cursorToTabletUsers(Cursor c){
				//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
				if (c.getCount() == 0)
					return null;
		 
				//Sinon on se place sur le premier �l�ment
				c.moveToFirst();
				
				//On cr�� un UserAccounts
				ArrayList<TabletUsers> tabletUsers = new ArrayList<TabletUsers>();
				
				//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
				
				do{
					TabletUsers tampon=new TabletUsers();
					tampon.id=(c.getInt(NUM_COL_ID));
					tampon.ScreenName=(c.getString(NUM_COL_ScreenName));
					tampon.Password=(c.getString(NUM_COL_Password));
					tampon.Age=(c.getInt(NUM_COL_Age));
					tampon.Occupation=(c.getInt(NUM_COL_Occupation));
				
				tabletUsers.add(tampon);
				
				
				}while(c.moveToNext());
				
				//c.moveToFirst();
				//On ferme le cursor
				c.close();
		 
				//On retourne le livre
				return tabletUsers;
			}
		
		public void close()
		{
			  if(database != null)
		   		    database.close();
		}

		  
		  
		  
}
