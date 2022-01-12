package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Publisher mcGrawHill = new Publisher("TataMcGrawHill","123 William St", "Leesburg", "VA", 20176 );
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        mcGrawHill.getBooks().add(ddd);
        ddd.setPublisher(mcGrawHill);


        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(mcGrawHill);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        mcGrawHill.getBooks().add(noEJB);
        noEJB.setPublisher(mcGrawHill);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(mcGrawHill);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of Books for Publisher: " + mcGrawHill.getBooks().size());
    }
}
