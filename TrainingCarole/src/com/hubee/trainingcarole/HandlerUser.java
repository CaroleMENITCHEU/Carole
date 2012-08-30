package com.hubee.trainingcarole;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class HandlerUser {

	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.hubee.trainingcarole/databases/";
	private static String DB_NAME = "HHLauncher07.db";
	// tabletUsers elements
	private static final String TABLE_TabletUsers = "'0300_TabletUsers'";
	private static final String COL_IDTablet = "_ID";
	private static final int NUM_COL_IDTablet = 0;
	private static final String COL_ScreenName = "ScreenName";
	private static final int NUM_COL_ScreenName = 1;
	private static final String COL_PasswordTablet = "Password";
	private static final int NUM_COL_PasswordTablet = 2;
	private static final String COL_gender = "Gender";
	private static final int NUM_COL_gender = 3;
	private static final String COL_AgeTablet = "Age";
	private static final int NUM_COL_AgeTablet = 4;
	private static final String COL_OccupationTablet = "Occupation";
	private static final int NUM_COL_OccupationTablet = 5;
	private static final String COL_Theme = "Theme";
	private static final int NUM_COL_Theme = 6;
	private static final String COL_Network = "Network";
	private static final int NUM_COL_Network = 7;
	// userAccounts elements
	private static final String TABLE_USERS = "'0302_UserAccounts'";
	private static final String COL_IDUser = "_ID";
	private static final int NUM_COL_IDUser = 0;
	private static final String COL_UserId = "UserId";
	private static final int NUM_COL_UserId = 1;
	private static final String COL_Email = "Email";
	private static final int NUM_COL_Email = 2;
	private static final String COL_PasswordUser = "Password";
	private static final int NUM_COL_PasswordUser = 3;
	private static final String COL_TabletUser = "TabletUser";
	private static final int NUM_COL_TabletUser = 4;

	// UserAges
	private static final String TABLE_UserAges = "'0305_UserAges'";
	private static final String COL_IDage = "_ID";
	private static final int NUM_COL_IDage = 0;
	private static final String COL_segmentage = "Segment";
	private static final int NUM_COL_Segmentage = 1;

	// UserOccupations
	private static final String TABLE_UserOccupations = "'0306_UserOccupations'";
	private static final String COL_IDOccupations = "_ID";
	private static final int NUM_COL_IDOccupations = 0;
	private static final String COL_segment = "Segment";
	private static final int NUM_COL_segment = 1;

	// useGender

	private static final String TABLE_UserGenders = "'0304_UserGenders'";
	private static final String COL_IDgender = "_ID";
	private static final int NUM_COL_IDgender = 0;
	private static final String COL_Segmentgender = "Segment";
	private static final int NUM_COL_Segmentgender = 1;

	// appselection elements
	private static final String TABLE_App_Selection = "'0500_AppSelection'";
	private static final String COL_IDselection = "_ID";
	private static final int NUM_COL_IDselection = 0;
	private static final String COL_TabletUserselection = "TabletUser";
	private static final int NUM_COL_TabletUserselection = 1;
	private static final String COL_Addedselection = "Added";
	private static final int NUM_COL_Addedselection=2;
	private static final String COL_Updatedselection = "Updated";
	private static final int NUM_COL_Updatedselection=3;
	private static final String COL_AppSelection = "App";
	private static final int NUM_COL_AppSelection=4;
	private static final String COL_StatusSelection = "COL_Status";
	private static final int NUM_COL_COL_StatusSelection=5;
	// appstatus elements
	private static final String TABLE_AppStatus = "'0502_AppStatus'";
	private static final String COL_IDStatus = "_ID";
	private static final int NUM_COL_IDStatus = 0;
	private static final String COL_Status = "Status";
	private static final int NUM_COL_Status = 1;
	private static final String COL_Affinity = "Affinity";
	private static final int NUM_COL_Affinity = 2;
	// appCatalog elements
	private static final String TABLE_APP_CATALOG = "'0501_AppCatalog'";
	private static final String COL_IDCATALOG = "_ID";
	private static final int NUM_COL_IDCATALOG = 0;
	private static final String COL_URI = "URI";
	private static final int NUM_COL_URI = 1;
	private static final String COL_App = "App";
	private static final int NUM_COL_App = 2;
	private static final String COL_LocalCategoryNAME = "LocalCategoryNAME";
	private static final int NUM_COL_LocalCategoryNAME = 3;
	private static final String COL_Added = "Added";
	private static final int NUM_COL_Added = 4;
	private static final String COL_Updated = "Updated ";
	private static final int NUM_COL_Updated = 5;
	private static final String COL_RankGlobal = "RankGlobal";
	private static final int NUM_COL_RankGlobal = 6;
	private static final String COL_PartnerCode = "PartnerCode";
	private static final int NUM_COL_PartnerCode = 7;

	/* appSegmentRanking */
	private static final String TABLE_AppSegmentRanking = "'0502_AppSegmentRanking'";
	private static final String COL_IDappSegRan = "_ID";
	private static final int NUM_COL_IDappSegRan = 0;
	private static final String COL_AppappSegRan = "App";
	private static final int NUM_COL_AppappSegRan = 1;
	private static final String COL_Segment = "Segment";
	private static final int NUM_COL_Segment = 2;
	private static final String COL_Rank = "Rank";
	private static final int NUM_COL_Rank = 3;
	/* marketingSegment */
	private static final String TABLE_MarketingSegments = "'0201_MarketingSegments'";
	private static final String COL_IDMarketingSegments = "_ID";
	private static final int NUM_COL_IDMarketingSegments = 0;
	private static final String COL_Dimension = "Dimension";
	private static final int NUM_COL_Dimension = 1;
	private static final String COL_SegmentMarketingSegments = "Segment";
	private static final int NUM_COL_SegmentMarketingSegments = 2;
	// localAppCategorie element
	private static final String TABLE_LocalAppCategories = "'0505_LocalAppCategories'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Country = "Country";
	private static final int NUM_COL_Country = 1;
	private static final String COL_Category = "Category";
	private static final int NUM_COL_Category = 2;
	private static final String COL_rank = "rank";
	private static final int NUM_COL_rank = 3;

	public Context mycontext;

	// Database fields
	public SQLiteDatabase database = null;
	public DataBaseHelper dbHelper;

	public HandlerUser(Context context) {
		// prend en entrée une BD ouverte
		dbHelper = new DataBaseHelper(context);
		mycontext = context;

	}

	/****************** copy de la BD si elle n'existe pas **************************/

	/*
	 * Creates an empty database on the system and rewrites it with your own
	 * database.
	 */

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
			open();
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			dbHelper.getWritableDatabase();

			try {

				copyDataBase();
				openDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/*
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */

	private boolean checkDataBase() {

		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();

	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = mycontext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		database = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);

	}

	/***************************** fin de la copie ****************************************/

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void createUser(User u) {
		ContentValues valuesUserAccount = new ContentValues();
		ContentValues valuesTabletUser = new ContentValues();
		Cursor c1 = database.query(TABLE_UserAges, new String[] { COL_IDage },
				COL_segmentage + " LIKE \"" + u.age + "\"", null, null, null,
				null);

		// insertion des elements tabletuser
		// valuesTabletUser.put(COL_IDTablet,u.id);
		valuesTabletUser.put(COL_ScreenName, u.screenName);
		valuesTabletUser.put(COL_PasswordTablet, u.passwordTabletUsers);
		// valuesTabletUser.put(COL_Network,u.network);
		// valuesTabletUser.put(COL_Theme,u.theme);
		Cursor cal = database.query(TABLE_UserGenders, new String[] {
				COL_IDgender, COL_Segmentgender }, COL_Segmentgender
				+ " LIKE \"" + u.gender + "\"", null, null, null, null);
		cal.moveToFirst();
		valuesTabletUser.put(COL_gender, cal.getInt(NUM_COL_IDgender));
		c1.moveToFirst();
		valuesTabletUser.put(COL_AgeTablet, c1.getInt(0));
		Cursor c2 = database.query(TABLE_UserOccupations,
				new String[] { COL_IDOccupations }, COL_Segment + " LIKE \""
						+ u.occupation + "\"", null, null, null, null);
		c2.moveToFirst();
		valuesTabletUser.put(COL_OccupationTablet, c2.getInt(0));
		// on insère l'objet dans la BDD via le ContentValues
		database.insert(TABLE_TabletUsers, null, valuesTabletUser);

		// insertion des elements useraccount
		for (int i = 0; i < u.accounts.size(); i++) {
			valuesUserAccount.put(COL_UserId, u.accounts.get(i).userId);
			valuesUserAccount.put(COL_Email, u.accounts.get(i).Email);
			valuesUserAccount.put(COL_PasswordUser, u.accounts.get(i).Password);
			Cursor c = database.query(TABLE_TabletUsers,
					new String[] { COL_IDTablet }, COL_PasswordTablet
							+ " LIKE \"" + u.passwordTabletUsers + "\"", null,
					null, null, null);
			c.moveToFirst();
			valuesUserAccount.put(COL_TabletUser, c.getInt(NUM_COL_IDTablet));
			database.insert(TABLE_USERS, null, valuesUserAccount);
		}

	}

	public User getUser(int id) {
		User u = new User();

		// Récupère dans un Cursor les valeurs correspondant à un user contenu
		// dans la BDD (ici on sélectionne le user grâce à son userID)
		Cursor c = database.query(TABLE_USERS, new String[] { COL_IDUser,
				COL_UserId, COL_Email, COL_PasswordUser }, COL_TabletUser + "="
				+ id, null, null, null, null);

		c.moveToFirst();

		do {
			UserAccounts user = new UserAccounts();
			user.id = c.getInt(NUM_COL_IDUser);
			user.userId = c.getString(NUM_COL_UserId);
			user.Email = c.getString(NUM_COL_Email);
			user.Password = c.getString(NUM_COL_PasswordUser);
			u.accounts.add(user);

		} while (c.moveToNext());
		c.close();

		Cursor c1 = database.query(TABLE_TabletUsers, new String[] {
				COL_IDTablet, COL_ScreenName, COL_PasswordTablet, COL_gender,
				COL_AgeTablet, COL_OccupationTablet, COL_Theme, COL_Network },
				COL_IDTablet + "=" + id, null, null, null, null);
		c1.moveToFirst();
		u.id = c1.getInt(NUM_COL_IDTablet);
		u.screenName = c1.getString(NUM_COL_ScreenName);
		u.passwordTabletUsers = c1.getString(NUM_COL_PasswordTablet);
		u.theme = c1.getInt(NUM_COL_Theme);

		Cursor m = database.query(TABLE_UserAges, new String[] { COL_IDage,
				COL_segmentage },
				COL_IDage + "=" + c1.getInt(NUM_COL_AgeTablet), null, null,
				null, null);
		m.moveToFirst();
		u.age = m.getString(NUM_COL_Segmentage);

		Cursor occ = database.query(TABLE_UserOccupations,
				new String[] { COL_Segment },
				COL_IDOccupations + "=" + c1.getInt(NUM_COL_OccupationTablet),
				null, null, null, null);
		occ.moveToFirst();
		u.occupation = occ.getString(0);

		Cursor l = database.query(TABLE_UserGenders, new String[] {
				COL_IDgender, COL_Segmentgender },
				COL_IDgender + "=" + c1.getInt(NUM_COL_gender), null, null,
				null, null);
		l.moveToFirst();
		u.gender = l.getString(NUM_COL_Segmentgender);
		occ.close();
		m.close();
		l.close();
		c1.close();

		return u;
	}

	public ArrayList<User> getAllUsers() {

		ArrayList<User> list = new ArrayList<User>();
		Cursor id = database.query(TABLE_TabletUsers,
				new String[] { COL_IDTablet }, null, null, null, null, null);
		id.moveToFirst();

		for (int i = 1; i <= id.getCount(); i++) {

			list.add(getUser(id.getInt(0)));

		}

		return list;
	}

	public void updateUserName(int id, String name) {

		// La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou
		// moins comme une insertion
		// il faut simplement préciser quelle user on doit mettre à jour grâce à
		// l'userID
		ContentValues values = new ContentValues();
		values.put(COL_ScreenName, name);

		database.update(TABLE_TabletUsers, values, COL_IDTablet + "=" + id,
				null);
	}

	public void updateUserPassword(int id, String password) {

		ContentValues values = new ContentValues();
		values.put(COL_PasswordTablet, password);

		database.update(TABLE_TabletUsers, values, COL_IDTablet + "=" + id,
				null);
	}

	public void updateUserOccupation(int id, String occupation) {
		ContentValues values = new ContentValues();
		Cursor occup = database.query(TABLE_UserOccupations,
				new String[] { COL_IDOccupations }, COL_Segment + " LIKE \""
						+ occupation + "\"", null, null, null, null);
		occup.moveToFirst();
		values.put(COL_OccupationTablet, occup.getInt(0));
		database.update(TABLE_TabletUsers, values, COL_IDTablet + "=" + id,
				null);

	}

	public void updateUserAge(int id, String age) {

		ContentValues values = new ContentValues();
		Cursor occup = database.query(TABLE_UserAges,
				new String[] { COL_IDage }, COL_segmentage + " LIKE \"" + age
						+ "\"", null, null, null, null);
		occup.moveToFirst();
		values.put(COL_AgeTablet, occup.getInt(0));
		database.update(TABLE_TabletUsers, values, COL_IDTablet + "=" + id,
				null);

	}

	public void deleteUser(int id) {

		database.delete(TABLE_TabletUsers, COL_IDTablet + "=" + id, null);
		database.delete(TABLE_USERS, COL_TabletUser + "=" + id, null);
		database.delete(TABLE_App_Selection, COL_TabletUserselection + "=" + id, null);

	}

	public ArrayList<AppCatalog> getApplications(String age,String occupation) {
		//User u = getUser(user);
		ArrayList<AppCatalog> myapps = new ArrayList<AppCatalog>();
		Cursor req = database.rawQuery("SELECT "+TABLE_APP_CATALOG +"."+COL_IDCATALOG+","+ TABLE_APP_CATALOG +"."
				+ COL_URI+","+TABLE_APP_CATALOG + "."
				+ COL_App+","+ TABLE_APP_CATALOG +"."
				+ COL_LocalCategoryNAME+","+ TABLE_APP_CATALOG +"."
				+ COL_RankGlobal+" FROM "
				+ TABLE_MarketingSegments +","+ TABLE_AppSegmentRanking +","
				+ TABLE_APP_CATALOG + " WHERE " + TABLE_APP_CATALOG +"."
				+ COL_IDCATALOG + "=" + TABLE_AppSegmentRanking +"."
				+ COL_AppappSegRan + " AND " + COL_Rank + " >= " + 4 + " AND "
				+ TABLE_AppSegmentRanking + "." + COL_Segment + " = "
				+ TABLE_MarketingSegments + "." + COL_IDMarketingSegments
				+ " AND " + TABLE_MarketingSegments + "."
				+ COL_SegmentMarketingSegments + " LIKE \"" + age + "\""
				+ " INTERSECT "+" SELECT "+TABLE_APP_CATALOG +"."+COL_IDCATALOG +","+ TABLE_APP_CATALOG + "."
				+ COL_URI+","+TABLE_APP_CATALOG + "."
				+ COL_App+","+ TABLE_APP_CATALOG + "."
				+ COL_LocalCategoryNAME+","+ TABLE_APP_CATALOG + "."
				+ COL_RankGlobal+" FROM "
				+ TABLE_MarketingSegments + "," + TABLE_AppSegmentRanking + ","
				+ TABLE_APP_CATALOG + " WHERE "  + TABLE_MarketingSegments + "."
				+ COL_SegmentMarketingSegments + " LIKE \"" + occupation
				+ "\"" + " AND " + TABLE_AppSegmentRanking + "." + COL_Rank
				+ " >= " + 4 + " AND " + TABLE_AppSegmentRanking + "."
				+ COL_Segment + "=" + TABLE_MarketingSegments + "."
				+ COL_IDMarketingSegments + " AND " + TABLE_APP_CATALOG + "."
				+ COL_IDCATALOG + "=" + TABLE_AppSegmentRanking + '.'
				+ COL_AppappSegRan, null);
		req.moveToFirst();
		
		System.out.println(req.getCount());
		do {

			AppCatalog appcat = new AppCatalog();
			appcat.id = req.getInt(0);
			appcat.URI = req.getString(1);
			appcat.APP = req.getString(2);
			appcat.LocalCategoryNAME = req.getString(3);
			appcat.RankGlobal = req.getInt(4);
			

			myapps.add(appcat);
                   
		} while (req.moveToNext());
		req.close();
		return myapps;
	}

	public ArrayList<Category> AppGroupByCatagories(String age,String occupation) {
		ArrayList<AppCatalog> app = getApplications(age,occupation);
		ArrayList<Category> categories = new ArrayList<Category>();
		
		Cursor c = database.query(TABLE_LocalAppCategories,
				new String[] { COL_Category }, null, null, null, null, null);
		c.moveToFirst();

		do {
			Category cat = new Category();
			
			cat.name = c.getString(0);
			for (int i = 0; i < app.size(); i++) {
				if ((cat.name).equals(app.get(i).LocalCategoryNAME)) {
							cat.apps.add(app.get(i));
				}
				
			}
			
			
			categories.add(cat);

		} while (c.moveToNext());
		return categories;
	}
	
	
	//gestion de la table  AppSelection
	
	public void CreateAppSelection(ArrayList<Category> list,int userId){
		for(int i=0;i<list.size();i++){
			for(int j=0;j<list.get(i).apps.size();j++){
			//Création d'un ContentValues 
			ContentValues values = new ContentValues();
		
		values.put(COL_TabletUserselection , userId);	
		values.put(COL_AppSelection, list.get(i).apps.get(j).id);
			//on insère l'objet dans la BDD via le ContentValues
			database.insert(TABLE_App_Selection, null, values);
			}
		}
	}
	
	public void DeleteAppSelection(int UserId,int APP){
		database.delete(TABLE_App_Selection, COL_TabletUserselection + "=" + UserId+" AND "+COL_AppSelection+"="+APP, null);
	}
 
	
	public ArrayList<Category> ReadAppSelection(int UserId){
		ArrayList<Category> myCatList =new ArrayList<Category>();
		//recuperation des ID des apps correspondant à ce User
		Cursor c=database.query(TABLE_App_Selection, new String[] {COL_AppSelection}, COL_TabletUserselection+"="+UserId, null, null, null, null);
		c.moveToFirst();
		//recuperation des apps correspondants à ces ID dans la table AppCatalog
		ArrayList<AppCatalog> myApps=new ArrayList<AppCatalog>();
		do{
			AppCatalog app=new AppCatalog();
			Cursor req=database.query(TABLE_APP_CATALOG, null, COL_IDCATALOG+"="+c.getInt(0), null, null, null, null);
			app.id=req.getInt(NUM_COL_IDCATALOG);
			app.URI=req.getString(NUM_COL_URI);
			app.APP=req.getString(NUM_COL_App);
			app.LocalCategoryNAME=req.getString(NUM_COL_LocalCategoryNAME);
			app.Added=req.getString(NUM_COL_Added);
			app.Updated=req.getString(NUM_COL_Updated);
			app.RankGlobal=req.getInt(NUM_COL_RankGlobal);
			app.PartnerCode=req.getString(NUM_COL_PartnerCode);
			myApps.add(app);		
		}while(c.moveToNext());
		
		//classement des applications par catégorie
		
		Cursor local = database.query(TABLE_LocalAppCategories,
				new String[] { COL_Category }, null, null, null, null, null);
		local.moveToFirst();

		do {
			Category cat = new Category();
		//	Category categorieRange= new Category();
			
			cat.name = local.getString(0);
			//categorieRange.name=cat.name;
			for (int i = 0; i < myApps.size(); i++) {
				if ((cat.name).equals(myApps.get(i).LocalCategoryNAME)) {
							cat.apps.add(myApps.get(i));
				}
				
			}
			
			myCatList.add(cat);

		} while (local.moveToNext());
		local.close();
		
		return myCatList;
	}
	 
	
	public void AddAppSelection(int UserId,int App){
		ContentValues val=new ContentValues();
		
		val.put(COL_TabletUserselection, UserId);
		val.put(COL_AppSelection, App);
		database.insert(TABLE_App_Selection, null, val);
	
	}
	
	public void close() {
		if (database != null)
			database.close();
	}

}
