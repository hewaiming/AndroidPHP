package com.android.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.json.login.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowDayTableActivity extends Activity implements HttpGetListener {
	private TextView tv1;
	private ListView lv;
	private String url = "http://125.64.59.11:8000/scgy/android/odbcPhP/dayTableArea.php";
	private HttpGetData mhttpgetdata;
	private List<dayTable> listBean = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_day_table);
		Intent intent = getIntent();
		int areaID = intent.getIntExtra("areaID", 11);
		mhttpgetdata = (HttpGetData) new HttpGetData(url, this, this, Integer.toString(areaID)).execute();
		tv1 = (TextView) findViewById(R.id.tv1);
		lv = (ListView) findViewById(R.id.lv);

	}

	@Override
	public void GetDataUrl(String data) {
		System.out.println(data);
		tv1.setText(data);
		// lists=JsonArrayToList(data);
		// System.out.println("lists----"+lists.toString());
		Comm comm = new Comm();
		listBean = comm.JsonArrayToListBean(data);

		ArrayAdapter adapter = new ArrayAdapter<dayTable>(this, android.R.layout.simple_list_item_1, listBean);
		lv.setAdapter(adapter);
		System.out.println("listBean----" + listBean.toString());
		for (int i = 0; i < listBean.size(); i++) {
			System.out.println(listBean.get(i).toString());
		}

	}

}
