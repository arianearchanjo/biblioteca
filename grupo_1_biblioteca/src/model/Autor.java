// ============================================================
// CLASSE AUTOR
// ============================================================
// Representa o autor de um livro da biblioteca.
// Encapsulamento rigoroso com validacao nos setters.
// ============================================================

package model;

import java.util.Objects;

public class Autor {

    private String nome;
    private String nacionalidade;

    public Autor(String nome, String nacionalidade) {
        setNome(nome);
        setNacionalidade(nacionalidade);
    }

    // GETTERS
    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    // SETTERS COM VALIDACAO
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do autor nao pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public void setNacionalidade(String nacionalidade) {
        if (nacionalidade == null || nacionalidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Nacionalidade do autor nao pode ser nula ou vazia.");
        }
        this.nacionalidade = nacionalidade.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nome, autor.nome) && Objects.equals(nacionalidade, autor.nacionalidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, nacionalidade);
    }

    @Override
    public String toString() {
        return nome + " (" + nacionalidade + ")";
    }
}