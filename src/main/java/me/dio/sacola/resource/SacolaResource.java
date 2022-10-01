package me.dio.sacola.resource;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.models.Item;
import me.dio.sacola.models.Sacola;
import me.dio.sacola.resource.dto.ItemDTO;
import me.dio.sacola.service.SacolaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor /* @RequiredArgsConstructor e exatamente igual ao construtor abaixo
  public SacolaResource(SacolaService sacolaService) {
        this.sacolaService = sacolaService;
    }*/
public class SacolaResource {

    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDTO itemDTO) {
        /*
            @RequestBody -> significa que a requisição do itemDTO vai ser utilizada para incluir
        */
        return sacolaService.incluirItemNaSacola(itemDTO);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharSacola/{sacolaId}")
    // qdo e pouco metod para alterar o remendado e utilizar o patch, se for muito dados utilizar PUT
    public Sacola fecharSacola(@PathVariable("sacolaId") Long id, @RequestParam("formaPagamento") int formaPagamento) {
        return sacolaService.fecharSacola(id, formaPagamento);
    }
}
