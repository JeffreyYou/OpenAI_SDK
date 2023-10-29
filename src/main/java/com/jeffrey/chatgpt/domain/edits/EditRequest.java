package com.jeffrey.chatgpt.domain.edits;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Edit
 */
@Slf4j
@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EditRequest implements Serializable {

    @NonNull
    private String model = Model.CODE_DAVINCI_EDIT_001.getCode();
    @NonNull
    private String input;
    @NonNull
    private String instruction;
    @Builder.Default
    private double temperature = 0.2;
    @JsonProperty("top_p")
    private Double topP = 1d;
    private Integer n = 1;

    @Getter
    @AllArgsConstructor
    public enum Model{
        /** text-davinci-edit-001 */
        TEXT_DAVINCI_EDIT_001("text-davinci-edit-001"),
        /** code-davinci-edit-001 */
        CODE_DAVINCI_EDIT_001("code-davinci-edit-001"),
        ;
        private String code;
    }

}
