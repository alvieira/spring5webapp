package com.alvieira.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.alvieira.spring5webapp.model.Author;
import com.alvieira.spring5webapp.model.Book;
import com.alvieira.spring5webapp.model.Publisher;
import com.alvieira.spring5webapp.repositories.AuthorRepository;
import com.alvieira.spring5webapp.repositories.BookRepository;
import com.alvieira.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;

	private BookRepository bookRepository;

	private PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();

	}

	private void initData() {

		Publisher pub1 = new Publisher("Harper Collins", "7th Avenue");
		publisherRepository.save(pub1);
		Publisher pub2 = new Publisher("Wrox", "Street Venus");
		publisherRepository.save(pub2);

		// Eric
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "1234", pub1);

		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		authorRepository.save(eric);
		bookRepository.save(ddd);

		// Rod
		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "23444", pub2);

		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		authorRepository.save(rod);
		bookRepository.save(noEJB);
		
		System.out.println("Started in Bootstrap");
		System.out.println("Number of books: " + bookRepository.count());

	}

}
