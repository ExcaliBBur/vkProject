package com.example.vkproject.service.logger;

import com.example.vkproject.model.entity.logger.Log;
import com.example.vkproject.repository.logger.LoggerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class LoggerService {
    private final LoggerRepository loggerRepository;

    public void saveLog(Log log) {
        loggerRepository.save(log);
    }

    public void log(Log myLog) {
        log.info(String.valueOf(myLog));
    }
}
