package com.jeffrey.chatgpt.session;

import com.jeffrey.chatgpt.domain.chat.ChatCompletionRequest;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionResponse;
import com.jeffrey.chatgpt.domain.qa.QACompletionRequest;
import com.jeffrey.chatgpt.domain.qa.QACompletionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @author Jeffrey You
 * @description OpenAI Session
 */
public interface OpenAiSession {

    /**
     * Q & A Model
     * @param qaCompletionRequest Request Body
     * @return                    Return Result
     */
    QACompletionResponse completions(QACompletionRequest qaCompletionRequest);

    /**
     * Q & A Model String Input
     * @param question Request Body
     * @return         Return Result
     */
    QACompletionResponse completions(String question);

    /**
     * Q & A Model Stream
     * @param qaCompletionRequest Request Body
     * @param eventSourceListener EventSourceListener
     */
    EventSource completions(QACompletionRequest qaCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;


    /**
     * Chat Model GPT-3.5/4.0
     * @param chatCompletionRequest Request Body
     * @return                      Return Result
     */
    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);

    /**
     * Chat Model GPT-3.5/4.0 Stream
     * @param chatCompletionRequest Request Body
     * @param eventSourceListener   EventSourceListener
     * @return                      Return Result
     */
    EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;



}
