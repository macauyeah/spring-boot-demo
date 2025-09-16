package io.github.macauyeah.springboot.tutorial.springbootdatadeletion.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;
    // @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    // @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void addBook(Book book){
        this.books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book){
        this.books.remove(book);
        book.setAuthor(null);
    }
}
