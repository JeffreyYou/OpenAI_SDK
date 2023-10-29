package com.jeffrey.chatgpt.interceptor;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Jeffrey You
 * @description Custom Interceptor
 */
public class OpenAiInterceptor implements Interceptor {

    /** OpenAi apiKey  */
    private String apiKey;
    /** Auth Token If needed */
    private String authToken;

    public OpenAiInterceptor(String apiKey, String authToken) {
        this.apiKey = apiKey;
        this.authToken = authToken;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(this.auth(apiKey, chain.request()));
    }

    private Request auth(String apiKey, Request original) {
        // Add token if needed

//        HttpUrl url = original.url().newBuilder()
//                .addQueryParameter("token", authToken)
//                .build();

         // Set up request.
//        return original.newBuilder()
//                .url(url)
//                .header(Header.AUTHORIZATION.getValue(), "Bearer " + apiKey)
//                .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
//                .method(original.method(), original.body())
//                .build();

        // Add API Key
        return original.newBuilder()
                  .header("Authorization", "Bearer " + apiKey)
                  .build();
    }

}
