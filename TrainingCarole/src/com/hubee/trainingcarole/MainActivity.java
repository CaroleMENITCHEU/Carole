package com.hubee.trainingcarole;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;


public class MainActivity extends Activity {
	
		 private HandlerUser datasource;
		 //public User  userTampon=new User() ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new HandlerUser(this);
       //datasource.open();
       try {
			datasource.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
     boolean  bool=datasource.database.isOpen();
     /*ArrayList<TabletUsers> myList = new ArrayList<TabletUsers>();
     myList=datasource.getAllTabletUsers();*/
        
            //Création d'un userAccounts
           /*  User user=new User();
             UserAccounts moi= new UserAccounts();
              user.accounts=new ArrayList<UserAccounts>();
              user.accounts.add(moi);
           
            //On insère le user que l'on vient de créer OK
           datasource.createUser(user);*/
           //User user=datasource.getUser(0);
           //ArrayList<User> user=new ArrayList<User>();
//     ArrayList<AppCatalog> myapps=new ArrayList<AppCatalog>();
   ArrayList<Category> mycats=new ArrayList<Category>();
             mycats=datasource.AppGroupByCatagories(2);
             /* System.out.println(user.id);
              System.out.println(user.age);
              System.out.println(user.network);
              System.out.println(user.occupation);
              System.out.println(user.passwordTabletUsers);
              System.out.println(user.screenName);
              System.out.println(user.theme);
         */
             for (int i=0;i<mycats.size();i++){
              System.out.println(mycats.get(i).name);
              System.out.println("espace");
              for(int j=0;j<(mycats.get(i).apps.size());i++){
            	  
            	  System.out.println(mycats.get(i).apps.get(j));
              }
              
              } 
            //  System.out.println(user.size());
                
        //Pour vérifier que l'on a bien créé notre user dans la BDD
           //on extrait le user de la BDD grâce au UserID du useraccounts que l'on a créé précédemment
      //userTampon =datasource.getLauncherThemes(30);
            //Si un user est retourné (donc si le user à bien été ajouté à la BDD)
       /*   if(userTampon != null){
            	//On affiche les infos du user dans un Toast
            	Toast.makeText(this, userTampon.toString(), Toast.LENGTH_LONG).show();
            	//On modifie le userId du livre
            	
            	userTampon.Theme =25;
            	
            	//Puis on met à jour la BDD
           	    datasource.updateLauncherThemes(30,userTampon);
            }
                
            
                   //On extrait le user de la BDD grâce au nouveau userID
       userTampon =datasource.getLauncherThemes(25);
           //S'il existe un livre possédant ce titre dans la BDD
         
             if(userTampon != null){
    	        //On affiche les nouvelle info du user pour vérifié que le userid du user a bien été mis à jour
    	        Toast.makeText(this, userTampon.toString(), Toast.LENGTH_LONG).show();
    	        //on supprime le user de la BDD grâce à son ID
    	        datasource.deleteLauncherThemes(25);
            }
*/
               
         /* //On essait d'extraire de nouveau le user de la BDD toujours grâce à son nouveau userId
            userTampon = datasource.getMarketingSegmentOccupationsWithOccupation("intern");
            //Si aucun user n'est retourné
            if(userTampon == null){
            	//On affiche un message indiquant que le livre n'existe pas dans la BDD
            	Toast.makeText(this, "Ce user n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
            }
            //Si le user existe (mais normalement il ne devrait pas)
            else{
            	//on affiche un message indiquant que le livre existe dans la BDD
            	Toast.makeText(this, "Ce user  existe dans la BDD alors kil ne devrait pas", Toast.LENGTH_LONG).show();
            }
        		*/
        
       System.out.println(bool);
              
            datasource.dbHelper.close();
    }

		    @Override
		    public boolean onCreateOptionsMenu(Menu menu) {
		        getMenuInflater().inflate(R.menu.activity_main, menu);
		        return true;
		    }
}
