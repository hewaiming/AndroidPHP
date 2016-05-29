package com.android.bean;

public class PotV {
	private int PotV;
	private int Cur;	
	private String Ddate;
	public PotV(int potV, int cur, String ddate) {
		super();
		PotV = potV;
		Cur = cur;
		Ddate = ddate;
	}
	public PotV() {
		super();
	}
	public int getPotV() {
		return PotV;
	}
	public void setPotV(int potV) {
		PotV = potV;
	}
	public int getCur() {
		return Cur;
	}
	public void setCur(int cur) {
		Cur = cur;
	}
	@Override
	public String toString() {
		return "PotV [PotV=" + PotV + ", Cur=" + Cur + ", Ddate=" + Ddate + "]";
	}
	public String getDdate() {
		return Ddate;
	}
	public void setDdate(String ddate) {
		Ddate = ddate;
	}
	
}