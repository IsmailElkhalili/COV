package com.example.cov19.Fragments;

import com.example.cov19.Notifications.MyResponse;
import com.example.cov19.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAobjpjwc:APA91bEAK9X6O7hoLaK-lyBLm44O1mPUjdydq4a5HSBla0sDh7qbaD0GUgfyywwZpVQI4BeqvmSnlaCfrujcKOMSgNx4QeDPAbxc6A1fRyeHZUGVvLk-MqqbiX5og35AwfvuHoAtt1aM"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body Sender body);
}
