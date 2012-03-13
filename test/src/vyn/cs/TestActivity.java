package vyn.cs;

import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import vyn.cs.chat.Chat;
import vyn.cs.chat.ProcessAllSocket;
import vyn.json.online.Online;
import vyn.json.online.ResponseRoot;
import vyn.socket.IOSocket;
import vyn.socket.MessageCallback;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestActivity extends Activity {
	/** Called when the activity is first created. */
	LinearLayout.LayoutParams layoutParams;
	static int i;
	private Button btnSend;
	private EditText inputLogin;
	public  IOSocket socket;
	MessageCallback testActivityCallback;

	private TextView lblWelcome;
	private LinearLayout linearLayout;
	public String session;
	public Context currentContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// finding id on element
		btnSend = (Button) findViewById(R.id.btnSend);
		inputLogin = (EditText) findViewById(R.id.txtChatBox);
		lblWelcome = (TextView) findViewById(R.id.lblWelcome);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		// create new element
		layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		currentContext = this;

		// to fix bug when request socket to server and don't forget add android
		// perminssion internet and android permisiion to network
		java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
		java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");

		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (btnSend.getText().toString().equals("login")) {
					ProcessAllSocket processTest = new ProcessAllSocket(currentContext,inputLogin.getText().toString());
					processTest.ConnectToSocket(socket,"testActivity");
				} else {
					// showing histori chat from client
					sendChat(linearLayout,layoutParams,inputLogin.getText().toString(), "Me",TestActivity.this);

					// send chat to server
					try {
						socket.emit("chat", new JSONObject().put("msg",
								inputLogin.getText().toString()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void sendChat(LinearLayout lay,LinearLayout.LayoutParams lp, String data, String identity,Context ctx ) {
		Chat chatClientServer = new Chat(ctx, data);
		layout.addLayout(lay,chatClientServer.createTextView(identity),lp);
	}

	public interface sendMessage{
		void addLayout(LinearLayout a,TextView b, LayoutParams c);
	}
	
	sendMessage layout = new sendMessage(){
		@Override
		public void addLayout(LinearLayout a, TextView b, LayoutParams c) {
			a.addView(b,c); 
			
		}
    };

	public void login() {
		// to open socket on execution just place the code oncreate only one
		// time after that client can make request and response
		socket = new IOSocket("http://192.168.43.191:3001", callback);
		//Log.d("status connection " ,socket.getConnection().toString());
		try {
			socket.connect();
			//setSocket(socket);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	MessageCallback callback = new MessageCallback() {
		@Override
		public void on(String event, final JSONObject... data) {
			System.out.println("the event is = " + event.toString());
			if (event.toString().equals("online")) {
				try {
					if (data[0].get("online").getClass().toString()
							.equals("class org.json.JSONArray")) {
//						runOnUiThread(new Runnable() {
//							public void run() {
//								String name = parseDataJson(data, "online");
//								Toast.makeText(getBaseContext(), "" + name, Toast.LENGTH_SHORT).show();
//								Intent inten = new Intent(TestActivity.this, DashboardActivity.class);
//								inten.putExtra("username", name);
//								inten.putExtra("ID", "834725");
//								startActivity(inten);
//							}
//						});
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(event.toString().equals("chat")){
				runOnUiThread(new Runnable(){
					public void run(){
						//ChatActivity chat = new ChatActivity();
						//chat.parseDataJsonChat("jgh", "Server");
					}
				});
				
			} else {
				runOnUiThread(new Runnable() {
					public void run() {
						Log.d("haloo ", "kok masuk sih");
						// send chat to android layout
						//sendChat(parseDataJson(data, "msg"), "Server");
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
				socket.emit("login", new JSONObject().put("username", inputLogin
						.getText().toString()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("server is connected");
		}

		@Override
		public void onDisconnect() {
			// Socket connection closed
			System.out.println("server is closed");
		}
	};

	public String parseDataJson(JSONObject[] data, String string) {
		Log.d("data jsonnya = ", data[0].toString());
		String name = null;
		Gson gson = new Gson();
		ResponseRoot response = gson.fromJson(data[0].toString(),
				ResponseRoot.class);

		Log.d("results", response.toString());
		List<Online> results = response.online;
		Log.d("results", results.toString());
		for (Online result : results) {
			sendChat(linearLayout,layoutParams,result.username, string,TestActivity.this);
			name = result.username;
			// Log.d("_id = ",result._id);
			// Toast.makeText(this, result._id, Toast.LENGTH_SHORT).show();
			// Toast.makeText(this, result.username, Toast.LENGTH_SHORT).show();
		}
		System.out.println(name);
		return name;
	};
	
	
};
