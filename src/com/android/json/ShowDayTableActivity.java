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
	private String url ="http://125.64.59.11:8000/scgy/jquery/android/dayTableArea.php";
	private HttpGetData mhttpgetdata;
	private List<JSONObject> lists=null;
	private List<dayTable> listBean=null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_day_table);
		Intent intent=getIntent();		
		int areaID=intent.getIntExtra("areaID", 11);
		mhttpgetdata=(HttpGetData) new HttpGetData(url,this,this,Integer.toString(areaID)).execute();
		tv1=(TextView) findViewById(R.id.tv1);
		lv=(ListView) findViewById(R.id.lv);	
		
	}	

	@Override
	public void GetDataUrl(String data) {
		 System.out.println(data);
		 tv1.setText(data);
//		 lists=JsonArrayToList(data);
//		 System.out.println("lists----"+lists.toString());
		 listBean=JsonArrayToListBean(data);
		
		ArrayAdapter adapter=new ArrayAdapter<dayTable>(this,
                android.R.layout.simple_list_item_1, listBean);
		lv.setAdapter(adapter);
		 System.out.println("listBean----"+listBean.toString());
		 for(int i=0;i<listBean.size();i++){
			 System.out.println(listBean.get(i).toString());
		 }
		
	}

	private List<JSONObject> JsonArrayToList(String data) {
		
		try {
			JSONArray jsonarray=new JSONArray(data);	

			lists=new ArrayList<JSONObject>();			
			System.out.println("jsonarray.length()---"+jsonarray.length());
			for(int i=0;i<jsonarray.length();i++){
				JSONObject jsonobj=jsonarray.getJSONObject(i);
		        lists.add(jsonobj);
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return lists;
	}
private List<dayTable> JsonArrayToListBean(String data) {
		
		try {
			JSONArray jsonarray=new JSONArray(data);		

			listBean=new ArrayList<dayTable>();			
			System.out.println("jsonarray.length()---"+jsonarray.length());
			for(int i=0;i<jsonarray.length();i++){
				JSONObject jsonobj=jsonarray.getJSONObject(i);
				
				dayTable mday=new dayTable();
				mday.setPotNo(jsonobj.getInt("PotNo"));
				mday.setPotSt(jsonobj.getString("PotST"));
				mday.setAeTime(jsonobj.getInt("AeTime"));
				mday.setAeV(Math.round(jsonobj.getLong("AeV")*1000)*0.001d);				
				mday.setSetV((double)Math.round(jsonobj.getLong("SetV")*1000)*0.001d);
				mday.setRealSetV(Math.round(jsonobj.getLong("RealSetV")*1000)*0.001d);
				mday.setDdate(jsonobj.getString("Ddate"));
		        listBean.add(mday);
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return listBean;
	}
}
