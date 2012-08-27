package com.hubee.trainingcarole;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HandlerAppCatalog {

	private static final String TABLE_APP_CATALOG = "'0501_AppCatalog'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_URI = "URI";
	private static final int NUM_COL_URI= 1;
	private static final String COL_App = "App";
	private static final int NUM_COL_App=2;
	private static final String COL_LocalCategoryNAME = "LocalCategoryNAME";
	private static final int NUM_COL_LocalCategoryNAME=3;
	private static final String COL_Added = "Added";
	private static final int NUM_COL_Added=4;
	private static final String COL_Updated = "Updated ";
	private static final int NUM_COL_Updated  = 5;
	private static final String COL_RankGlobal = "RankGlobal";
	private static final int NUM_COL_RankGlobal=6;
	private static final String COL_PartnerCode= "PartnerCode";
	private static final int NUM_COL_PartnerCode=7;
	private static final String COL_RankSenior = "RankSenior";
	private static final int NUM_COL_RankSenior=8;
	private static final String COL_RankAdult = "RankAdult";
	private static final int NUM_COL_RankAdult=9;
	private static final String COL_RankTeenAger = "RankTeenAger";
	private static final int NUM_COL_RankTeenAger=10;
	private static final String COL_RankKid = "RankKid";
	private static final int NUM_COL_RankKid=11;
	private static final String COL_RankSelfEmployed = "RankSelfEmployed";
	private static final int NUM_COL_RankSelfEmployed=12;
	private static final String COL_RankHouseHusband = "RankHouseHusband";
	private static final int NUM_COL_RankHouseHusband=13;
	private static final String COL_RankStudent = "RankStudent";
	private static final int NUM_COL_RankStudent=14;
	private static final String COL_RankTeleWorker = "RankTeleWorker";
	private static final int NUM_COL_RankTeleWorker=15;
	private static final String COL_RankEmployee = "RankEmployee";
	private static final int NUM_COL_RankEmployee=16;
	private static final String COL_RankHousewife = "RankHousewife";
	private static final int NUM_COL_RankHousewife=17;
	private static final String COL_RankRetired = "RankRetired ";
	private static final int NUM_COL_RankRetired =18;
	private static final String COL_RankJobless = "RankJobless";
	private static final int NUM_COL_RankJobless=19;
	
	 public SQLiteDatabase database;
	 public DataBaseHelper dbHelper;
	 
	 public HandlerAppCatalog(Context context){
			
			dbHelper = new DataBaseHelper(context);
		
		}
		//ouverture de la BD
		public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
		
		public long insertAppCatalog(AppCatalog appCatalog){
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			
			values.put(COL_URI , appCatalog.URI);
			values.put( COL_App, appCatalog.APP);
			values.put(COL_LocalCategoryNAME,appCatalog.LocalCategoryNAME);
			values.put(COL_Added ,appCatalog.Added);
			values.put(COL_Updated  ,appCatalog.Updated);
			values.put( COL_RankGlobal, appCatalog.RankGlobal);
			values.put(COL_PartnerCode, appCatalog.PartnerCode);
			values.put(COL_RankSenior , appCatalog.RankSenior);
			values.put(COL_RankAdult  , appCatalog.RankAdult);
			values.put( COL_RankTeenAger,appCatalog.RankTeenAger);
			values.put(COL_RankKid, appCatalog.RankKid);
			values.put(COL_RankSelfEmployed,appCatalog.RankSelfEmployed);
			values.put(COL_RankHouseHusband , appCatalog.RankHouseHusband);
			values.put(COL_RankStudent, appCatalog.RankStudent);
			values.put(COL_RankTeleWorker , appCatalog.RankTeleworker);
			values.put(COL_RankEmployee , appCatalog.RankEmployee);
			values.put(COL_RankHousewife , appCatalog.RankHousewife);
			values.put(COL_RankRetired , appCatalog.RankRetired);
			values.put(COL_RankJobless , appCatalog.RankJobless);
			
			//on insère l'objet dans la BDD via le ContentValues
			return database.insert(TABLE_APP_CATALOG , null, values);
		}
		
		public int updateAppCatalog(String  uri, AppCatalog appCatalog){
			//La mise à jour d'un appCatalog dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle appCatalog on doit mettre à jour grâce à son URI
			ContentValues values = new ContentValues();
			values.put(COL_URI , appCatalog.URI);
			values.put( COL_App, appCatalog.APP);
			values.put(COL_LocalCategoryNAME,appCatalog.LocalCategoryNAME);
			values.put(COL_Added ,appCatalog.Added);
			values.put(COL_Updated  ,appCatalog.Updated);
			values.put( COL_RankGlobal, appCatalog.RankGlobal);
			values.put(COL_PartnerCode, appCatalog.PartnerCode);
			values.put(COL_RankSenior , appCatalog.RankSenior);
			values.put(COL_RankAdult  , appCatalog.RankAdult);
			values.put( COL_RankTeenAger,appCatalog.RankTeenAger);
			values.put(COL_RankKid, appCatalog.RankKid);
			values.put(COL_RankSelfEmployed,appCatalog.RankSelfEmployed);
			values.put(COL_RankHouseHusband , appCatalog.RankHouseHusband);
			values.put(COL_RankStudent, appCatalog.RankStudent);
			values.put(COL_RankTeleWorker , appCatalog.RankTeleworker);
			values.put(COL_RankEmployee , appCatalog.RankEmployee);
			values.put(COL_RankHousewife , appCatalog.RankHousewife);
			values.put(COL_RankRetired , appCatalog.RankRetired);
			values.put(COL_RankJobless , appCatalog.RankJobless);
			return database.update(TABLE_APP_CATALOG, values,  COL_URI+ " LIKE \"" + uri +"\"" , null);
		}
		
		public int updateAppCatalog(int  id, AppCatalog appCatalog){
			//La mise à jour d'un appCatalog dans la BDD fonctionne plus ou moins comme une insertion
			//il faut simplement préciser quelle appCatalog on doit mettre à jour grâce à son URI
			ContentValues values = new ContentValues();
			values.put(COL_URI , appCatalog.URI);
			values.put( COL_App, appCatalog.APP);
			values.put(COL_LocalCategoryNAME,appCatalog.LocalCategoryNAME);
			values.put(COL_Added ,appCatalog.Added);
			values.put(COL_Updated  ,appCatalog.Updated);
			values.put( COL_RankGlobal, appCatalog.RankGlobal);
			values.put(COL_PartnerCode, appCatalog.PartnerCode);
			values.put(COL_RankSenior , appCatalog.RankSenior);
			values.put(COL_RankAdult  , appCatalog.RankAdult);
			values.put( COL_RankTeenAger,appCatalog.RankTeenAger);
			values.put(COL_RankKid, appCatalog.RankKid);
			values.put(COL_RankSelfEmployed,appCatalog.RankSelfEmployed);
			values.put(COL_RankHouseHusband , appCatalog.RankHouseHusband);
			values.put(COL_RankStudent, appCatalog.RankStudent);
			values.put(COL_RankTeleWorker , appCatalog.RankTeleworker);
			values.put(COL_RankEmployee , appCatalog.RankEmployee);
			values.put(COL_RankHousewife , appCatalog.RankHousewife);
			values.put(COL_RankRetired , appCatalog.RankRetired);
			values.put(COL_RankJobless , appCatalog.RankJobless);
			return database.update(TABLE_APP_CATALOG, values,  COL_ID + " = " + id , null);
		}
		
		public int deleteAppCatalogWithUri(String uri){
			//Suppression d'un user de la BDD grâce à l'ID
			return database.delete(TABLE_APP_CATALOG,  COL_URI+ " LIKE \"" + uri +"\"", null);
		}
		
		public int deleteAppCatalogWithID(int  id){
			//Suppression d'un user de la BDD grâce à l'ID
			return database.delete(TABLE_APP_CATALOG,  COL_ID+ " =" + id, null);
		}
		
		public AppCatalog getAppCatalogWithUri(String uri){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_APP_CATALOG, new String[] {COL_ID, COL_URI, COL_App,COL_LocalCategoryNAME,COL_Added,COL_Updated,COL_RankGlobal,COL_PartnerCode,COL_RankSenior,COL_RankAdult ,COL_RankTeenAger,COL_RankKid,COL_RankSelfEmployed,COL_RankHouseHusband ,COL_RankStudent,COL_RankTeleWorker,COL_RankEmployee,COL_RankHousewife,COL_RankRetired,COL_RankJobless}, COL_URI+ " LIKE \"" + uri +"\"", null, null, null, null);
			return cursorToAppCatalog(c);
		}
		
		public AppCatalog getAppCatalogWithUri(int  id){
			//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_APP_CATALOG, new String[] {COL_ID, COL_URI, COL_App,COL_LocalCategoryNAME,COL_Added,COL_Updated,COL_RankGlobal,COL_PartnerCode,COL_RankSenior,COL_RankAdult ,COL_RankTeenAger,COL_RankKid,COL_RankSelfEmployed,COL_RankHouseHusband ,COL_RankStudent,COL_RankTeleWorker,COL_RankEmployee,COL_RankHousewife,COL_RankRetired,COL_RankJobless}, COL_ID+ " =" + id, null, null, null, null);
			return cursorToAppCatalog(c);
		}
		
		//Cette méthode permet de convertir un cursor en un AppCatalog
				private AppCatalog cursorToAppCatalog(Cursor c){
					//si aucun élément n'a été retourné dans la requête, on renvoie null
					if (c.getCount() == 0)
						return null;
			 
					//Sinon on se place sur le premier élément
					c.moveToFirst();
					//On créé un UserAccounts
					AppCatalog appcatalog = new AppCatalog();
					//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
					appcatalog.id=(c.getInt(NUM_COL_ID));
					appcatalog.URI=(c.getString(NUM_COL_URI));
					appcatalog.APP=(c.getString(NUM_COL_App));
					appcatalog.LocalCategoryNAME=(c.getString(NUM_COL_LocalCategoryNAME));
					appcatalog.Added=(c.getString(NUM_COL_Added));
					appcatalog.Updated=(c.getString(NUM_COL_Updated));
					appcatalog.RankGlobal=(c.getInt(NUM_COL_RankGlobal));
					appcatalog.PartnerCode=(c.getString(NUM_COL_PartnerCode));
					appcatalog.RankSenior=(c.getInt(NUM_COL_RankSenior));
					appcatalog.RankAdult=(c.getInt(NUM_COL_RankAdult));
					appcatalog.RankTeenAger=(c.getInt(NUM_COL_RankTeenAger));
					appcatalog.RankKid=(c.getInt(NUM_COL_RankKid));
					appcatalog.RankSelfEmployed=(c.getInt(NUM_COL_RankSelfEmployed));
					appcatalog.RankHouseHusband=(c.getInt(NUM_COL_RankHouseHusband));
					appcatalog.RankStudent=(c.getInt(NUM_COL_RankStudent));
					appcatalog.RankTeleworker=(c.getInt(NUM_COL_RankTeleWorker));
					appcatalog.RankEmployee=(c.getInt(NUM_COL_RankEmployee));
					appcatalog.RankHousewife=(c.getInt(NUM_COL_RankHousewife));
					appcatalog.RankRetired=(c.getInt(NUM_COL_RankRetired));
					appcatalog.RankJobless=(c.getInt(NUM_COL_RankJobless));
					


					//On ferme le cursor
					c.close();
			 
					//On retourne le livre
					return appcatalog;
				}
			
			public void close()
			{
				  if(database != null)
			   		    database.close();
			}
		
}
