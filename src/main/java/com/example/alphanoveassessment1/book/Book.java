package com.example.alphanoveassessment1.book;


import jakarta.persistence.*;

@Entity(name="book")
@Table(name="book", uniqueConstraints = { @UniqueConstraint(
        name="UK_isbn", columnNames = "isbn")
})
public class Book {
    @SequenceGenerator(
            name="book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Id
    private Long bookId;
    private String isbn;
    private String name;
    private String author;
    private Integer publicationYear;
    private Double priceGbp;

    public Book(){
    }

    public Book(String isbn, String name, String author, Integer publicationYear, Double priceGbp) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publicationYear = publicationYear;
        this.priceGbp = priceGbp;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Double getPriceGbp() {
        return priceGbp;
    }

    public void setPriceGbp(double priceGbp) {
        this.priceGbp = priceGbp;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", priceGbp=" + priceGbp +
                '}';
    }
}
