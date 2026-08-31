// ============================================================
// CLASSE MAIN
// ============================================================
// Esta classe serve para DEMONSTRAR a modelagem da biblioteca.
// Ela cria objetos, executa metodos e imprime um relatorio no
// console mostrando o comportamento.
//
// A versao da Etapa 3 inclui:
// - A superclasse ItemBiblioteca (com titulo e disponivel).
// - As subclasses Livro e Revista usando extends e super(...).
// - O atributo anoPublicado (int) no Livro, pedido pelo professor.
// - Sobrescrita (@Override) e sobrecarga do metodo exibirResumo.
// - Polimorfismo no console.
// ============================================================

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // ----------------------------------------------------
        // 1) CRIACAO DOS AUTORES
        // ----------------------------------------------------
        Autor machado = new Autor("Machado de Assis", "Brasileira");
        Autor exupery = new Autor("Antoine de Saint-Exupery", "Francesa");
        Autor coelho = new Autor("Paulo Coelho", "Brasileira");

        System.out.println("==================================================");
        System.out.println("   RELATORIO DA BIBLIOTECA - GRUPO 1 - ETAPA 3");
        System.out.println("==================================================");

        System.out.println("\n-- AUTORES CADASTRADOS --");
        System.out.println("Autor 1: " + machado.getNome() + " (" + machado.getNacionalidade() + ")");
        System.out.println("Autor 2: " + exupery.getNome() + " (" + exupery.getNacionalidade() + ")");
        System.out.println("Autor 3: " + coelho.getNome() + " (" + coelho.getNacionalidade() + ")");

        // ----------------------------------------------------
        // 2) CRIACAO DOS LIVROS (com autores e ano de publicacao)
        // ----------------------------------------------------
        ArrayList<Autor> autoresLivro1 = new ArrayList<Autor>();
        autoresLivro1.add(machado);
        Livro livro1 = new Livro("Dom Casmurro", autoresLivro1, "9788535910663", 1899);

        ArrayList<Autor> autoresLivro2 = new ArrayList<Autor>();
        autoresLivro2.add(exupery);
        Livro livro2 = new Livro("O Pequeno Principe", autoresLivro2, "9788595081516", 1943);

        ArrayList<Autor> autoresLivro3 = new ArrayList<Autor>();
        autoresLivro3.add(coelho);
        Livro livro3 = new Livro("O Alquimista", autoresLivro3, "9788576651352", 1988);

        System.out.println("\n-- LIVROS CADASTRADOS --");
        System.out.println("Livro 1: " + livro1.getTitulo() + " | Ano: " + livro1.getAnoPublicado()
                + " | Disponivel? " + livro1.isDisponivel());
        System.out.println("Livro 2: " + livro2.getTitulo() + " | Ano: " + livro2.getAnoPublicado()
                + " | Disponivel? " + livro2.isDisponivel());
        System.out.println("Livro 3: " + livro3.getTitulo() + " | Ano: " + livro3.getAnoPublicado()
                + " | Disponivel? " + livro3.isDisponivel());

        // ----------------------------------------------------
        // 3) CRIACAO DAS REVISTAS (nova subclasse da Etapa 3)
        // ----------------------------------------------------
        Revista revista1 = new Revista("Superinteressante", "Abril", 250);
        Revista revista2 = new Revista("Veja", "Abril", 2600);

        System.out.println("\n-- REVISTAS CADASTRADAS --");
        System.out.println("Revista 1: " + revista1.getTitulo() + " | Edicao: " + revista1.getEdicao()
                + " | Disponivel? " + revista1.isDisponivel());
        System.out.println("Revista 2: " + revista2.getTitulo() + " | Edicao: " + revista2.getEdicao()
                + " | Disponivel? " + revista2.isDisponivel());

        // ----------------------------------------------------
        // 4) POLIMORFISMO + SOBRESCRITA
        // ----------------------------------------------------
        // Referencias da superclasse (ItemBiblioteca) apontando para
        // objetos das subclasses (Livro e Revista). Ao chamar
        // exibirResumo(), cada um usa a sua versao sobrescrita.
        System.out.println("\n-- POLIMORFISMO --");
        ItemBiblioteca item1 = new Livro("Dom Casmurro", autoresLivro1, "9788535910663", 1899);
        ItemBiblioteca item2 = new Revista("Superinteressante", "Abril", 250);
        item1.exibirResumo();
        item2.exibirResumo();

        // ----------------------------------------------------
        // 5) SOBRECARGA DE METODO
        // ----------------------------------------------------
        // Mesmo nome exibirResumo com parametro boolean (detalhado).
        System.out.println("\n-- SOBRECARGA --");
        item1.exibirResumo(true);  // versao detalhada
        item2.exibirResumo(false); // versao curta

        // ----------------------------------------------------
        // 6) CRIACAO DOS USUARIOS
        // ----------------------------------------------------
        Usuario ana = new Usuario("Ana Souza", "2023001", 3);
        Usuario bruno = new Usuario("Bruno Lima", "2023002", 1);

        System.out.println("\n-- USUARIOS CADASTRADOS --");
        System.out.println("Ana   - emprestimos ativos: " + ana.getQtdEmprestimosAtivos()
                + " de " + ana.getLimiteEmprestimos());
        System.out.println("Bruno - emprestimos ativos: " + bruno.getQtdEmprestimosAtivos()
                + " de " + bruno.getLimiteEmprestimos());

        // ----------------------------------------------------
        // 7) OPERACOES VALIDAS (emprestimos)
        // ----------------------------------------------------
        String dataEmprestimo1 = LocalDate.now().minusDays(7).toString();
        String dataPrevista1 = LocalDate.now().plusDays(7).toString();
        String dataEmprestimo2 = LocalDate.now().minusDays(20).toString();
        String dataPrevista2 = LocalDate.now().minusDays(6).toString();

        boolean livro1Emprestado = livro1.emprestar();
        boolean anaRegistrou = ana.registrarEmprestimo();
        Emprestimo emprestimo1 = null;
        if (livro1Emprestado && anaRegistrou) {
            emprestimo1 = new Emprestimo(livro1, ana, dataEmprestimo1, dataPrevista1);
        }

        boolean livro2Emprestado = livro2.emprestar();
        boolean brunoRegistrou = bruno.registrarEmprestimo();
        Emprestimo emprestimo2 = null;
        if (livro2Emprestado && brunoRegistrou) {
            emprestimo2 = new Emprestimo(livro2, bruno, dataEmprestimo2, dataPrevista2);
        }

        System.out.println("\n-- APOS OS EMPRESTIMOS --");
        System.out.println("Emprestimo 1: " + emprestimo1.getLivro().getTitulo()
                + " -> " + emprestimo1.getUsuario().getNome());
        System.out.println("Emprestimo 2: " + emprestimo2.getLivro().getTitulo()
                + " -> " + emprestimo2.getUsuario().getNome());
        System.out.println("Emprestimo 1 esta atrasado? " + emprestimo1.estaAtrasado());
        System.out.println("Emprestimo 2 esta atrasado? " + emprestimo2.estaAtrasado());

        // ----------------------------------------------------
        // 8) TENTATIVAS INVALIDAS (validacoes)
        // ----------------------------------------------------
        System.out.println("\n-- TENTATIVAS INVALIDAS --");
        boolean brunoTenta2 = bruno.registrarEmprestimo();
        System.out.println("Bruno tentou registrar emprestimo com limite atingido? "
                + brunoTenta2 + " (deveria ser false)");
        boolean livro1JaEmprestado = livro1.emprestar();
        System.out.println("Livro 1 ja emprestado, tentou emprestar de novo? "
                + livro1JaEmprestado + " (deveria ser false)");

        // ----------------------------------------------------
        // 9) DEVOLUCAO DO EMPRESTIMO 1
        // ----------------------------------------------------
        emprestimo1.finalizar();
        System.out.println("\n-- APOS A DEVOLUCAO DO EMPRESTIMO 1 --");
        System.out.println("Livro 1 (" + livro1.getTitulo() + ") disponivel? " + livro1.isDisponivel());
        System.out.println("Ana   - emprestimos ativos: " + ana.getQtdEmprestimosAtivos());

        // ----------------------------------------------------
        // 10) RESUMO FINAL (usando o metodo sobrescrito)
        // ----------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("   RESUMO FINAL");
        System.out.println("==================================================");
        livro1.exibirResumo();
        livro2.exibirResumo();
        livro3.exibirResumo();
        revista1.exibirResumo();
        revista2.exibirResumo();
        System.out.println("Ana:   " + ana.getQtdEmprestimosAtivos() + " emprestimo(s) ativo(s)");
        System.out.println("Bruno: " + bruno.getQtdEmprestimosAtivos() + " emprestimo(s) ativo(s)");
        System.out.println("Emprestimo 1 (Ana) ativo? " + emprestimo1.isAtivo());
        System.out.println("Emprestimo 2 (Bruno) ativo? " + emprestimo2.isAtivo());
    }
}
