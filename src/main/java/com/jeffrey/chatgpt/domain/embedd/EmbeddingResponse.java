package com.jeffrey.chatgpt.domain.embedd;

import com.jeffrey.chatgpt.domain.other.Usage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeffrey You
 * @description Embedding Response
 */
@Data
public class EmbeddingResponse implements Serializable {

    private String object;
    private List<Item> data;
    private String model;
    private Usage usage;

}
