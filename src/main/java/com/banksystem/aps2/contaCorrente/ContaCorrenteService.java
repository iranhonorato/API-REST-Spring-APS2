package com.banksystem.aps2.contaCorrente;


import com.banksystem.aps2.cliente.Cliente;
import com.banksystem.aps2.cliente.ClienteService;
import com.banksystem.aps2.movimentacao.Movimentacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContaCorrenteService {

    private final Map<String, ContaCorrente> contas = new HashMap<>();

    public Collection<ContaCorrente> listarContas() {return contas.values();}

    public ContaCorrente cadastrarConta(ContaCorrente conta) {
        if (conta.getConta() == null || conta.getConta().isBlank()) {
            throw new IllegalArgumentException("O número da conta não pode ser nulo ou vazio");
        }

        if (contas.containsKey(conta.getConta())) {
            throw new IllegalArgumentException("Já existe uma conta com esse número corrente");
        }
        contas.put(conta.getConta(), conta);
        return conta;
    };

    public ArrayList<Movimentacao> listarMovimentacoes(String conta) {
        ContaCorrente contaCorrente = contas.get(conta);
        if (contaCorrente == null) {
            throw new IllegalArgumentException("Conta não encontrada");
        }
        return contaCorrente.listaMovimentacoes();
    }


    public void depositar(String conta, Float valor) {
        ContaCorrente contaCorrente = contas.get(conta);
        if (contaCorrente == null) {
            throw new IllegalArgumentException("Conta não encontrada");
        }
        contaCorrente.deposito(valor);
    }

    public void sacar(String conta, Float valor) {
        ContaCorrente contaCorrente = contas.get(conta);
        if (contaCorrente == null) {
            throw new IllegalArgumentException("Conta não encontrada");
        }
        contaCorrente.saque(valor);
    }

}
