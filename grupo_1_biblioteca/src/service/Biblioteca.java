// ============================================================
// CLASSE BIBLIOTECA (SERVICE)
// ============================================================
// Gerencia o acervo, usuarios e emprestimos da biblioteca usando
// colecoes do Java (List, Set, Map) e Iterator.
// Responsabilidade unica: administrar o catalogo, cadastro de usuarios
// e operacoes de emprestimo/devolucao com regras de negocio.
// ============================================================

package service;

import model.*;
import exception.*;

import java.time.LocalDate;
import java.util.*;

public class Biblioteca {

    private List<ItemBiblioteca> catalogo;
    private Set<String> isbnsCadastrados;
    private Map<String, Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.catalogo = new ArrayList<>();
        this.isbnsCadastrados = new HashSet<>();
        this.usuarios = new HashMap<>();
        this.emprestimos = new ArrayList<>();
    }

    // ============================================================
    // CADASTRO DE ITENS
    // ============================================================

    public void cadastrarItem(ItemBiblioteca item) throws IsbnDuplicadoException {
        if (item instanceof Livro) {
            String isbn = ((Livro) item).getIsbn();
            if (isbnsCadastrados.contains(isbn)) {
                throw new IsbnDuplicadoException("ISBN '" + isbn + "' ja esta cadastrado no sistema.");
            }
            isbnsCadastrados.add(isbn);
        }
        catalogo.add(item);
    }

    public void removerItemPorTitulo(String titulo) throws ItemNaoEncontradoException {
        Iterator<ItemBiblioteca> it = catalogo.iterator();
        boolean removido = false;
        while (it.hasNext()) {
            ItemBiblioteca item = it.next();
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                if (item instanceof Livro) {
                    isbnsCadastrados.remove(((Livro) item).getIsbn());
                }
                it.remove();
                removido = true;
            }
        }
        if (!removido) {
            throw new ItemNaoEncontradoException("Nenhum item com o titulo '" + titulo + "' foi encontrado para remocao.");
        }
    }

    // ============================================================
    // CADASTRO DE USUARIOS
    // ============================================================

    public void cadastrarUsuario(Usuario usuario) throws MatriculaDuplicadaException {
        if (usuarios.containsKey(usuario.getMatricula())) {
            throw new MatriculaDuplicadaException("Matricula '" + usuario.getMatricula() + "' ja esta cadastrada.");
        }
        usuarios.put(usuario.getMatricula(), usuario);
    }

    public void removerUsuario(String matricula) throws UsuarioNaoEncontradoException {
        if (!usuarios.containsKey(matricula)) {
            throw new UsuarioNaoEncontradoException("Usuario com matricula '" + matricula + "' nao encontrado.");
        }
        Usuario usuario = usuarios.get(matricula);
        if (usuario.getQtdEmprestimosAtivos() > 0) {
            throw new IllegalStateException("Nao e possivel remover usuario com emprestimos ativos.");
        }
        usuarios.remove(matricula);
    }

    // ============================================================
    // CONSULTAS
    // ============================================================

    public ItemBiblioteca buscarItemPorTitulo(String titulo) throws ItemNaoEncontradoException {
        for (ItemBiblioteca item : catalogo) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException("Item com titulo '" + titulo + "' nao encontrado no catalogo.");
    }

    public List<ItemBiblioteca> buscarItensPorTitulo(String titulo) {
        List<ItemBiblioteca> resultados = new ArrayList<>();
        for (ItemBiblioteca item : catalogo) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                resultados.add(item);
            }
        }
        return resultados;
    }

    public ItemBiblioteca buscarItemPorIsbn(String isbn) throws ItemNaoEncontradoException {
        for (ItemBiblioteca item : catalogo) {
            if (item instanceof Livro && ((Livro) item).getIsbn().equals(isbn)) {
                return item;
            }
        }
        throw new ItemNaoEncontradoException("Livro com ISBN '" + isbn + "' nao encontrado.");
    }

    public Usuario buscarUsuarioPorMatricula(String matricula) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarios.get(matricula);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuario com matricula '" + matricula + "' nao encontrado.");
        }
        return usuario;
    }

    public boolean isbnJaCadastrado(String isbn) {
        return isbnsCadastrados.contains(isbn);
    }

    // ============================================================
    // EMPRESTIMO / DEVOLUCAO
    // ============================================================

    public Emprestimo emprestarItem(String titulo, String matricula, int diasPrazo) 
            throws ItemNaoEncontradoException, UsuarioNaoEncontradoException, 
                   ItemIndisponivelException, LimiteEmprestimosExcedidoException {
        
        ItemBiblioteca item = buscarItemPorTitulo(titulo);
        Usuario usuario = buscarUsuarioPorMatricula(matricula);

        if (!usuario.podeEmprestar()) {
            throw new LimiteEmprestimosExcedidoException(
                "Usuario '" + usuario.getNome() + "' atingiu o limite de " + usuario.getLimiteEmprestimos() + " emprestimos."
            );
        }

        item.emprestar();
        usuario.registrarEmprestimo();

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(diasPrazo);

        Emprestimo emprestimo = new Emprestimo(item, usuario, dataEmprestimo, dataDevolucaoPrevista);
        emprestimos.add(emprestimo);

        return emprestimo;
    }

    public void devolverItem(String titulo, String matricula) 
            throws ItemNaoEncontradoException, UsuarioNaoEncontradoException, 
                   EmprestimoNaoEncontradoException {
        
        ItemBiblioteca item = buscarItemPorTitulo(titulo);
        Usuario usuario = buscarUsuarioPorMatricula(matricula);

        // Buscar emprestimo ativo correspondente
        Emprestimo emprestimoAtivo = null;
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo() && e.getItem().equals(item) && e.getUsuario().equals(usuario)) {
                emprestimoAtivo = e;
                break;
            }
        }

        if (emprestimoAtivo == null) {
            throw new EmprestimoNaoEncontradoException(
                "Nenhum emprestimo ativo encontrado para o item '" + titulo + "' e usuario '" + usuario.getNome() + "'."
            );
        }

        emprestimoAtivo.finalizar();
    }

    // ============================================================
    // LISTAGENS E RELATORIOS
    // ============================================================

    public void listarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("Catalogo vazio.");
            return;
        }
        System.out.println("\n=== CATALOGO DA BIBLIOTECA ===");
        for (ItemBiblioteca item : catalogo) {
            item.exibirResumo();
        }
        System.out.println("Total de itens: " + catalogo.size());
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }
        System.out.println("\n=== USUARIOS CADASTRADOS ===");
        for (Usuario usuario : usuarios.values()) {
            System.out.println(usuario);
        }
        System.out.println("Total de usuarios: " + usuarios.size());
    }

    public void listarEmprestimosAtivos() {
        System.out.println("\n=== EMPRESTIMOS ATIVOS ===");
        boolean temAtivos = false;
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo()) {
                System.out.println(e + (e.estaAtrasado() ? " *** ATRASADO (" + e.getDiasAtraso() + " dias) ***" : ""));
                temAtivos = true;
            }
        }
        if (!temAtivos) {
            System.out.println("Nenhum emprestimo ativo no momento.");
        }
    }

    public void listarEmprestimosAtrasados() {
        System.out.println("\n=== EMPRESTIMOS ATRASADOS ===");
        boolean temAtrasados = false;
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo() && e.estaAtrasado()) {
                System.out.println(e + " | Dias de atraso: " + e.getDiasAtraso());
                temAtrasados = true;
            }
        }
        if (!temAtrasados) {
            System.out.println("Nenhum emprestimo atrasado.");
        }
    }

    public void listarHistoricoEmprestimos() {
        System.out.println("\n=== HISTORICO DE EMPRESTIMOS ===");
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum emprestimo registrado.");
            return;
        }
        for (Emprestimo e : emprestimos) {
            System.out.println(e);
        }
    }

    // ============================================================
    // GETTERS ESTATISTICOS
    // ============================================================

    public int getTotalItens() {
        return catalogo.size();
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    public int getTotalIsbnsUnicos() {
        return isbnsCadastrados.size();
    }

    public int getTotalEmprestimosAtivos() {
        int count = 0;
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo()) count++;
        }
        return count;
    }

    public List<ItemBiblioteca> getCatalogo() {
        return new ArrayList<>(catalogo);
    }

    public Collection<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public List<Emprestimo> getEmprestimos() {
        return new ArrayList<>(emprestimos);
    }
}