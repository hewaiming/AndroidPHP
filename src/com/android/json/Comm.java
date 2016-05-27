package com.android.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Comm {

	public List<JSONObject> JsonArrayToList(String data) {

		ArrayList<JSONObject> lists = null;
		try {
			JSONArray jsonarray = new JSONArray(data);

			lists = new ArrayList<JSONObject>();
			System.out.println("jsonarray.length()---" + jsonarray.length());
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobj = jsonarray.getJSONObject(i);
				lists.add(jsonobj);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return lists;
	}

	public List<dayTable> JsonArrayToListBean(String data) {

		ArrayList<dayTable> listBean = null;
		try {
			JSONArray jsonarray = new JSONArray(data);

			listBean = new ArrayList<dayTable>();
			System.out.println("jsonarray.length()---" + jsonarray.length());
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobj = jsonarray.getJSONObject(i);
				dayTable mday = new dayTable();
				mday.setPotNo(jsonobj.getInt("PotNo"));
				mday.setPotSt(jsonobj.getString("PotST"));
				if ((jsonobj.getString("PotST").toUpperCase()).equals("STOP")) {
					mday.setAeTime(0);
					mday.setAeV(0);
					mday.setSetV(0);
					mday.setRealSetV(0);
				} else {
					mday.setAeTime(jsonobj.getInt("AeTime"));
					mday.setAeV(jsonobj.getDouble("AeV"));
					mday.setSetV(jsonobj.getDouble("SetV"));
					mday.setRealSetV(jsonobj.getDouble("RealSetV"));
				}

				mday.setDdate(jsonobj.getString("Ddate"));
				listBean.add(mday);
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return listBean;
	}
}
