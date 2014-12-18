package com.example.loginui;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class Field extends LinearLayout{
	
	private EditText editText;
	private String headerLabel;
	private TextView headerTextView, checkTextView;
	private LinearLayout horizontalLayout;
	private int textColor, backgroundColor;
	
	public Field(Context context) {
		super(context);
	}
	
	public Field(Context context, String headerLabel) {
		super(context);
		
		//INSTANCE EVERYTHING
		this.headerLabel = headerLabel;
		editText = new EditText(context);
		headerTextView = new TextView(context);
		headerTextView.setText(headerLabel);
		checkTextView = new TextView(context);
		horizontalLayout = new LinearLayout(context);
		
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
	}
	
	public void setTextColor(int color) {
		textColor = color;
		editText.setTextColor(textColor);
		checkTextView.setTextColor(textColor);
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
