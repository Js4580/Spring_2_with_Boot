package kg.ANA.project2Boot.services;


import kg.ANA.project2Boot.models.Book;
import kg.ANA.project2Boot.models.Person;
import kg.ANA.project2Boot.repositories.BooksRepository;
import kg.ANA.project2Boot.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> find(){
        return booksRepository.findAll();
    }
    public List<Book> findAll(boolean sortByYear){
        if (sortByYear) return booksRepository.findAll(Sort.by("year"));
        else return booksRepository.findAll();
    }

    public Book findById(int id){
        return booksRepository.findById(id).stream().findAny().orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book){
        Book bookToBeUpdated = booksRepository.findById(id).get();

        book.setId(id);
        book.setOwner(bookToBeUpdated.getOwner());

        booksRepository.save(book);
    }
    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(
                book ->{
                    book.setCreatedAt(null);
                    book.setOwner(null);
                }
        );
    }
    
    @Transactional
    public void assign(int id, Person person){
        booksRepository.findById(id).ifPresent(
                book->{
                    book.setOwner(person);
                    book.setCreatedAt(new Date());
                }
        );
    }
    
    public Optional<Person> getBookOwner(int id){
        return Optional.ofNullable(findById(id).getOwner());
    }

    public List<Book> pagination(int page, int size, boolean sortByYear){
        if (sortByYear) return booksRepository.findAll(PageRequest.of(page, size, Sort.by("year"))).getContent();
        else return booksRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public List<Book> findByTitleStartingWith(String first_letters){
        return booksRepository.findByTitleStartingWith(first_letters);
    }
}
