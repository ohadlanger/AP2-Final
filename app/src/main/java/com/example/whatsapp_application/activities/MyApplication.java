package com.example.whatsapp_application.activities;

import android.app.Application;
import android.content.Context;

import com.example.whatsapp_application.entities.User;

public class MyApplication extends Application {

        public static String getToken() {
                return token;
        }

        public static User getUser() {
                return user;
        }

        public static Context context;
        public static String token;
        public static User user;

        public static Context getContext() {
            return context;
        }

        @Override
        public void onCreate() {
                super.onCreate();
                context = getApplicationContext();
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFyaWVsIiwiaWF0IjoxNjg3MzQxNjQwLCJleHAiOjE2ODczNDUyNDB9.hTYCj__te9R8f257rwYMtcJokDJPGy_V1bk4xpKRbsM";
                // create a user object
                user = new User("ariel", "Ari123a123!", "");
        }

}
