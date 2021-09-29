package com.example.twquickadd;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SyncService extends Service {

    private Retrofit mHttpClient;
    private TiddlyWikiSyncService mTiddlyWikiHttpService;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHttpClient = new Retrofit.Builder().baseUrl("http://127.0.0.1/").build();
        mTiddlyWikiHttpService = mHttpClient.create(TiddlyWikiSyncService.class);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void syncWithDesktop() {
        retrofit2.Call call = mTiddlyWikiHttpService.put("aaa", "bbb");
        call.enqueue(new retrofit2.Callback<Response>() {
            @Override
            public void onResponse(Call call, Response response) {
                // TODO: add notification to get user know. And clear the todo list
                Log.i("response", response.body().toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // TODO: tell this to user using notification with retry button? And auto hide notification after retry success?
            }
        });
    }
}
