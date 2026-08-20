// ============================================================
// CLASSE LIVRO
// ============================================================
// A classe Livro representa um livro do acervo da biblioteca.
// Responsabilidade: guardar os dados do livro e controlar se ele
// esta disponivel para emprestimo ou se ja esta emprestado.
//
// Decisao de modelagem:
// - O atributo "disponivel" pertence a esta classe porque e o
//   proprio livro que sabe se esta livre ou ocupado.
// - O atributo "autor" foi substituido por uma lista de autores
//   (ArrayList<Autor>) porque um livro pode ter mais de um autor.
//   Cada autor e um objeto da classe Autor, que guarda nome e
//   nacionalidade.
// ============================================================

import java.util.ArrayList; // lista dinamica para guardar os autores

public class Livro {

    // --------------------------------------------------------
    // ATRIBUTOS (caracteristicas de um livro)
    // --------------------------------------------------------
    // Sao privados (private) para que ninguem de fora da classe
    // possa altera-los diretamente. A alteracao so acontece pelos
    // metodos da classe.
    private String titulo;          // nome do livro
    private ArrayList<Autor> autores; // lista de autores do livro
    private String isbn;            // codigo internacional do livro
    private boolean disponivel;     // true = disponivel | false = emprestado

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    // Metodo especial que roda quando criamos um novo livro.
    // Recebe a lista de autores como ArrayList<Autor> porque um
    // livro pode ter mais de um autor.
    public Livro(String titulo, ArrayList<Autor> autores, String isbn) {
        this.titulo = titulo;
        this.autores = autores;
        this.isbn = isbn;
        this.disponivel = true; // todo livro cadastrado comeca disponivel
    }

    // --------------------------------------------------------
    // METODOS DE COMPORTAMENTO (o que o livro "faz")
    // --------------------------------------------------------

    // Empresta o livro: muda o estado de disponivel para emprestado.
    // Retorna true se deu certo (o livro estava disponivel) e false
    // se o livro ja estava emprestado (nesse caso nada acontece).
    public boolean emprestar() {
        if (disponivel) {
            disponivel = false;
            return true;
        }
        return false;
    }

    // Devolve o livro: muda o estado de emprestado para disponivel.
    // Retorna true se deu certo (o livro estava emprestado) e false
    // se o livro ja estava disponivel.
    public boolean devolver() {
        if (!disponivel) {
            disponivel = true;
            return true;
        }
        return false;
    }

    // --------------------------------------------------------
    // METODOS GETTERS (leitura dos atributos)
    // --------------------------------------------------------
    // Como os atributos sao privados, precisamos de um metodo
    // para ler o valor de cada um. Os metodos que leem atributos
    // sao chamados de "getters".

    public String getTitulo() {
        return titulo;
    }

    // Retorna a lista completa de autores do livro.
    public ArrayList<Autor> getAutores() {
        return autores;
    }

    // Retorna os nomes de todos os autores separados por virgula.
    // Util para exibir no console sem precisar percorrer a lista.
    public String getNomesAutores() {
        String nomes = "";
        for (int i = 0; i < autores.size(); i++) {
            if (i > 0) {
                nomes += ", ";
            }
            nomes += autores.get(i).getNome();
        }
        return nomes;
    }

    public String getIsbn() {
        return isbn;
    }

    // O getter de um atributo boolean costuma comecar com "is"
    // em vez de "get" (ex.: isDisponivel(), isAtivo()).
    public boolean isDisponivel() {
        return disponivel;
    }
}
