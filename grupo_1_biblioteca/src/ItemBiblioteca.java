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

public abstract class ItemBiblioteca implements Emprestavel {

    // --------------------------------------------------------
    // ATRIBUTOS COMUNS
    // --------------------------------------------------------
    private String titulo;       // nome do item (livro ou revista)
    private boolean disponivel;  // true = disponivel | false = emprestado

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    public ItemBiblioteca(String titulo) {
        this.titulo = titulo;
        this.disponivel = true; // todo item comeca disponivel
    }

// --------------------------------------------------------
// IMPLEMENTACAO DA INTERFACE Emprestavel
// --------------------------------------------------------

    // Empresta o item. Se ja estiver emprestado, lanca excecao
    // para nao permitir a operacao invalida.
    @Override
    public void emprestar() throws ItemIndisponivelException {
        if (!disponivel) {
            throw new ItemIndisponivelException("O item '" + titulo + "' nao esta disponivel para emprestimo.");
        }
        disponivel = false;
    }

    // Devolve o item ao acervo (marca como disponivel novamente).
    @Override
    public void devolver() {
        if (!disponivel) {
            disponivel = true;
        }
    }

    @Override
    public boolean isDisponivel() {
        return disponivel;
    }

    // --------------------------------------------------------
    // METODO ABSTRATO (implementado pelas subclasses)
    // --------------------------------------------------------
    // Cada subclasse sabe exibir seu próprio resumo com o dado
    // especifico dela (ano para Livro, edicao para Revista).
    public abstract void exibirResumo();

    // SOBRECARGA: mesmo nome com parametro boolean detalhado.
    public void exibirResumo(boolean detalhado) {
        if (detalhado) {
            System.out.println("Item: " + titulo + " | Disponivel? " + disponivel);
        } else {
            System.out.println("Item: " + titulo);
        }
    }

    // --------------------------------------------------------
    // METODOS GETTERS
    // --------------------------------------------------------

    public String getTitulo() {
        return titulo;
    }
}
