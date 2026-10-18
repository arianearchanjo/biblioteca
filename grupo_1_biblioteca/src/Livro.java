// ============================================================
// CLASSE LIVRO (SUBCLASSE DE ITEMBIBLIOTECA)
// ============================================================
// A classe Livro representa um livro do acervo da biblioteca.
// Responsabilidade: guardar os dados especificos do livro (autores,
// isbn e ano de publicacao) e herdar da ItemBiblioteca o que e
// comum a todo item (titulo, disponivel, emprestar, devolver).
//
// Decisao de modelagem:
// - "Um Livro E UM ItemBiblioteca", por isso usa extends.
// - anoPublicado (int) foi pedido pelo professor.
// ============================================================

import java.util.ArrayList; // lista para guardar os autores

public class Livro extends ItemBiblioteca implements Emprestavel {

    // --------------------------------------------------------
    // ATRIBUTOS ESPECIFICOS DO LIVRO
    // --------------------------------------------------------
    private ArrayList<Autor> autores; // lista de autores do livro
    private String isbn;              // codigo internacional do livro
    private int anoPublicado;         // ano em que o livro foi publicado
    private String categoria;         // categoria do livro (ex.: romance, ficcao, tecnico)

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    // super(titulo) repassa o titulo para a superclasse, pois o
    // atributo titulo pertence a ItemBiblioteca.
    public Livro(String titulo, ArrayList<Autor> autores, String isbn, int anoPublicado, String categoria) {
        super(titulo);
        this.autores = autores;
        this.isbn = isbn;
        this.anoPublicado = anoPublicado;
        this.categoria = categoria;
    }

    // --------------------------------------------------------
    // METODOS GETTERS
    // --------------------------------------------------------

    // SOBRESCRITA (@Override) do exibirResumo da superclasse:
    // mostra o resumo do livro com o dado especifico dele (o ano).
    @Override
    public void exibirResumo() {
        System.out.println("Livro: " + getTitulo() + " | Ano: " + anoPublicado);
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }

    // Retorna os nomes dos autores separados por virgula.
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

    public int getAnoPublicado() {
        return anoPublicado;
    }

    public String getCategoria() {
        return categoria;
    }
}
