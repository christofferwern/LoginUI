package com.example.loginui;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.loginui.PasswordComponent.SecurityType;

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
	
	Map<String, String> userMap;
	String tag = "Reg"; /*!< Log tag */
	Button btnCreate; /*!< Button to create a user*/
	int themeBackgroundColor, themeTextColor, themeLabelColor; /*!< Integers of colors*/
	
	JSONObject infoJson;
	ArrayList<Field> listField; /*!< List of all fields in the component*/
	private Context context; /*!< Context */
	
	public enum Type{
		DEFAULT,EMAIL,PASSWORD;
	}

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
		this.userMap = new LinkedHashMap<String, String>();
		this.infoJson = new JSONObject();
		
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
		this.userMap = new LinkedHashMap<String, String>();
		this.infoJson = new JSONObject();
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
		
		setFieldBackgroundColor(themeBackgroundColor);
		setTextColor(themeTextColor);
		setHeaderColor(themeLabelColor);
		
		btnCreate = new Button(context);
		btnCreate.setText("Create");
		btnCreate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				for(int i=0; i<listField.size(); i++){
					
					try {
						infoJson.put(listField.get(i).getHeaderLabel(), listField.get(i).getText());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		
		btnCreate.setEnabled(false);
		addView(btnCreate);
		
	}

	
	/**
	 * Function to add a new field
	 * 
	 * @param fieldname - sets the name of the field {@link string}
	 * @throws JSONException 
	 */
	public void addField(String fieldname){
		addField(fieldname,Type.DEFAULT,true);
	}
	
	/**
	 * Function to add a new field
	 * 
	 * @param fieldname - sets the name of the field {@link string}
	 * @throws JSONException 
	 */
	public void addField(String fieldname, Type TYPE){
		addField(fieldname,TYPE,true);
	}
	
	/**
	 * Function to add a new field
	 * 
	 * @param fieldname - sets the name of the field {@link string}
	 * @param required - true makes it a required field {@link bool}
	 * @throws JSONException 
	 */
	public void addField(String fieldname,Type TYPE, Boolean required){
			
		removeView(btnCreate);

		Field newField = new Field(context,fieldname,required);
		
		switch (TYPE) {
			case DEFAULT:
			break;
			
			case EMAIL:
				newField.setToEmailField(true);
			break;
			
			case PASSWORD:
				newField.setToPasswordField(true);
			break;
		}
		newField.setTextColor(themeTextColor);
		newField.setBackgroundColor(themeBackgroundColor);
		newField.setHeaderColor(themeLabelColor);
		
		newField.getEditText().addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				btnCreate.setEnabled(true);

				for(int i = 0; i<listField.size();i++){
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
	
	public Button getCreateButton(){
		if(btnCreate != null)
			return btnCreate;
		else
			return null;
	}
	
	public JSONObject getJson(){
		if(infoJson.length() != 0)
			return infoJson;
		return null;
		
	}

	public void setPasswordLevel(SecurityType TYPE){
		for(int i = 0; i<listField.size(); i++){
			if(listField.get(i).isPassword()){
				listField.get(i).getPasswordComponent().setSecurityLevel(TYPE);
			}
		}
	}
}
