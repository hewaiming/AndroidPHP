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

public class HttpGetData_date extends AsyncTask<String, Void, String> {
	private ProgressDialog pDialog;
	private Context mContext;
	private String url;
	// �����ӿ�
	private HttpGetListener listener;
	private JSONArrayParserPost jsonParser = new JSONArrayParserPost();

	public HttpGetData_date() {

	}

	public HttpGetData_date(String url) {
		this.url = url;
	}

	public HttpGetData_date(String url, HttpGetListener listener, Context context) {
		this.url = url;
		this.listener = listener;
		this.mContext = context;
		
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(mContext);
		pDialog.setMessage("��������....");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();

	}

	@Override
	protected String doInBackground(String... params) {
		// Building Parameters
//		List<NameValuePair> mparams = new ArrayList<NameValuePair>();
//		mparams.add(new BasicNameValuePair("date","" ));
		JSONArray json = jsonParser.makeHttpRequest(url, "POST");		

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
