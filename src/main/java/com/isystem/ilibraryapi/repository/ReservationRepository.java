package com.isystem.ilibraryapi.repository;

import com.isystem.ilibraryapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {



}
