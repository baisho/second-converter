package com.webstardemo.second_converter.controller;

import com.webstardemo.second_converter.model.ConvertData;
import com.webstardemo.second_converter.service.ConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convert")
public class ConverterController {

    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/{seconds}")
    public ResponseEntity<String> convert(@PathVariable long seconds) {
        String result = converterService.convertSeconds(seconds);
        return ResponseEntity.ok(result);
    }

    @PostMapping("{userId}/{seconds}")
    public ResponseEntity<String> save(@PathVariable long userId, @PathVariable long seconds) {
        String savedData = converterService.save(userId, seconds);
        return ResponseEntity.status(201).body(savedData);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<ConvertData>> getByUserId(@PathVariable Long userId) {
        List<ConvertData> dataList = converterService.getHistoricDataByUser(userId);
        if (dataList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dataList);
    }
}
