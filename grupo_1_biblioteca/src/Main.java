// ============================================================
// CLASSE MAIN
// ============================================================
// Esta classe serve apenas para DEMONSTRAR a modelagem da biblioteca.
// Ela cria objetos das classes Livro, Usuario e Emprestimo, executa
// metodos que mudam o estado desses objetos e imprime um mini-relatorio
// no console mostrando o comportamento.
//
// Nao ha regras de negocio aqui: o objetivo e evidenciar que objetos
// da MESMA classe podem ter estados diferentes no mesmo momento.
// ============================================================

import java.time.LocalDate; // classe pronta do Java para trabalhar com datas

public class Main {

    public static void main(String[] args) {

        // ----------------------------------------------------
        // 1) CRIACAO DOS OBJETOS
        // ----------------------------------------------------

        // Criamos DOIS livros da classe Livro.
        // No inicio, os dois estao disponiveis (mesmo estado).
        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", "9788535910663");
        Livro livro2 = new Livro("O Pequeno Principe", "Antoine de Saint-Exupery", "9788595081516");

        // Criamos DOIS usuarios da classe Usuario.
        // Eles ja comecam com estados diferentes: Ana pode pegar ate 3
        // livros emprestados e Bruno pode pegar ate 1 livro.
        Usuario ana = new Usuario("Ana Souza", "2023001", 3);
        Usuario bruno = new Usuario("Bruno Lima", "2023002", 1);

        // Datas de exemplo para os emprestimos (formato aaaa-mm-dd).
        // LocalDate.now() pega a data de hoje do computador.
        // minusDays(n) volta n dias e plusDays(n) avanca n dias.
        String dataEmprestimo1 = LocalDate.now().minusDays(7).toString();  // Ana pegou ha 7 dias
        String dataPrevista1 = LocalDate.now().plusDays(7).toString();    // devolucao daqui a 7 dias (no prazo)
        String dataEmprestimo2 = LocalDate.now().minusDays(20).toString(); // Bruno pegou ha 20 dias
        String dataPrevista2 = LocalDate.now().minusDays(6).toString();   // devolucao venceu ha 6 dias (atrasado)

        System.out.println("==================================================");
        System.out.println("   RELATORIO DA BIBLIOTECA - GRUPO 1");
        System.out.println("==================================================");

        // ----------------------------------------------------
        // 2) ESTADO INICIAL DOS OBJETOS
        // ----------------------------------------------------
        System.out.println("\n-- ESTADO INICIAL DOS OBJETOS --");
        System.out.println("Livro 1: " + livro1.getTitulo() + " | disponivel? " + livro1.isDisponivel());
        System.out.println("Livro 2: " + livro2.getTitulo() + " | disponivel? " + livro2.isDisponivel());
        System.out.println("Ana   - emprestimos ativos: " + ana.getQtdEmprestimosAtivos()
                + " de " + ana.getLimiteEmprestimos());
        System.out.println("Bruno - emprestimos ativos: " + bruno.getQtdEmprestimosAtivos()
                + " de " + bruno.getLimiteEmprestimos());

        // ----------------------------------------------------
        // 3) REALIZANDO OS EMPRESTIMOS
        // ----------------------------------------------------

        // Ana pega o livro1 emprestado.
        // Chamamos o metodo emprestar() do livro (muda o estado dele) e o
        // metodo registrarEmprestimo() do usuario (aumenta os emprestimos).
        boolean livro1Emprestado = livro1.emprestar();
        boolean anaRegistrou = ana.registrarEmprestimo();
        Emprestimo emprestimo1 = null;
        if (livro1Emprestado && anaRegistrou) {
            emprestimo1 = new Emprestimo(livro1, ana, dataEmprestimo1, dataPrevista1);
        }

        // Bruno pega o livro2 emprestado (mas a devolucao dele ja venceu).
        boolean livro2Emprestado = livro2.emprestar();
        boolean brunoRegistrou = bruno.registrarEmprestimo();
        Emprestimo emprestimo2 = null;
        if (livro2Emprestado && brunoRegistrou) {
            emprestimo2 = new Emprestimo(livro2, bruno, dataEmprestimo2, dataPrevista2);
        }

        System.out.println("\n-- APOS OS EMPRESTIMOS --");
        System.out.println("Emprestimo 1: " + emprestimo1.getLivro().getTitulo()
                + " -> " + emprestimo1.getUsuario().getNome()
                + " (prevista: " + emprestimo1.getDataDevolucaoPrevista() + ")");
        System.out.println("Emprestimo 2: " + emprestimo2.getLivro().getTitulo()
                + " -> " + emprestimo2.getUsuario().getNome()
                + " (prevista: " + emprestimo2.getDataDevolucaoPrevista() + ")");

        // Depois dos emprestimos, os dois livros ficaram emprestados
        // (disponivel = false). O estado dos dois mudou.
        System.out.println("\nLivro 1 (" + livro1.getTitulo() + ") disponivel? " + livro1.isDisponivel());
        System.out.println("Livro 2 (" + livro2.getTitulo() + ") disponivel? " + livro2.isDisponivel());

        // Os dois emprestimos da MESMA classe tem comportamentos diferentes:
        // o emprestimo 1 esta no prazo e o emprestimo 2 esta atrasado.
        System.out.println("\nEmprestimo 1 esta atrasado? " + emprestimo1.estaAtrasado());
        System.out.println("Emprestimo 2 esta atrasado? " + emprestimo2.estaAtrasado());

        // ----------------------------------------------------
        // 4) TESTANDO OS LIMITES DE EMPRESTIMO
        // ----------------------------------------------------

        // Ana ainda pode emprestar (1 ativo < limite 3).
        System.out.println("\nAna pode emprestar outro livro? " + ana.podeEmprestar());

        // Bruno ja atingiu o limite (1 ativo = limite 1).
        System.out.println("Bruno pode emprestar outro livro? " + bruno.podeEmprestar());

        // Bruno tenta pegar o livro1 tambem, mas nao consegue: o livro1
        // nao esta disponivel e o limite do Bruno ja foi atingido.
        boolean segundaTentativaLivro = livro1.emprestar();
        boolean segundaTentativaRegistro = bruno.registrarEmprestimo();
        boolean segundaTentativa = segundaTentativaLivro && segundaTentativaRegistro;
        System.out.println("Bruno conseguiu o livro1? " + segundaTentativa
                + " (livro nao disponivel e limite atingido)");

        // ----------------------------------------------------
        // 5) DEVOLUCAO DO LIVRO 1
        // ----------------------------------------------------

        // Ana devolve o livro1 no prazo.
        // O metodo finalizar() do emprestimo marca o emprestimo como
        // devolvido, libera o livro (livro.devolver()) e diminui os
        // emprestimos ativos da Ana (usuario.registrarDevolucao()).
        emprestimo1.finalizar();

        System.out.println("\n-- APOS A DEVOLUCAO DO EMPRESTIMO 1 --");
        System.out.println("Livro 1 (" + livro1.getTitulo() + ") disponivel? " + livro1.isDisponivel());
        System.out.println("Livro 2 (" + livro2.getTitulo() + ") disponivel? " + livro2.isDisponivel());
        System.out.println("Ana   - emprestimos ativos: " + ana.getQtdEmprestimosAtivos());
        System.out.println("Bruno - emprestimos ativos: " + bruno.getQtdEmprestimosAtivos()
                + " (ainda com o livro2 em atraso)");

        // ----------------------------------------------------
        // 6) RESUMO FINAL
        // ----------------------------------------------------

        // Montamos o texto "ativo" ou "devolvido" com um if/else
        // simples, para ficar mais facil de ler o relatorio.
        String statusEmprestimo1;
        if (emprestimo1.isAtivo()) {
            statusEmprestimo1 = "ativo";
        } else {
            statusEmprestimo1 = "devolvido";
        }

        String statusEmprestimo2;
        if (emprestimo2.isAtivo()) {
            statusEmprestimo2 = "ativo";
        } else {
            statusEmprestimo2 = "devolvido";
        }

        System.out.println("\n==================================================");
        System.out.println("   RESUMO FINAL");
        System.out.println("==================================================");
        System.out.println("Livro 1: " + livro1.getTitulo() + " -> disponivel: " + livro1.isDisponivel());
        System.out.println("Livro 2: " + livro2.getTitulo() + " -> disponivel: " + livro2.isDisponivel());
        System.out.println("Ana:   " + ana.getQtdEmprestimosAtivos() + " emprestimo(s) ativo(s)");
        System.out.println("Bruno: " + bruno.getQtdEmprestimosAtivos() + " emprestimo(s) ativo(s)");
        System.out.println("Emprestimo 1 (Ana): " + statusEmprestimo1);
        System.out.println("Emprestimo 2 (Bruno): " + statusEmprestimo2);
    }
}
