package com.jeffrey.chatgpt.domain.images;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeffrey You
 * @description Image Response
 */
@Data
public class ImageResponse implements Serializable {
    private List<Item> data;
    private long created;
}
