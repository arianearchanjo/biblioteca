// ============================================================
// CLASSE AUTOR
// ============================================================
// A classe Autor representa o autor de um livro da biblioteca.
// Responsabilidade: guardar os dados do autor (nome e nacionalidade).
//
// Decisao de modelagem: um autor e uma entidade propria porque
// um livro pode ter mais de um autor, e um autor pode escrever
// varios livros. Separar autor em classe propria permite
// representar essa relacao de forma limpa.
// ============================================================

public class Autor {

    // --------------------------------------------------------
    // ATRIBUTOS (caracteristicas de um autor)
    // --------------------------------------------------------
    private String nome;         // nome completo do autor
    private String nacionalidade; // pais de origem do autor

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    // Cria um novo autor com nome e nacionalidade.
    public Autor(String nome, String nacionalidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    // --------------------------------------------------------
    // METODOS GETTERS (leitura dos atributos)
    // --------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
}
