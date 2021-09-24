package com.example.twquickadd;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TiddlyWikiSyncService {
    @PUT("recipes/default/tiddlers/{tiddlerTitle}")
    @Headers({"x-requested-with: TiddlyWiki",
            "Content-Type: application/json"})
    Call<Response> put(@Path("tiddlerTitle") String tiddlerTitle, @Body String content);
}
