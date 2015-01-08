package com.example.loginui;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

public class SecurityEasy implements SecurityLevel {

	public int section = 2;
	private int colorStart;
	private int colorEnd;
	
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
		if(s>0)
			return (Integer) getColors(colorStart, colorEnd, getSecuritySections()).get(s-1);
		else
			return Color.BLACK;
	}

	@Override
	public int getSecurityValue(String s) {
		return getSecurity(s);
	}

	@Override
	public int getSecuritySections() {
		return section;
	}
	
	@Override
	public ArrayList getColors(int color1, int color2, int n){
		
		ArrayList colorList = new ArrayList();
		colorList.add(mixColors(color2,color1,0));
		
		for(int i=1;i<=n-1;i++){
			double percent = (double) ((double) i / (double) (n-1)) ;  
			colorList.add(mixColors(color2,color1,percent));
		}
		
		return colorList;
	}

	@Override
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
		this.colorStart = colorStart;
		this.colorEnd = colorEnd;
		// TODO Auto-generated method stub
		
	}

}
