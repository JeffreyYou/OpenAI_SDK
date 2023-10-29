package com.jeffrey.chatgpt.domain.qa;

import com.jeffrey.chatgpt.domain.other.Usage;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Q & A Completion Response
 */
@Data
public class QACompletionResponse implements Serializable {

    private String id;
    private String object;
    private String model;
    private QAChoice[] choices;
    private long created;
    private Usage usage;

}
