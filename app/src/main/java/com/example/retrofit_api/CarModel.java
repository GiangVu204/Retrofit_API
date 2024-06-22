package com.example.retrofit_api;

public class CarModel {
  private String _id;
  private String ten_xe_ph33056;

  private String mau_sac_ph33056;

  private double gia_ban_ph33056;

  private String mo_ta_ph33056;
  private String hinh_anh_ph33056;

  public CarModel(String ten_xe_ph33056, String mau_sac_ph33056, double gia_ban_ph33056, String mo_ta_ph33056, String hinh_anh_ph33056) {
    this.ten_xe_ph33056 = ten_xe_ph33056;
    this.mau_sac_ph33056 = mau_sac_ph33056;
    this.gia_ban_ph33056 = gia_ban_ph33056;
    this.mo_ta_ph33056 = mo_ta_ph33056;
    this.hinh_anh_ph33056 = hinh_anh_ph33056;
  }

  public CarModel(String _id, String ten_xe_ph33056, String mau_sac_ph33056, double gia_ban_ph33056, String mo_ta_ph33056, String hinh_anh_ph33056) {
    this._id = _id;
    this.ten_xe_ph33056 = ten_xe_ph33056;
    this.mau_sac_ph33056 = mau_sac_ph33056;
    this.gia_ban_ph33056 = gia_ban_ph33056;
    this.mo_ta_ph33056 = mo_ta_ph33056;
    this.hinh_anh_ph33056 = hinh_anh_ph33056;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getTen_xe_ph33056() {
    return ten_xe_ph33056;
  }

  public void setTen_xe_ph33056(String ten_xe_ph33056) {
    this.ten_xe_ph33056 = ten_xe_ph33056;
  }

  public String getMau_sac_ph33056() {
    return mau_sac_ph33056;
  }

  public void setMau_sac_ph33056(String mau_sac_ph33056) {
    this.mau_sac_ph33056 = mau_sac_ph33056;
  }

  public double getGia_ban_ph33056() {
    return gia_ban_ph33056;
  }

  public void setGia_ban_ph33056(double gia_ban_ph33056) {
    this.gia_ban_ph33056 = gia_ban_ph33056;
  }

  public String getMo_ta_ph33056() {
    return mo_ta_ph33056;
  }

  public void setMo_ta_ph33056(String mo_ta_ph33056) {
    this.mo_ta_ph33056 = mo_ta_ph33056;
  }

  public String getHinh_anh_ph33056() {
    return hinh_anh_ph33056;
  }

  public void setHinh_anh_ph33056(String hinh_anh_ph33056) {
    this.hinh_anh_ph33056 = hinh_anh_ph33056;
  }

}
