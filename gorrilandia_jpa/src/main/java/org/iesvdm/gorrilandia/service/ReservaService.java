package org.iesvdm.gorrilandia.service;

import org.iesvdm.gorrilandia.domain.Reserva;
import org.iesvdm.gorrilandia.exception.ReservaNotFoundException;
import org.iesvdm.gorrilandia.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
    public List<Reserva> all() {
        return this.reservaRepository.findAll();
    }
    public Reserva save(Reserva reserva) {
        return this.reservaRepository.save(reserva);
    }
    public Reserva one(Long id) {
        return this.reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));
    }
    public Reserva replace(Long id, Reserva reserva) {
        return this.reservaRepository.findById(id).map(u -> {
            if (id.equals(reserva.getId())) {
                reserva.setId(id);
                return this.reservaRepository.save(reserva);
            } else {
                throw new IllegalArgumentException("El ID de la reserva no coincide con el ID proporcionado.");
            }
        }).orElseThrow(() -> new ReservaNotFoundException(id));
    }
    public void delete(Long id) {
        this.reservaRepository.findById(id).ifPresentOrElse(
                reserva -> this.reservaRepository.delete(reserva),
                () -> { throw new ReservaNotFoundException(id); }
        );
    }
}