package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserForm {

    @NotBlank
    private String nome;

    @NotBlank @Email
    private String email;

    @NotBlank @Length(min = 5)
    private String profession;

    @NotBlank
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario converter(){
        return new Usuario(nome,email,profession,senha);
    }

}
