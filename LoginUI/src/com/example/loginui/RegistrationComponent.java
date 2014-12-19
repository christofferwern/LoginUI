package com.example.loginui;

import java.util.ArrayList;

import android.R.attr;
import android.R.bool;
import android.R.integer;
import android.R.string;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class RegistrationComponent extends LinearLayout {
	
	String tag = "Reg"; /*!< Log tag */
	Button btnCreate; /*!< Button to create a user*/
	int themeBackgroundColor, themeTextColor, themeLabelColor; /*!< Integers of colors*/
	
	ArrayList<Field> listField; /*!< List of all fields in the component*/
	private Context context; /*!< Context */

	/**
	 * Constructor of Registration component
	 * @param context
	 */
	public RegistrationComponent(Context context) {
		super(context);
		this.setOrientation(VERTICAL);
		this.setBackgroundColor(Color.alpha(0));
		this.themeBackgroundColor = Color.argb(100, 0, 0, 0);
		this.themeTextColor = Color.WHITE;
		this.themeLabelColor = Color.BLACK;
		this.listField = new ArrayList<Field>();
		
	}
	
	/**
	 * Constructor of Registration component
	 * 
	 * @param context {@link Context}
	 * @param attrs {@link attr}
	 */
	public RegistrationComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(VERTICAL);
		this.setBackgroundColor(Color.alpha(0));
		this.themeBackgroundColor = Color.argb(100, 0, 0, 0);
		this.themeTextColor = Color.WHITE;
		this.themeLabelColor = Color.BLACK;
		this.context = context;
		this.listField = new ArrayList<Field>();
		onCreate(context);
	}
	
	/**
	 * Function to set text color of the headers
	 * @param color - defines the color {@link integer}
	 */
	public void setHeaderColor(int color){
		themeLabelColor = color;
		for(int i= 0; i<listField.size(); i++){
			listField.get(i).setHeaderColor(color);
		}
	}
	
	/**
	 * Function to set text color
	 * 
	 * @param color - defines the color {@link integer}
	 */
	public void setTextColor(int color){
		themeTextColor = color;
		for(int i= 0; i<listField.size(); i++){
			listField.get(i).setTextColor(color);
		}
	}
	
	/**
	 * Function to set background color for each field
	 * 
	 * @param color - defines the color {@link integer}
	 */
	public void setFieldBackgroundColor(int color){
		themeBackgroundColor = color;
		for(int i=0; i<listField.size(); i++){
			listField.get(i).setBackgroundColor(color);
		}
	}
	
	/**
	 * Creates the standard registration fields
	 * 
	 * @param context - context {@link Context}
	 */
	public void onCreate(Context context){
	
		addField("Username");
		addField("Password");
		addField("Email");
		addField("Address", false);
		
		setFieldBackgroundColor(themeBackgroundColor);
		setTextColor(themeTextColor);
		setHeaderColor(themeLabelColor);
		
		btnCreate = new Button(context);
		btnCreate.setText("Create");
		
		btnCreate.setEnabled(false);
		
		btnCreate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});

		
		addView(btnCreate);
		
	}

	/**
	 * Function to add a new field
	 * 
	 * @param fieldname - sets the name of the field {@link string}
	 */
	public void addField(String fieldname){
		addField(fieldname, true);
	}
	
	/**
	 * Function to add a new field
	 * 
	 * @param fieldname - sets the name of the field {@link string}
	 * @param required - true makes it a required field {@link bool}
	 */
	public void addField(String fieldname, Boolean required){
			
		removeView(btnCreate);
		
		Field newField = new Field(context, fieldname,required);
		newField.setTextColor(themeTextColor);
		newField.setBackgroundColor(themeBackgroundColor);
		newField.setHeaderColor(themeLabelColor);
		
		newField.getEditText().addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				btnCreate.setEnabled(true);
				for(int i = 0; i<listField.size();i++){
					Log.w(tag, ""+listField.get(i).isCorrectInput());
					if(!listField.get(i).isCorrectInput()){
						btnCreate.setEnabled(false);
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		listField.add(newField);
		
		addView(newField);
		if(btnCreate!=null)
			addView(btnCreate);
	}

}
