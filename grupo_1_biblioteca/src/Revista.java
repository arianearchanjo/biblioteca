// ============================================================
// CLASSE REVISTA (SUBCLASSE DE ITEMBIBLIOTECA)
// ============================================================
// A classe Revista representa uma revista do acervo da biblioteca.
// Responsabilidade: guardar os dados especificos de uma revista
// (editora e edicao) e herdar da ItemBiblioteca o que e comum
// (titulo, disponivel, emprestar, devolver).
//
// Decisao de modelagem:
// - "Uma Revista E UM ItemBiblioteca", pois tambem pode ser
//   emprestada. Por isso usa extends.
// ============================================================

public class Revista extends ItemBiblioteca {

    // --------------------------------------------------------
    // ATRIBUTOS ESPECIFICOS DA REVISTA
    // --------------------------------------------------------
    private String editora; // quem publica a revista
    private int edicao;     // numero da edicao da revista

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    // super(titulo) repassa o titulo para a superclasse, pois o
    // atributo titulo pertence a ItemBiblioteca.
    public Revista(String titulo, String editora, int edicao) {
        super(titulo);
        this.editora = editora;
        this.edicao = edicao;
    }

    // --------------------------------------------------------
    // METODOS GETTERS
    // --------------------------------------------------------

    // SOBRESCRITA (@Override) do exibirResumo da superclasse:
    // mostra o resumo da revista com o dado especifico dela (edicao).
    @Override
    public void exibirResumo() {
        System.out.println("Revista: " + getTitulo() + " | Edicao: " + edicao);
    }

    public String getEditora() {
        return editora;
    }

    public int getEdicao() {
        return edicao;
    }
}
