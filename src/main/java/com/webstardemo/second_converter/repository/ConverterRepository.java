package com.webstardemo.second_converter.repository;

import com.webstardemo.second_converter.model.ConvertData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConverterRepository extends CrudRepository<ConvertData, Long> {

    List<ConvertData> findByUserId(Long userId);

}
