package com.hubee.trainingcarole;

import java.util.ArrayList;

public class User {
	//ce id correspond au id de la table TabletUsers
	public int id;
	
	public  String screenName;
	public String passwordTabletUsers;
	public String age;
	public String occupation;
	public int theme;
	public int network;
	public ArrayList<UserAccounts> accounts;

	User()
	{
		accounts=new ArrayList<UserAccounts>();
	}
	
	 
}
	


