package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerTabletNetworks {
	private static final String TABLE_TabletNetworks = "'0303_TabletNetworks'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Network = "Network";
	private static final int NUM_COL_Networks = 1;
	
	 // Database fields
	  public SQLiteDatabase database;
	  public DataBaseHelper dbHelper;
	  public HandlerTabletNetworks(Context context){
			// prend en entr�e une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
		public long insertTabletNetworks(TabletNetworks tabletnetworks){
			//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Network,tabletnetworks.Network);
		
			//on ins�re l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_TabletNetworks, null, values);
		}
		
		public int updateTabletNetworks(String  network, TabletNetworks tabletnetworks){
			//La mise � jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement pr�ciser quelle user on doit mettre � jour gr�ce � l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Network,tabletnetworks.Network);
			return database.update(TABLE_TabletNetworks, values,  COL_Network+ " LIKE \"" + network +"\"" , null);
		}
	 
		public int deleteTabletNetworksWithNetwork(String network){
			//Suppression d'un user de la BDD gr�ce � l'ID
			return database.delete(TABLE_TabletNetworks,  COL_Network+ " LIKE \"" + network +"\"", null);
		}

		public TabletNetworks getTabletNetworksWithNetwork(String  network){
			//R�cup�re dans un Cursor les valeurs correspondant � un user contenu dans la BDD (ici on s�lectionne le user gr�ce � son userID)
			Cursor c =database.query(TABLE_TabletNetworks, new String[] {COL_ID, COL_Network}, COL_Network+ " LIKE \"" + network +"\"", null, null, null, null);
			return cursorToTabletNetworks(c);
		}
		
		//Cette m�thode permet de convertir un cursor en un UserAccounts
		private TabletNetworks cursorToTabletNetworks(Cursor c){
			//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier �l�ment
			c.moveToFirst();
			//On cr�� un UserAccounts
			TabletNetworks tabletnetworks = new TabletNetworks();
			//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
			tabletnetworks.id=(c.getInt(NUM_COL_ID));
			tabletnetworks.Network=(c.getString(NUM_COL_Networks));
			

			//On ferme le cursor
			c.close();
	 
			//On retourne le livre
			return tabletnetworks;
		}
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}
 
}
