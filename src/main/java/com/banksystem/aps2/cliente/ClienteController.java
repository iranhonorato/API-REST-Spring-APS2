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
    public Collection<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscarPorCpf(cpf);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> salvarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.salvarCliente(cliente);
            return ResponseEntity.ok(novoCliente);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> atualizarCliente(@PathVariable String cpf, @RequestBody Cliente cliente) {
        try {
            Cliente clienteEditado = clienteService.editarCliente(cpf, cliente);
            return ResponseEntity.ok(clienteEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}