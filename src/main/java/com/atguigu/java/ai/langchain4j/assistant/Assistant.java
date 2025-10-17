package com.atguigu.java.ai.langchain4j.assistant;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * 助手接口
 */
//因为我们在配置文件中同时配置了多个大语言模型， 所以需要在这里明确指定（EXPLICIT） 模型的beanName（qwenChatModel）
@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel")
public interface Assistant {

     /**
     * 与助手交互
     * @param userMessage 交互消息
     * @return 助手回复
     */
    String chat(String userMessage);

}
