package com.banksystem.aps2.cartao;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


//Cliente → envia requisição HTTP → Controller
//Controller → chama métodos → Service
//Service → usa/gera dados → Model (Autor)
//Service → retorna resultado → Controller
//Controller → devolve JSON → Cliente



//Service = regra de negócio.
@Service
public class CartaoService {
    // Banco de dados em memória
    private final Map<UUID, Cartao> cartoes = new HashMap<>();

    public Collection<Cartao> listarCartoes() {
        return cartoes.values();
    }

    public Cartao buscarPorNumero(String numeroCartao) {
        return cartoes.values().stream()
                .filter(c -> c.getNumeroCartao().equals(numeroCartao))
                .findFirst()
                .orElse(null);
    }


    public Cartao salvarCartao(Cartao cartao) {
        if (cartao.getNumeroCartao() == null || cartao.getNumeroCartao().isEmpty()) {
            throw new IllegalArgumentException("O número do cartão não pode ser nulo ou vazio.");
        }
        // Verifica duplicidade
        if (cartoes.containsKey(cartao.getNumeroCartao())) {
            throw new IllegalArgumentException("Já existe um cartão com esse número.");
        }

        UUID id = UUID.randomUUID();
        cartoes.put(id, cartao);
        return cartao;
    }


    public void deletar(String numeroCartao) {

        UUID idParaRemover = cartoes.entrySet().stream()
                .filter(entry -> entry.getValue().getNumeroCartao().equals(numeroCartao))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (idParaRemover != null) {
            cartoes.remove(idParaRemover);
        }
    }

}
