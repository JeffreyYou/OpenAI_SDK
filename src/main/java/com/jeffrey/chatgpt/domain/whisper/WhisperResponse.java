package com.jeffrey.chatgpt.domain.whisper;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Whisper Response
 */
@Data
public class WhisperResponse implements Serializable {
    private String text;
}
