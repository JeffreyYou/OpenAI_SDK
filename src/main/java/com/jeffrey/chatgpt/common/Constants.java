package com.jeffrey.chatgpt.common;

/**
 * @author Jeffrey You
 * @description Role Definition
 */
public class Constants {

    public enum Role {

        SYSTEM("system"),
        USER("user"),
        ASSISTANT("assistant"),
        ;

        private String code;

        Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

    }

}
