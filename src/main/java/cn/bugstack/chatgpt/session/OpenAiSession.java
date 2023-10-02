package cn.bugstack.chatgpt.session;

import cn.bugstack.chatgpt.domain.chat.ChatCompletionRequest;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionResponse;
import cn.bugstack.chatgpt.domain.qa.QACompletionRequest;
import cn.bugstack.chatgpt.domain.qa.QACompletionResponse;

/**
 * @author 小傅哥，微信：fustack
 * @description OpenAi 会话接口
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface OpenAiSession {

    /**
     * 文本问答
     * @param qaCompletionRequest 请求信息
     * @return                    返回结果
     */
    QACompletionResponse completions(QACompletionRequest qaCompletionRequest);

    /**
     * 文本问答；简单请求
     * @param question 请求信息
     * @return         返回结果
     */
    QACompletionResponse completions(String question);

    /**
     * 默认 GPT-3.5 问答模型
     * @param chatCompletionRequest 请求信息
     * @return                      返回结果
     */
    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);

}
