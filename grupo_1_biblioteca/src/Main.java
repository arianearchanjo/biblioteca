// ============================================================
// CLASSE MAIN
// ============================================================
// Demonstracao da modelagem da biblioteca na Etapa 4:
// classe abstrata, interface, excecao personalizada e
// colecoes (List, Set, Map, Iterator).
//
// Cria pelo menos dois objetos de cada classe com estados
// diferentes e mostra essa diferenca no console.
// ============================================================

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // ----------------------------------------------------
        // 1) AUTORES (dois objetos com estados diferentes)
        // ----------------------------------------------------
        Autor machado = new Autor("Machado de Assis", "Brasileira");
        Autor exupery = new Autor("Antoine de Saint-Exupery", "Francesa");

        System.out.println("==================================================");
        System.out.println("   RELATORIO DA BIBLIOTECA - GRUPO 1 - ETAPA 4");
        System.out.println("==================================================");

        System.out.println("\n-- AUTORES CADASTRADOS --");
        System.out.println("Autor 1: " + machado.getNome() + " (" + machado.getNacionalidade() + ")");
        System.out.println("Autor 2: " + exupery.getNome() + " (" + exupery.getNacionalidade() + ")");

        // ----------------------------------------------------
        // 2) LIVROS (dois objetos com estados diferentes)
        // ----------------------------------------------------
        ArrayList<Autor> autoresLivro1 = new ArrayList<Autor>();
        autoresLivro1.add(machado);
        Livro livro1 = new Livro("Dom Casmurro", autoresLivro1, "9788535910663", 1899, "Romance");

        ArrayList<Autor> autoresLivro2 = new ArrayList<Autor>();
        autoresLivro2.add(exupery);
        Livro livro2 = new Livro("O Pequeno Principe", autoresLivro2, "9788595081516", 1943, "Infantil");

        System.out.println("\n-- LIVROS CADASTRADOS --");
        System.out.println("Livro 1: " + livro1.getTitulo() + " | Ano: " + livro1.getAnoPublicado()
                + " | ISBN: " + livro1.getIsbn() + " | Categoria: " + livro1.getCategoria()
                + " | Disponivel? " + livro1.isDisponivel());
        System.out.println("Livro 2: " + livro2.getTitulo() + " | Ano: " + livro2.getAnoPublicado()
                + " | ISBN: " + livro2.getIsbn() + " | Categoria: " + livro2.getCategoria()
                + " | Disponivel? " + livro2.isDisponivel());

        // ----------------------------------------------------
        // 3) REVISTAS (dois objetos com estados diferentes)
        // ----------------------------------------------------
        Revista revista1 = new Revista("Superinteressante", "Abril", 250);
        Revista revista2 = new Revista("Veja", "Abril", 2600);

        System.out.println("\n-- REVISTAS CADASTRADAS --");
        System.out.println("Revista 1: " + revista1.getTitulo() + " | Edicao: " + revista1.getEdicao()
                + " | Disponivel? " + revista1.isDisponivel());
        System.out.println("Revista 2: " + revista2.getTitulo() + " | Edicao: " + revista2.getEdicao()
                + " | Disponivel? " + revista2.isDisponivel());

        // ----------------------------------------------------
        // 4) USUARIOS (dois objetos com estados diferentes)
        // ----------------------------------------------------
        Usuario ana = new Usuario("Ana Souza", "2023001", 3);
        Usuario bruno = new Usuario("Bruno Lima", "2023002", 1);

        System.out.println("\n-- USUARIOS CADASTRADOS --");
        System.out.println("Ana   - matricula: " + ana.getMatricula()
                + " | emprestimos ativos: " + ana.getQtdEmprestimosAtivos() + " de " + ana.getLimiteEmprestimos());
        System.out.println("Bruno - matricula: " + bruno.getMatricula()
                + " | emprestimos ativos: " + bruno.getQtdEmprestimosAtivos() + " de " + bruno.getLimiteEmprestimos());

        // ----------------------------------------------------
        // 5) BIBLIOTECA E COLECOES (List, Set, Map)
        // ----------------------------------------------------
        Biblioteca bib = new Biblioteca();

        // List<ItemBiblioteca> catalogo (ordem de cadastro, permite repetir titulo)
        bib.cadastrarItem(livro1);
        bib.cadastrarItem(livro2);
        bib.cadastrarItem(revista1);
        bib.cadastrarItem(revista2);

        // Set<String> isbns (impede ISBN duplicado)
        System.out.println("\n-- COLECOES --");
        System.out.println("ISBN cadastrado (9788535910663)? " + bib.isbnJaCadastrado("9788535910663")
                + " (deveria ser true)");
        System.out.println("ISBN cadastrado (0000000000000)? " + bib.isbnJaCadastrado("0000000000000")
                + " (deveria ser false)");

        // Map<String, Usuario> usuarios (busca por matricula)
        bib.cadastrarUsuario(ana);
        bib.cadastrarUsuario(bruno);
        Usuario usuarioEncontrado = bib.buscarUsuarioPorMatricula("2023001");
        System.out.println("Usuario encontrado pela matricula 2023001: "
                + (usuarioEncontrado != null ? usuarioEncontrado.getNome() : "nenhum"));

        // ----------------------------------------------------
        // 6) POLIMORFISMO (referencias da classe abstrata)
        // ----------------------------------------------------
        System.out.println("\n-- POLIMORFISMO (ItemBiblioteca abstrata) --");
        ItemBiblioteca item1 = livro1;
        ItemBiblioteca item2 = revista1;
        item1.exibirResumo(); // usa sobrescricao do Livro
        item2.exibirResumo(); // usa sobrescricao da Revista

        // ----------------------------------------------------
        // 7) EMPRESTIMO COM TRATAMENTO DE EXCECAO (try/catch)
        // ----------------------------------------------------
        System.out.println("\n-- EMPRESTIMO COM TRY/CATCH --");

        // Cenario valido: emprestar livro disponivel
        try {
            bib.emprestarItem("Dom Casmurro");
            System.out.println("Emprestimo de 'Dom Casmurro' realizado com sucesso.");
        } catch (ItemIndisponivelException e) {
            System.out.println("Erro ao emprestar 'Dom Casmurro': " + e.getMessage());
        }

        // Cenario invalido: tentar emprestar o mesmo livro de novo (ja emprestado)
        try {
            bib.emprestarItem("Dom Casmurro");
            System.out.println("Emprestimo de 'Dom Casmurro' realizado com sucesso (2a tentativa).");
        } catch (ItemIndisponivelException e) {
            System.out.println("Erro ao emprestar 'Dom Casmurro' novamente: " + e.getMessage());
        }

        // Cenario invalido: item nao encontrado no catalogo
        try {
            bib.emprestarItem("Livro Que Nao Existe");
        } catch (ItemIndisponivelException e) {
            System.out.println("Erro ao emprestar item inexistente: " + e.getMessage());
        }

        // ----------------------------------------------------
        // 8) ITERATOR (remocao segura do catalogo)
        // ----------------------------------------------------
        System.out.println("\n-- ITERATOR (remocao segura) --");
        System.out.println("Total de itens antes da remocao: " + bib.getTotalItens());
        bib.removerItensPorTitulo("Veja"); // remove a revista "Veja" do catalogo
        System.out.println("Total de itens apos remover 'Veja': " + bib.getTotalItens());
        System.out.println("Catalogo apos remocao:");
        bib.listarCatalogo();

        // ----------------------------------------------------
        // 9) DEVOLUCAO
        // ----------------------------------------------------
        bib.devolverItem("Dom Casmurro");
        System.out.println("\n-- APOS DEVOLUCAO DE 'Dom Casmurro' --");
        System.out.println("'Dom Casmurro' disponivel? " + livro1.isDisponivel() + " (deveria ser true)");

        // ----------------------------------------------------
        // 10) RESUMO FINAL
        // ----------------------------------------------------
        System.out.println("\n==================================================");
        System.out.println("   RESUMO FINAL");
        System.out.println("==================================================");
        System.out.println("Itens no catalogo: " + bib.getTotalItens());
        System.out.println("ISBNs unicos cadastrados: " + bib.getTotalIsbnsUnicos());
        System.out.println("Usuarios cadastrados: " + bib.getTotalUsuarios());
        System.out.println("Ana   - emprestimos ativos: " + ana.getQtdEmprestimosAtivos());
        System.out.println("Bruno - emprestimos ativos: " + bruno.getQtdEmprestimosAtivos());
        livro1.exibirResumo(true);
        livro2.exibirResumo(true);
        revista1.exibirResumo(true);
    }
}
