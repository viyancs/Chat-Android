package vyn.cs;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import vyn.cs.chat.ProcessAllSocket;
import vyn.socket.IOSocket;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatActivity extends Activity {
    LinearLayout.LayoutParams lp;
	public String user;
	private Button btnSend;
	private EditText inputChat;
	private LinearLayout ly;
	public String datas;
	private TextView txtToChat;
	public IOSocket socketIO;
	private String socketid;

	public ChatActivity(){
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_chat);
		Bundle param = getIntent().getExtras();
		
		// finding id on element
		btnSend = (Button) findViewById(R.id.btnSend);
		inputChat = (EditText) findViewById(R.id.txtMessage);
		ly = (LinearLayout) findViewById(R.id.chatBox);
		txtToChat = (TextView) findViewById(R.id.txtChat);
		txtToChat.setText("chat to " + param.getString("singgle"));
		user = param.getString("singgle");
		socketid = param.getString("socketid");

		// create new element
		lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
        
		btnSend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				//making new object ProcessAllSocket
				ProcessAllSocket processTest = new ProcessAllSocket(ChatActivity.this,ly,lp,user);
				processTest.sendChat(ly, lp, inputChat.getText().toString(), "Me", ChatActivity.this);
				try {
					//processTest.getSocketIo().emit("coba", new JSONObject().put("username", "subhanallah"));
					ProcessAllSocket.getSocketIo().emit("chat", new JSONObject().put("msg", inputChat.getText()).put("user", user).put("socketid", socketid));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
}
