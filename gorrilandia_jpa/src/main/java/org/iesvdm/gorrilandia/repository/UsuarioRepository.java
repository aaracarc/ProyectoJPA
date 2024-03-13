package org.iesvdm.gorrilandia.repository;

import org.iesvdm.gorrilandia.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Page<Usuario> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}