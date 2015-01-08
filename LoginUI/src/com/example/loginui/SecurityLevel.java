package com.example.loginui;

import java.util.ArrayList;

import android.R.color;
import android.R.integer;
import android.R.string;

public interface SecurityLevel {
	
	/**
	 * Handles the security algorithm.
	 * @param s {@link string} - input string
	 * @return magnitude of the security {@link integer}
	 */
	public int getSecurity(String s);
	
	/**
	 * Which color that corresponds to the current security value.
	 * @param s {@link string} - input string
	 * @return Color {@link integer}
	 */
	public int getSecurityColor(String s);
	
	/**
	 * Returns the value of the security.
	 * @param s {@link string} - input string
	 * @return {@link integer}
	 */
	public int getSecurityValue(String s);
	
	/**
	 * Numbers of sections in the password security bar.
	 * @return {@link integer}
	 */
	public int getSecuritySections();
	
	/**
	 * Returns a blended color between x and y depending on blending value.
	 * 
	 * @param x {@link integer} Color
	 * @param y {@link integer} Color
	 * @param blending {@link Double} - interpolation value
	 * @return {@link integer} blended color
	 */
	public int mixColors(int x, int y, double blending);
	
	/**
	 * Returns an array of n colors from color1 to color2.
	 * 
	 * @param color1 {@link integer} - Start color
	 * @param color2 {@link integer} - End color
	 * @param n {@link integer} - amount of colors 
	 * @return {@link ArrayList} of the colors
	 */
	public ArrayList getColors(int color1, int color2, int n);
	
	/**
	 * Set the color theme on the password bar. 
	 * 
	 * @param colorStart {@link integer}
	 * @param colorEnd {@link integer}
	 */
	public void setColors(int colorStart, int colorEnd);

}
