package com.example.alphanoveassessment1.book;

import com.example.alphanoveassessment1.exception.ApiRequestException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void registerBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(book.getIsbn());
        if (bookOptional.isPresent()) {
            throw new ApiRequestException("Book with ISBN - " + book.getIsbn() + " already exists.",
                    HttpStatus.CONFLICT);
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) throws IllegalStateException{
        boolean isBookExist = bookRepository.existsById(bookId);
        if (!isBookExist){
            throw new ApiRequestException("Book with id - " + bookId + " does not exist.",
                    HttpStatus.NOT_FOUND);
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String isbn, String name, String author, Integer publicationYear, Double priceGbp) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException(
                "Book with id  " + bookId + " does not exist."));
        if (isbn != null && isbn.length()>0 && !(Objects.equals(isbn, book.getIsbn()))){
            if (bookRepository.findBookByIsbn(isbn).isPresent()){
                throw new ApiRequestException("Book with ISBN " + isbn + " already exists.",
                        HttpStatus.CONFLICT);
            }
            book.setIsbn(isbn);
        }

        if (name != null && name.length()>0 && !(Objects.equals(name, book.getName()))){
            book.setName(name);
        }

        if (author != null && author.length()>0 && !(Objects.equals(author, book.getAuthor()))){
            book.setAuthor(author);
        }

        if (publicationYear != null && publicationYear > 0 && !(Objects.equals(publicationYear,
                book.getPublicationYear()))){
            book.setPublicationYear(publicationYear);
        }

        if (priceGbp != null && priceGbp > 0 && !(Objects.equals(priceGbp, book.getPriceGbp()))){
            book.setPriceGbp(priceGbp);
        }
    }

    public Book getBookById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()){
          throw new ApiRequestException("Book with id " + bookId + " does not exist.",
                  HttpStatus.NOT_FOUND);
        }
        return book.get();
    }
}
