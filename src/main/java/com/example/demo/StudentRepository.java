package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
     @Query("SELECT s FROM Student s WHERE s.email = ?1")
     Optional<Student> findStudentByEmail(String email);
     @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
     List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThan(
             String firstName,Integer age);

     @Query(
             value = "select * from student where first_name = :firstName and age >= :age",
             nativeQuery = true)
     List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterOrEqualNative(
             @Param("firstName") String firstName,
             @Param("age") Integer age);
}




































