// ============================================================
// EXCECAO PERSONALIZADA ItemIndisponivelException
// ============================================================
// Lancada quando uma operacao de emprestimo nao pode ser
// realizada porque o item nao esta disponivel.
// ============================================================

package exception;

public class ItemIndisponivelException extends BibliotecaException {

    public ItemIndisponivelException(String mensagem) {
        super(mensagem);
    }
}