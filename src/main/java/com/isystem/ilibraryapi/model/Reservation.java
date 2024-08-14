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


}
