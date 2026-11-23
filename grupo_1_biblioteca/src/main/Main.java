// ============================================================
// CLASSE MAIN - PONTO DE ENTRADA COM MENU INTERATIVO
// ============================================================
// Menu navegavel com operacoes de CRUD e negocio para o dominio
// Biblioteca. Tratamento de entradas invalidas e encerramento limpo.
// ============================================================

package main;

import model.*;
import service.Biblioteca;
import exception.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SISTEMA DE BIBLIOTECA - GRUPO 1 (ADS 2)");
        System.out.println("==================================================");
        
        // Carregar dados de exemplo
        carregarDadosExemplo();
        
        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerOpcaoMenu(0, 9);
            
            try {
                switch (opcao) {
                    case 1 -> menuItens();
                    case 2 -> menuUsuarios();
                    case 3 -> menuEmprestimos();
                    case 4 -> menuDevolucoes();
                    case 5 -> menuRelatorios();
                    case 6 -> buscarItem();
                    case 7 -> buscarUsuario();
                    case 8 -> verificarAtrasados();
                    case 9 -> inicializarDadosExemplo();
                    case 0 -> {
                        System.out.println("\nEncerrando o sistema... Ate logo!");
                        executando = false;
                    }
                }
            } catch (BibliotecaException e) {
                System.out.println("\n[ERRO DE NEGOCIO] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n[ERRO INESPERADO] " + e.getMessage());
            }
            
            if (executando) {
                pausar();
            }
        }
        
        scanner.close();
    }

    // ============================================================
    // MENUS
    // ============================================================

    private static void exibirMenuPrincipal() {
        System.out.println("\n==================================================");
        System.out.println("           MENU PRINCIPAL - BIBLIOTECA");
        System.out.println("==================================================");
        System.out.println("1  - Gerenciar Itens (Livros/Revistas)");
        System.out.println("2  - Gerenciar Usuarios");
        System.out.println("3  - Realizar Emprestimo");
        System.out.println("4  - Realizar Devolucao");
        System.out.println("5  - Relatorios e Listagens");
        System.out.println("6  - Buscar Item por Titulo");
        System.out.println("7  - Buscar Usuario por Matricula");
        System.out.println("8  - Verificar Emprestimos Atrasados");
        System.out.println("9  - Recarregar Dados de Exemplo");
        System.out.println("0  - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static void menuItens() {
        boolean voltando = false;
        while (!voltando) {
            System.out.println("\n--- GERENCIAR ITENS ---");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Cadastrar Revista");
            System.out.println("3 - Listar Todos os Itens");
            System.out.println("4 - Remover Item por Titulo");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            
            int opcao = lerOpcaoMenu(0, 4);
            try {
                switch (opcao) {
                    case 1 -> cadastrarLivro();
                    case 2 -> cadastrarRevista();
                    case 3 -> biblioteca.listarCatalogo();
                    case 4 -> removerItem();
                    case 0 -> voltando = true;
                }
            } catch (BibliotecaException e) {
                System.out.println("[ERRO] " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("[VALIDACAO] " + e.getMessage());
            }
        }
    }

    private static void menuUsuarios() {
        boolean voltando = false;
        while (!voltando) {
            System.out.println("\n--- GERENCIAR USUARIOS ---");
            System.out.println("1 - Cadastrar Usuario");
            System.out.println("2 - Listar Todos os Usuarios");
            System.out.println("3 - Remover Usuario");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            
            int opcao = lerOpcaoMenu(0, 3);
            try {
                switch (opcao) {
                    case 1 -> cadastrarUsuario();
                    case 2 -> biblioteca.listarUsuarios();
                    case 3 -> removerUsuario();
                    case 0 -> voltando = true;
                }
            } catch (BibliotecaException e) {
                System.out.println("[ERRO] " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("[ERRO] " + e.getMessage());
            }
        }
    }

    private static void menuEmprestimos() {
        System.out.println("\n--- REALIZAR EMPRESTIMO ---");
        try {
            System.out.print("Titulo do item: ");
            String titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) throw new EntradaInvalidaException("Titulo nao pode ser vazio.");

            System.out.print("Matricula do usuario: ");
            String matricula = scanner.nextLine().trim();
            if (matricula.isEmpty()) throw new EntradaInvalidaException("Matricula nao pode ser vazia.");

            System.out.print("Prazo em dias (padrao 7): ");
            String diasStr = scanner.nextLine().trim();
            int dias = diasStr.isEmpty() ? 7 : lerInteiroPositivo(diasStr, "Prazo");

            Emprestimo emprestimo = biblioteca.emprestarItem(titulo, matricula, dias);
            System.out.println("\n[SUCESSO] Emprestimo realizado!");
            System.out.println(emprestimo);
        } catch (BibliotecaException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    private static void menuDevolucoes() {
        System.out.println("\n--- REALIZAR DEVOLUCAO ---");
        try {
            System.out.print("Titulo do item: ");
            String titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) throw new EntradaInvalidaException("Titulo nao pode ser vazio.");

            System.out.print("Matricula do usuario: ");
            String matricula = scanner.nextLine().trim();
            if (matricula.isEmpty()) throw new EntradaInvalidaException("Matricula nao pode ser vazia.");

            biblioteca.devolverItem(titulo, matricula);
            System.out.println("\n[SUCESSO] Devolucao realizada com sucesso!");
        } catch (BibliotecaException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    private static void menuRelatorios() {
        boolean voltando = false;
        while (!voltando) {
            System.out.println("\n--- RELATORIOS E LISTAGENS ---");
            System.out.println("1 - Listar Catalogo Completo");
            System.out.println("2 - Listar Usuarios");
            System.out.println("3 - Listar Emprestimos Ativos");
            System.out.println("4 - Listar Emprestimos Atrasados");
            System.out.println("5 - Historico Completo de Emprestimos");
            System.out.println("6 - Estatisticas do Sistema");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            
            int opcao = lerOpcaoMenu(0, 6);
            switch (opcao) {
                case 1 -> biblioteca.listarCatalogo();
                case 2 -> biblioteca.listarUsuarios();
                case 3 -> biblioteca.listarEmprestimosAtivos();
                case 4 -> biblioteca.listarEmprestimosAtrasados();
                case 5 -> biblioteca.listarHistoricoEmprestimos();
                case 6 -> exibirEstatisticas();
                case 0 -> voltando = true;
            }
        }
    }

    // ============================================================
    // OPERACOES DE CADASTRO
    // ============================================================

    private static void cadastrarLivro() throws BibliotecaException {
        System.out.println("\n--- CADASTRAR LIVRO ---");
        
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) throw new EntradaInvalidaException("Titulo nao pode ser vazio.");

        System.out.print("ISBN (10 ou 13 digitos): ");
        String isbn = scanner.nextLine().trim();
        if (isbn.isEmpty()) throw new EntradaInvalidaException("ISBN nao pode ser vazio.");

        System.out.print("Ano de publicacao: ");
        int ano = lerInteiro(scanner.nextLine().trim(), "Ano");
        
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine().trim();
        if (categoria.isEmpty()) throw new EntradaInvalidaException("Categoria nao pode ser vazia.");

        System.out.print("Quantidade de autores: ");
        int qtdAutores = lerInteiroPositivo(scanner.nextLine().trim(), "Quantidade de autores");
        
        List<Autor> autores = new java.util.ArrayList<>();
        for (int i = 1; i <= qtdAutores; i++) {
            System.out.println("  Autor " + i + ":");
            System.out.print("    Nome: ");
            String nomeAutor = scanner.nextLine().trim();
            if (nomeAutor.isEmpty()) throw new EntradaInvalidaException("Nome do autor nao pode ser vazio.");
            
            System.out.print("    Nacionalidade: ");
            String nacionalidade = scanner.nextLine().trim();
            if (nacionalidade.isEmpty()) throw new EntradaInvalidaException("Nacionalidade nao pode ser vazia.");
            
            autores.add(new Autor(nomeAutor, nacionalidade));
        }

        Livro livro = new Livro(titulo, autores, isbn, ano, categoria);
        biblioteca.cadastrarItem(livro);
        System.out.println("\n[SUCESSO] Livro cadastrado com sucesso!");
    }

    private static void cadastrarRevista() throws BibliotecaException {
        System.out.println("\n--- CADASTRAR REVISTA ---");
        
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) throw new EntradaInvalidaException("Titulo nao pode ser vazio.");

        System.out.print("Editora: ");
        String editora = scanner.nextLine().trim();
        if (editora.isEmpty()) throw new EntradaInvalidaException("Editora nao pode ser vazia.");

        System.out.print("Edicao (numero): ");
        int edicao = lerInteiroPositivo(scanner.nextLine().trim(), "Edicao");

        Revista revista = new Revista(titulo, editora, edicao);
        biblioteca.cadastrarItem(revista);
        System.out.println("\n[SUCESSO] Revista cadastrada com sucesso!");
    }

    private static void cadastrarUsuario() throws BibliotecaException {
        System.out.println("\n--- CADASTRAR USUARIO ---");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) throw new EntradaInvalidaException("Nome nao pode ser vazio.");

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine().trim();
        if (matricula.isEmpty()) throw new EntradaInvalidaException("Matricula nao pode ser vazia.");

        System.out.print("Limite de emprestimos simultaneos: ");
        int limite = lerInteiroPositivo(scanner.nextLine().trim(), "Limite de emprestimos");

        Usuario usuario = new Usuario(nome, matricula, limite);
        biblioteca.cadastrarUsuario(usuario);
        System.out.println("\n[SUCESSO] Usuario cadastrado com sucesso!");
    }

    private static void removerItem() throws BibliotecaException {
        System.out.print("\nTitulo do item a remover: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) throw new EntradaInvalidaException("Titulo nao pode ser vazio.");
        
        biblioteca.removerItemPorTitulo(titulo);
        System.out.println("[SUCESSO] Item(s) removido(s) com sucesso!");
    }

    private static void removerUsuario() throws BibliotecaException {
        System.out.print("\nMatricula do usuario a remover: ");
        String matricula = scanner.nextLine().trim();
        if (matricula.isEmpty()) throw new EntradaInvalidaException("Matricula nao pode ser vazia.");
        
        biblioteca.removerUsuario(matricula);
        System.out.println("[SUCESSO] Usuario removido com sucesso!");
    }

    // ============================================================
    // BUSCAS
    // ============================================================

    private static void buscarItem() throws BibliotecaException {
        System.out.print("\nTitulo para buscar: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) throw new EntradaInvalidaException("Titulo nao pode ser vazio.");
        
        List<ItemBiblioteca> itens = biblioteca.buscarItensPorTitulo(titulo);
        if (itens.isEmpty()) {
            System.out.println("Nenhum item encontrado com esse titulo.");
        } else {
            System.out.println("\nItens encontrados:");
            for (ItemBiblioteca item : itens) {
                item.exibirResumo();
            }
        }
    }

    private static void buscarUsuario() throws BibliotecaException {
        System.out.print("\nMatricula para buscar: ");
        String matricula = scanner.nextLine().trim();
        if (matricula.isEmpty()) throw new EntradaInvalidaException("Matricula nao pode ser vazia.");
        
        Usuario usuario = biblioteca.buscarUsuarioPorMatricula(matricula);
        System.out.println("\nUsuario encontrado:");
        System.out.println(usuario);
    }

    private static void verificarAtrasados() {
        biblioteca.listarEmprestimosAtrasados();
    }

    private static void exibirEstatisticas() {
        System.out.println("\n=== ESTATISTICAS DO SISTEMA ===");
        System.out.println("Total de itens no catalogo: " + biblioteca.getTotalItens());
        System.out.println("Total de ISBNs unicos: " + biblioteca.getTotalIsbnsUnicos());
        System.out.println("Total de usuarios cadastrados: " + biblioteca.getTotalUsuarios());
        System.out.println("Emprestimos ativos: " + biblioteca.getTotalEmprestimosAtivos());
    }

    // ============================================================
    // DADOS DE EXEMPLO
    // ============================================================

    private static void carregarDadosExemplo() {
        try {
            // Autores
            Autor machado = new Autor("Machado de Assis", "Brasileira");
            Autor exupery = new Autor("Antoine de Saint-Exupery", "Francesa");
            Autor clarice = new Autor("Clarice Lispector", "Brasileira");
            Autor asimov = new Autor("Isaac Asimov", "Americana");

            // Livros
            List<Autor> autores1 = new java.util.ArrayList<>();
            autores1.add(machado);
            Livro livro1 = new Livro("Dom Casmurro", autores1, "9788535910663", 1899, "Romance");

            List<Autor> autores2 = new java.util.ArrayList<>();
            autores2.add(exupery);
            Livro livro2 = new Livro("O Pequeno Principe", autores2, "9788595081516", 1943, "Infantil");

            List<Autor> autores3 = new java.util.ArrayList<>();
            autores3.add(clarice);
            Livro livro3 = new Livro("A Hora da Estrela", autores3, "9788520923251", 1977, "Romance");

            List<Autor> autores4 = new java.util.ArrayList<>();
            autores4.add(asimov);
            Livro livro4 = new Livro("Fundacao", autores4, "9788535902774", 1951, "Ficcao Cientifica");

            // Revistas
            Revista revista1 = new Revista("Superinteressante", "Abril", 250);
            Revista revista2 = new Revista("Veja", "Abril", 2600);
            Revista revista3 = new Revista("National Geographic", "National Geographic Society", 150);

            // Cadastrar itens
            biblioteca.cadastrarItem(livro1);
            biblioteca.cadastrarItem(livro2);
            biblioteca.cadastrarItem(livro3);
            biblioteca.cadastrarItem(livro4);
            biblioteca.cadastrarItem(revista1);
            biblioteca.cadastrarItem(revista2);
            biblioteca.cadastrarItem(revista3);

            // Usuarios
            Usuario ana = new Usuario("Ana Souza", "2023001", 3);
            Usuario bruno = new Usuario("Bruno Lima", "2023002", 1);
            Usuario carla = new Usuario("Carla Mendes", "2023003", 5);
            Usuario diego = new Usuario("Diego Santos", "2023004", 2);

            biblioteca.cadastrarUsuario(ana);
            biblioteca.cadastrarUsuario(bruno);
            biblioteca.cadastrarUsuario(carla);
            biblioteca.cadastrarUsuario(diego);

            // Alguns emprestimos de exemplo
            biblioteca.emprestarItem("Dom Casmurro", "2023001", 7);
            biblioteca.emprestarItem("O Pequeno Principe", "2023002", 7);
            biblioteca.emprestarItem("Fundacao", "2023003", 14);

            System.out.println("\n[SISTEMA] Dados de exemplo carregados com sucesso!");
            System.out.println("Itens: " + biblioteca.getTotalItens() + " | Usuarios: " + biblioteca.getTotalUsuarios() + " | Emprestimos ativos: " + biblioteca.getTotalEmprestimosAtivos());
        } catch (Exception e) {
            System.out.println("[AVISO] Erro ao carregar dados de exemplo: " + e.getMessage());
        }
    }

    private static void inicializarDadosExemplo() {
        System.out.println("\nRecarregando dados de exemplo...");
        carregarDadosExemplo();
    }

    // ============================================================
    // UTILITARIOS DE ENTRADA
    // ============================================================

    private static int lerOpcaoMenu(int min, int max) {
        while (true) {
            try {
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    System.out.print("Entrada vazia. Digite um numero entre " + min + " e " + max + ": ");
                    continue;
                }
                int opcao = Integer.parseInt(entrada);
                if (opcao < min || opcao > max) {
                    System.out.print("Opcao invalida. Digite um numero entre " + min + " e " + max + ": ");
                    continue;
                }
                return opcao;
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Digite apenas numeros entre " + min + " e " + max + ": ");
            }
        }
    }

    private static int lerInteiro(String entrada, String campo) throws EntradaInvalidaException {
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException(campo + " deve ser um numero inteiro valido.");
        }
    }

    private static int lerInteiroPositivo(String entrada, String campo) throws EntradaInvalidaException {
        int valor = lerInteiro(entrada, campo);
        if (valor <= 0) {
            throw new EntradaInvalidaException(campo + " deve ser um numero positivo.");
        }
        return valor;
    }

    private static void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}