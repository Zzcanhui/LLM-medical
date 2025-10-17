package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.Assistant;
import com.atguigu.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private Assistant assistant;
    @Test
    public void testChatMemory() {
        String answer1 = assistant.chat("我是环环");
        System.out.println(answer1);

        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);
    }


    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testChatMemory2() {

        // 第一轮对话
        UserMessage userMessage1 = UserMessage.userMessage("我是环环");
        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        // 输出大语言模型的回复
        System.out.println(aiMessage1.text());

        // 第二轮对话
        UserMessage userMessage2 = UserMessage.userMessage("你知道我是谁吗");
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage1,
                aiMessage1, userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        // 输出大语言模型的回复
        System.out.println(aiMessage2.text());
    }

    @Test
    public void testChatMemory3() {
        // 创建一个消息窗口聊天内存， 最多存储10条消息
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // 将消息窗口聊天内存添加到助手的配置中
        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();


        // 第一轮对话
        String answer1 = assistant.chat("我是环环");
        System.out.println(answer1);

        // 第二轮对话
        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);

        String answer3 = assistant.chat("你知道我之前的对话吗");
        System.out.println(answer3);
    }


    @Autowired
    private MemoryChatAssistant memoryChatAssistant;

    /**
     * 测试有记忆的大模型助手
     */

    @Test
    public void testChatMemory4() {
        // 第一轮对话
        String answer1 = memoryChatAssistant.chat("我是环环");
        System.out.println(answer1);

        // 第二轮对话
        String answer2 = memoryChatAssistant.chat("我是谁");
        System.out.println(answer2);

        String answer3 = memoryChatAssistant.chat("上一句我问你什么");
        System.out.println(answer3);
    }

    @Autowired
    private SeparateChatAssistant separateChatAssistant;


    /**
     * 测试分离聊天记录的大模型助手
     */
    @Test
    public void testChatMemory5() {
        // 第一轮对话
        String answer1 = separateChatAssistant.chat(1, "我是环环");
        System.out.println(answer1);

        // 第二轮对话
        String answer2 = separateChatAssistant.chat(1, "我是谁");
        System.out.println(answer2);

        String answer3 = separateChatAssistant.chat(2, "我是谁");
        System.out.println(answer3);
    }
}