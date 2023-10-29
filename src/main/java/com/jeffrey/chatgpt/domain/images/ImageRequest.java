package com.jeffrey.chatgpt.domain.images;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Request Image
 */
@Slf4j
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ImageRequest extends ImageEnum implements Serializable {

    @NonNull
    private String prompt;
    @Builder.Default
    private Integer n = 1;
    @Builder.Default
    private String size = Size.size_256.getCode();
    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = ResponseFormat.URL.getCode();
    @Setter
    private String user;

}
