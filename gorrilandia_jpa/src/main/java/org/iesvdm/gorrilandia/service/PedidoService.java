package org.iesvdm.gorrilandia.service;

import org.iesvdm.gorrilandia.domain.Pedido;
import org.iesvdm.gorrilandia.exception.PedidoNotFoundException;
import org.iesvdm.gorrilandia.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    public List<Pedido> all() {
        return this.pedidoRepository.findAll();
    }
    public Pedido save(Pedido pedido) {
        return this.pedidoRepository.save(pedido);
    }
    public Pedido one(Long id) {
        return this.pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(id));
    }

    public void delete(Long id) {
        this.pedidoRepository.findById(id).ifPresentOrElse(
                pedido -> this.pedidoRepository.delete(pedido),
                () -> { throw new PedidoNotFoundException(id); }
        );
    }
}