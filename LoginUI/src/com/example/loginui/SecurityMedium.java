package com.example.loginui;

import android.graphics.Color;
import android.util.Log;

public class SecurityMedium implements SecurityLevel{
	
	public int section = 4;

	@Override
	public int getSecurity(String word) {
		// TODO Auto-generated method stub
		int security = 0;
		if((word.length()>7)?true:false)
			security++;
		if(!word.equals(word.toLowerCase()))
			security++;
		if(!word.equals(word.toUpperCase()))
			security++;
		if(word.matches(".*\\d.*"))
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
		if(s==3)
			return Color.rgb(255,255,0);
		if(s==4)
			return Color.rgb(178,255,102);
		
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
