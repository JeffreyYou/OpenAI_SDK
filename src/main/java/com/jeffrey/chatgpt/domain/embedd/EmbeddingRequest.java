package com.jeffrey.chatgpt.domain.embedd;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeffrey You
 * @description Creates a variation of a given image.
 */
@Slf4j
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EmbeddingRequest implements Serializable {

    @NonNull
    @Builder.Default
    private String model = Model.TEXT_EMBEDDING_ADA_002.getCode();
    @NonNull
    private List<String> input;
    @Setter
    private String user;

    @Getter
    @AllArgsConstructor
    public enum Model {
        TEXT_EMBEDDING_ADA_002("text-embedding-ada-002"),
        ;
        private String code;
    }

}
