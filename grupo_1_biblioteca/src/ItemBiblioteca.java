// ============================================================
// CLASSE ITEMBIBLIOTECA (SUPERCLASSE)
// ============================================================
// Classe que representa um item generico da biblioteca, algo que
// pode ser emprestado (livro ou revista).
//
// Decisao de modelagem:
// - "Um Livro E UM ItemBiblioteca" e "Uma Revista E UM
//   ItemBiblioteca". Por isso Livro e Revista herdam (extends)
//   de ItemBiblioteca.
// - O que e comum a todos os itens fica aqui: titulo, disponivel,
//   emprestar() e devolver().
// ============================================================

public class ItemBiblioteca {

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
    // METODOS DE COMPORTAMENTO COMUNS
    // --------------------------------------------------------

    public boolean emprestar() {
        if (disponivel) {
            disponivel = false;
            return true;
        }
        return false;
    }

    public boolean devolver() {
        if (!disponivel) {
            disponivel = true;
            return true;
        }
        return false;
    }

    // --------------------------------------------------------
    // METODO COMUM (SOBRESCRITO NAS SUBCLASSES)
    // --------------------------------------------------------
    // Exibe uma linha resumida do item. Nas subclasses esse metodo
    // sera SOBRESCRITO (@Override) para mostrar o dado especifico.
    // Isso permite o polimorfismo no Main.
    public void exibirResumo() {
        System.out.println("Item: " + titulo);
    }

    // SOBRECARGA (overload): mesmo nome exibirResumo, mas agora
    // recebendo um parametro boolean. E a mesma operacao com outra
    // assinatura. O parametro "detalhado" controla quanto mostramos.
    public void exibirResumo(boolean detalhado) {
        if (detalhado) {
            System.out.println("Item detalhado: " + titulo + " | Disponivel? " + disponivel);
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

    public boolean isDisponivel() {
        return disponivel;
    }
}
