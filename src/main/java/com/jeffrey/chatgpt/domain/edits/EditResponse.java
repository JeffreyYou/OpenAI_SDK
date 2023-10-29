package com.jeffrey.chatgpt.domain.edits;

import com.jeffrey.chatgpt.domain.other.Choice;
import com.jeffrey.chatgpt.domain.other.Usage;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Edit
 */
@Data
public class EditResponse implements Serializable {

    private String id;
    private String object;
    private String model;
    private Choice[] choices;
    private long created;
    private Usage usage;

}
