package com.android.json;

import java.util.ArrayList;
import java.util.List;

import com.android.json.Home.SpinnerSelectedListener;
import com.android.json.login.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Home extends Activity implements HttpGetListener {
	public class SpinnerSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_LONG).show();
			switch (position) {
			case 0:
				areaId = 11;
				break;
			case 1:
				areaId = 12;
				break;
			case 2:
				areaId = 13;
				break;
			case 3:
				areaId = 21;
				break;
			case 4:
				areaId = 22;
				break;
			case 5:
				areaId = 23;
				break;
			default:
				areaId = 11;
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	}

	private int areaId;
	private String seldate, enddate;
	private Button dayTableBtn, PotVBtn;
	private Spinner mSpinner, dateSpinner, ENDdateSpinner;
	private List<String> dateBean = new ArrayList<String>();
	private ArrayAdapter<String> adapter, adapterDate;
	private String url = "http://125.64.59.11:8000/scgy/android/odbcPhP/getDate.php";
	private HttpGetData_date mhttpgetdata_date;
	private static final String[] str = { "一厂房一区", "一厂房二区", "一厂房三区", "二厂房一区", "二厂房二区", "二厂房三区" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		mhttpgetdata_date = (HttpGetData_date) new HttpGetData_date(url, this, this).execute();
		dayTableBtn = (Button) findViewById(R.id.dayTableBtn);
		PotVBtn = (Button) findViewById(R.id.PotVBtn);
		mSpinner = (Spinner) findViewById(R.id.spinner_area);
		dateSpinner = (Spinner) findViewById(R.id.spinner_date);
		ENDdateSpinner = (Spinner) findViewById(R.id.spinner_ENDdate);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		mSpinner.setAdapter(adapter);
		// 添加事件Spinner事件监听
		mSpinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		// 设置默认值
		mSpinner.setVisibility(View.VISIBLE);

		dayTableBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent(getApplicationContext(), ShowDayTableActivity.class);
				mIntent.putExtra("areaID", areaId);
				mIntent.putExtra("date", seldate);
				startActivity(mIntent);
			}
		});
		PotVBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent PotVIntent = new Intent(getApplicationContext(), PotVActivity.class);
				PotVIntent.putExtra("PotNo", 2302);
				PotVIntent.putExtra("begindate", seldate);
				PotVIntent.putExtra("enddate", enddate);
				startActivity(PotVIntent);
				
			}
			
		});
	}

	@Override
	public void GetDataUrl(String data) {
		Comm comm = new Comm();
		dateBean = comm.JsonArrayToDate(data);
		adapterDate = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dateBean);
		// 设置下拉列表的风格
		adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		dateSpinner.setAdapter(adapterDate);
		ENDdateSpinner.setAdapter(adapterDate);
		ENDdateSpinner.setVisibility(View.VISIBLE);
		dateSpinner.setVisibility(View.VISIBLE);
		enddate = ENDdateSpinner.getItemAtPosition(0).toString();
		seldate = dateSpinner.getItemAtPosition(0).toString();
		
		dateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				seldate = dateSpinner.getItemAtPosition(position).toString();

			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});	
		ENDdateSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				enddate = ENDdateSpinner.getItemAtPosition(position).toString();
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {			
				
			}			
		});

	}
}
