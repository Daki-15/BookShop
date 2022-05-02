package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Book {

    @Id
    private String bookName;
    private String authorName;
    private String bookType;
    private String publishingHouse;
    private float bookPrice;

    public Book(String bookName, String authorName, String bookType, String publishingHouse, float bookPrice) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookType = bookType;
        this.publishingHouse = publishingHouse;
        this.bookPrice = bookPrice;
    }

    public Book() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Float.compare(book.bookPrice, bookPrice) == 0 && Objects.equals(bookName, book.bookName) && Objects.equals(authorName, book.authorName) && Objects.equals(bookType, book.bookType) && Objects.equals(publishingHouse, book.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, authorName, bookType, publishingHouse, bookPrice);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", bookType='" + bookType + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
