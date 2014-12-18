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
	
	public void onCreate(Context context){
		this.setBackgroundColor(Color.alpha(0));
		
		
		Field user = new Field(context); 
		
		
		createUserField(context);
		createPassField(context);
		createEmailField(context);
		createAddressField(context);
		setTextColor(themeTextColor);
		
		

		btnCreate = new Button(context);
		btnCreate.setText("Create");
		
		lblUser = new TextView(context);
		lblUser.setText("Username");

		lblPass = new TextView(context);
		lblPass.setText("Password");

		lblEmail = new TextView(context);
		lblEmail.setText("Email");
		
		lblAddress = new TextView(context);
		lblAddress.setText("Address");

		btnCreate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
		addView(lblUser);
		addView(temp);
		addView(lblPass);
		addView(PC);
		addView(lblEmail);
		addView(temp);
		addView(lblAddress);
		addView(temp);
		addView(btnCreate);
		
	}
	
	public void createUserField(Context context){
		Field temp = new Field(context, );
		temp.setOrientation(HORIZONTAL);
		temp.setWeightSum(weightSum);
		LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setMargins(0, 5, 0, 5);
		temp.setBackgroundColor(themeBackgroundColor);
		temp.setLayoutParams(ll);
		
		username = new EditText(context);
		username.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weightLeftChild));
		
		checkUser = new TextView(context);
		checkUser.setBackgroundColor(themeBackgroundColor);
		checkUser.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weightRightChild));
		
		temp.addView(username);
		temp.addView(checkUser);
		
		return temp;
	}
	
	public LinearLayout createEmailField(Context context){
		LinearLayout temp = new LinearLayout(context);
		temp.setOrientation(HORIZONTAL);
		temp.setWeightSum(weightSum);
		LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setMargins(0, 5, 0, 5);
		temp.setBackgroundColor(themeBackgroundColor);
		temp.setLayoutParams(ll);
		
		email = new EditText(context);
		email.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weightLeftChild));
		
		checkEmail = new TextView(context);
		checkEmail.setBackgroundColor(themeBackgroundColor);
		checkEmail.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weightRightChild));
		
		temp.addView(email);
		temp.addView(checkEmail);
		
		return temp;
	}
	
	public void createAddressField(Context context){
		LinearLayout temp = new LinearLayout(context);
		temp.setOrientation(HORIZONTAL);
		temp.setWeightSum(weightSum);
		LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setMargins(0, 5, 0, 5);
		temp.setBackgroundColor(themeBackgroundColor);
		temp.setLayoutParams(ll);
		
		address = new EditText(context);
		address.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weightLeftChild));
		
		checkAddress = new TextView(context);
		checkAddress.setBackgroundColor(themeBackgroundColor);
		checkAddress.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weightRightChild));
		
		temp.addView(address);
		temp.addView(checkAddress);
		
		listField.add(temp);
	}
	
	public LinearLayout createPassField(Context context){
		PC = new PasswordComponent(context);
		PC.setBackgroundColor(themeBackgroundColor);
		
		return PC;
	}
}
