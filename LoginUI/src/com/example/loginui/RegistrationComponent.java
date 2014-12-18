package com.example.loginui;

import java.util.ArrayList;

import android.animation.ArgbEvaluator;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegistrationComponent extends LinearLayout {
	
	String tag = "Reg";
	TextView lblUser, lblPass, lblEmail, lblAddress, checkUser, checkPass, checkEmail, checkAddress;
	LinearLayout fieldUser, fieldEmail, fieldAddress;
	EditText username, email, address;
	Button btnCreate;
	LayoutParams horizontalUser, horizontalEmail, horizontalAddress;
	PasswordComponent PC;
	int themeBackgroundColor, themeTextColor;
	int weightSum = 4, weightLeftChild = 3, weightRightChild = 1;
	
	ArrayList<Field> listField;
	private Context context;

	public RegistrationComponent(Context context) {
		super(context);
		this.setOrientation(VERTICAL);
		this.themeBackgroundColor = Color.argb(100, 0, 0, 0);
		this.themeTextColor = Color.WHITE;
		//this.listCustomField = new ArrayList<Field>();
		
	}
	
	public RegistrationComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(VERTICAL);
		this.themeBackgroundColor = Color.argb(100, 0, 0, 0);
		this.themeTextColor = Color.WHITE;
		this.context = context;
		onCreate(context);
		
	}
	
	public void setTextColor(int color){
		for(int i= 0; i<listField.size(); i++){
			
		}
		
		username.setTextColor(color);
		PC.setTextColor(color);
		email.setTextColor(color);
		address.setTextColor(color);

	}
	
	@Override
	public void setBackgroundColor(int color) {
		
	}
	
	public void onCreate(Context context){
		this.setBackgroundColor(Color.alpha(0));
		
		
		Field fieldUser = new Field(context, "Username");
		PC = new PasswordComponent(context);
		lblPass = new TextView(context);
		lblPass.setText("Password");
		Field fieldEmail = new Field(context, "Email");
		Field fieldAddress = new Field(context, "Address");
		
		createPassField(context);
		//setTextColor(themeTextColor);
		
		addView(fieldUser);
		addView(lblPass);
		addView(PC);
		addView(fieldEmail);
		addView(fieldAddress);
		
	}
	
	public LinearLayout createPassField(Context context){
		PC = new PasswordComponent(context);
		PC.setBackgroundColor(themeBackgroundColor);
		
		return PC;
	}
}
