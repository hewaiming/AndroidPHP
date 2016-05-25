package com.android.json;

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

public class Home extends Activity {
	public class SpinnerSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_LONG).show();
			switch (position) {
			case 0:
				areaId=11;
				break;
			case 1:
				areaId=12;
				break;
			case 2:
				areaId=13;
				break;
			case 3:
				areaId=21;
				break;
			case 4:
				areaId=22;
				break;
			case 5:
				areaId=23;
				break;	
			default:
				areaId=11;
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	}

	private int areaId;
	private Button dayTableBtn;
	private Spinner mSpinner;
	private ArrayAdapter<String> adapter;
	private static final String[] str = { "一厂房一区", "一厂房二区", "一厂房三区", "二厂房一区", "二厂房二区", "二厂房三区" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		dayTableBtn = (Button) findViewById(R.id.dayTableBtn);
		mSpinner = (Spinner) findViewById(R.id.spinner_area);
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
				startActivity(mIntent);
			}
		});
	}
}
