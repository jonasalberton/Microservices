package br.com.faculdade.model;

import javax.persistence.*;

@Entity
@Table(name = "Pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    public Pessoa() {
    }

    public Pessoa(long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
