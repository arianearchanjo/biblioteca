// ============================================================
// EXCECAO PERSONALIZADA ItemNaoEncontradoException
// ============================================================
// Lancada quando um item nao e encontrado no catalogo.
// ============================================================

package exception;

public class ItemNaoEncontradoException extends BibliotecaException {

    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}