package com.jeffrey.chatgpt.session.defaults;

import com.jeffrey.chatgpt.domain.chat.ChatCompletionRequest;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionResponse;
import com.jeffrey.chatgpt.domain.qa.QACompletionRequest;
import com.jeffrey.chatgpt.domain.qa.QACompletionResponse;
import com.jeffrey.chatgpt.session.Configuration;
import com.jeffrey.chatgpt.IOpenAiApi;
import com.jeffrey.chatgpt.session.OpenAiSession;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @author Jeffrey You
 * @description OpenAI API Session
 */
public class DefaultOpenAiSession implements OpenAiSession {

   private final Configuration configuration;

   private final IOpenAiApi openAiApi;

   private final EventSource.Factory factory;



   public DefaultOpenAiSession(Configuration configuration) {
      this.configuration = configuration;
      this.openAiApi = configuration.getOpenAiApi();
      this.factory = configuration.createRequestFactory();
   }

    @Override
    public QACompletionResponse completions(QACompletionRequest qaCompletionRequest) {
        return this.openAiApi.completions(qaCompletionRequest).blockingGet();
    }

    @Override
    public QACompletionResponse completions(String question) {
        QACompletionRequest request = QACompletionRequest
                .builder()
                .prompt(question)
                .build();
        Single<QACompletionResponse> completions = this.openAiApi.completions(request);
        return completions.blockingGet();
    }

   @Override
   public EventSource completions(QACompletionRequest qaCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
      if (!qaCompletionRequest.isStream()){
         throw new RuntimeException("illegal parameter stream is false!");
      }

      Request request = new Request.Builder()
            .url(configuration.getApiHost().concat(IOpenAiApi.v1_completions))
            .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(qaCompletionRequest)))
            .build();

      return factory.newEventSource(request, eventSourceListener);

   }

   @Override
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
    }

   @Override
   public EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
      if (!chatCompletionRequest.isStream()){
         throw new RuntimeException("illegal parameter stream is false!");
      }

      Request request = new Request.Builder()
            .url(configuration.getApiHost().concat(IOpenAiApi.v1_chat_completions))
            .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(chatCompletionRequest)))
            .build();

      return factory.newEventSource(request, eventSourceListener);
   }

}