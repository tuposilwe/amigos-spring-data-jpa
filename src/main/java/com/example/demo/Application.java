package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository
            ){
        return args -> {
            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu",firstName,lastName);

            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 90)
            );

            student.addBook(
                    new Book("Clean Code",
                            LocalDateTime.now().minusDays(4))
            );

            student.addBook(
                    new Book("Think and Grow Rich",
                            LocalDateTime.now())
            );

            student.addBook(
                    new Book("Spring Data JPA",
                            LocalDateTime.now().minusYears(1))
            );

            StudentIdCard studentIdCard = new StudentIdCard(
                    "123456782",
                    student);

            student.setStudentIdCard(studentIdCard);


            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L,1L),
                    student,
                    new Course("Computer Science","IT"),
                    LocalDateTime.now()
            ));

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L,2L),
                    student,
                    new Course("Machine Learning","IT"),
                    LocalDateTime.now().minusDays(18)
            ));

            studentRepository.save(student);


            studentRepository.findById(64L)
                    .ifPresent(
                            s -> {
                                System.out.println("fetch book lazy...");
                                List<Book> books = student.getBooks();
                                books.forEach(book -> {
                                    System.out.println(
                                            s.getFirstName() +" borrowed "+ book.getBookName()
                                    );
                                });
                            }
                    );

//            studentIdCardRepository
//                    .findById(1L)
//                    .ifPresent(System.out::println);

        };
    }

    private void generateRandomStudents(StudentRepository studentRepository){
        Faker faker = new Faker();
        for (int i = 0; i <= 20; i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu",firstName,lastName);

            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 90)
            );
            studentRepository.save(student);
        }
    }

}


































