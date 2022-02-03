package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.StatusTopico;
import br.com.alura.forum.modelo.Topico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDoTopicoDto extends TopicoDto{
    private String nomeAutor;
    private StatusTopico status;
    private List<ResponstaDto> respostas;

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<ResponstaDto> getRespostas() {
        return respostas;
    }

    public DetalhesDoTopicoDto(Topico topico) {
        super(topico);
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(ResponstaDto::new).collect(Collectors.toList()));
    }
}
