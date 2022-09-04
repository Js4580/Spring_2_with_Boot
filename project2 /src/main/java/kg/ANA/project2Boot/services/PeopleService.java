package kg.ANA.project2Boot.services;


import kg.ANA.project2Boot.models.Book;
import kg.ANA.project2Boot.models.Person;
import kg.ANA.project2Boot.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id){
        return peopleRepository.findById(id).stream().findAny().orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person){
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            person.get().getBooks().forEach(book->{
                long valueInMillis = Math.abs(book.getCreatedAt().getTime() - new Date().getTime());
                if (valueInMillis > 864_000_000) book.setExpired(true);
            });
            return person.get().getBooks();
        }
        return Collections.emptyList();
    }
    @Transactional
    public Person findByName(String full_name){
        return peopleRepository.findByFullName(full_name);
    }

    @Transactional
    public Optional<Person> trueOrFalsePersonIsPresent(String full_name){
        return Optional.ofNullable(findByName(full_name));
    }


}
