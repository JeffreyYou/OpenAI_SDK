package cn.bugstack.chatgpt.session.defaults;

import cn.bugstack.chatgpt.common.Constants;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionRequest;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionResponse;
import cn.bugstack.chatgpt.domain.chat.Message;
import cn.bugstack.chatgpt.domain.qa.QACompletionRequest;
import cn.bugstack.chatgpt.domain.qa.QACompletionResponse;
import cn.bugstack.chatgpt.session.Configuration;
import cn.bugstack.chatgpt.IOpenAiApi;
import cn.bugstack.chatgpt.session.OpenAiSession;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.Collections;

/**
 * @author 小傅哥，微信：fustack
 * @description
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DefaultOpenAiSession implements OpenAiSession {

   /** 配置信息 */
   private final Configuration configuration;

   /** OpenAI 接口 */
   private final IOpenAiApi openAiApi;

   /** 工厂事件 */
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
      // 核心参数校验；不对用户的传参做更改，只返回错误信息。
      if (!qaCompletionRequest.isStream()){
         throw new RuntimeException("illegal parameter stream is false!");
      }

      // 构建请求信息
      Request request = new Request.Builder()
            .url(configuration.getApiHost().concat(IOpenAiApi.v1_completions))
            .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(qaCompletionRequest)))
            .build();

      // 返回事件结果
      return factory.newEventSource(request, eventSourceListener);

   }

   @Override
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
    }

   @Override
   public EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
      // 核心参数校验；不对用户的传参做更改，只返回错误信息。
      if (!chatCompletionRequest.isStream()){
         throw new RuntimeException("illegal parameter stream is false!");
      }

      // 构建请求信息
      Request request = new Request.Builder()
            .url(configuration.getApiHost().concat(IOpenAiApi.v1_chat_completions))
            .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(chatCompletionRequest)))
            .build();

      // 返回事件结果
      return factory.newEventSource(request, eventSourceListener);
   }

}