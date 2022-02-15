package com.plb.vinylmgt.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "vinyl")
public class Vinyl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vinylSequenceGenerator")
    @SequenceGenerator(name = "vinylSequenceGenerator", allocationSize = 1)
    private Long id;

    @NotNull(message = "Song name must not be null")
    @Column(name = "song_name", nullable = false)
    private String songName;

    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @ManyToOne
    @JsonIgnoreProperties("vinyls")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("vinyls")
    private Author author;

    public Vinyl() {
    }

    public Vinyl(String songName, LocalDate releaseDate,  Author author, User user) {
        this.songName = songName;
        this.releaseDate = releaseDate;
        this.user = user;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vinyl vinyl = (Vinyl) o;
        return Objects.equals(id, vinyl.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vinyl{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", releaseDate=" + releaseDate +
                ", user=" + user +
                '}';
    }
}
