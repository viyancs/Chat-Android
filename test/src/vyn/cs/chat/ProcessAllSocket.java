/**
 * Process All Socket - Chat-Android
 *
 * connect to server socket io
 * handling response message from server
 * request message to server
 * update ui android to another activity
 *
 * @author    M Sofiyan
 * @email     msofyancs@gmail.com
 * @skypeid   viyancs
 * please don't remove this comment if you want to using part of full this code
 * 
 **/
package vyn.cs.chat;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import vyn.cs.ChatLayoutTabActivity;
import vyn.cs.interfaces.sendMessage;
import vyn.cs.interfaces.TestActivityInterface;
import vyn.json.parse.JSONParse;
import vyn.socket.IOSocket;
import vyn.socket.MessageCallback;

public class ProcessAllSocket extends Application{
	private Context currentContext;
	public  static IOSocket socketIo;
	private String txtLogin;
	public  static LinearLayout linearLayout;
	public  static LinearLayout.LayoutParams layoutParams;
	public  static String user;
	
	
	public LinearLayout getLinearLayout() {
		return linearLayout;
	}

	public void setLinearLayout(LinearLayout linearLayout) {
		this.linearLayout = linearLayout;
	}

	public LinearLayout.LayoutParams getLayoutParams() {
		return layoutParams;
	}

	public void setLayoutParams(LinearLayout.LayoutParams layoutParams) {
		this.layoutParams = layoutParams;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//private String socketid;
	public static String testing;
	
	/*
	 * using in testactivity
	 */
	public ProcessAllSocket(Context ctx, String login) {
		super();
		this.currentContext = ctx;
		this.txtLogin = login;
	}
	
	/*
	 * using in dashboardactivity
	 */
	public ProcessAllSocket(){
		super();
	}
	
	/*
	 * using in chat activity
	 */
	public ProcessAllSocket(Context ctx, LinearLayout ly, android.widget.LinearLayout.LayoutParams lp, String receiveUser){
		super();
		this.currentContext = ctx;
		setLayoutParams(lp);
		setLinearLayout(ly);
		setUser(receiveUser);
	}
	
	/*
	 * connect to server
	 */
	public void ConnectToSocket(IOSocket socket, String description) {
		if (description.equals("testActivity")) {
			tac.TestActivitySocket(socket, testActivityCallback);
		}else {
			System.out
					.println("opps....something wrong description is not equals....at ProcessAllSocket.class");
		}
	}

	/*
	 * create list in chat box
	 */
	public void sendChat(LinearLayout lay,LinearLayout.LayoutParams lp, String data, String identity,Context ctx ) {
		Chat chatClientServer = new Chat(ctx, data);
		layout.addLayout(lay,chatClientServer.createTextView(identity),lp);
	}


	public static IOSocket getSocketIo() {
		return socketIo;
	}

	public static void setSocketIo(IOSocket socketIo) {
		ProcessAllSocket.socketIo = socketIo;
	}

	/*
 * implement interface
 * =================================================================================\
 */
	TestActivityInterface tac = new TestActivityInterface() {
		
		@Override
		public void TestActivitySocket(IOSocket a, MessageCallback b) {
			// TODO Auto-generated method stub
			a = new IOSocket("http://192.168.1.22:3001", b);
    		try {
    			a.connect();
    			a.setSessionID("loginsession");
    			setSocketIo(a);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
		}
	};
	
	sendMessage layout = new sendMessage(){
		@Override
		public void addLayout(LinearLayout a, TextView b, LayoutParams c) {
			a.addView(b,c); 
			
		}
    };


	/*
	 * call back for web socket in TestActivity class
	 * 
	 * ============================================================
	 */
	MessageCallback testActivityCallback = new MessageCallback() {
		@Override
		public void on(String event, final JSONObject... data) {
			System.out.println("the event is = " + event.toString());
			final JSONParse json = new JSONParse();
			final Intent inten = new Intent(currentContext,ChatLayoutTabActivity.class);
			if (event.toString().equals("online")) {
				try {
					if (data[0].get("online").getClass().toString()
							.equals("class org.json.JSONArray")) {
						ArrayList<String> userOnline = json.parseJsonTestActivity(data, "is online");
						String idSocket = json.parseOneFieldJson(data, "socketId");
						inten.putExtra("username", userOnline);
						inten.putExtra("type", "main");
						inten.putExtra("welcome", idSocket);
						currentContext.startActivity(inten);
						//getSocketIo().disconnect(); //disconnect socket
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (event.toString().equals("chat")) {
				Log.d("check contecx now..?",currentContext.getApplicationContext().toString());
				((Activity) currentContext).runOnUiThread(new Runnable() {
					public void run() {
						JSONParse json = new JSONParse();
						String msg = json.parseDataJsonChat(data, "msg");
						Log.d("message",msg);
						sendChat(getLinearLayout(), getLayoutParams(), msg, getUser(),
								currentContext);
					}
				});
			}
		}

		@Override
		public void onMessage(String message) {
			// Handle simple messages
			System.out.println("the simple message is = " + message);
		}

		@Override
		public void onMessage(JSONObject message) {
			// Handle JSON messages
			System.out.println("the json message is = " + message);
		}

		@Override
		public void onConnect() {
			// Socket connection opened
			try {
				getSocketIo().emit("login", new JSONObject().put("username", txtLogin));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("server is connected");
		}

		@Override
		public void onDisconnect() {
			// Socket connection closed
			System.out.println("server is closed to test activity");
		}
	};


}

