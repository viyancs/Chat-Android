package vyn.cs;

import java.util.ArrayList;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class ChatLayoutTabActivity extends TabActivity{
	TabHost tab;
	public String type;
	public String part;
	public TextView welcome;
	private Intent intent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);	
		
		intent = new Intent(this, ChatLayoutTabActivity.class);
		welcome = (TextView)findViewById(R.id.txtWelcome);
		tab = (TabHost)findViewById(android.R.id.tabhost);
		Bundle params = getIntent().getExtras();
		type = params.getString("type");
		part = params.getString("part");
		

		if(type.equals("main")){
			createMainTab();
		}else if(type.equals("singglechat")){
			singgleChat(params.getString("singgleUsername"));
		}
	}
		
		/*
		 * set element attribute
		 */
		
		public void setAttr(String value){
			Log.d("masuk ngga sih capek nih shit...", value);
			welcome.setText("welcome " + value);
		}
	
	/*
	 * create main tab after login
	 */
	public void createMainTab(){
        // Tab for Notification
        TabSpec notifySpec = tab.newTabSpec("notify");
        // setting Title and Icon for the Tab
        notifySpec.setIndicator("notify", getResources().getDrawable(R.drawable.icon_notify_chat));
        Intent usernameIntent = new Intent(this, DashboardActivity.class);
        Bundle params = getIntent().getExtras();
		ArrayList<String> param = getIntent().getExtras().getStringArrayList(
				"username");
		usernameIntent.putExtra("username", param);
		usernameIntent.putExtra("socketid", params.getString("welcome"));
		welcome.setText("welcome " + params.getString("welcome"));
		notifySpec.setContent(usernameIntent);
        

        // Tab for config this is not implement may be next
        TabSpec chatSinggle = tab.newTabSpec("setting");
        chatSinggle.setIndicator("config", getResources().getDrawable(R.drawable.icon_contact_chat));
        Intent songsIntent = new Intent(this, ChatActivity.class);
        songsIntent.putExtra("singgleUsername", "just in test");
        chatSinggle.setContent(songsIntent);
 
        // Tab for Videos
        TabSpec chatGroup = tab.newTabSpec("group");
        chatGroup.setIndicator("group", getResources().getDrawable(R.drawable.icon_chat_group));
        Intent videosIntent = new Intent(this, GroupActivity.class);
        chatGroup.setContent(videosIntent);
 
        // Adding all TabSpec to TabHost
        tab.addTab(notifySpec); // Adding photos tab
        tab.addTab(chatSinggle); // Adding songs tab
        tab.addTab(chatGroup); // Adding videos tab
	}
	
	/*
	 * create tab to singgle chat
	 */
	public void singgleChat(String username){
		//singgle cat content in tab
		TabSpec chatSinggle = tab.newTabSpec("notify");
        chatSinggle.setIndicator("notify", getResources().getDrawable(R.drawable.icon_notify_chat));
        Intent singgleIntent = new Intent(this, ChatActivity.class);
        singgleIntent.putExtra("singgle", username);
        chatSinggle.setContent(singgleIntent);
        tab.addTab(chatSinggle);
	}
}
