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
import io.reactivex.Single;

import java.util.Collections;

/**
 * @author 小傅哥，微信：fustack
 * @description
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DefaultOpenAiSession implements OpenAiSession {

    private IOpenAiApi openAiApi;

    public DefaultOpenAiSession(IOpenAiApi openAiApi) {
        this.openAiApi = openAiApi;
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
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
    }

}