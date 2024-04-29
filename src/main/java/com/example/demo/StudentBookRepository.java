package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface StudentBookRepository
        extends CrudRepository<Book,Long> {
}
