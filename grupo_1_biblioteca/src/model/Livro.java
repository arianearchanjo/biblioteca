// ============================================================
// CLASSE LIVRO (SUBCLASSE DE ITEMBIBLIOTECA)
// ============================================================
// Representa um livro do acervo da biblioteca.
// Herda de ItemBiblioteca e implementa Emprestavel.
// Encapsulamento rigoroso com validacao nos setters.
// ============================================================

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Livro extends ItemBiblioteca {

    private List<Autor> autores;
    private String isbn;
    private int anoPublicado;
    private String categoria;

    public Livro(String titulo, List<Autor> autores, String isbn, int anoPublicado, String categoria) {
        super(titulo);
        setAutores(autores);
        setIsbn(isbn);
        setAnoPublicado(anoPublicado);
        setCategoria(categoria);
    }

    // GETTERS
    public List<Autor> getAutores() {
        return new ArrayList<>(autores);
    }

    public String getNomesAutores() {
        StringBuilder nomes = new StringBuilder();
        for (int i = 0; i < autores.size(); i++) {
            if (i > 0) {
                nomes.append(", ");
            }
            nomes.append(autores.get(i).getNome());
        }
        return nomes.toString();
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnoPublicado() {
        return anoPublicado;
    }

    public String getCategoria() {
        return categoria;
    }

    // SETTERS COM VALIDACAO
    public void setAutores(List<Autor> autores) {
        if (autores == null || autores.isEmpty()) {
            throw new IllegalArgumentException("Livro deve ter pelo menos um autor.");
        }
        this.autores = new ArrayList<>(autores);
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN nao pode ser nulo ou vazio.");
        }
        // Validacao basica de ISBN (10 ou 13 digitos)
        String isbnLimpo = isbn.replace("-", "").replace(" ", "");
        if (!isbnLimpo.matches("\\d{10}|\\d{13}")) {
            throw new IllegalArgumentException("ISBN deve conter 10 ou 13 digitos numericos.");
        }
        this.isbn = isbn.trim();
    }

    public void setAnoPublicado(int anoPublicado) {
        if (anoPublicado < 0 || anoPublicado > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Ano de publicacao invalido.");
        }
        this.anoPublicado = anoPublicado;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria nao pode ser nula ou vazia.");
        }
        this.categoria = categoria.trim();
    }

    // SOBRESCRITA DO METODO ABSTRATO
    @Override
    public void exibirResumo() {
        System.out.println("Livro: " + getTitulo() + " | Ano: " + anoPublicado + " | ISBN: " + isbn + " | Categoria: " + categoria + " | Autores: " + getNomesAutores());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Livro livro = (Livro) o;
        return Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isbn);
    }

    @Override
    public String toString() {
        return "Livro: " + getTitulo() + " | ISBN: " + isbn + " | Ano: " + anoPublicado + " | Categoria: " + categoria + " | Autores: " + getNomesAutores() + " | " + (isDisponivel() ? "Disponivel" : "Emprestado");
    }
}