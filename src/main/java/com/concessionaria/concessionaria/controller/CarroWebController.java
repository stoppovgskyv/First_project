package com.concessionaria.concessionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concessionaria.concessionaria.dto.CarroRequestDTO;
import com.concessionaria.concessionaria.service.CarroService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/carros")
public class CarroWebController {

    private final CarroService service;

    public CarroWebController(CarroService service) {
        this.service = service;
    }

    // LISTAR (com filtro opcional por modelo)
    @GetMapping
    public String listar(@RequestParam(required = false) String modelo, Model model) {
        if (modelo != null && !modelo.isBlank()) {
            model.addAttribute("carros", service.listarTodos().stream()
                    .filter(c -> c.getModelo().toLowerCase().contains(modelo.toLowerCase()))
                    .toList());
            model.addAttribute("filtroModelo", modelo);
        } else {
            model.addAttribute("carros", service.listarTodos());
        }
        return "carros-lista";
    }

    // FORMULÁRIO DE NOVO CARRO
    @GetMapping("/novo")
    public String novoCarroForm(Model model) {
        model.addAttribute("carro", new CarroRequestDTO());
        return "carros-form";
    }

    // SALVAR (novo)
    @PostMapping
    public String salvar(@Valid @ModelAttribute("carro") CarroRequestDTO dto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "carros-form";
        }
        service.criar(dto);
        return "redirect:/carros";
    }

    // FORMULÁRIO DE EDIÇÃO
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        var carro = service.buscarPorId(id); // ResponseDTO
        CarroRequestDTO dto = new CarroRequestDTO();
        dto.setModelo(carro.getModelo());
        dto.setMarca(carro.getMarca());
        dto.setAno(carro.getAno());
        dto.setPreco(carro.getPreco());

        model.addAttribute("carroId", carro.getId());
        model.addAttribute("carro", dto);
        return "carros-form-editar";
    }

    // ATUALIZAR (editar)
    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id,
            @Valid @ModelAttribute("carro") CarroRequestDTO dto,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("carroId", id);
            return "carros-form-editar";
        }
        service.atualizar(id, dto);
        return "redirect:/carros";
    }

    // EXCLUIR
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/carros";
    }
}
