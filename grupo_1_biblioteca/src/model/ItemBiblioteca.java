// ============================================================
// CLASSE ABSTRATA ITEMBIBLIOTECA
// ============================================================
// Classe abstrata que representa um item generico da biblioteca
// (algo que pode ser emprestado: livro ou revista).
//
// Decisao de modelagem:
// - ItemBiblioteca e ABSTRATA porque nao faz sentido instanciar
//   um "item generico": todo item da biblioteca e, concretamente,
//   um Livro ou uma Revista. Nao existe um item sem tipo.
// - O metodo exibirResumo() e abstrato: cada tipo de item exibe
//   seu resumo de forma diferente (livro mostra o ano, revista
//   mostra a edicao), entao nao ha uma implementacao padrao valida
//   para a superclasse.
// - ItemBiblioteca implementa a interface Emprestavel, fornecendo
//   a implementacao concreta de emprestar(), devolver() e
//   isDisponivel(), herdada por Livro e Revista.
// ============================================================

package model;

import exception.ItemIndisponivelException;

public abstract class ItemBiblioteca implements Emprestavel {

    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String titulo) {
        setTitulo(titulo);
        this.disponivel = true;
    }

    // GETTERS
    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // SETTERS COM VALIDACAO
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Titulo do item nao pode ser nulo ou vazio.");
        }
        this.titulo = titulo.trim();
    }

    // IMPLEMENTACAO DA INTERFACE EMPRESTAVEL
    @Override
    public void emprestar() throws ItemIndisponivelException {
        if (!disponivel) {
            throw new ItemIndisponivelException("O item '" + titulo + "' nao esta disponivel para emprestimo.");
        }
        disponivel = false;
    }

    @Override
    public void devolver() {
        if (!disponivel) {
            disponivel = true;
        }
    }

    // METODO ABSTRATO (implementado pelas subclasses)
    public abstract void exibirResumo();

    // SOBRECARGA: mesmo nome com parametro boolean detalhado
    public void exibirResumo(boolean detalhado) {
        if (detalhado) {
            System.out.println("Item: " + titulo + " | Disponivel? " + disponivel);
        } else {
            System.out.println("Item: " + titulo);
        }
    }

    @Override
    public String toString() {
        return titulo + " | Disponivel: " + (disponivel ? "Sim" : "Nao");
    }
}