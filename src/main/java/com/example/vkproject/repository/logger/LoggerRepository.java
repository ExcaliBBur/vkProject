package com.example.vkproject.repository.logger;

import com.example.vkproject.model.entity.logger.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<Log, Long> {
}
