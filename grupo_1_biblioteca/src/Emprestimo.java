// ============================================================
// CLASSE EMPRESTIMO
// ============================================================
// A classe Emprestimo representa o emprestimo de um livro a um usuario.
// Responsabilidade: registrar quando o livro saiu, quando deve voltar
// e se o emprestimo ainda esta ativo, ja foi devolvido ou esta atrasado.
//
// Decisao de modelagem: guardamos a referencia ao Livro e ao Usuario
// porque o emprestimo liga esses dois objetos (um livro emprestado
// para um usuario em uma determinada data).
// ============================================================

import java.time.LocalDate; // classe pronta do Java que representa a data de hoje

public class Emprestimo {

    // --------------------------------------------------------
    // ATRIBUTOS
    // --------------------------------------------------------
    private Livro livro;                  // qual livro foi emprestado
    private Usuario usuario;              // quem pegou o livro
    private String dataEmprestimo;        // dia em que o livro saiu da biblioteca
    private String dataDevolucaoPrevista; // dia em que o livro deve voltar
    private boolean ativo;                // true = emprestimo em andamento

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    // Cria um novo emprestimo. Todo emprestimo comeca ativo.
    //
    // Observacao sobre as datas: guardamos as datas como texto no
    // formato aaaa-mm-dd. Nesse formato, comparar dois textos com o
    // metodo compareTo() compara as datas na ordem cronologica certa,
    // sem precisar de bibliotecas complicadas de data.
    public Emprestimo(Livro livro, Usuario usuario, String dataEmprestimo, String dataDevolucaoPrevista) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.ativo = true;
    }

    // --------------------------------------------------------
    // METODOS DE COMPORTAMENTO (o que o emprestimo "faz")
    // --------------------------------------------------------

    // Verifica se o emprestimo esta atrasado.
    // Um emprestimo so pode estar atrasado se ainda estiver ativo
    // (um emprestimo ja devolvido nunca esta atrasado).
    // Se a data de hoje for "maior" que a data prevista de devolucao,
    // significa que o prazo ja passou, ou seja, esta atrasado.
    public boolean estaAtrasado() {
        if (!ativo) {
            return false; // emprestimo ja devolvido nunca esta atrasado
        }
        String hoje = LocalDate.now().toString(); // pega a data de hoje como texto
        return hoje.compareTo(dataDevolucaoPrevista) > 0;
    }

    // Encerra o emprestimo (devolucao do livro).
    // Marca o emprestimo como nao ativo, devolve o livro ao acervo
    // (chama livro.devolver()) e registra no usuario que ele devolveu
    // um livro (chama usuario.registrarDevolucao()).
    public void finalizar() {
        if (ativo) {
            ativo = false;
            livro.devolver();
            usuario.registrarDevolucao();
        }
    }

    // --------------------------------------------------------
    // METODOS GETTERS (leitura dos atributos)
    // --------------------------------------------------------

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
