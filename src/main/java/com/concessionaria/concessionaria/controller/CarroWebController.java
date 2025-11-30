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

/**
 * Controlador responsável por gerenciar toda a parte WEB relacionada aos
 * carros. Aqui ficam as rotas que retornam páginas HTML (via Thymeleaf).
 *
 * As operações conduzidas são: - Listagem (com e sem filtro) - Formulário de
 * criar novo carro - Salvar novo carro - Formulário de edição - Atualizar carro
 * existente - Excluir carro
 */
@Controller
@RequestMapping("/carros") // Todas as rotas começam com /carros
public class CarroWebController {

    private final CarroService service;

    public CarroWebController(CarroService service) {
        this.service = service;
    }

    // ========================================================================
    // LISTAR (com filtro opcional por modelo)
    // ========================================================================
    /**
     * Exibe a lista de carros. Se o parâmetro "modelo" for enviado, faz um
     * filtro por modelo.
     */
    @GetMapping
    public String listar(@RequestParam(required = false) String modelo, Model model) {

        // Se o usuário digitou algo no campo "modelo"
        if (modelo != null && !modelo.isBlank()) {

            // Filtra a lista completa, mantendo apenas carros cujo modelo contém o texto digitado
            model.addAttribute("carros", service.listarTodos().stream()
                    .filter(c -> c.getModelo().toLowerCase().contains(modelo.toLowerCase()))
                    .toList());

            // Mantém o texto digitado para exibir no campo de busca
            model.addAttribute("filtroModelo", modelo);
        } else {
            // Sem filtro, retorna a lista completa
            model.addAttribute("carros", service.listarTodos());
        }

        // Retorna a página carros-lista.html
        return "carros-lista";
    }

    // ========================================================================
    // FORMULÁRIO PARA CRIAR NOVO CARRO
    // ========================================================================
    /**
     * Exibe o formulário de criação de um novo carro. Envia um DTO vazio para
     * popular o formulário do Thymeleaf.
     */
    @GetMapping("/novo")
    public String novoCarroForm(Model model) {
        model.addAttribute("carro", new CarroRequestDTO());
        return "carros-form";
    }

    // ========================================================================
    // SALVAR NOVO CARRO
    // ========================================================================
    /**
     * Recebe os dados do formulário de novo carro. Valida o DTO e, caso existam
     * erros, retorna ao formulário. Se estiver tudo ok, chama o serviço para
     * criar o carro.
     */
    @PostMapping
    public String salvar(
            @Valid @ModelAttribute("carro") CarroRequestDTO dto,
            BindingResult bindingResult) {

        // Se houver erros de validação (@NotBlank, @Positive etc)
        if (bindingResult.hasErrors()) {
            return "carros-form";
        }

        // Chama o serviço para criar o carro no banco
        service.criar(dto);

        // Redireciona para a lista após salvar
        return "redirect:/carros";
    }

    // ========================================================================
    // FORMULÁRIO PARA EDITAR CARRO EXISTENTE
    // ========================================================================
    /**
     * Abre o formulário de edição preenchendo o DTO com os dados atuais do
     * carro.
     */
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {

        // Busca o carro pelo ID — retorna um ResponseDTO
        var carro = service.buscarPorId(id);

        // Preenche o DTO usado no formulário de edição
        CarroRequestDTO dto = new CarroRequestDTO();
        dto.setModelo(carro.getModelo());
        dto.setMarca(carro.getMarca());
        dto.setAno(carro.getAno());
        dto.setPreco(carro.getPreco());

        // Envia o ID e os dados do carro para a página
        model.addAttribute("carroId", carro.getId());
        model.addAttribute("carro", dto);

        // Página carros-form-editar.html
        return "carros-form-editar";
    }

    // ========================================================================
    // ATUALIZAR CARRO EXISTENTE
    // ========================================================================
    /**
     * Recebe os dados do formulário de edição. Valida o DTO e atualiza o carro
     * no banco.
     */
    @PostMapping("/editar/{id}")
    public String atualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute("carro") CarroRequestDTO dto,
            BindingResult bindingResult,
            Model model) {

        // Se houver erros de validação, volta para o formulário mantendo o ID
        if (bindingResult.hasErrors()) {
            model.addAttribute("carroId", id);
            return "carros-form-editar";
        }

        // Atualiza via serviço
        service.atualizar(id, dto);

        // Redireciona para a lista
        return "redirect:/carros";
    }

    // ========================================================================
    // EXCLUIR CARRO
    // ========================================================================
    /**
     * Exclui um carro com base no ID e redireciona para a listagem.
     */
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/carros";
    }
}
