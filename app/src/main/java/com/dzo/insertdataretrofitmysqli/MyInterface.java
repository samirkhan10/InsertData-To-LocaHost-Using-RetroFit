package com.dzo.insertdataretrofitmysqli;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyInterface {

    @FormUrlEncoded
    @POST("insertData.php")
    Call<myModel> addData  (@Field("name") String name , @Field("email") String email);
}
