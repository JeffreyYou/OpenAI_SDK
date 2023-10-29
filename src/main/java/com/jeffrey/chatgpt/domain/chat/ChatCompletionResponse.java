package com.jeffrey.chatgpt.domain.chat;

import com.jeffrey.chatgpt.domain.other.Usage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeffrey You
 * @description Chat Response
 */
@Data
public class ChatCompletionResponse implements Serializable {

    /** ID */
    private String id;
    /** 对象 */
    private String object;
    /** 模型 */
    private String model;
    /** 对话 */
    private List<ChatChoice> choices;
    /** 创建 */
    private long created;
    /** 耗材 */
    private Usage usage;

}
