package guru.springframework.spring5webapp.bootstrap;

import ch.qos.logback.core.net.SyslogOutputStream;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by jt on 12/23/19.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        Book newBook = new Book("Test Book", "3936789");
        Publisher mcGrawHill = new Publisher("TataMcGrawHill","123 William St", "Leesburg", "VA", 20176 );
        publisherRepository.save(mcGrawHill);

        eric.getBooks().add(newBook);
        newBook.getAuthors().add(eric);
        newBook.setPublisher(mcGrawHill);
        mcGrawHill.getBooks().add(newBook);
        authorRepository.save(eric);
        bookRepository.save(newBook);
        publisherRepository.save(mcGrawHill);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(mcGrawHill);
        mcGrawHill.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(mcGrawHill);

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.getAuthors().add(eric);
        noEJB.setPublisher(mcGrawHill);
        mcGrawHill.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(mcGrawHill);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books for Publisher: " + mcGrawHill.getBooks().size());
        System.out.println("Publisher of Book DDD is : " + ddd.getPublisher().getName());
        System.out.println("Publisher of Book NOEJB is : " + noEJB.getPublisher().getName());
        System.out.println("Publisher_ID of Book NOEJB is : " + noEJB.getPublisher().getId());
        System.out.println("Authors of book NoEJB are : " + noEJB.getAuthors().stream().map(a->a.getFirstName()).collect(Collectors.toList()));
        System.out.println("No. of books Eric wrote : " + eric.getBooks().stream().map(b->b.getTitle()).collect(Collectors.toList()));
    }
}
