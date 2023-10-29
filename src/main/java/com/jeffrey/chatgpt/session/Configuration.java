package com.jeffrey.chatgpt.session;

import com.jeffrey.chatgpt.IOpenAiApi;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;

/**
 * @author Jeffrey You
 * @description Configuration
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    @Getter
    @Setter
    private IOpenAiApi openAiApi;


    @Getter
    @NotNull
    private String apiKey;

    @Getter
    private String apiHost;

    @Getter
    private String authToken;

    @Getter
    @Setter
    private OkHttpClient okHttpClient;

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

}
