package com.demo.example.model;

import javax.persistence.*;

@Entity
//@Table(name="PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NOME_COMPLETO")
    private String nome;

   // @Column(name="IDADE")
    private int idade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
