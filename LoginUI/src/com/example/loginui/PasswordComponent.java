package com.example.loginui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PasswordComponent extends LinearLayout{
	
	private EditText editText;
	private TextView label, securityView;
	private int editTextWeight, labelWeight;
	
	private String word, tag="DEBUG", msg="msg";
	private String[] passwordSecurityLabels = new String[]{"","Bad","Weak","Fair","Good","Very good", "Excellent"};
	
	private LinearLayout horizontalLinearLayout, securityHorizontalLinearLayout;
	
	public PasswordComponent(Context context) {
		super(context);
		
		onCreate(context);
	}
	
	public PasswordComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
				
		onCreate(context);
	}

	@SuppressLint("NewApi") private void onCreate(Context context) {
		
		editTextWeight = 3;
		labelWeight = 1;
		
		horizontalLinearLayout = new LinearLayout(context);
		securityHorizontalLinearLayout = new LinearLayout(context);
		
		this.setOrientation(VERTICAL);
		
		horizontalLinearLayout.setOrientation(HORIZONTAL);
		horizontalLinearLayout.setWeightSum(editTextWeight + labelWeight);
		securityHorizontalLinearLayout.setOrientation(HORIZONTAL);
		securityHorizontalLinearLayout.setWeightSum(6);
		
		securityView = new TextView(context);
		securityView.setScaleY(0.5f);
		securityHorizontalLinearLayout.addView(securityView);
	
		label = new TextView(context);
		label.setText("");
		
		editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		editText.setHint("Password");
		editText.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				word = s.toString();
				securityView.setText(getSecurityLabel());
				securityView.setBackgroundColor(getSecurityColor());
				LinearLayout.LayoutParams securityParam = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT, getSecurity());
				
				securityHorizontalLinearLayout.removeAllViews();
				securityHorizontalLinearLayout.addView(securityView, securityParam);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		LinearLayout.LayoutParams editTextParam = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT, editTextWeight);
		LinearLayout.LayoutParams labelParam = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT, labelWeight);

		horizontalLinearLayout.addView(editText, editTextParam);
		horizontalLinearLayout.addView(label, labelParam);
		
		this.addView(horizontalLinearLayout);
		this.addView(securityHorizontalLinearLayout);
	}
	
	public void setWeight(int editTextWeight, int labelWeight){
		this.editTextWeight = editTextWeight;
		this.labelWeight = labelWeight;
	}
	
	public int getSecurityColor() {
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

