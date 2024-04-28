package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
             Student maria = new Student(
                     "Maria",
                     "Jones",
                     "maria.jones@amigoscode.edu",
                     21
             );

            Student maria2 = new Student(
                    "Maria",
                    "Jones",
                    "maria2.jones@amigoscode.edu",
                    25
            );

            Student ahmed = new Student(
                    "Ahmed",
                    "Ali",
                    "ahmed.ali@amigoscode.edu",
                    18
            );

//            studentRepository.saveAll(List.of(maria2));

             studentRepository
                     .findStudentByEmail("ahmed.ali@amigoscode.edu")
                     .ifPresentOrElse(System.out::println,
                             () -> System.out.println("student with email ahmed.ali@amigoscode.edu not found"));
             studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterOrEqualNative(
                     "Maria",
                     18
             ).forEach(System.out::println);
        };
    }
}


































