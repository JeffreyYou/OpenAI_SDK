package com.jeffrey.chatgpt.test;

import com.jeffrey.chatgpt.IOpenAiApi;
import com.jeffrey.chatgpt.common.Constants;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionRequest;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionResponse;
import com.jeffrey.chatgpt.domain.chat.Message;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jeffrey You
 * @description Http Client Test
 */
@Slf4j
public class HttpClientTest {

   private String openAiKey = "Please enter your API key here";

   @Test
   public void test_normal() {
      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient okHttpClient = new OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chain -> {
               Request original = chain.request();

               HttpUrl url = original.url();
               Request request = original.newBuilder()
                     .url(url)
                     .header(Header.AUTHORIZATION.getValue(), "Bearer " + this.openAiKey)
                     .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                     .method(original.method(), original.body())
                     .build();
               return chain.proceed(request);
            })
            .build();

      IOpenAiApi openAiApi = new Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(IOpenAiApi.class);

      Message message = Message.builder().role(Constants.Role.USER).content("Hello").build();
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

   @Test
   public void test_client_stream() throws JsonProcessingException, InterruptedException {
      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient okHttpClient = new OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chain -> {
               Request original = chain.request();


               HttpUrl url = original.url();
               Request request = original.newBuilder()
                     .url(url)
                     .header(Header.AUTHORIZATION.getValue(), "Bearer " + this.openAiKey)
                     .header(Header.CONTENT_TYPE.getValue(), ContentType.JSON.getValue())
                     .method(original.method(), original.body())
                     .build();
               return chain.proceed(request);
            })
            .build();

      Message message = Message.builder().role(Constants.Role.USER).content("Hello").build();
      ChatCompletionRequest chatCompletion = ChatCompletionRequest
            .builder()
            .messages(Collections.singletonList(message))
            .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
            .stream(true)
            .build();

      EventSource.Factory factory = EventSources.createFactory(okHttpClient);
      String requestBody = new ObjectMapper().writeValueAsString(chatCompletion);

      Request request = new Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), requestBody))
            .build();

      EventSource eventSource = factory.newEventSource(request, new EventSourceListener() {
         @Override
         public void onEvent(EventSource eventSource, String id, String type, String data) {
            log.info("Test Result：{}", data);
         }
      });

      new CountDownLatch(1).await();

   }

}
