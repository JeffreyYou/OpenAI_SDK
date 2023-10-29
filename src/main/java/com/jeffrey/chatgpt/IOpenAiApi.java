package com.jeffrey.chatgpt;

import com.jeffrey.chatgpt.domain.chat.ChatCompletionRequest;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionResponse;
import com.jeffrey.chatgpt.domain.qa.QACompletionRequest;
import com.jeffrey.chatgpt.domain.qa.QACompletionResponse;
import com.jeffrey.chatgpt.domain.billing.BillingUsage;
import com.jeffrey.chatgpt.domain.billing.Subscription;
import com.jeffrey.chatgpt.domain.edits.EditRequest;
import com.jeffrey.chatgpt.domain.edits.EditResponse;
import com.jeffrey.chatgpt.domain.embedd.EmbeddingRequest;
import com.jeffrey.chatgpt.domain.embedd.EmbeddingResponse;
import com.jeffrey.chatgpt.domain.files.DeleteFileResponse;
import com.jeffrey.chatgpt.domain.files.UploadFileResponse;
import com.jeffrey.chatgpt.domain.images.ImageRequest;
import com.jeffrey.chatgpt.domain.images.ImageResponse;
import com.jeffrey.chatgpt.domain.other.OpenAiResponse;
import com.jeffrey.chatgpt.domain.whisper.WhisperResponse;


import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;


import java.io.File;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Jeffrey You
 * @description ChatGPT API：https://platform.openai.com/playground
 */
public interface IOpenAiApi {
    String v1_completions = "v1/completions";
    String v1_chat_completions = "v1/chat/completions";


    /**
     * Default GPT-3.5 Chat Model
     *
     * @param chatCompletionRequest Request Body
     * @return                      Return Result
     */
    @POST("v1/chat/completions")
    Single<ChatCompletionResponse> completions(@Body ChatCompletionRequest chatCompletionRequest);

    /**
     * Q & A Model
     *
     * @param qaCompletionRequest Request Body
     * @return                    Return Result
     */
    @POST("v1/completions")
    Single<QACompletionResponse> completions(@Body QACompletionRequest qaCompletionRequest);



    /**
     * Edit Text
     *
     * @param editRequest Request Body
     * @return            Return Result
     */
    @POST("v1/edits")
    Single<EditResponse> edits(@Body EditRequest editRequest);

    /**
     * Generate Picture
     *
     * @param imageRequest Request Body
     * @return            Return Result
     */
    @POST("v1/images/generations")
    Single<ImageResponse> genImages(@Body ImageRequest imageRequest);


    /**
     * Modify Picture
     *
     * @param image          The image to edit
     * @param mask           Optional mask to apply to the image
     * @param requestBodyMap Request Body
     * @return               Return Result
     */
    @Multipart
    @POST("v1/images/edits")
    Single<ImageResponse> editImages(@Part MultipartBody.Part image, @Part MultipartBody.Part mask, @PartMap Map<String, RequestBody> requestBodyMap);

    /**
     * Embedding
     *
     * @param embeddingRequest Request Body
     * @return                 Return Result
     */
    @POST("v1/embeddings")
    Single<EmbeddingResponse> embeddings(@Body EmbeddingRequest embeddingRequest);

    /**
     * Get Files
     * @return Return Result
     */
    @GET("v1/files")
    Single<OpenAiResponse<File>> files();

    /**
     * Upload File
     *
     * @param file     file
     * @param purpose "fine-tune"
     * @return         Return Result
     */
    @Multipart
    @POST("v1/files")
    Single<UploadFileResponse> uploadFile(@Part MultipartBody.Part file, @Part("purpose") RequestBody purpose);

    /**
     * Delete File
     *
     * @param fileId File ID
     * @return       Return Result
     */
    @DELETE("v1/files/{file_id}")
    Single<DeleteFileResponse> deleteFile(@Path("file_id") String fileId);

    /**
     * Search File
     *
     * @param fileId File ID
     * @return       Return Result
     */
    @GET("v1/files/{file_id}")
    Single<File> retrieveFile(@Path("file_id") String fileId);

    /**
     * Search File Content
     *
     * @param fileId File ID
     * @return       Return Result
     */
    @Streaming
    @GET("v1/files/{file_id}/content")
    Single<ResponseBody> retrieveFileContent(@Path("file_id") String fileId);

    /**
     * Audio Transcription
     *
     * @param file           Audio file
     * @param requestBodyMap Request Body
     * @return               Return Result
     */
    @Multipart
    @POST("v1/audio/transcriptions")
    Single<WhisperResponse> speed2TextTranscriptions(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> requestBodyMap);

    /**
     * Audio Translation
     *
     * @param file           Audio file
     * @param requestBodyMap Request Body
     * @return               Return Result
     */
    @Multipart
    @POST("v1/audio/translations")
    Single<WhisperResponse> speed2TextTranslations(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> requestBodyMap);

    /**
     * Billing Subscription
     *
     * @return Return Result
     */
    @GET("v1/dashboard/billing/subscription")
    Single<Subscription> subscription();

    /**
     * Usage
     *
     * @param starDate Start Time
     * @param endDate  End Time
     * @return         Return Result
     */
    @GET("v1/dashboard/billing/usage")
    Single<BillingUsage> billingUsage(@Query("start_date") LocalDate starDate, @Query("end_date") LocalDate endDate);


}
