package br.com.alura.forum.controller.dto;


import br.com.alura.forum.modelo.Usuario;
import org.springframework.data.domain.Page;

public class UserDto {

    private Long id;
    private String nome;
    private String email;
    private String profession;
    private Long rating;

    public UserDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.profession = usuario.getProfession();
        this.rating = usuario.getRating();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getProfession() {
        return profession;
    }

    public Long getRating() {
        return rating;
    }

    public static Page<UserDto> converter(Page<Usuario> users){
        return users.map(UserDto::new);
    }
}
