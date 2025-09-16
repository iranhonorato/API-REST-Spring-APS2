package com.banksystem.aps2.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Collection<Cliente> listarClientesController() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarPorCpfController(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscarPorCpf(cpf);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> salvarClienteController(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.salvarCliente(cliente);
            return ResponseEntity.ok(novoCliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> editarClienteController(@PathVariable String cpf, @RequestBody Cliente cliente) {
        try {
            Cliente clienteEditado = clienteService.editarCliente(cpf, cliente);
            return ResponseEntity.ok(clienteEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}