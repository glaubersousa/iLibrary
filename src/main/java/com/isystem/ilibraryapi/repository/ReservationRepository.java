package com.isystem.ilibraryapi.repository;

import com.isystem.ilibraryapi.enums.Status;
import com.isystem.ilibraryapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(Status status);

}
