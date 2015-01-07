package com.example.loginui;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class SecurityEasy implements SecurityLevel {

	public int section = 2;
	
	@Override
	public int getSecurity(String word) {
		int security = 0;
		if((word.length()>7)?true:false)
			security++;
		if(!word.equals(word.toUpperCase()))
			security++;
		
		return security;
	}

	@Override
	public int getSecurityColor(String word) {
		
		int s = getSecurity(word);
		
		if(s==1)
			return Color.rgb(255,0,0);
		if(s==2)
			return Color.rgb(255,128,0);
		
		return Color.BLACK;
	}

	@Override
	public int getSecurityValue(String s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSecuritySections() {
		// TODO Auto-generated method stub
		return section;
	}

}
