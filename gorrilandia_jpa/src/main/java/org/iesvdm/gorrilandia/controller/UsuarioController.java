package org.iesvdm.gorrilandia.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.gorrilandia.domain.Usuario;
import org.iesvdm.gorrilandia.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = {"","/"})
    public List<Usuario> all() {
        log.info("Accediendo a todos los usuarios");
        return this.usuarioService.all();
    }

    @PostMapping({"","/"})
    public Usuario newUsuario(@RequestBody Usuario usuario) {
        return this.usuarioService.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario one(@PathVariable("id") Long id) {
        return this.usuarioService.one(id);
    }
    @PutMapping("/{id}")
    public Usuario replaceUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        return this.usuarioService.replace(id, usuario);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Long id) {
        this.usuarioService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Usuario>> buscarUsuariosPorNombre(
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamano", defaultValue = "10") int tamano,
            @RequestParam(value = "orden", defaultValue = "asc") String orden) {
        Page<Usuario> usuarios = usuarioService.findbynombrecontainingignorecase(nombre, pagina, tamano, orden);
        return ResponseEntity.ok(usuarios);
    }

}