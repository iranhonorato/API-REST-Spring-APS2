package com.banksystem.aps2.cartao;
import java.time.LocalDate;



//Cliente → envia requisição HTTP → Controller
//Controller → chama métodos → Service
//Service → usa/gera dados → Model (Autor)
//Service → retorna resultado → Controller
//Controller → devolve JSON → Cliente



//Model = dados.
public class Cartao {
    public enum CartaoStatus {ATIVO, CANCELADO}
    private String numeroCartao;
    private String tipo;
    private LocalDate validade;
    private CartaoStatus status;


    public Cartao() {}


    public Cartao(String numeroCartao, String tipo, LocalDate validade, CartaoStatus status) {
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


    public Boolean isExpired(LocalDate dataAtual) {
        if (dataAtual.isAfter(this.validade)) {
            return true;
        }
        return false;
    };

    public Boolean cancelaCartao() {
        if (this.status == CartaoStatus.ATIVO) {
            this.status = CartaoStatus.CANCELADO;
        }
        return true;
    };

}
