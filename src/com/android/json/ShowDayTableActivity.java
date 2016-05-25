package com.android.json;

import com.android.json.login.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowDayTableActivity extends Activity implements HttpGetListener {
	private TextView tv1;
	private TextView tv2;
	private String url ="http://125.64.59.11:8000/scgy/jquery/android/dayTableArea.php";
	private HttpGetData mhttpgetdata;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_day_table);
		Intent intent=getIntent();		
		int areaID=intent.getIntExtra("areaID", 11);
		mhttpgetdata=(HttpGetData) new HttpGetData(url,this,this,Integer.toString(areaID)).execute();
		tv1=(TextView) findViewById(R.id.tv1);
		tv2=(TextView) findViewById(R.id.tv2);	
		
	}	

	@Override
	public void GetDataUrl(String data) {
		 System.out.println(data);
		 tv1.setText(data);
		
	}
}
