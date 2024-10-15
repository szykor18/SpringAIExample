package com.szymonkornik.springai;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GymCoachAIService {

    private String openAiApiKey = "";

    private static final String initialPrompt = "You are professional gym coach, you can answer only if the " +
            "question is related to your professional area." +
            "If the question is not related to gym coaching you should answer with this text 'I'm sorry," +
            " this is not area of my expertise.' " +
            "Now, if you know how to act here is the question from the user: {question}";

    @Autowired
    public GymCoachAIService(@Value("${spring.ai.openai.api-key}") String openAiKey) {
        openAiApiKey = openAiKey;
    }

    public String returnResponseRelatedToCoaching(String question) {
        PromptTemplate template = new PromptTemplate(initialPrompt);
        template.add("question", question);

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .build();
        OpenAiChatModel openAiChatModel = new OpenAiChatModel(new OpenAiApi(openAiApiKey), options);

        Prompt prompt = template.create();
        ChatResponse chatResponse = openAiChatModel.call(prompt);

        return chatResponse.getResult().getOutput().getContent();
    }

}
