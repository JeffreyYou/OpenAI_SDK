package cn.bugstack.chatgpt.test;

import cn.bugstack.chatgpt.IOpenAiApi;
import cn.bugstack.chatgpt.common.Constants;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionRequest;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionResponse;
import cn.bugstack.chatgpt.domain.chat.Message;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import io.reactivex.Single;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URL;
import java.util.Collections;

/**
 * @author 小傅哥，微信：fustack
 * @description
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class HttpClientTest {

    public static void main(String[] args) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    // 从请求中获取 token 参数，并将其添加到请求路径中
                    HttpUrl url = original.url().newBuilder()
                            .addQueryParameter("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZmciLCJleHAiOjE2ODMyNzIyMjAsImlhdCI6MTY4MzI2ODYyMCwianRpIjoiOTkwMmM4MjItNzI2MC00OGEwLWI0NDUtN2UwZGZhOGVhOGYwIiwidXNlcm5hbWUiOiJ4ZmcifQ.Om7SdWdiIevvaWdPn7D9PnWS-ZmgbNodYTh04Tfb124")
                            .build();

                    Request request = original.newBuilder()
                            .url(url)
                            .header(Header.AUTHORIZATION.getValue(), "Bearer " + "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")
                            .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                })
                .build();

        IOpenAiApi openAiApi = new Retrofit.Builder()
                .baseUrl("https://api.xfg.im/b8b6/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build().create(IOpenAiApi.class);

        Message message = Message.builder().role(Constants.Role.USER).content("写一个java冒泡排序").build();
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .messages(Collections.singletonList(message))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();

        Single<ChatCompletionResponse> chatCompletionResponseSingle = openAiApi.completions(chatCompletion);
        ChatCompletionResponse chatCompletionResponse = chatCompletionResponseSingle.blockingGet();
        chatCompletionResponse.getChoices().forEach(e -> {
            System.out.println(e.getMessage());
        });
    }

}
