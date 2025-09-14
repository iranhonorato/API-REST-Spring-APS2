package com.banksystem.aps2.cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


//Cliente → envia requisição HTTP → Controller
//Controller → chama métodos → Service
//Service → usa/gera dados → Model (Autor)
//Service → retorna resultado → Controller
//Controller → devolve JSON → Cliente


//Controller = entrada/saída da API.
@RestController // Indica que a classe é um controller e que todos os métodos retornarão um JSON
@RequestMapping("/cartoes")  // Define o caminho base da API: todos os endpoints começam com /cartoes.
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public Collection<Cartao> listarCartoes() {
        return cartaoService.listarCartoes();
    }

    @GetMapping("/{numeroCartao}")
    public Cartao buscarCartao(@PathVariable String numeroCartao) {
        return cartaoService.buscarPorNumero(numeroCartao);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cartao cartao) {
        try {
            Cartao novoCartao = cartaoService.salvarCartao(cartao);
            return ResponseEntity.ok(novoCartao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{numeroCartao}")
    public void deletarCartao(@PathVariable String numeroCartao) {
        cartaoService.deletar(numeroCartao);
    }


}
