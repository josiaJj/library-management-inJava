package school.hei;

import school.hei.Model.Author;
import school.hei.Model.Book;
import school.hei.Model.Subscribers;
import school.hei.Repository.AuthorCrudOperations;
import school.hei.Repository.BookCrudOperations;
import school.hei.Repository.SubscribersCrudOperations;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        Author author = new Author();
        Subscribers subscribers = new Subscribers();

        BookCrudOperations bookCrudOperations = new BookCrudOperations();
        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();
        SubscribersCrudOperations subscribersCrudOperations = new SubscribersCrudOperations();

        List<Book> allBooks = bookCrudOperations.findAll();
        List<Author> allAuthors = authorCrudOperations.findAll();
        List<Subscribers> allSubscribers = subscribersCrudOperations.findAll();

        System.out.println("List of all Books : \n" + allBooks);
        System.out.println("List of all Authors : \n" + allAuthors);
        System.out.println("List of all Subscribers : \n" + allSubscribers);
    }
}