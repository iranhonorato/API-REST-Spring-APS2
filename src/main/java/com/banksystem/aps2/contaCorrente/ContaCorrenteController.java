package com.banksystem.aps2.contaCorrente;


import com.banksystem.aps2.movimentacao.Movimentacao;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/conta")
public class ContaCorrenteController {

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @GetMapping
    public Collection<ContaCorrente> listarContasController(){ return  contaCorrenteService.listarContas(); }


    @GetMapping("/{conta}")
    public ArrayList<Movimentacao> listarMovimentacoesController(@PathVariable String conta){
        return  contaCorrenteService.listarMovimentacoes(conta);
    }


    @PostMapping
    public ResponseEntity<?> cadastrarContaController(@RequestBody ContaCorrente contaCorrente){
        try {
            ContaCorrente novaConta = contaCorrenteService.cadastrarConta(contaCorrente);
            return ResponseEntity.ok().body(novaConta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/{conta}")
    public ResponseEntity<?> saqueController(@PathVariable String conta, @RequestBody Float valor){
        try {
            contaCorrenteService.sacar(conta, valor);
            return ResponseEntity.ok().body("Saque realizado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/{conta}")
    public ResponseEntity<?> depositarController(@PathVariable String conta, @RequestBody Float valor){
        try {
            contaCorrenteService.depositar(conta, valor);
            return ResponseEntity.ok().body("Depósito realizado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}