package com.example.loginui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Text;

import android.R.bool;
import android.R.string;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
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
	private boolean required, isEmail, isPassword, correctInput;
	private String labelOk, labelNotOk;
	private boolean isUsername;
	private ArrayList<String> userList;
	
	/**
	 * Constructor
	 * @param context - defines the context
	 */
	public Field(Context context) {
		super(context);
		this.context = context;
	}
	
	/**
	 * Constructor
	 * @param context - defines context
	 * @param headerLabel - defines the header of the field 
	 */
	public Field(Context context, String headerLabel){
		this(context,headerLabel,true);
	}
	
	/**
	 * Constructor
	 * @param context - defines context
	 * @param headerLabel - defines the header of the field 
	 * @param required - define if the field should be required or not
	 */
	public Field(Context context, String headerLabel, Boolean required) {
		super(context);
		this.setRequired(required);
		this.setCorrectInput(required?false:true);
		this.context = context;
		
		labelOk = "OK";
		labelNotOk = "Not OK";
		
		//INSTANCE EVERYTHING
		this.headerLabel = headerLabel;
		editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_TEXT);
		headerTextView = new TextView(context);
		headerTextView.setText(required?headerLabel + " (required)":headerLabel);
		LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		textParams.setMargins(10, 0, 0, 0);
		headerTextView.setLayoutParams(textParams);
		checkTextView = new TextView(context);
		checkTextView.setText(required?labelNotOk:"");
		pc = new PasswordComponent(context);
		pc.getCheckTextView().setText(required?labelNotOk:"");
		horizontalLayout = new LinearLayout(context);
		isEmail = false;
		 
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
	}

	/**
	 * Adds Listener to the {@link EditText}
	 */
	private void editTextListeners() {
		editText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				if(!isRequired()){
					setCorrectInput(true);
					return;
				}
				
				if(isEmail)  {
					if(isEmail(s.toString())){
						setCorrectInput(true);
						checkTextView.setText(labelOk);
					}
					else{
						setCorrectInput(false);
						checkTextView.setText(labelNotOk);
					}
				}else if(isUsername){
					
					if(userList.contains(s.toString())){
						setCorrectInput(false);
						checkTextView.setText("Not available");
					}else if(s.toString().equals("")){
						setCorrectInput(false);
						checkTextView.setText("Not OK");
					}else{
						setCorrectInput(true);
						checkTextView.setText("Available!");
					}
					
				}
				else{
					if(s.toString().equals("")){
						setCorrectInput(false);
						checkTextView.setText(labelNotOk);
					}else{
						setCorrectInput(true);
						checkTextView.setText(labelOk);
					}
				}
					
			}
		});	
		
		pc.getEditText().addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.e("onText","onText");
				if(pc.getSecurity()!=pc.getSections()){
					Log.e("WHOOOO","HOHOHOHO");
					setCorrectInput(false);
				}
				else{
					setCorrectInput(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
	}

	/**
	 * Check if {@link string} s is a correct email.
	 * @param s - input {@link string}
	 * @return {@link bool} - true if correct email.
	 */
	protected boolean isEmail(String s) {	 
		final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);;
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	/**
	 * Set {@link Field} to PasswordField if input is true.
	 * @param bool - decides whether to set {@link Field} to PasswordField or not.
	 */
	
	public void setToEmailField(boolean bool){
		isEmail = bool;
	}

	/**
	 * Set {@link Field} to PasswordField if input is true.
	 * @param bool - decides whether to set {@link Field} to PasswordField or not.
	 */
	public void setToPasswordField(boolean bool){
		removeView(horizontalLayout);
		
		isPassword = true;
		
		if(bool)
			addView(pc);
		else
			addView(horizontalLayout);
	}
	
	/**
	 * Get text from {@link EditText} in {@link Field}
	 * @return {@link String}
	 */
	public String getText(){
		if(isPassword)
			return pc.getEditText().getText().toString();
		else
			return editText.getText().toString();
	}
	
	/**
	 * Set {@link Color} of the text in the {@link EditText} and check - {@link TextView}.
	 * @param color - defines the color
	 */
	public void setTextColor(int color) {
		textColor = color;
		editText.setTextColor(textColor);
		checkTextView.setTextColor(textColor);
		pc.getEditText().setTextColor(textColor);
		pc.getCheckTextView().setTextColor(textColor);
	}
	
	/**
	 * Set {@link Color} of the text in the header.
	 * @param color - defines the color.
	 */
	public void setHeaderColor(int color){
		headerTextView.setTextColor(color);
	}
	
	/**
	 * Add user to the userlist.
	 * @param s - user name {@link string}.
	 */
	public void addUser(String s){
		if(userList!=null)
			userList.add(s);
	}
	
	/**
	 * Set userlist to parameter list.
	 * @param list - {@link ArrayList}
	 */
	public void setUserList(ArrayList<String> list){
		if(userList!=null)
			userList = list;
	}
	
	/**
	 * Get {@link EditText} of the {@link Field}
	 * @return {@link EditText}
	 */
	public EditText getEditText(){
		if(isPassword)
			return pc.getEditText();
		else
			return editText;
	}
	
	/**
	 * Get the header label of the {@link Field}.
	 * @return {@link String}
	 */
	public String getHeaderLabel() {
		return headerLabel;
	}
	
	/**
	 * Set the header label of the {@link Field} to input {@link string}
	 * @param headerLabel - {@link String}
	 */
	public void setHeaderLabel(String headerLabel) {
		this.headerLabel = headerLabel;
	}
	
	/**
	 * Get the header {@link TextView} of the {@link Field}
	 * @return {@link TextView}
	 */
	public TextView getHeaderTextView() {
		return headerTextView;
	}
	
	/**
	 * Set the header {@link TextView} of the {@link Field} to input {@link TextView}
	 * @param headerTextView - {@link TextView}
	 */
	public void setHeaderTextView(TextView headerTextView) {
		this.headerTextView = headerTextView;
	}
	
	/**
	 * Get the check {@link TextView} of the {@link Field}
	 * @return {@link TextView}
	 */
	public TextView getCheckTextView() {
		return checkTextView;
	}
	
	/**
	 * Set the check {@link TextView} of the {@link Field} to input {@link TextView}
	 * @param checkTextView - {@link TextView}
	 */
	public void setCheckTextView(TextView checkTextView) {
		this.checkTextView = checkTextView;
	}

	/**
	 * Check if {@link Field} is reuired
	 * @return {@link bool} - true if {@link Field} is required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * Set if the {@link Field} is required or not.
	 * @param required - {@link bool}
	 */
	public void setRequired(boolean required) {
		this.required = required;
		if(headerTextView!=null && headerLabel!=null)
			headerTextView.setText(required?headerLabel + " (required)":headerLabel);
	}

	/**
	 * Check if {@link Field} has correct input.
	 * @return {@link bool} - true if correct.
	 */
	public boolean isCorrectInput() {
		return correctInput;
	}

	/**
	 * Set correct input of the {@link Field} to input {@link bool}.
	 * @param correctInput - {@link bool}
	 */
	public void setCorrectInput(boolean correctInput) {
		this.correctInput = correctInput;
	}
	
	public boolean isPassword(){
		return isPassword;
	}
	public PasswordComponent getPasswordComponent(){
		return pc;
	}

}
