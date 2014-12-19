package com.example.loginui;

import android.R.integer;
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
	
	private String word;
	private String[] passwordSecurityLabels = new String[]{"","Bad","Weak","Fair","Good","Very good", "Excellent"};
	
	private LinearLayout horizontalLinearLayout, securityHorizontalLinearLayout;
	protected String labelNotOk, labelOk;
	
	/**
	 * Constructor
	 * @param context - defines the context
	 */
	public PasswordComponent(Context context) {
		super(context);
		
		onCreate(context);
	}
	
	/**
	 * Constructor
	 * @param context - defines the context
	 * @param attrs - defines the attributes for the component
	 */
	public PasswordComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
				
		onCreate(context);
	}
	
	/**
	 * Set the text color of the component to parameter
	 * @param color - defines the color of the text
	 */
	public void setTextColor(int color){
		editText.setTextColor(color);
		label.setTextColor(color);
	}
	
	/**
	 * Function that is called on creation
	 * @param context - defines the context
	 */
	private void onCreate(Context context) {
		
		labelOk = "OK";
		labelNotOk = "Not OK";
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
		securityHorizontalLinearLayout.addView(securityView);
	
		label = new TextView(context);
		label.setText("");
		
		editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		editText.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				word = s.toString();
				securityView.setBackgroundColor(getSecurityColor());
				LinearLayout.LayoutParams securityParam = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT, getSecurity());
				
				securityHorizontalLinearLayout.removeAllViews();
				securityHorizontalLinearLayout.addView(securityView, securityParam);
				
				if(getSecurity()<=3){
					editText.setText(labelNotOk);
				}
				else{
					editText.setText(labelOk);
				}
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
	
	/**
	 * Get the EditText of the component
	 * @return EditText
	 */
	public EditText getEditText(){
		return editText;
	}
	
	/**
	 * Get the right label TextView
	 * @return TextView
	 */
	public TextView getCheckTextView(){
		return label;
	}
	
	/**
	 * Sets the weights in the component
	 * @param editTextWeight - defines weight of the EditText (right)
	 * @param labelWeight - - defines weight of the TexView (left)
	 */
	public void setWeight(int editTextWeight, int labelWeight){
		this.editTextWeight = editTextWeight;
		this.labelWeight = labelWeight;
	}
	
	/**
	 * Get the color corresponding to the Password component security
	 * @return Color {@link integer} 
	 */
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
	
	/**
	 * @return an integer corresponding the Password component's security from 0 to 6
	 */
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
	
	/**
	 * @return the Password component's security label
	 */
	public String getSecurityLabel(){
		int security = getSecurity();
		
		for(int i=0;i<passwordSecurityLabels.length;i++)
			if(security>i-1 && security<=i)
				return passwordSecurityLabels[i];
		
		return "";
	}
	
	/**
	 * @return true if the Password component contains any upper letters
	 */	
	private boolean hasUpperLetters(){
		return !word.equals(word.toLowerCase());
	}
	
	/**
	 * @return true if the Password component contains any lower letters
	 */
	private boolean hasLowerLetters(){
		return !word.equals(word.toUpperCase());
	}

	/**
	 * @return true if the Password component contains any numbers
	 */
	private boolean hasNumbers(){
		return word.matches(".*\\d.*");
	}	

	/**
	 * @return true if the Password component contains special characters
	 */
	private boolean hasSpecialCharacters(){
		return !word.matches("[A-Za-z0-9 ]*");
	}
	
	/**
	 * @return true if the Password component has more than 8 characters
	 */
	private boolean has8Chars(){
		return (word.length()>7)?true:false;
	}
	
	/**
	 * @return true if the Password component has more than 12 characters
	 */
	private boolean has12Chars(){
		return (word.length()>11)?true:false;
	}
}

