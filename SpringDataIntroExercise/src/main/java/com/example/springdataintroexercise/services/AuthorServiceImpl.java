package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entitites.Author;
import com.example.springdataintroexercise.entitites.Book;
import com.example.springdataintroexercise.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookService bookService;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count()>0;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAllAndFlush(authors);
    }

    @Override
    public Author getRandomAuthor() {
        final long count = this.authorRepository.count();
        if(count!=0){
            Integer randomId = Math.toIntExact(new Random().nextLong(1L, count + 1L));
            return this.authorRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
        }
        throw new RuntimeException();
    }

    @Override
    public Set<Author> getAuthorsWithBooksBefore1990() {
        Set<Author> authors = this.bookService.getAllBooksBefore1990()
                .stream()
                .map(Book::getAuthor)
                .collect(Collectors.toSet());
        authors.forEach(a-> System.out.println(a.getFirstName()+" "+a.getLastName()));
        return authors;
    }

    @Override
    public Set<Author> getAuthorsOrderedByBooks() {
        Set<Author> authors = this.authorRepository.findAllOrderByBooksDesc();
        authors.forEach(a-> System.out.println(a.getFirstName()+" "+a.getLastName()+" "+a.getBooksList().size()));
        return authors;
    }

    @Override
    public Set<Author> getAuthorsWhoseFirstNameEndsWithString(String end) {
        Set<Author> authors = this.authorRepository.findAllByFirstNameEndingWith(end);
        authors.forEach(a-> System.out.println(a.getFirstName()+" "+a.getLastName()));
        return authors;
    }

    @Override
    public void printAuthorsWithTotalCopiesOfBooks() {
        List<Author> authors = authorRepository.findAll();
        HashMap<String, Integer> authorsCopiesMap = new HashMap<>();
        for(Author author : authors){
            authorsCopiesMap.put(author.getFirstName()+" "+author.getLastName(), this.bookService.getTotalCopiesOfBooksForAuthor(author));
        }

        List<Map.Entry<String, Integer>> entriesList = new ArrayList<>(authorsCopiesMap.entrySet());
        entriesList.sort((entry1, entry2)->entry2.getValue().compareTo(entry1.getValue()));

        LinkedHashMap<String, Integer> sortedAuthorsCopiesMap = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry:entriesList){
            sortedAuthorsCopiesMap.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : sortedAuthorsCopiesMap.entrySet()) {
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }

    }
    @Override
    public void printAuthorWithTheirTotalBooks(String fullName) {
        int countOfBooks = authorRepository.findAllBooksByGivenAuthor(fullName);
        System.out.printf("%s has written %d books%n",fullName,countOfBooks);
    }
}
