package com.banksystem.aps2.cliente;


import com.banksystem.aps2.cartao.Cartao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



@Service
public class ClienteService {
    private final Map<UUID, Cliente> clientes = new HashMap<>();


    public Collection<Cliente> listarClientes() {return clientes.values();}


    public Cliente buscarPorCpf(String cpf) {
        return clientes.values().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public Cliente salvarCliente(Cliente cliente) throws IllegalAccessException {
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new IllegalAccessException("O CPF do cliente não pode ser nulo ou vazio");
        }

        if (clientes.containsKey(cliente.getCpf())) {
            throw new IllegalAccessException("Já existe um cliente com esse CPF");
        }

        UUID id = UUID.randomUUID();
        clientes.put(id, cliente);
        return cliente;
    }

    public Cliente editarCliente(String nome, String cpf, LocalDate data, Float salario) {
        Cliente cliente = clientes.values().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setDataNascimento(data);
        cliente.setSalario(salario);

        return cliente;
    }

}
