package com.example.loginui;

import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
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
	
	public static int colorStart = Color.RED, colorEnd = Color.GREEN;
	
	SecurityLevel SL;
	private int textColor = Color.BLACK;
	
	/** 
	 * Defines the level of the security.  
     */
	public enum SecurityType{
		EASY, /**< EASY Level*/
		MEDIUM, /**< MEDIUM Level*/ 
		HARD; /**< HARD level*/
	}
	
	/**
	 * Constructor
	 * @param context - defines the context
	 */
	public PasswordComponent(Context context) {
		super(context);
		setSecurityLevel(SecurityType.EASY);
		horizontalLinearLayout = new LinearLayout(context);
		securityHorizontalLinearLayout = new LinearLayout(context);
		onCreate(context);
	}
	
	/**
	 * Constructor
	 * @param context - defines the context
	 * @param attrs - defines the attributes for the component
	 */
	public PasswordComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
		setSecurityLevel(SecurityType.EASY);
		horizontalLinearLayout = new LinearLayout(context);
		securityHorizontalLinearLayout = new LinearLayout(context);
		onCreate(context);
	}
	
	/**
	 * Set the text color of the component to parameter
	 * @param color - defines the color of the text
	 */
	public void setTextColor(int color){
		textColor = color;
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
		
		
		this.setOrientation(VERTICAL);
		
		horizontalLinearLayout.setOrientation(HORIZONTAL);
		horizontalLinearLayout.setWeightSum(editTextWeight + labelWeight);
		securityHorizontalLinearLayout.setOrientation(HORIZONTAL);
		securityHorizontalLinearLayout.setWeightSum(SL.getSecuritySections());
		
		securityView = new TextView(context);
		securityHorizontalLinearLayout.addView(securityView);
	
		label = new TextView(context);
		label.setText("");
		
		editText = new EditText(context);
		editText.setTextColor(textColor);
		editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		editText.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				word = s.toString();
				securityView.setBackgroundColor(SL.getSecurityColor(word));
				LinearLayout.LayoutParams securityParam = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT, SL.getSecurity(word));
				
				securityHorizontalLinearLayout.removeAllViews();
				securityHorizontalLinearLayout.addView(securityView, securityParam);
				
				if(SL.getSecurityValue(word)!=SL.getSecuritySections()){
					label.setText(labelNotOk);
				}
				else{
					label.setText(labelOk);
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
	 * @return {@link EditText}
	 */
	public EditText getEditText(){
		return editText;
	}
	
	/**
	 * Get the right label TextView
	 * @return {@link TextView}
	 */
	public TextView getCheckTextView(){
		return label;
	}
	
	/**
	 * Sets the weights in the component
	 * @param editTextWeight - defines weight of the {@link EditText} (right)
	 * @param labelWeight - - defines weight of the {@link TextView} (left)
	 */
	public void setWeight(int editTextWeight, int labelWeight){
		this.editTextWeight = editTextWeight;
		this.labelWeight = labelWeight;
	}
	
	/**
	 * Get the Password component's security label
	 * @return {@link String}
	 */
	public String getSecurityLabel(){
		int security = SL.getSecurityValue(word);
		
		for(int i=0;i<passwordSecurityLabels.length;i++)
			if(security>i-1 && security<=i)
				return passwordSecurityLabels[i];
		
		return "";
	}
	
	/**
	 * Get the current security value of the input string
	 * 
	 * @return security value 
	 */
	public int getSecurity(){
		return SL.getSecurityValue(word);
	}
	
	/**
	 * Get the number of sections in the passward bar.
	 * 
	 * @return security section
	 */
	public int getSections(){
		return SL.getSecuritySections();
	}
	
	/**
	 * Set {@link SecurityType} of the password component. EASY, MEDIUM or HARD.
	 * @param TYPE
	 */
	public void setSecurityLevel(SecurityType TYPE){
		SecurityLevel(TYPE);
	}
	
	/**
	 * Set colors of the password security bar. It will interpolate between the two parameter colors {@link integer}.
	 * @param start
	 * @param end
	 */
	public void passwordBarColors(int start, int end){
		colorStart = start;
		colorEnd = end;
		SL.setColors(colorStart, colorEnd);
	}
	
	/**
	 * Sets the level of the password security
	 * @param TYPE {@link SecurityLevel}
	 */
	public void SecurityLevel(SecurityType TYPE){
		switch (TYPE) {
			case EASY:
				this.SL = new SecurityEasy();
				break;
			case MEDIUM:
				this.SL = new SecurityMedium();
				break;
			case HARD:
				this.SL = new SecurityHard();
				break;
		}
		
		SL.setColors(colorStart, colorEnd);
		
		if(	securityHorizontalLinearLayout!=null)
			securityHorizontalLinearLayout.setWeightSum(SL.getSecuritySections()); 
	
		
	}

}

