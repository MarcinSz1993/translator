package com.example.translator.service;

import com.example.translator.dto.TranslationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

@Component
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "translations",groupId = "translator-group")
    public void consumeMessageAndSaveToFile(TranslationDto translationDto) throws FileNotFoundException {
        String translation = translationDto.getTranslation();

        PrintWriter writer = new PrintWriter(new FileOutputStream("allTranslations.txt",true));
        writer.println(translation);
        writer.close();
    }
}
