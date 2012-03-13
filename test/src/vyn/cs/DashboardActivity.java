package vyn.cs;

import java.util.ArrayList;

import vyn.cs.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DashboardActivity extends Activity
{
	public Bundle user ;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_dashboard);
		ArrayList<String> param = getIntent().getExtras().getStringArrayList("username");
		initcomponent(param);	
	}
	
	
	
	private ListView lvUser ;
	private void initcomponent( final ArrayList<String> param)
	{

		lvUser = (ListView)findViewById(R.id.lv_user);	
		final Bundle params = getIntent().getExtras();
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, param);
		lvUser.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lvUser.setAdapter(arrayAdapter); 
		lvUser.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) 
			{
				Toast.makeText(DashboardActivity.this, "position = " + param.get(position), Toast.LENGTH_SHORT).show();
				Intent inten = new Intent(DashboardActivity.this, ChatLayoutTabActivity.class);
				inten.putExtra("singgleUsername", param.get(position));
				inten.putExtra("socketid", params.getString("socketid"));
				inten.putExtra("type", "singglechat");
				startActivity(inten);
			}
		});
	}
	
	
}
