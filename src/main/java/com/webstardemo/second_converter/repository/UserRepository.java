package com.webstardemo.second_converter.repository;

import com.webstardemo.second_converter.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findById(long id);
}
