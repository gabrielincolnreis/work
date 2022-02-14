package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UpdateUserForm {

    @NotBlank
    private String nome;

    @NotBlank @Length(min = 5)
    private String profession;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Usuario update(Long id, UsuarioRepository usuarioRepository){
        Usuario usuario = usuarioRepository.getById(id);

        usuario.setNome(this.nome);
        usuario.setProfession(this.profession);

        return  usuario;
    }
}
