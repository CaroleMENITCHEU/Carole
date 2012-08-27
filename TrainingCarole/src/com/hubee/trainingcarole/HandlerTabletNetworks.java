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
			// prend en entrée une BD ouverte
			dbHelper = new DataBaseHelper(context);
		
		}
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
		public long insertTabletNetworks(TabletNetworks tabletnetworks){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_Network,tabletnetworks.Network);
		
			//on insère l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_TabletNetworks, null, values);
		}
		
		public int updateTabletNetworks(String  network, TabletNetworks tabletnetworks){
			//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
			ContentValues values = new ContentValues();
			values.put(COL_Network,tabletnetworks.Network);
			return database.update(TABLE_TabletNetworks, values,  COL_Network+ " LIKE \"" + network +"\"" , null);
		}
	 
		public int deleteTabletNetworksWithNetwork(String network){
			//Suppression d'un user de la BDD grâce à l'ID
			return database.delete(TABLE_TabletNetworks,  COL_Network+ " LIKE \"" + network +"\"", null);
		}

		public TabletNetworks getTabletNetworksWithNetwork(String  network){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_TabletNetworks, new String[] {COL_ID, COL_Network}, COL_Network+ " LIKE \"" + network +"\"", null, null, null, null);
			return cursorToTabletNetworks(c);
		}
		
		//Cette méthode permet de convertir un cursor en un UserAccounts
		private TabletNetworks cursorToTabletNetworks(Cursor c){
			//si aucun élément n'a été retourné dans la requête, on renvoie null
			if (c.getCount() == 0)
				return null;
	 
			//Sinon on se place sur le premier élément
			c.moveToFirst();
			//On créé un UserAccounts
			TabletNetworks tabletnetworks = new TabletNetworks();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
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
