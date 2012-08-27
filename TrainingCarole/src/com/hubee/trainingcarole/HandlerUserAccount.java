package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerUserAccount {
	

	private static final String TABLE_USERS = "'0301_UserAccounts'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_UserId = "UserId";
	private static final int NUM_COL_UserId = 1;
	private static final String COL_Email = "Email";
	private static final int NUM_COL_Email=2;
	private static final String COL_Password = "Password";
	private static final int NUM_COL_Password=3;
	private static final String COL_TabletUser = "TabletUser";
	private static final int NUM_COL_TabletUser=4;

	 // Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	
	public HandlerUserAccount(Context context){
		// prend en entrée une BD ouverte
		dbHelper = new DataBaseHelper(context);
	//	bdd=maBaseSQLite.myDataBase;
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }
	public long insertUserAccounts(UserAccounts useraccounts){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		
		values.put(COL_UserId, useraccounts.userId);
		values.put(COL_Email, useraccounts.Email);
		values.put(COL_Password, useraccounts.Password);
		
		//on insère l'objet dans la BDD via le ContentValues
		return database.insert(TABLE_USERS, null, values);
	}
	
	public int updateUserAccounts(String  userid, UserAccounts useraccounts){
		//La mise à jour d'un user dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
		ContentValues values = new ContentValues();
		values.put(COL_UserId, useraccounts.userId);
		values.put(COL_Email, useraccounts.Email);
		values.put(COL_Password, useraccounts.Password);
	
		return database.update(TABLE_USERS, values,  COL_UserId+ " LIKE \"" + userid +"\"" , null);
	}
 
	public int deleteUserWithID(String userid){
		//Suppression d'un user de la BDD grâce à l'ID
		return database.delete(TABLE_USERS,  COL_UserId+ " LIKE \"" + userid +"\"", null);
	}


	public UserAccounts getUserWithUserId(String  userid){
		//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
		Cursor c =database.query(TABLE_USERS, new String[] {COL_ID, COL_UserId, COL_Email,COL_Password,COL_TabletUser}, COL_UserId+ " LIKE \"" + userid +"\"", null, null, null, null);
		return cursorToUserAccounts(c);
	}
	
	//Cette méthode permet de convertir un cursor en un UserAccounts
		private UserAccounts cursorToUserAccounts(Cursor c){
			//si aucun élément n'a été retourné dans la requête, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un UserAccounts
			UserAccounts useraccount = new UserAccounts();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			useraccount.id=(c.getInt(NUM_COL_ID));
			useraccount.userId=(c.getString(NUM_COL_UserId));
			useraccount.Email=(c.getString(NUM_COL_Email));
			useraccount.Password=(c.getString(NUM_COL_Password));
			


			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return useraccount;
		}
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}
 
}
