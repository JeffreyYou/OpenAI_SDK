package com.jeffrey.chatgpt.domain.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Image
 */
@Data
public class Item implements Serializable {

    private String url;
    @JsonProperty("b64_json")
    private String b64Json;

}
