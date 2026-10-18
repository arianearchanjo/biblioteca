// ============================================================
// CLASSE BIBLIOTECA
// ============================================================
// Gerencia o acervo e os usuarios da biblioteca usando colecoes
// do Java (List, Set, Map) e Iterator.
//
// Decisao de modelagem:
// - A Biblioteca tem responsabilidade clara: administrar o
//   catalogo de itens, o cadastro de usuarios e as operacoes de
//   emprestimo/devolucao. Nao e uma classe "porque sim".
// - List<ItemBiblioteca> catalogo: guarda os itens em ordem de
//   cadastro, permitindo repeticao de titulos (pode haver varios
//   exemplares com o mesmo nome).
// - Set<String> isbnsCadastrados: impede que o mesmo ISBN seja
//   cadastrado mais de uma vez (unicidade de codigo de livro).
// - Map<String, Usuario> usuarios: localiza rapidamente um
//   usuario pela matricula (chave) sem percorrer uma lista.
// - Iterator: usado para remover itens do catalogo com seguranca
//   durante a percorrida (evita ConcurrentModificationException).
// ============================================================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Biblioteca {

    // --------------------------------------------------------
    // COLECOES
    // --------------------------------------------------------
    private List<ItemBiblioteca> catalogo;        // ordem de cadastro, pode repetir titulo
    private Set<String> isbnsCadastrados;         // ISBNs unicos (nao permite duplicidade)
    private Map<String, Usuario> usuarios;        // chave: matricula | valor: Usuario

    // --------------------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------------------
    public Biblioteca() {
        this.catalogo = new ArrayList<ItemBiblioteca>();
        this.isbnsCadastrados = new HashSet<String>();
        this.usuarios = new HashMap<String, Usuario>();
    }

    // --------------------------------------------------------
    // CADASTRO
    // --------------------------------------------------------

    // Cadastra um item no catalogo. Se for Livro, registra o ISBN
    // no Set para garantir que nao haja ISBN duplicado.
    public void cadastrarItem(ItemBiblioteca item) {
        catalogo.add(item);
        if (item instanceof Livro) {
            String isbn = ((Livro) item).getIsbn();
            isbnsCadastrados.add(isbn);
        }
    }

    // Cadastra um usuario indexado por sua matricula.
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getMatricula(), usuario);
    }

    // --------------------------------------------------------
    // CONSULTA
    // --------------------------------------------------------

    // Busca um item pelo titulo (percorre a lista).
    public ItemBiblioteca buscarItemPorTitulo(String titulo) {
        for (ItemBiblioteca item : catalogo) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        return null;
    }

    // Busca um usuario pela matricula (acesso direto pelo Map).
    public Usuario buscarUsuarioPorMatricula(String matricula) {
        return usuarios.get(matricula);
    }

    // Indica se um ISBN ja esta cadastrado (uso do Set).
    public boolean isbnJaCadastrado(String isbn) {
        return isbnsCadastrados.contains(isbn);
    }

    // --------------------------------------------------------
    // EMPRESTIMO / DEVOLUCAO
    // --------------------------------------------------------

    // Empresta um item pelo titulo. Lanca excecao se o item nao
    // existir ou nao estiver disponivel.
    public void emprestarItem(String titulo) throws ItemIndisponivelException {
        ItemBiblioteca item = buscarItemPorTitulo(titulo);
        if (item == null) {
            throw new ItemIndisponivelException("O item '" + titulo + "' nao foi encontrado no catalogo.");
        }
        item.emprestar(); // pode lancar ItemIndisponivelException
    }

    // Devolve um item pelo titulo.
    public void devolverItem(String titulo) {
        ItemBiblioteca item = buscarItemPorTitulo(titulo);
        if (item != null) {
            item.devolver();
        }
    }

    // --------------------------------------------------------
    // ITERATOR (percorrida e remocao segura)
    // --------------------------------------------------------

    // Remove do catalogo todos os itens com o titulo informado,
    // usando Iterator para remocao segura durante a percorrida.
    public void removerItensPorTitulo(String titulo) {
        Iterator<ItemBiblioteca> it = catalogo.iterator();
        while (it.hasNext()) {
            ItemBiblioteca item = it.next();
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                it.remove(); // removicao segura: nao quebra a percorrida
            }
        }
    }

    // Exibe o resumo de todos os itens do catalogo.
    public void listarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("Catalogo vazio.");
            return;
        }
        for (ItemBiblioteca item : catalogo) {
            item.exibirResumo();
        }
    }

    // --------------------------------------------------------
    // GETTERS
    // --------------------------------------------------------

    public int getTotalItens() {
        return catalogo.size();
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    public int getTotalIsbnsUnicos() {
        return isbnsCadastrados.size();
    }
}
