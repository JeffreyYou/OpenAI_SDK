package com.jeffrey.chatgpt.test;

import com.jeffrey.chatgpt.common.Constants;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionRequest;
import com.jeffrey.chatgpt.domain.chat.ChatCompletionResponse;
import com.jeffrey.chatgpt.domain.chat.Message;
import com.jeffrey.chatgpt.session.Configuration;
import com.jeffrey.chatgpt.session.OpenAiSession;
import com.jeffrey.chatgpt.session.OpenAiSessionFactory;
import com.jeffrey.chatgpt.session.defaults.DefaultOpenAiSessionFactory;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jeffrey You
 * @description Client Test
 */
public class ClientTest {

   public static void main(String[] args) throws InterruptedException {
      Configuration configuration = new Configuration();
      configuration.setApiHost("https://api.openai.com/");
      configuration.setApiKey("Please enter your API key here");
      OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
      OpenAiSession openAiSession = factory.openSession();

      System.out.println("Please enter the question：");

      ChatCompletionRequest chatCompletion = ChatCompletionRequest
            .builder()
            .messages(new ArrayList<>())
            .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
            .user("testUser01")
            .build();

      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNextLine()) {
         String text = scanner.nextLine();
         chatCompletion.getMessages().add(Message.builder().role(Constants.Role.USER).content(text).build());
         ChatCompletionResponse chatCompletionResponse = openAiSession.completions(chatCompletion);
         chatCompletion.getMessages().add(Message.builder().role(Constants.Role.USER).content(chatCompletionResponse.getChoices().get(0).getMessage().getContent()).build());

         System.out.println(chatCompletionResponse.getChoices().get(0).getMessage().getContent());
         System.out.println("Please enter your question：");
      }

   }

}
