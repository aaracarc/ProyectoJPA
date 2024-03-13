package org.iesvdm.gorrilandia.exception;

public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException(Long id) {
        super("Not found Pedido with id: " + id);
    }
}
