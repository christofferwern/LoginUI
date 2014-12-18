package com.example.loginui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Field extends LinearLayout{
	
	private EditText editText;
	private String headerLabel;
	private TextView headerTextView, checkTextView;
	private LinearLayout horizontalLayout;
	private int textColor, backgroundColor;
	private Context context;
	private PasswordComponent pc;
	private boolean required, isEmail, isPassword;
	
	public Field(Context context) {
		super(context);
		this.context = context;
	}

	public Field(Context context, String headerLabel) {
		super(context);
		this.context = context;
		
		//INSTANCE EVERYTHING
		this.headerLabel = headerLabel;
		editText = new EditText(context);
		headerTextView = new TextView(context);
		headerTextView.setText(headerLabel);
		checkTextView = new TextView(context);
		pc = new PasswordComponent(context);
		horizontalLayout = new LinearLayout(context);
		
		//GET INPUT TYPE
		changeInputType();
		
		//EDITTEXT LISTENER
		editTextListeners();
		
		//FIX LAYOUT
		this.setOrientation(VERTICAL);
		horizontalLayout.setOrientation(HORIZONTAL);
		horizontalLayout.setWeightSum(4);
		
		LinearLayout.LayoutParams editTextParam = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT, 3);
		LinearLayout.LayoutParams checkParam = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT, 1);
		
		horizontalLayout.addView(editText, editTextParam);
		horizontalLayout.addView(checkTextView, checkParam);
		
		addView(headerTextView);
		addView(horizontalLayout);
		
		//ADD MARGIN TO WHOLE FIELD
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, 5, 0, 5);	
		this.setLayoutParams(layoutParams);
		
		//IF LABEL IS PASSWORD USE PASSWORDFIELD
		if(isPassword) {
			setToPasswordField(true);
		}		
	}
	
	private void changeInputType() {
		if( (headerLabel.toLowerCase()).equals("password")) {
			isPassword=true;
			isEmail=false;
		}else if( (headerLabel.toLowerCase()).equals("email") || 
				  (headerLabel.toLowerCase()).equals("e-mail") ) {
			isPassword=false;
			isEmail=true;
		}else{
			isPassword=false;
			isEmail=false;
		}
	}

	private void editTextListeners() {
		editText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(isEmail)  {
					if(isEmail(s.toString()))
						checkTextView.setText("OK");
					else
						checkTextView.setText("Not OK");
				}
				else
					checkTextView.setText(s.toString());
			}
		});	
	}

	protected boolean isEmail(String s) {	 
		final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);;
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public void setToPasswordField(boolean bool){
		removeView(horizontalLayout);
		
		if(bool)
			addView(pc);
		else
			addView(horizontalLayout);
	}
	
	public void setTextColor(int color) {
		textColor = color;
		editText.setTextColor(textColor);
		checkTextView.setTextColor(textColor);
		pc.getEditText().setTextColor(textColor);
		pc.getCheckTextView().setTextColor(textColor);
	}
	
	public void setHeaderColor(int color){
		headerTextView.setTextColor(color);
	}
	
	//GETTERS AND SETTERS
	public String getHeaderLabel() {
		return headerLabel;
	}
	public void setHeaderLabel(String headerLabel) {
		this.headerLabel = headerLabel;
	}
	public TextView getHeaderTextView() {
		return headerTextView;
	}
	public void setHeaderTextView(TextView headerTextView) {
		this.headerTextView = headerTextView;
	}
	public TextView getCheckTextView() {
		return checkTextView;
	}
	public void setCheckTextView(TextView checkTextView) {
		this.checkTextView = checkTextView;
	}

}
