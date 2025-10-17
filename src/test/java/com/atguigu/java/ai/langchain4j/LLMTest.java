package com.atguigu.java.ai.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LLMTest {

    @Test
    public void testGPTDemo() {
        // 初始化模型
        OpenAiChatModel model = OpenAiChatModel.builder()
                // LangChain4j提供的代理服务器， 该代理服务器会将演示密钥替换成真实密钥， 再将请求转发给OpenAI API
                .baseUrl("http://langchain4j.dev/demo/openai/v1") // 设置模型api地址（如果apiKey="demo"， 则可省略baseUrl的配置）
                .apiKey("demo") // 设置模型apiKey
                .modelName("gpt-4o-mini")// 设置模型名称
                .build();

        String answer = model.chat("你是谁啊");
        System.out.println(answer);


    }
}
