package me.dio.sacola.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Builder
@Data
@Embeddable // essa anotação significa que não sera criada nenhuma tabela no banco de dados
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String complemento;
}
