package me.dio.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.enumaration.FormaPagamento;
import me.dio.sacola.models.Item;
import me.dio.sacola.models.Restaurante;
import me.dio.sacola.models.Sacola;
import me.dio.sacola.repositoty.ItemRepository;
import me.dio.sacola.repositoty.ProdutoRepository;
import me.dio.sacola.repositoty.SacolaRepository;
import me.dio.sacola.resource.dto.ItemDTO;
import me.dio.sacola.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDTO itemDTO) {
        Sacola sacola = verSacola(itemDTO.getIdSacola());

        if (sacola.isFechada()) {
            throw new RuntimeException("Esta sacola está fechada");
        }

        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDTO.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDTO.getProdutoID()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Este produto não existe");
                        }
                ))
                .build();

        List<Item> itemsDaSacola = sacola.getItems();
        if (itemsDaSacola.isEmpty()) {
            itemsDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itemsDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();

            if (restauranteAtual.equals(restauranteParaAdicionar)) {
                itemsDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Produto precisa ser do mesmo restaurante, necessário finalizar a compra ou esvaziar a sacola");
            }
        }
        sacolaRepository.save(sacola);
        return itemRepository.save(itemParaSerInserido);
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Essa sacola não existe");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);

        if (sacola.getItems().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola");
        }

        FormaPagamento formaPagamento = numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);

        return sacolaRepository.save(sacola);
    }
}
