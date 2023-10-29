package com.jeffrey.chatgpt.domain.other;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeffrey You
 * @description Response
 */
@Data
public class OpenAiResponse<T> implements Serializable {

    private String object;
    private List<T> data;
    private Error error;


    @Data
    public class Error {
        private String message;
        private String type;
        private String param;
        private String code;
    }

}
