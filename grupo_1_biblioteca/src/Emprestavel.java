// ============================================================
// INTERFACE EMPRESTAVEL
// ============================================================
// Contrato para qualquer item da biblioteca que possa ser
// emprestado e devolvido.
//
// Decisao de modelagem:
// - Emprestavel e uma interface (contrato) porque emprestar e
//   devolver sao comportamentos que podem aparecer em diferentes
//   tipos de item, sem que esses itens precisam herdar de uma
//   mesma classe concreta.
// - ItemBiblioteca implementa esta interface; Livro e Revista
//   herdam a implementacao por meio de ItemBiblioteca.
// ============================================================

public interface Emprestavel {

    // Empresta o item. Lanca excecao se o item nao estiver disponivel.
    void emprestar() throws ItemIndisponivelException;

    // Devolve o item ao acervo.
    void devolver();

    // Indica se o item esta disponivel para emprestimo.
    boolean isDisponivel();
}
