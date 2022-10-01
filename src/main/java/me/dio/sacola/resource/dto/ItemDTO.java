package me.dio.sacola.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
@Data
public class ItemDTO {
    private long produtoID;
    private int quantidade;
    private long idSacola;
}
