package com.jeffrey.chatgpt.domain.files;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Delete File Response
 */
@Data
public class DeleteFileResponse implements Serializable {

    private String id;
    private String object;
    private boolean deleted;

}
