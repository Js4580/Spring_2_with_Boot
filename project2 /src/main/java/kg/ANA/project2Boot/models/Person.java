package kg.ANA.project2Boot.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "Ваше имя не должно быть пустным")
    @Size(min = 2, max = 100, message = "Ваше имя должно быть в пределах от 2 до 100")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+", message = "Ваше имя должно быть примерно такого шаблона, который описан тут: Амантурова Назар Алымбекович")
    private String fullName;
    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Год вашего рождения выше 1900")
    @Max(value = 2030, message = "Год вышего рождения не выше 2030")
    private int yearOfBirth;


    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int year_of_birth) {
        this.yearOfBirth = year_of_birth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
