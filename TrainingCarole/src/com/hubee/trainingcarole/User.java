package com.hubee.trainingcarole;

import java.util.ArrayList;

public class User {
	//cet id correspond au id de la table TabletUsers
	public int id;
	
	public  String screenName="kisss";
	public String passwordTabletUsers="kissss";
	public String age="adult";
	public String occupation="teleworker";
	public String gender="female";
	public int theme;
	public int network;
	public ArrayList<UserAccounts> accounts;

	User()
	{
		accounts=new ArrayList<UserAccounts>();
	}
	
	 
}
	


