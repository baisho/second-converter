package com.webstardemo.second_converter.service;

import com.webstardemo.second_converter.model.ConvertData;
import com.webstardemo.second_converter.repository.ConverterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConverterService {

    private final ConverterRepository converterRepository;
    private static final Logger logger = LoggerFactory.getLogger(ConverterService.class);

    public ConverterService(ConverterRepository converterRepository) {
        this.converterRepository = converterRepository;
    }

    public String convertSeconds(long seconds) {

        logger.info("Conversion is under progress. The converted value: {}" , seconds);
        Map<String, Long> resultMap = new LinkedHashMap<>();
        long remainder = seconds;

        if (seconds == 0) {
            return "now";
        }

        if (seconds < 0) {
            return "Give a positive integer";
        }

        for (TimeUnits unit : TimeUnits.values()){
            long noOfSeconds = unit.getSeconds();
            long resultValue = remainder / noOfSeconds;
            remainder = remainder % noOfSeconds;
            resultMap.put(unit.name(), resultValue);
        }

        return generateAnswerText(resultMap);
    }

    private String generateAnswerText(Map<String, Long> resultMap) {
        logger.info("Answer text is generating");
        StringBuilder answer = new StringBuilder();
        for (Map.Entry<String, Long> entry: resultMap.entrySet()) {
            if (entry.getValue() == 1) {
                answer.append(entry.getValue()).append(" ").append(entry.getKey().toLowerCase(), 0, entry.getKey().length() - 1).append(", ");
            } else if (entry.getValue() > 1) {
                answer.append(entry.getValue()).append(" ").append(entry.getKey().toLowerCase()).append(", ");
            }
        }
        answer.setLength(answer.length() - 2);
        int lastComma = answer.toString().lastIndexOf(",");
        if (lastComma > -1) {
            answer.replace(lastComma, lastComma + 1, " and");
        }
        logger.info("Generated answer is: {}", answer.toString());
        return answer.toString();
    }

    public String save(long userId, long seconds) {

        ConvertData convertData = new ConvertData();
        convertData.setUserId(userId);
        convertData.setSeconds(seconds);
        convertData.setConvertedAnswer(convertSeconds(seconds));
        convertData.setConversionDate(LocalDateTime.now());
        converterRepository.save(convertData);
        return "";

    }

    public List<ConvertData> getHistoricDataByUser(Long userId) {
        List<ConvertData> data = converterRepository.findByUserId(userId);
        return data;
    }

    public enum TimeUnits {
        YEARS(60*60*24*365),
        DAYS(60*60*24),
        HOURS(60*60),
        MINUTES(60),
        SECONDS(1);

        private final long seconds;

        TimeUnits(long seconds) {
            this.seconds = seconds;
        }

        public long getSeconds() {
            return seconds;
        }
    }
}
