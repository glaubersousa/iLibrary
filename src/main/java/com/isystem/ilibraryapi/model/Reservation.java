package com.isystem.ilibraryapi.model;

import com.isystem.ilibraryapi.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "bookId")
    private Book book;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "userId")
    private User user;

    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Reservation() {
    }

    public Reservation(Book book, User user, LocalDate reservationDate, Status status) {
        this.book = book;
        this.user = user;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
