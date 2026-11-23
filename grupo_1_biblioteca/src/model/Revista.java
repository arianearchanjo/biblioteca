// ============================================================
// CLASSE REVISTA (SUBCLASSE DE ITEMBIBLIOTECA)
// ============================================================
// Representa uma revista do acervo da biblioteca.
// Herda de ItemBiblioteca e implementa Emprestavel.
// Encapsulamento rigoroso com validacao nos setters.
// ============================================================

package model;

import java.util.Objects;

public class Revista extends ItemBiblioteca {

    private String editora;
    private int edicao;

    public Revista(String titulo, String editora, int edicao) {
        super(titulo);
        setEditora(editora);
        setEdicao(edicao);
    }

    // GETTERS
    public String getEditora() {
        return editora;
    }

    public int getEdicao() {
        return edicao;
    }

    // SETTERS COM VALIDACAO
    public void setEditora(String editora) {
        if (editora == null || editora.trim().isEmpty()) {
            throw new IllegalArgumentException("Editora nao pode ser nula ou vazia.");
        }
        this.editora = editora.trim();
    }

    public void setEdicao(int edicao) {
        if (edicao <= 0) {
            throw new IllegalArgumentException("Edicao deve ser um numero positivo.");
        }
        this.edicao = edicao;
    }

    // SOBRESCRITA DO METODO ABSTRATO
    @Override
    public void exibirResumo() {
        System.out.println("Revista: " + getTitulo() + " | Edicao: " + edicao + " | Editora: " + editora);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Revista revista = (Revista) o;
        return edicao == revista.edicao && Objects.equals(editora, revista.editora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), editora, edicao);
    }

    @Override
    public String toString() {
        return "Revista: " + getTitulo() + " | Edicao: " + edicao + " | Editora: " + editora + " | " + (isDisponivel() ? "Disponivel" : "Emprestado");
    }
}