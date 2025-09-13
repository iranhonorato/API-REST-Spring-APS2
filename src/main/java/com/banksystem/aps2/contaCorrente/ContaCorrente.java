package com.banksystem.aps2.contaCorrente;

import com.banksystem.aps2.cartao.Cartao;
import com.banksystem.aps2.cliente.Cliente;
import com.banksystem.aps2.movimentacao.Movimentacao;
import java.time.LocalDate;
import java.util.ArrayList;
import static com.banksystem.aps2.movimentacao.Movimentacao.TipoMovimentacao.DEPOSITO;
import static com.banksystem.aps2.movimentacao.Movimentacao.TipoMovimentacao.SAQUE;




public class ContaCorrente {
    private String agencia;
    private String conta;
    private Float saldo;
    private final Float limite;
    private final Cliente cliente;
    private final ArrayList<Movimentacao> movimentacoes = new ArrayList<>(); // N Movimentações para cada Conta Corrente
    private final ArrayList<Cartao> cartoes = new ArrayList<>(); // N Cartões para cada Conta Corrente




    public ContaCorrente(String agencia, String conta, Float saldo, Float limite, Cliente cliente) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.limite = limite;
        this.cliente = cliente;
    }




    // Métodos get
    public String getAgencia() {return agencia;}
    public String getConta() {return conta;}


    // Métodos set
    public void setAgencia(String agencia) {this.agencia = agencia;}
    public void setConta(String conta) {this.conta = conta;}




    public void saque(Float valor) {
        Float novoSaldo = this.saldo - valor;

        // Verifica se o valor é válido
        if (valor == null || valor < 0) {
            System.out.println("Valor inválido");

            // Verifica se o novo saldo está dentro do limite permitido da conta
        } else if (novoSaldo >= -this.limite) {
            // Adiciona movimentacao à lista
            Movimentacao movimentacao = new Movimentacao(valor, LocalDate.now(), SAQUE);
            this.movimentacoes.add(movimentacao);

            // Atualiza saldo
            this.saldo = novoSaldo;
            System.out.println(movimentacao.getTipo() + " de " + movimentacao.getValor() + " na data " + movimentacao.getData());
        }
    }

    public void deposito(Float valor)    {

        // Verifica se o valor é válido
        if (valor == null || valor < 0) {
            System.out.println("Valor inválido");
            return;

        } else {
            // Adiciona movimentação à lista
            Movimentacao movimentacao = new Movimentacao(valor, LocalDate.now(), DEPOSITO);
            this.movimentacoes.add(movimentacao);

            // Atualiza saldo
            this.saldo += valor;
            System.out.println(movimentacao.getTipo() + " de " + movimentacao.getValor() + " na data " + movimentacao.getData());
        }
    }

    public ArrayList<Movimentacao> listaMovimentacoes() {return movimentacoes;}
    public ArrayList<Cartao> listaCartoes() {return cartoes;}
}
