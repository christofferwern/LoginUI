package com.example.loginui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PasswordComponent extends LinearLayout{
	
	private EditText editText;
	private TextView label;
	
	private String word;
	private String[] passwordSecurityLabels = new String[]{"","Very weak","Weak","Fair","Good","Very good", "Excellent"};
	
	public PasswordComponent(Context context) {
		super(context);
		
		onCreate(context);
	}
	
	public PasswordComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
				
		onCreate(context);
	}

	private void onCreate(Context context) {
		
		setBackgroundColor(Color.WHITE);
		
		label = new TextView(context);
		label.setText("");
		
		editText = new EditText(context);
		//editText.setWidth((int) (getLayoutParams().width * 0.7));
		editText.setHint("Password");
		editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				word = s.toString();
				label.setTextColor(getLabelColor());
				label.setText(getSecurityLabel());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		addView(editText);
		addView(label);
		
		Log.w("DEBUG", "Width: " + getWidth() );
		Log.w("DEBUG", "Height: " + getHeight() );	
		
		word = "hehehB12";
		Log.w("DEBUG", "Security: " + getSecurity() );
	}
	
	public int getLabelColor() {
		int s = getSecurity();
		if(s==1)
			return Color.rgb(255,0,0);;
		if(s==2)
			return Color.rgb(255,128,0);;
		if(s==3)
			return Color.rgb(255,255,0);;
		if(s==4)
			return Color.rgb(178,255,102);
		if(s==5)
			return Color.rgb(128,255,0);
		if(s==6)
			return Color.rgb(0,255,0);
		
		return Color.BLACK;
	}

	public int getSecurity(){
		
		int security = 0;
		
		if(this.hasUpperLetters())
			security++;
		
		if(this.hasLowerLetters())
			security++;
		
		if(this.hasNumbers())
			security++;
		
		if(this.hasSpecialCharacters())
			security++;
		
		if(this.has8Chars())
			security++;
		
		if(this.has12Chars())
			security++;
		
		return security;
	}
	
	public String getSecurityLabel(){
		int security = getSecurity();
		
		for(int i=0;i<passwordSecurityLabels.length;i++)
			if(security>i-1 && security<=i)
				return passwordSecurityLabels[i];
		
		return "";
	}
	
	private boolean hasUpperLetters(){
		return !word.equals(word.toLowerCase());
	}
	
	private boolean hasLowerLetters(){
		return !word.equals(word.toUpperCase());
	}

	private boolean hasNumbers(){
		return word.matches(".*\\d.*");
	}	
	
	private boolean hasSpecialCharacters(){
		return !word.matches("[A-Za-z0-9 ]*");
	}
	
	private boolean has8Chars(){
		return (word.length()>7)?true:false;
	}
	
	private boolean has12Chars(){
		return (word.length()>11)?true:false;
	}
}

