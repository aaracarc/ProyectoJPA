package org.iesvdm.gorrilandia.service;

import org.iesvdm.gorrilandia.domain.Usuario;
import org.iesvdm.gorrilandia.exception.UsuarioNotFoundException;
import org.iesvdm.gorrilandia.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public List<Usuario> all() {
        return this.usuarioRepository.findAll();
    }
    public Usuario save(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }
    public Usuario one(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }
    public Usuario replace(Long id, Usuario usuario) {
        return this.usuarioRepository.findById(id).map(u -> {
            if (id.equals(usuario.getId())) {
                usuario.setId(id);
                return this.usuarioRepository.save(usuario);
            } else {
                throw new IllegalArgumentException("El ID del usuario no coincide con el ID proporcionado.");
            }
        }).orElseThrow(() -> new UsuarioNotFoundException(id));
    }
    public void delete(Long id) {
        this.usuarioRepository.findById(id).ifPresentOrElse(
                usuario -> this.usuarioRepository.delete(usuario),
                () -> { throw new UsuarioNotFoundException(id); }
        );
    }

    public Page<Usuario> findbynombrecontainingignorecase(String nombre, int pagina, int tamano, String orden) {
        Sort.Direction direction = orden.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pagina, tamano, Sort.by(direction, "nombre"));
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre, pageable);
    }

}