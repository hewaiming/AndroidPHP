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
import org.json.JSONObject;

import android.R.string;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class HttpGetData extends AsyncTask<String, Void, String> {
	private HttpClient mHttpClient;
	private HttpGet mHttpGet;
	private HttpResponse mHttpResponse;
	private HttpEntity mHttpEntity;
	private InputStream is;
	private StringBuffer sb;
	private ProgressDialog pDialog;
	private Context mContext;
	private String url;
	private String areaID;
	// 声明接口
	private HttpGetListener listener;
	private JSONParser jsonParser=new JSONParser();

	public HttpGetData() {

	}

	public HttpGetData(String url) {
		this.url = url;
	}

	public HttpGetData(String url, HttpGetListener listener,Context context,String areaId) {
		this.url = url;
		this.listener = listener;
		this.mContext=context;
		this.areaID=areaId;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(mContext);
		pDialog.setMessage("搜索数据....");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
		
	}

	@Override
	protected String doInBackground(String... params) {
	/*	// 首先创建一个客户端实例
		mHttpClient = new DefaultHttpClient();
		// 设置传递的方法
		mHttpGet = new HttpGet(url);*/

		// Building Parameters
		List<NameValuePair> mparams = new ArrayList<NameValuePair>();
		mparams.add(new BasicNameValuePair("areaID",areaID ));           

		Log.d("request!", "starting");            
		//Posting user data to script 
		JSONObject json = jsonParser.makeHttpRequest(
		       url, "POST", mparams);

		// full json response
		Log.d("Login attempt", json.toString());		
		
		/*		
		// 通过客户端进行发送
		mHttpResponse = mHttpClient.execute(mHttpGet);
		// 通过HttpResponse获取方法体
		mHttpEntity = mHttpResponse.getEntity();
		// 通过流获取具体的内容
		is = mHttpEntity.getContent();
		// 创建缓冲区
		BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
		sb = new StringBuffer();
		String line = null;
		while ((line = bReader.readLine()) != null) {
			sb.append(line);
		}*/
		return json.toString();		
	}

	@Override
	protected void onPostExecute(String result) {
		pDialog.dismiss();
		listener.GetDataUrl(result);
		super.onPostExecute(result);
	}

}
