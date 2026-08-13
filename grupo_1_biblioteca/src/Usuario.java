// ============================================================
// CLASSE USUARIO
// ============================================================
// A classe Usuario representa uma pessoa que usa a biblioteca.
// Responsabilidade: guardar os dados do usuario e controlar a
// quantidade de livros que ele tem emprestados no momento,
// respeitando um limite pessoal de emprestimos.
//
// Decisao de modelagem: o limite de emprestimos fica no proprio
// usuario porque cada usuario pode ter um limite diferente
// (ex.: um aluno pode pegar 3 livros e um professor pode pegar 5).
// ============================================================

public class Usuario {

    // --------------------------------------------------------
    // ATRIBUTOS (caracteristicas de um usuario)
    // --------------------------------------------------------
    private String nome;              // nome do usuario
    private String matricula;         // identificacao do usuario na biblioteca
    private int qtdEmprestimosAtivos; // quantos livros ele esta devendo agora
    private int limiteEmprestimos;    // quantos livros ele pode pegar no maximo

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    // Cria um novo usuario. Todo usuario novo comeca sem nenhum
    // emprestimo ativo (qtdEmprestimosAtivos = 0).
    public Usuario(String nome, String matricula, int limiteEmprestimos) {
        this.nome = nome;
        this.matricula = matricula;
        this.limiteEmprestimos = limiteEmprestimos;
        this.qtdEmprestimosAtivos = 0;
    }

    // --------------------------------------------------------
    // METODOS DE COMPORTAMENTO (o que o usuario "faz")
    // --------------------------------------------------------

    // Verifica se o usuario ainda pode pegar livros emprestados.
    // Compara a quantidade atual (qtdEmprestimosAtivos) com o limite
    // dele. Retorna true se ainda ha espaco e false se ja atingiu o limite.
    public boolean podeEmprestar() {
        return qtdEmprestimosAtivos < limiteEmprestimos;
    }

    // Registra que o usuario pegou mais um livro emprestado.
    // So aumenta a quantidade se ele ainda tiver limite disponivel.
    // Retorna true se conseguiu registrar e false caso contrario.
    public boolean registrarEmprestimo() {
        if (podeEmprestar()) {
            qtdEmprestimosAtivos++;
            return true;
        }
        return false;
    }

    // Registra que o usuario devolveu um livro.
    // Diminui a quantidade de emprestimos ativos.
    // O if impede que a quantidade fique negativa por engano.
    public void registrarDevolucao() {
        if (qtdEmprestimosAtivos > 0) {
            qtdEmprestimosAtivos--;
        }
    }

    // --------------------------------------------------------
    // METODOS GETTERS (leitura dos atributos)
    // --------------------------------------------------------

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getQtdEmprestimosAtivos() {
        return qtdEmprestimosAtivos;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }
}
