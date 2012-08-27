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
	
	
	 //The Android's default system path of your application database.
	   private static String DB_PATH = "/data/data/com.hubee.trainingcarole/databases/";
       private static String DB_NAME = "HHLauncher03.db";
       
	
	private static final String TABLE_TabletUsers  = "'0300_TabletUsers'";
	private static final String COL_IDTablet = "_ID";
	private static final int NUM_COL_IDTablet = 0;
	private static final String COL_ScreenName= "ScreenName";
	private static final int NUM_COL_ScreenName = 1;
	private static final String COL_PasswordTablet = "Password";
	private static final int NUM_COL_PasswordTablet = 2;
	private static final String COL_AgeTablet = "Age";
	private static final int NUM_COL_AgeTablet = 3;
	private static final String COL_OccupationTablet = "Occupation";
	private static final int NUM_COL_OccupationTablet = 4;
	private static final String COL_Theme = "Theme";
	private static final int NUM_COL_Theme = 5;
	private static final String COL_Network = "Network";
	private static final int NUM_COL_Network = 6;
	
	private static final String TABLE_USERS = "'0301_UserAccounts'";
	private static final String COL_IDUser = "_ID";
	private static final int NUM_COL_IDUser = 0;
	private static final String COL_UserId = "UserId";
	private static final int NUM_COL_UserId = 1;
	private static final String COL_Email = "Email";
	private static final int NUM_COL_Email=2;
	private static final String COL_PasswordUser = "Password";
	private static final int NUM_COL_PasswordUser=3;
	private static final String COL_TabletUser = "TabletUser";
	private static final int NUM_COL_TabletUser=4;
	
	//marketingsegmentAge
	private static final String TABLE_MarketingSegmentAges  = "'0203_MarketingSegmentAges'";
	private static final String COL_IDAge = "_ID";
	private static final int NUM_COL_IDAge = 0;
	private static final String COL_Age = "Age";
	private static final int NUM_COL_Age = 1;
	
	//marketingsegmentoccupation
	private static final String TABLE_MarketingSegmentOccupations = "'0204_MarketingSegmentOccupations'";
	private static final String COL_IDoccup = "_ID";
	private static final int NUM_COL_IDoccup = 0;
	private static final String COL_Occupation = "Occupation";
	private static final int NUM_COL_Occupation = 1;
	
	//gesttion des applications
	
	private static final String TABLE_App_Selection = "'0500_AppSelection'";
	private static final String COL_IDAppSelection = "_ID";
	private static final int NUM_COL_IDAppSelection  = 0;
	private static final String COL_AppSelection = "App";
	private static final int NUM_COL_AppSelection = 1;
	private static final String COL_StatusSelection = "Status";
	private static final int NUM_COL_StatusSelection=2;
	private static final String COL_User = "User";
	private static final int NUM_COL_User=3;
	private static final String COL_AddedSelection = "Added";
	private static final int NUM_COL_AddedSelection=4;
	private static final String COL_UpdatedSelection = "Updated";
	private static final int NUM_COL_UpdatedSelection=5;
	
	private static final String TABLE_AppStatus = "'0502_AppStatus'";
	private static final String COL_IDStatus = "_ID";
	private static final int NUM_COL_IDStatus = 0;
	private static final String COL_Status = "Status";
	private static final int NUM_COL_Status = 1;
	private static final String COL_Affinity = "Affinity";
	private static final int NUM_COL_Affinity = 2;
	
	
	private static final String TABLE_APP_CATALOG = "'0501_AppCatalog'";
	private static final String COL_IDCATALOG  = "_ID";
	private static final int NUM_COL_IDCATALOG  = 0;
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
	
	private static final String TABLE_LocalAppCategories = "'0505_LocalAppCategories'";
	private static final String COL_ID = "_ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_Country = "Country";
	private static final int NUM_COL_Country = 1;
	private static final String COL_Category= "Category";
	private static final int NUM_COL_Category = 2;
	private static final String COL_rank = "rank";
	private static final int NUM_COL_rank = 3;
	
	public Context mycontext;
	
	// Database fields
	  public SQLiteDatabase database=null;
	  public DataBaseHelper dbHelper;
	  public HandlerUser (Context context){
			// prend en entrée une BD ouverte
			dbHelper = new DataBaseHelper(context);
			mycontext=context;
		
		}
	  /******************copy de la BD si elle n'existe pas **************************/
	  
	    /*
	     Creates an empty database on the system and rewrites it with your own database.
	    */
	   
	   public void createDataBase() throws IOException{
	   	 
	 	boolean dbExist = checkDataBase();
		   

	   	if(dbExist){
	   		//do nothing - database already exist
	   		open() ;
	   	}else{

	   		//By calling this method and empty database will be created into the default system path
	              //of your application so we are gonna be able to overwrite that database with our database.
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
	    * Check if the database already exist to avoid re-copying the file each time you open the application.
	    * @return true if it exists, false if it doesn't
	    */
	   
	   private boolean checkDataBase(){
		   
		   
		   File dbFile = new File(DB_PATH + DB_NAME);
		    return dbFile.exists();
		
	   }

	   /**
	    * Copies your database from your local assets-folder to the just created empty database in the
	    * system folder, from where it can be accessed and handled.
	    * This is done by transfering bytestream.
	    * */
	   private void copyDataBase() throws IOException{
	   	 
	   	//Open your local db as the input stream
	   	InputStream myInput = mycontext.getAssets().open(DB_NAME);

	   	// Path to the just created empty db
	   	String outFileName = DB_PATH + DB_NAME;

	   	//Open the empty db as the output stream
	   	OutputStream myOutput = new FileOutputStream(outFileName);

	   	//transfer bytes from the inputfile to the outputfile
	   	byte[] buffer = new byte[1024];
	   	int length;
	   	while ((length = myInput.read(buffer))>0){
	   		myOutput.write(buffer, 0, length);
	   	}
	   	//Close the streams
	   	myOutput.flush();
	   	myOutput.close();
	   	myInput.close();

	   }
	   
	   public void openDataBase() throws SQLException{
		   	 
		   	//Open the database
		       String myPath = DB_PATH + DB_NAME;
		   	database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

		   }
	  
	  
	  
	  /*****************************fin de la copie****************************************/
		
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public void createUser(User u){
		  ContentValues valuesUserAccount = new ContentValues();
		  ContentValues valuesTabletUser = new ContentValues();
		 
		  
		  //insertion des elements tabletuser
		  valuesTabletUser.put(COL_IDTablet,u.id);
		  valuesTabletUser.put(COL_ScreenName,u.screenName);
		  valuesTabletUser.put(COL_PasswordTablet ,u.passwordTabletUsers);
		  valuesTabletUser.put(COL_Network,u.network);
		  valuesTabletUser.put(COL_Theme,u.theme);
		  Cursor c1=database.query(TABLE_MarketingSegmentAges, new String[] {COL_IDAge}, COL_Age+ " LIKE \"" + u.age +"\"", null, null, null, null);
		  c1.moveToFirst();
		  valuesTabletUser.put(COL_AgeTablet,c1.getInt(NUM_COL_IDAge));
		  Cursor c2=database.query(TABLE_MarketingSegmentOccupations, new String[] {COL_IDoccup}, COL_Occupation + " LIKE \"" + u.occupation+"\"", null, null, null, null);
		  c2.moveToFirst();
		  valuesTabletUser.put(COL_OccupationTablet,c2.getInt(NUM_COL_IDoccup));
			//on insère l'objet dans la BDD via le ContentValues
			database.insert(TABLE_TabletUsers, null, valuesTabletUser);
		
		  
		//insertion des elements useraccount
		  for(int i=0;i<u.accounts.size();i++){
			  valuesUserAccount.put(COL_UserId, u.accounts.get(i).userId);
			  valuesUserAccount.put(COL_Email, u.accounts.get(i).Email);
			  valuesUserAccount.put(COL_PasswordUser, u.accounts.get(i).Password);
			  Cursor c=database.query(TABLE_TabletUsers, new String[] {COL_IDTablet}, COL_PasswordTablet+ " LIKE \"" + u.passwordTabletUsers +"\"", null, null, null, null);
			  c.moveToFirst();
			  valuesUserAccount.put(COL_TabletUser, c.getInt(NUM_COL_IDTablet));
	        	 database.insert(TABLE_USERS, null, valuesUserAccount);
		  }
			
			
		  
		  
	  }
	  
	    
	  public User getUser(int id){
		  User u=new User();
		 
		  
		//Récupère dans un Cursor les valeurs correspondant à un user contenu dans la BDD (ici on sélectionne le user grâce à son userID)
			Cursor c =database.query(TABLE_USERS, new String[] {COL_IDUser,COL_UserId,COL_Email,COL_PasswordUser},COL_TabletUser+ "="+ id, null, null, null, null);
			c.moveToFirst();
			
		do{
			   UserAccounts user=new UserAccounts();
				user.id= c.getInt(NUM_COL_IDUser);
				user.userId= c.getString(NUM_COL_UserId);
				user.Email= c.getString(NUM_COL_Email);
				user.Password= c.getString(NUM_COL_PasswordUser);
				u.accounts.add(user);
				
			}while(c.moveToNext());
			c.close();
			
			Cursor c1 =database.query(TABLE_TabletUsers, new String[] {COL_IDTablet,COL_ScreenName,COL_PasswordTablet,COL_AgeTablet, COL_OccupationTablet,COL_Theme,COL_Network},COL_IDTablet+ "="+ id, null, null, null, null);
			c1.moveToFirst();	
			u.id=c1.getInt(NUM_COL_IDTablet);
		    u.screenName=c1.getString(NUM_COL_ScreenName);
			u.passwordTabletUsers=c1.getString(NUM_COL_PasswordTablet);
			u.theme=c1.getInt(NUM_COL_Theme);
			u.network=c1.getInt(NUM_COL_Network);
			int i=c1.getInt(NUM_COL_AgeTablet);
			
			
			Cursor m=database.query(TABLE_MarketingSegmentAges, new String[] {COL_IDAge, COL_Age},COL_IDAge+ "="+ i, null, null, null, null);
			m.moveToFirst();
			u.age=m.getString(NUM_COL_Age);
			
			Cursor occ=database.query(TABLE_MarketingSegmentOccupations,new String[] {COL_Occupation}, COL_IDoccup+ "=" + c1.getInt(NUM_COL_OccupationTablet), null, null, null, null);
			occ.moveToFirst();
			u.occupation=occ.getString(0);
			
			occ.close();
			m.close();
			c1.close();	
			return u;
		}
	  
	  
			
	  
public ArrayList<User> getAllUsers(){
	  
		  ArrayList<User> list=new ArrayList<User>();
		  Cursor id=database.query(TABLE_TabletUsers, new String[] {COL_IDTablet},null, null, null, null, null);
		  id.moveToFirst();
		
		  for(int i=1;i<=id.getCount();i++){
			  
			  list.add(getUser(id.getInt(0)));
			  
		  }
		 		  
		  return list;
	 }
	  

	public void updateUserName(int id, String name){
		
		//La mise à jour d'une tabletnetworks dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simplement préciser quelle user on doit mettre à jour grâce à l'userID
		ContentValues values = new ContentValues();
		values.put(COL_ScreenName,name);
		
		 database.update(TABLE_TabletUsers, values,  COL_IDTablet+ "=" + id , null);
	}
	
	public void updateUserPassword(int id, String password){
		
		ContentValues values = new ContentValues();
		values.put(COL_PasswordTablet,password);
		
		 database.update(TABLE_TabletUsers, values,  COL_IDTablet+ "=" + id , null);
	}
	
	public void updateUserOccupation(int id, String occupation){
		ContentValues values = new ContentValues();
		Cursor occup=database.query(TABLE_MarketingSegmentOccupations,new String[] {COL_IDoccup}, COL_Occupation+ " LIKE \"" + occupation +"\"", null, null, null, null);
		occup.moveToFirst();
		values.put(COL_OccupationTablet,occup.getInt(NUM_COL_IDoccup));
		 database.update(TABLE_TabletUsers, values,  COL_IDTablet+ "=" + id , null);
		
	}
	
	public void updateUserAge(int id, String age){
		
		ContentValues values = new ContentValues();
		Cursor occup=database.query(TABLE_MarketingSegmentAges,new String[] {COL_IDAge}, COL_Age+ " LIKE \"" + age +"\"", null, null, null, null);
		occup.moveToFirst();
		values.put(COL_AgeTablet,occup.getInt(0));
		 database.update(TABLE_TabletUsers, values,  COL_IDTablet+ "=" + id , null);
		
	}
	  
	
	public void deleteUser(int id){
		
		 database.delete(TABLE_TabletUsers,COL_IDTablet+ "=" + id  , null);
		 database.delete(TABLE_USERS,COL_TabletUser+ "=" + id  , null);
		 
	}
	
	
	 public ArrayList<AppCatalog> getApplications(int user){
		  ArrayList<AppCatalog> myapps=new ArrayList<AppCatalog>();
		  
		  
		  Cursor c =database.query(TABLE_APP_CATALOG, new String[] {COL_IDCATALOG,COL_URI,COL_App,COL_LocalCategoryNAME,COL_Added,COL_Updated,COL_RankGlobal,COL_PartnerCode,COL_RankSenior,COL_RankAdult,COL_RankTeenAger,COL_RankKid,COL_RankSelfEmployed,COL_RankHouseHusband,COL_RankStudent,COL_RankTeleWorker,COL_RankEmployee,COL_RankHousewife,COL_RankRetired,COL_RankJobless},COL_RankEmployee+">="+4 +" AND "+ COL_RankAdult +">="+ 4 , null, null, null, null);
		  c.moveToFirst();
		  do{
			  
			  AppCatalog appcat=new AppCatalog();
			  appcat.id=c.getInt(NUM_COL_IDCATALOG);
			  appcat.URI=c.getString(NUM_COL_URI);
			  appcat.APP=c.getString(NUM_COL_App);
			  appcat.LocalCategoryNAME=c.getString(NUM_COL_LocalCategoryNAME);
			  appcat.Added=c.getString(NUM_COL_Added);
			  appcat.Updated=c.getString(NUM_COL_Updated);
			  appcat.RankGlobal=c.getInt(NUM_COL_RankGlobal);
			  appcat.PartnerCode=c.getString(NUM_COL_PartnerCode);
			  appcat.RankSenior=c.getInt(NUM_COL_RankSenior);
			  appcat.RankAdult=c.getInt(NUM_COL_RankAdult);
			  appcat.RankTeenAger=c.getInt(NUM_COL_RankTeenAger);
			  appcat.RankKid=c.getInt(NUM_COL_RankKid);
			  appcat.RankSelfEmployed=c.getInt(NUM_COL_RankSelfEmployed);
			  appcat.RankHouseHusband=c.getInt(NUM_COL_RankHouseHusband);
			  appcat.RankStudent=c.getInt(NUM_COL_RankStudent);
			  appcat.RankTeleworker=c.getInt(NUM_COL_RankTeleWorker);
			  appcat.RankEmployee=c.getInt(NUM_COL_RankEmployee);
			  appcat.RankHousewife=c.getInt(NUM_COL_RankHousewife);
			  appcat.RankRetired=c.getInt(NUM_COL_RankRetired);
			  appcat.RankJobless=c.getInt(NUM_COL_RankJobless);
			  myapps.add(appcat);
			  
			  
		  }while(c.moveToNext());
		  return myapps;
	 }
	  
		  public  ArrayList<Category> AppGroupByCatagories(int user){
			  ArrayList<AppCatalog> app= getApplications(user);
			  ArrayList<Category> categories=new ArrayList<Category>();
		
			  
			  Cursor c=database.query(TABLE_LocalAppCategories,new String[] {COL_Category},null, null, null, null, null);  
			  c.moveToFirst();


			  do{
				  Category cat=new Category();
				  cat.name=c.getString(0);
				  for(int i=0;i<app.size();i++){
					  if((cat.name).equals(app.get(i).LocalCategoryNAME)){
						cat.apps.add(app.get(i).APP) ; 
					  }
				  }
				  
				  categories.add(cat);
				  
			  }while(c.moveToNext());
			  return categories;
		  }
		  
	
	public void close()
	{
		  if(database != null)
	   		    database.close();
	}
	  

}
