package school.hei;

import school.hei.Model.*;
import school.hei.Repository.AuthorCrudOperations;
import school.hei.Repository.BookCrudOperations;
import school.hei.Repository.SubscribersCrudOperations;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        BookCrudOperations bookCrudOperations = new BookCrudOperations();
        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();
        SubscribersCrudOperations subscribersCrudOperations = new SubscribersCrudOperations();

        // test findAll()
        List<Book> allBooks = bookCrudOperations.findAll();
        System.out.println("List of all Books : \n" + allBooks);

        // test saveAll(List<T> toSave)
        List<Author> authorsToSave = new ArrayList<>();
        Author author1 = new Author("Author_X",  Sex.M);
        authorsToSave.add(author1);

        Author author2 = new Author("Author_Y", Sex.F);
        authorsToSave.add(author2);

        Author author3 = new Author("Author_Z", Sex.M);
        authorsToSave.add(author3);

        List<Author> savedAuthors = authorCrudOperations.saveAll(authorsToSave);

        System.out.println("Recently registered Authors : \n" + savedAuthors);

        // TEST delete(Subscribers)
        /*Subscribers subscriberToDelete = new Subscribers();
        subscriberToDelete.setUsername("Subscriber1");
        subscriberToDelete.setPassword("Password1");
        subscriberToDelete.setSex(Sex.F);

        subscribersCrudOperations.delete(subscriberToDelete);
        */
    }
}