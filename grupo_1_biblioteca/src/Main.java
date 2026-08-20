// ============================================================
// CLASSE MAIN
// ============================================================
// Esta classe serve para DEMONSTRAR a modelagem da biblioteca.
// Ela cria objetos das classes Autor, Livro, Usuario e Emprestimo,
// executa metodos que mudam o estado desses objetos e imprime um
// mini-relatorio no console mostrando o comportamento.
//
// A versao da Etapa 2 inclui:
// - A classe Autor (um livro pode ter varios autores)
// - Atributos privados com encapsulamento
// - Construtores para todas as classes
// - Validacoes de estado invalido
// ============================================================

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // ----------------------------------------------------
        // 1) CRIACAO DOS AUTORES
        // ----------------------------------------------------

        // Criamos tres autores da classe Autor.
        Autor machado = new Autor("Machado de Assis", "Brasileira");
        Autor exupery = new Autor("Antoine de Saint-Exupery", "Francesa");
        Autor coelho = new Autor("Paulo Coelho", "Brasileira");

        System.out.println("==================================================");
        System.out.println("   RELATORIO DA BIBLIOTECA - GRUPO 1 - ETAPA 2");
        System.out.println("==================================================");

        // Mostramos os dados dos autores criados.
        System.out.println("\n-- AUTORES CADASTRADOS --");
        System.out.println("Autor 1: " + machado.getNome() + " (" + machado.getNacionalidade() + ")");
        System.out.println("Autor 2: " + exupery.getNome() + " (" + exupery.getNacionalidade() + ")");
        System.out.println("Autor 3: " + coelho.getNome() + " (" + coelho.getNacionalidade() + ")");

        // ----------------------------------------------------
        // 2) CRIACAO DOS LIVROS (com varios autores)
        // ----------------------------------------------------

        // Livro 1: Dom Casmurro - apenas UM autor (Machado).
        ArrayList<Autor> autoresLivro1 = new ArrayList<Autor>();
        autoresLivro1.add(machado);
        Livro livro1 = new Livro("Dom Casmurro", autoresLivro1, "9788535910663");

        // Livro 2: O Pequeno Principe - apenas UM autor (Exupery).
        ArrayList<Autor> autoresLivro2 = new ArrayList<Autor>();
        autoresLivro2.add(exupery);
        Livro livro2 = new Livro("O Pequeno Principe", autoresLivro2, "9788595081516");

        // Livro 3: Alquimista - apenas UM autor (Coelho).
        // Livro extra para testar validacao: tentar emprestar um livro
        // que ja esta emprestado.
        ArrayList<Autor> autoresLivro3 = new ArrayList<Autor>();
        autoresLivro3.add(coelho);
        Livro livro3 = new Livro("O Alquimista", autoresLivro3, "9788576651352");

        System.out.println("\n-- LIVROS CADASTRADOS --");
        System.out.println("Livro 1: " + livro1.getTitulo()
                + " | Autores: " + livro1.getNomesAutores()
                + " | Disponivel? " + livro1.isDisponivel());
        System.out.println("Livro 2: " + livro2.getTitulo()
                + " | Autores: " + livro2.getNomesAutores()
                + " | Disponivel? " + livro2.isDisponivel());
        System.out.println("Livro 3: " + livro3.getTitulo()
                + " | Autores: " + livro3.getNomesAutores()
                + " | Disponivel? " + livro3.isDisponivel());

        // ----------------------------------------------------
        // 3) CRIACAO DOS USUARIOS
        // ----------------------------------------------------

        // Criamos DOIS usuarios com limites diferentes.
        Usuario ana = new Usuario("Ana Souza", "2023001", 3);
        Usuario bruno = new Usuario("Bruno Lima", "2023002", 1);

        System.out.println("\n-- USUARIOS CADASTRADOS --");
        System.out.println("Ana   - emprestimos ativos: " + ana.getQtdEmprestimosAtivos()
                + " de " + ana.getLimiteEmprestimos());
        System.out.println("Bruno - emprestimos ativos: " + bruno.getQtdEmprestimosAtivos()
                + " de " + bruno.getLimiteEmprestimos());

        // ----------------------------------------------------
        // 4) OPERACOES VALIDAS (emprestimos)
        // ----------------------------------------------------

        // Datas de exemplo (formato aaaa-mm-dd).
        String dataEmprestimo1 = LocalDate.now().minusDays(7).toString();
        String dataPrevista1 = LocalDate.now().plusDays(7).toString();
        String dataEmprestimo2 = LocalDate.now().minusDays(20).toString();
        String dataPrevista2 = LocalDate.now().minusDays(6).toString();

        // Ana pega o livro1 emprestado (operacao valida).
        boolean livro1Emprestado = livro1.emprestar();
        boolean anaRegistrou = ana.registrarEmprestimo();
        Emprestimo emprestimo1 = null;
        if (livro1Emprestado && anaRegistrou) {
            emprestimo1 = new Emprestimo(livro1, ana, dataEmprestimo1, dataPrevista1);
        }

        // Bruno pega o livro2 emprestado (operacao valida, mas com prazo vencido).
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

        System.out.println("\nLivro 1 (" + livro1.getTitulo() + ") disponivel? " + livro1.isDisponivel());
        System.out.println("Livro 2 (" + livro2.getTitulo() + ") disponivel? " + livro2.isDisponivel());

        System.out.println("\nEmprestimo 1 esta atrasado? " + emprestimo1.estaAtrasado());
        System.out.println("Emprestimo 2 esta atrasado? " + emprestimo2.estaAtrasado());

        // ----------------------------------------------------
        // 5) TENTATIVAS INVALIDAS (validacoes de estado)
        // ----------------------------------------------------

        System.out.println("\n-- TENTATIVAS INVALIDAS --");

        // Tentativa 1: Bruno tenta emprestar outro livro, mas ja atingiu o limite.
        boolean brunoTenta2 = bruno.registrarEmprestimo();
        System.out.println("Bruno tentou registrar emprestimo com limite atingido? "
                + brunoTenta2 + " (deveria ser false)");

        // Tentativa 2: Tentar emprestar um livro que ja esta emprestado.
        // O livro1 ja esta com Ana, entao emprestar() deve retornar false.
        boolean livro1JaEmprestado = livro1.emprestar();
        System.out.println("Livro 1 ja emprestado, tentou emprestar de novo? "
                + livro1JaEmprestado + " (deveria ser false)");

        // ----------------------------------------------------
        // 6) METODO DE NEGOCIO PROTEGE MELHOR QUE SETTER
        // ----------------------------------------------------

        // Em vez de permitir setDisponivel(true/false) diretamente,
        // o Livro oferece emprestar() e devolver(), que verificam
        // o estado antes de alterar. Aqui mostramos que chamar
        // devolver() em um livro ja disponivel nao faz nada.
        System.out.println("\n-- PROTECAO POR METODO DE NEGOCIO --");
        System.out.println("Livro 3 disponivel? " + livro3.isDisponivel());
        boolean livro3Devolveu = livro3.devolver();
        System.out.println("Tentou devolver livro 3 (ja disponivel)? "
                + livro3Devolveu + " (deveria ser false, pois o metodo protege o estado)");

        // ----------------------------------------------------
        // 7) TESTANDO OS LIMITES DE EMPRESTIMO
        // ----------------------------------------------------

        System.out.println("\n-- LIMITES DE EMPRESTIMO --");
        System.out.println("Ana pode emprestar outro livro? " + ana.podeEmprestar());
        System.out.println("Bruno pode emprestar outro livro? " + bruno.podeEmprestar());

        // ----------------------------------------------------
        // 8) DEVOLUCAO DO EMPRESTIMO 1
        // ----------------------------------------------------

        emprestimo1.finalizar();

        System.out.println("\n-- APOS A DEVOLUCAO DO EMPRESTIMO 1 --");
        System.out.println("Livro 1 (" + livro1.getTitulo() + ") disponivel? " + livro1.isDisponivel());
        System.out.println("Livro 2 (" + livro2.getTitulo() + ") disponivel? " + livro2.isDisponivel());
        System.out.println("Ana   - emprestimos ativos: " + ana.getQtdEmprestimosAtivos());
        System.out.println("Bruno - emprestimos ativos: " + bruno.getQtdEmprestimosAtivos()
                + " (ainda com o livro2 em atraso)");

        // ----------------------------------------------------
        // 9) RESUMO FINAL
        // ----------------------------------------------------

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
        System.out.println("Livro 1: " + livro1.getTitulo()
                + " | Autores: " + livro1.getNomesAutores()
                + " | disponivel: " + livro1.isDisponivel());
        System.out.println("Livro 2: " + livro2.getTitulo()
                + " | Autores: " + livro2.getNomesAutores()
                + " | disponivel: " + livro2.isDisponivel());
        System.out.println("Livro 3: " + livro3.getTitulo()
                + " | Autores: " + livro3.getNomesAutores()
                + " | disponivel: " + livro3.isDisponivel());
        System.out.println("Ana:   " + ana.getQtdEmprestimosAtivos() + " emprestimo(s) ativo(s)");
        System.out.println("Bruno: " + bruno.getQtdEmprestimosAtivos() + " emprestimo(s) ativo(s)");
        System.out.println("Emprestimo 1 (Ana): " + statusEmprestimo1);
        System.out.println("Emprestimo 2 (Bruno): " + statusEmprestimo2);
    }
}
