package com.jeffrey.chatgpt.test;

import com.jeffrey.chatgpt.common.Constants;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionRequest;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionResponse;
import com.jeffrey.chatgpt.domain.chat.Message;
import com.jeffrey.chatgpt.domain.qa.QACompletionRequest;
import com.jeffrey.chatgpt.domain.qa.QACompletionResponse;
import com.jeffrey.chatgpt.session.Configuration;
import com.jeffrey.chatgpt.session.OpenAiSession;
import com.jeffrey.chatgpt.session.OpenAiSessionFactory;
import com.jeffrey.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jeffrey You
 * @description API Test
 */
@Slf4j
public class ApiTest {

    private OpenAiSession openAiSession;

    @Before
    public void test_OpenAiSessionFactory() {
        // 1. Configuration
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://api.openai.com/");
        configuration.setApiKey("Please input your API Key");
        // 2. SessionFactory
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        // 3. Open Session
        this.openAiSession = factory.openSession();
    }

    /**
     * Q & A Model
     */
    @Test
    public void test_qa_completions() throws JsonProcessingException {
        QACompletionResponse response01 = openAiSession.completions("Write Bubble Sort in Java");
        log.info("Test Result：{}", new ObjectMapper().writeValueAsString(response01.getChoices()));
    }

    /**
     * Q & A Model Stream
     */
    @Test
    public void test_qa_completions_stream() throws JsonProcessingException, InterruptedException {
        // 1. Create Request
        QACompletionRequest request = QACompletionRequest
              .builder()
              .prompt("Write Bubble Sort in Java")
              .stream(true)
              .build();

        for (int i = 0; i < 1; i++) {
            // 2. Send Request
            EventSource eventSource = openAiSession.completions(request, new EventSourceListener() {
                @Override
                public void onEvent(EventSource eventSource, String id, String type, String data) {
                    log.info("Test Result：{}", data);
                }
            });
        }
        // Wait Finish
        new CountDownLatch(1).await();
    }


    /**
     * Chat Model
     */
    @Test
    public void test_chat_completions() {
        // 1. Create Request
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("Write Bubble Sort in Java").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();
        // 2. Send Request
        ChatCompletionResponse chatCompletionResponse = openAiSession.completions(chatCompletion);
        // 3. Print Result
        chatCompletionResponse.getChoices().forEach(e -> {
            log.info("Test Result：{}", e.getMessage());
        });
    }

    /**
     * Chat Model Stream
     */
    @Test
    public void test_chat_completions_stream() throws JsonProcessingException, InterruptedException {
        // 1. Create Request
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
              .builder()
              .stream(true)
              .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("Write Bubble Sort in Java").build()))
              .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
              .build();
        // 2. Send Request
        EventSource eventSource = openAiSession.chatCompletions(chatCompletion, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                log.info("Test Result：{}", data);
            }
        });
        // 3. Wait Finish
        new CountDownLatch(1).await();
    }


}
