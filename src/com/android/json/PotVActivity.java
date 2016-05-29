package com.android.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.bean.PotV;
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

public class PotVActivity extends Activity implements HttpGetListener {
	private TextView tv1;
	private ListView lv;
	private String url = "http://125.64.59.11:8000/scgy/android/odbcPhP/PotVoltage.php";
	private HttpGetPotV mhttpgetPotV;
	private List<PotV> listBean = new ArrayList<PotV>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_potv);
		Intent intent = getIntent();
		int PotNo = intent.getIntExtra("PotNo", 2301);
		String Begindate=intent.getStringExtra("begindate");
		String Enddate=intent.getStringExtra("enddate");
		mhttpgetPotV = (HttpGetPotV) new HttpGetPotV(url, PotNo,Begindate,Enddate,this).execute();
//		tv1 = (TextView) findViewById(R.id.tv1);
		lv = (ListView) findViewById(R.id.lv_potv);

	}

	@Override
	public void GetDataUrl(String data) {
		System.out.println(data);
//		tv1.setText(data);
		// lists=JsonArrayToList(data);
		// System.out.println("lists----"+lists.toString());
		Comm comm = new Comm();
		listBean = comm.JsonArrayToPotVBean(data);

		ArrayAdapter adapter = new ArrayAdapter<PotV>(this, android.R.layout.simple_list_item_1, listBean);
		lv.setAdapter(adapter);
		System.out.println("PotVBean----" + listBean.toString());
		for (int i = 0; i < listBean.size(); i++) {
			System.out.println(listBean.get(i).toString());
		}

	}

}
