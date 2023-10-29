package com.jeffrey.chatgpt.domain.qa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Jeffrey You
 * @description Q & A Completion Request
 */
@Data
@Builder
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class QACompletionRequest implements Serializable {

    @NonNull
    @Builder.Default
    private String model = Model.TEXT_DAVINCI_003.getCode();
    @NonNull
    private String prompt;
    private String suffix;
    private double temperature = 0.2;
    @JsonProperty("top_p")
    private Double topP = 1d;
    private Integer n = 1;
    private boolean stream = false;
    private List<String> stop;
    @JsonProperty("max_tokens")
    private Integer maxTokens = 2048;
    @Builder.Default
    private boolean echo = false;
    @JsonProperty("frequency_penalty")
    private double frequencyPenalty = 0;
    @JsonProperty("presence_penalty")
    private double presencePenalty = 0;
    @JsonProperty("best_of")
    @Builder.Default
    private Integer bestOf = 1;
    private Integer logprobs;
    @JsonProperty("logit_bias")
    private Map logitBias;
    private String user;

    @Getter
    @AllArgsConstructor
    public enum Model {
        TEXT_DAVINCI_003("text-davinci-003"),
        TEXT_DAVINCI_002("text-davinci-002"),
        DAVINCI("davinci"),
        ;
        private String code;
    }

}
