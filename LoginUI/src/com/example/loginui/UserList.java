package com.example.loginui;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class UserList {
	
	private ArrayList<User> userList = null;

	public UserList(){
		
		if(userList==null){
			
			userList = new ArrayList<User>();
			
			Log.w("Userlist", ""+userList + " is created");
			
			User nr1 = new User("tobbe", "pass", "email");
	        User nr2 = new User("lasse", "pass", "email");
	        
	        addToList(nr1);
	        addToList(nr2);
		}
		else
			Log.w("Userlist", "List already in use");
		
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	
	public void addToList(User user){
		
		if(checkUsername(user.getUsername()))
		{
			userList.add(user);
			Log.w("UserList", ""+user.getUsername() + " added");
		}	
		else
			Log.w("Userlist", ""+user.getUsername() + " already exists in the list");
		
	}
	
	/**
	 * Checks if the user already exists
	 * 
	 * @param username - String 
	 * @return true - if user does not exists
	 */
	public boolean checkUsername(String username){
		
		if(userList.size() == 0)
			return true;
		
		for(int i = 0; i<userList.size(); i++){
			if(userList.get(i).getUsername().toString().equals(username.toString()))
				return false;		
		}
		
		return true;
	}
}
