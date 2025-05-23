package com.webstardemo.second_converter.service;

import com.webstardemo.second_converter.model.ConvertData;
import com.webstardemo.second_converter.repository.ConverterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConverterServiceTest {

    @Mock
    private ConverterRepository converterRepository;

    @InjectMocks
    private ConverterService converterService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void convertSeconds_zeroSeconds_returnsNow() {
        String result = converterService.convertSeconds(0);
        assertEquals("now", result);
    }

    @Test
    void convertSeconds_negativeSeconds_returnsErrorMessage() {
        String result = converterService.convertSeconds(-10);
        assertEquals("Give a positive integer", result);
    }

    @Test
    void convertSeconds_justSeconds_correctFormat() {
        String result = converterService.convertSeconds(45);
        // 45 seconds = 45 seconds
        assertEquals("45 seconds", result);
    }

    @Test
    void convertSeconds_mixedTimeUnits_correctFormat() {
        // 3662 seconds = 1 hour, 1 minute and 2 seconds
        String result = converterService.convertSeconds(3662);
        assertEquals("1 hour, 1 minute and 2 seconds", result);
    }

    @Test
    void save_callsRepositorySave() {
        long userId = 1L;
        long seconds = 3662;

        String result = converterService.save(userId, seconds);

        assertEquals("", result);

        ArgumentCaptor<ConvertData> captor = ArgumentCaptor.forClass(ConvertData.class);
        verify(converterRepository, times(1)).save(captor.capture());

        ConvertData savedData = captor.getValue();
        assertEquals(userId, savedData.getUserId());
        assertEquals(seconds, savedData.getSeconds());
        assertNotNull(savedData.getConversionDate());
        assertEquals("1 hour, 1 minute and 2 seconds", savedData.getConvertedAnswer());
    }

    @Test
    void getHistoricDataByUser_returnsRepositoryData() {
        long userId = 42L;

        List<ConvertData> mockList = List.of(
                new ConvertData(userId, 60L, "1 minute", LocalDateTime.now()),
                new ConvertData(userId, 120L, "2 minutes", LocalDateTime.now())
        );

        when(converterRepository.findByUserId(userId)).thenReturn(mockList);

        List<ConvertData> result = converterService.getHistoricDataByUser(userId);

        assertEquals(2, result.size());
        verify(converterRepository, times(1)).findByUserId(userId);
    }
}
