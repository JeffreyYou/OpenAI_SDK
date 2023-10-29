package com.jeffrey.chatgpt.domain.chat;

import com.jeffrey.chatgpt.common.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jeffrey You
 * @description Message
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message implements Serializable {

    private String role;
    private String content;
    private String name;

    public Message() {
    }

    private Message(Builder builder) {
        this.role = builder.role;
        this.content = builder.content;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 建造者模式
     */
    public static final class Builder {

        private String role;
        private String content;
        private String name;

        public Builder() {
        }

        public Builder role(Constants.Role role) {
            this.role = role.getCode();
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

}
