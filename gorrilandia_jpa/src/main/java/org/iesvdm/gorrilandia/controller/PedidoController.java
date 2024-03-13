package org.iesvdm.gorrilandia.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.gorrilandia.domain.Pedido;
import org.iesvdm.gorrilandia.domain.Pedido;
import org.iesvdm.gorrilandia.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping({"","/"})
    public List<Pedido> all() {
        log.info("Accediendo a todos los pedidos");
        return this.pedidoService.all();
    }

    @PostMapping({"","/"})
    public Pedido newPedido(@RequestBody Pedido pedido) {
        return this.pedidoService.save(pedido);
    }

    @GetMapping("/{id}")
    public Pedido one(@PathVariable("id") Long id) {
        return this.pedidoService.one(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable("id") Long id) {
        this.pedidoService.delete(id);
    }
}