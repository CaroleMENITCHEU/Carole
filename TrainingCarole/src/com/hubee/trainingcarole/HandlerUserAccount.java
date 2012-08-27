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
		// prend en entr�e une BD ouverte
		dbHelper = new DataBaseHelper(context);
	//	bdd=maBaseSQLite.myDataBase;
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }
	public long insertUserAccounts(UserAccounts useraccounts){
		//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		
		values.put(COL_UserId, useraccounts.userId);
		values.put(COL_Email, useraccounts.Email);
		values.put(COL_Password, useraccounts.Password);
		
		//on ins�re l'objet dans la BDD via le ContentValues
		return database.insert(TABLE_USERS, null, values);
	}
	
	public int updateUserAccounts(String  userid, UserAccounts useraccounts){
		//La mise � jour d'un user dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
		ContentValues values = new ContentValues();
		values.put(COL_UserId, useraccounts.userId);
		values.put(COL_Email, useraccounts.Email);
		values.put(COL_Password, useraccounts.Password);
	
		return database.update(TABLE_USERS, values,  COL_UserId+ " LIKE \"" + userid +"\"" , null);
	}
 
	public int deleteUserWithID(String userid){
		//Suppression d'un user de la BDD gr�ce � l'ID
		return database.delete(TABLE_USERS,  COL_UserId+ " LIKE \"" + userid +"\"", null);
	}


	public UserAccounts getUserWithUserId(String  userid){
		//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
		Cursor c =database.query(TABLE_USERS, new String[] {COL_ID, COL_UserId, COL_Email,COL_Password,COL_TabletUser}, COL_UserId+ " LIKE \"" + userid +"\"", null, null, null, null);
		return cursorToUserAccounts(c);
	}
	
	//Cette m�thode permet de convertir un cursor en un UserAccounts
		private UserAccounts cursorToUserAccounts(Cursor c){
			//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier �l�ment
			c.moveToFirst();
			//On cr�� un UserAccounts
			UserAccounts useraccount = new UserAccounts();
			//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
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
