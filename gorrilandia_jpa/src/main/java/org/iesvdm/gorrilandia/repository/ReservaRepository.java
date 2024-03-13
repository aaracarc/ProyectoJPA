package org.iesvdm.gorrilandia.repository;

import org.iesvdm.gorrilandia.domain.Reserva;
import org.iesvdm.gorrilandia.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}