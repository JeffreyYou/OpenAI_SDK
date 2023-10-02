package cn.bugstack.chatgpt.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author 小傅哥，微信：fustack
 * @description 配置信息
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    @Getter
    @NotNull
    private String apiKey;

    @Getter
    private String apiHost;

    @Getter
    @NotNull
    private String authToken;

}
