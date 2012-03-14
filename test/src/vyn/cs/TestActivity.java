/**
 * Test Activity - Chat-Android
 *
 * implement login to chat
 * connect to socket io 
 *
 * @author    M Sofiyan
 * @email     msofyancs@gmail.com
 * @skypeid   viyancs
 * please don't remove this comment if you want to using part of full this code
 * 
 **/

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
						processTest.getSocketIo.emit("chat", new JSONObject().put("msg",
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

	
	
};
