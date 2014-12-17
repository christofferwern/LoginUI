package com.example.loginui;

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
	LinearLayout linearUser, linearEmail, linearAddress;
	EditText username, email, address;
	Button btnCreate;
	LayoutParams horizontalUser, horizontalEmail, horizontalAddress;
	PasswordComponent PC;
	int weightSum = 4, weightLeftChild = 3, weightRightChild = 1;

	public RegistrationComponent(Context context) {
		super(context);
		this.setOrientation(VERTICAL);
	}
	
	public RegistrationComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(VERTICAL);		
		onCreate(context);
	}
	
	public void onCreate(Context context){
		
		createUserField(context);
		createEmailField(context);
		createAddressField(context);
		
		PC = new PasswordComponent(context);
	
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
		addView(linearUser);
		addView(lblPass);
		addView(PC);
		addView(lblEmail);
		addView(linearEmail);
		addView(lblAddress);
		addView(linearAddress);
		addView(btnCreate);
		
	}
	
	public void createUserField(Context context){
		linearUser = new LinearLayout(context);
		linearUser.setOrientation(HORIZONTAL);
		linearUser.setWeightSum(weightSum);
		
		username = new EditText(context);
		username.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weightLeftChild));
		checkUser = new TextView(context);
		checkUser.setBackgroundColor(Color.BLACK);
		checkUser.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weightRightChild));
		
		linearUser.addView(username);
		linearUser.addView(checkUser);
	}
	
	public void createEmailField(Context context){
		linearEmail = new LinearLayout(context);
		linearEmail.setOrientation(HORIZONTAL);
		linearEmail.setWeightSum(weightSum);
		
		email = new EditText(context);
		email.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weightLeftChild));
		checkEmail = new TextView(context);
		checkEmail.setBackgroundColor(Color.BLACK);
		checkEmail.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weightRightChild));
		
		linearEmail.addView(email);
		linearEmail.addView(checkEmail);
	}
	
	public void createAddressField(Context context){
		linearAddress = new LinearLayout(context);
		linearAddress.setOrientation(HORIZONTAL);
		linearAddress.setWeightSum(weightSum);
		
		address = new EditText(context);
		address.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weightLeftChild));
		checkAddress = new TextView(context);
		checkAddress.setBackgroundColor(Color.BLACK);
		checkAddress.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weightRightChild));
		
		linearAddress.addView(address);
		linearAddress.addView(checkAddress);
	}
	
}
