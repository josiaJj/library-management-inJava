package school.hei;

import school.hei.Model.Author;
import school.hei.Model.Book;
import school.hei.Repository.AuthorCrudOperations;
import school.hei.Repository.BookCrudOperations;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        Author author = new Author();

        BookCrudOperations bookCrudOperations = new BookCrudOperations();
        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();

        List<Book> allBooks = bookCrudOperations.findAll();
        List<Author> allAuthors = authorCrudOperations.findAll();

        System.out.println("List of all Books : \n" + allBooks);
        System.out.println("List of all Authors : \n" + allAuthors);
    }
}