package com.jeffrey.chatgpt.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Chat Info
 */
@Data
public class ChatChoice implements Serializable {

    private long index;
    /** stream = true, return type is delta */
    @JsonProperty("delta")
    private Message delta;
    /** stream = false, return type is message */
    @JsonProperty("message")
    private Message message;
    @JsonProperty("finish_reason")
    private String finishReason;

}
