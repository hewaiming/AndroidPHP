package com.android.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.R.string;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class HttpGetPotV extends AsyncTask<String, Void, String> {
	private ProgressDialog pDialog;
	private Context mContext;
	private String url;
	private int PotNo;
	private String Begindate,Enddate;
	// 声明接口
	private HttpGetListener listener;
	private JSONArrayParser jsonParser = new JSONArrayParser();

	public HttpGetPotV() {

	}

	public HttpGetPotV(String url) {
		this.url = url;
	}	

	public HttpGetPotV(String url, int potNo, String begindate, String enddate, HttpGetListener listener) {
	
		this.url = url;
		this.PotNo = potNo;
		this.Begindate = begindate;
		this.Enddate = enddate;
		this.listener = listener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(mContext);
		pDialog.setMessage("下载槽压数据....");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();

	}

	@Override
	protected String doInBackground(String... params) {
		// Building Parameters
		List<NameValuePair> mparams = new ArrayList<NameValuePair>();
		mparams.add(new BasicNameValuePair("PotNo", String.valueOf(PotNo)));
		mparams.add(new BasicNameValuePair("Begin_date",Begindate)); 
		mparams.add(new BasicNameValuePair("End_date",Enddate)); 
		JSONArray json = jsonParser.makeHttpRequest(url, "POST", mparams);

		// full json response
		Log.d("Login attempt", json.toString());

		return json.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		pDialog.dismiss();
		listener.GetDataUrl(result);
		super.onPostExecute(result);
	}

}
