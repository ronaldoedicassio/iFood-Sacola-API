package me.dio.sacola.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.sacola.enumaration.FormaPagamento;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sacola {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore //ignorar os erros de serialização
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    private Double valorTotal;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    private boolean fechada;
}
