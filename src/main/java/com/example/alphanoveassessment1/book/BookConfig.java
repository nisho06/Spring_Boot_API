package com.example.alphanoveassessment1.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    public CommandLineRunner commandLineRunner(BookRepository bookRepository){
        return args -> {
            Book book1 = new Book(
                    "978-1-60309-467-2",
                    "Kodi",
                    "Jared Cullum",
                    2020,
                    11.19);
            Book book2 = new Book(
                    "978-1-60309-491-7",
                    "Monster on the Hill (Expanded Edition)",
                    "Rob Harrell",
                    2020,
                    11.99);

            bookRepository.saveAll(List.of(book1, book2));
        };
    }
}
