// ============================================================
// CLASSE LIVRO
// ============================================================
// A classe Livro representa um livro do acervo da biblioteca.
// Responsabilidade: guardar os dados do livro e controlar se ele
// esta disponivel para emprestimo ou se ja esta emprestado.
//
// Decisao de modelagem: o atributo "disponivel" pertence a esta
// classe porque e o proprio livro que sabe se esta livre ou ocupado.
// ============================================================

public class Livro {

    // --------------------------------------------------------
    // ATRIBUTOS (caracteristicas de um livro)
    // --------------------------------------------------------
    // Sao privados (private) para que ninguem de fora da classe
    // possa altera-los diretamente. A alteracao so acontece pelos
    // metodos da classe.
    private String titulo;      // nome do livro
    private String autor;       // quem escreveu o livro
    private String isbn;        // codigo internacional do livro
    private boolean disponivel; // true = disponivel | false = emprestado

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    // Metodo especial que roda quando criamos um novo livro
    // (ex.: new Livro(...)). Recebe os dados basicos e define
    // que todo livro novo entra no acervo disponivel.
    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
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
