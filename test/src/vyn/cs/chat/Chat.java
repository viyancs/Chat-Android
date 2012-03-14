/**
 * Chat - Chat-Android
 *
 * model chat return element Textview
 *
 * @author    M Sofiyan
 * @email     msofyancs@gmail.com
 * @skypeid   viyancs
 * please don't remove this comment if you want to using part of full this code
 * 
 **/
package vyn.cs.chat;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class Chat extends View{
	public Context contextview;
	public String value;
	
	/*
	 * constructur 
	 * @params context,value = value for textview
	 */
	public Chat(Context ctx, String value) {
		super(ctx);
		setContextview(ctx);
		setValue(value);
	}
	
	/*
	 * getting value
	 */
	public String getValue() {
		return value;
	}
	
	/*
	 * setting value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/*
	 * getting contextview
	 */
	public Context getContextview() {
		return contextview;
	}
	
	/*
	 * setting contextview
	 */
	public void setContextview(Context contextview) {
		this.contextview = contextview;
	}

	/*
	 * creating object textview, set value to textview 
	 * return textview()
	 */
	public TextView createTextView(String identity) {
		TextView newText = new TextView(getContext());
		newText.setText(identity + "  : " + value);
		return newText;
	}	
}
