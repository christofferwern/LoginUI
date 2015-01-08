package com.example.loginui;

import java.util.ArrayList;

import android.R.bool;
import android.graphics.Color;
import android.util.Log;

public class SecurityHard implements SecurityLevel{
	
	public int section = 6;
	private int colorStart;
	private int colorEnd;
	

	@Override
	public int getSecurity(String word) {
		
		int security = 0;
		
		if(!word.equals(word.toLowerCase()))
			security++;
		if(!word.equals(word.toUpperCase()))
			security++;
		if(word.matches(".*\\d.*"))
			security++;
		if(!word.matches("[A-Za-z0-9 ]*"))
			security++;
		if((word.length()>7)?true:false)
			security++;
		if((word.length()>11)?true:false)
			security++;
		
		return security;
	}

	@Override
	public int getSecurityColor(String word) {
		
		
		int s = getSecurity(word);
		if(s>0)
			return (Integer) getColors(colorStart, colorEnd, getSecuritySections()).get(s-1);
		else
			return Color.BLACK;
	
	}

	@Override
	public int getSecurityValue(String s) {
		// TODO Auto-generated method stub
		return getSecurity(s);
	}

	@Override
	public int getSecuritySections() {
		// TODO Auto-generated method stub
		return section;
	}
	
	public ArrayList getColors(int color1, int color2, int n){

		ArrayList colorList = new ArrayList();
		colorList.add(mixColors(color2,color1,0));
		
		for(int i=1;i<=n-1;i++){
			double percent = (double) ((double) i / (double) (n-1)) ;  
			colorList.add(mixColors(color2,color1,percent));
		}
		
		return colorList;
	}

	public int mixColors(int x, int y, double blending){
		
		float inverse_blending = (float) (1 - blending);

		int red =   (int) (Color.red(x) * blending   +   Color.red(y)  * inverse_blending);
		int green = (int) (Color.green(x)* blending   +   Color.green(y) * inverse_blending);
		int blue =  (int) (Color.blue(x)* blending   +   Color.blue(y)  * inverse_blending);

		int blended = Color.rgb(red, green, blue);
		
		return blended;
	}

	@Override
	public void setColors(int colorStart, int colorEnd) {
		Log.w("COLORS", ""+colorStart);
		this.colorStart = colorStart;
		this.colorEnd = colorEnd;
		
	}

}
