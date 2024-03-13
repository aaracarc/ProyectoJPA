package org.iesvdm.gorrilandia.repository;

import org.iesvdm.gorrilandia.domain.Pedido;
import org.iesvdm.gorrilandia.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}