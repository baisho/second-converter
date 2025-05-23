package com.webstardemo.second_converter.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConvertData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long seconds;
    private String convertedAnswer;
    private LocalDateTime conversionDate;

    public ConvertData() {
    }

    public ConvertData(Long userId, Long seconds, String convertedAnswer, LocalDateTime conversionDate) {
        this.userId = userId;
        this.seconds = seconds;
        this.convertedAnswer = convertedAnswer;
        this.conversionDate = conversionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public String getConvertedAnswer() {
        return convertedAnswer;
    }

    public void setConvertedAnswer(String convertedAnswer) {
        this.convertedAnswer = convertedAnswer;
    }

    public LocalDateTime getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(LocalDateTime conversionDate) {
        this.conversionDate = conversionDate;
    }
}


