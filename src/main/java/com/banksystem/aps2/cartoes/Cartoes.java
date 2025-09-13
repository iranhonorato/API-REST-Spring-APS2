package com.banksystem.aps2.cartoes;
import java.time.LocalDate;



public class Cartoes {
    enum CartaoStatus {ATIVO, CANCELADO}
    private String numeroCartao;
    private String tipo;
    private LocalDate validade;
    private CartaoStatus status;

    public Cartoes(String numeroCartao, String tipo, LocalDate validade, CartaoStatus status) {
        this.numeroCartao = numeroCartao;
        this.tipo = tipo;
        this.validade = validade;
        this.status = status;
    }


    // Métodos get
    public String getNumeroCartao() {return this.numeroCartao;}
    public String getTipo() {return this.tipo;}
    public LocalDate getValidade() {return this.validade;}
    public CartaoStatus getStatus() {return this.status;}

    // Métodos set
    public void setNumeroCartao(String numeroCartao) {this.numeroCartao = numeroCartao;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public void setValidade(LocalDate validade) {this.validade = validade;}
    public void setStatus(CartaoStatus status) {this.status = status;}

}
