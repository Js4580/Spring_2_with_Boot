package kg.ANA.project2Boot.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Название книги не должно быть пустным")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Имя автора книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора книги должно быть в пределах от 2 до 100")
    @Pattern(regexp = "[А-Я][а-я]+ [А-Я][а-я]+", message = "Имя автора книги должно иметь такую форму: Амантурова Назари")
    private String author;

    @Column(name = "year")
    @Min(value = 1500, message = "Год выхода книги выше 1500")
    @Max(value = 2025, message = "Год вызода книги не выше 2025")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Transient
    private boolean expired;
    public Book() {

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
