package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric","Clapton");
        Book jungle = new Book("Domain Driven jungle", "48476");
        Publisher giangi = new Publisher("b","c","d", "e");

        publisherRepository.save(giangi);
        eric.getBooks().add(jungle);
        authorRepository.save(eric);
        jungle.getAuthors().add(eric);
        jungle.setPublisher(giangi);
        bookRepository.save(jungle);
        giangi.getBooks().add(jungle);



        System.out.println("Publisher Count: " + publisherRepository.count());

        Author rod = new Author("Rod","Johonos");
        Book noEjB = new Book ("j2ee", "1432");
        noEjB.setPublisher(giangi);
        rod.getBooks().add(noEjB);
        noEjB.getAuthors().add(rod);
        giangi.getBooks().add(noEjB);
        authorRepository.save(rod);
        bookRepository.save(noEjB);
        publisherRepository.save(giangi);
        System.out.println("Started in bootstrap");
        System.out.println("Number of Authors " + authorRepository.count());
        System.out.println("Number of Books " + bookRepository.count());
        System.out.println("Number of Books of publisher " + giangi.getId() + " :" +giangi.getBooks().size());
     }

}
