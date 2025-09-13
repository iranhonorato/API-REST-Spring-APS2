package com.banksystem.aps2.contaCorrente;

import java.time.LocalDate;

public class ContaCorrente {
    private String agencia;
    private String conta;
    private Float salario;
    private Float limite;

    public ContaCorrente(String agencia, String conta, Float salario, Float limite) {
        this.agencia = agencia;
        this.conta = conta;
        this.salario = salario;
        this.limite = limite;
    }

    // Métodos get
    public String getAgencia() {return agencia;}
    public String getConta() {return conta;}


    // Métodos set
    public void setAgencia(String agencia) {this.agencia = agencia;}
    public void setConta(String conta) {this.conta = conta;}

}
