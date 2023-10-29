package com.jeffrey.chatgpt.domain.whisper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;

/**
 * @author Jeffrey You
 * @description Transcriptions Request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TranscriptionsRequest {

    @Builder.Default
    private String model = WhisperEnum.Model.WHISPER_1.getCode();
    private String prompt;
    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = WhisperEnum.ResponseFormat.JSON.getCode();
    private double temperature = 0.2;

    private String language;

}
