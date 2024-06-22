package com.example.retrofit_api;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface APIService {
  String DOMAIN = "http://192.168.2.106:3000/";//192.168.1.249:3000
  @GET("/api/list")
  Call<List<CarModel>> getCars();
  @POST("/api/addCar")
  Call<CarModel> addCar(@Body CarModel carModel);
  @PUT("/api/updateCar/{id}")
  Call<Response<CarModel>> updateCarById(@Path("id") String id, @Body CarModel carWithoutImage);
  @DELETE("/api/deleteCar/{id}")
  Call<Response<Void>> deleteCarById(@Path("id") String id);

//    @GET("/api/search")
//    Call<List<CarModel>> searchCars(@Query("query") String query);

  @GET("/api/search")
  Call<List<CarModel>> searchCarsByName(@Query("name") String name);

//    @GET("/api/sort")
//    Call<List<CarModel>> sortCars(@Query("sortBy") String sortBy);

  @GET("/api/sort")
  Call<List<CarModel>> sortCars(@Query("sortBy") String sortBy);

  @Multipart
  @POST("/api/add-car-with-images")
  Call<CarModel> addCarWithImage(
          @Part("ten_xe_ph33056") RequestBody ten_xe_ph33056,
          @Part("mau_sac_ph33056") RequestBody mau_sac_ph33056,
          @Part("gia_ban_ph33056") RequestBody gia_ban_ph33056,
          @Part("mo_ta_ph33056") RequestBody mo_ta_ph33056,
          @Part MultipartBody.Part hinh_anh_ph33056 // Đảm bảo khớp với key mà server của bạn mong đợi
  );

}

//implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//
//implementation ("com.squareup.okhttp3:logging-interceptor:3.4.1")
//implementation ("com.squareup.okhttp3:okhttp:4.11.0")
//implementation ("com.google.code.gson:gson:2.10.1")
//androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
//
//
//implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
//implementation("com.google.firebase:firebase-analytics")
//implementation("com.google.firebase:firebase-auth:20.0.3")
//annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
//implementation ("com.github.bumptech.glide:glide:4.16.0")
