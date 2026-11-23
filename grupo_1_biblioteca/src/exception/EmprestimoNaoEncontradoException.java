// ============================================================
// EXCECAO PERSONALIZADA EmprestimoNaoEncontradoException
// ============================================================
// Lancada quando um emprestimo nao e encontrado para um usuario/item.
// ============================================================

package exception;

public class EmprestimoNaoEncontradoException extends BibliotecaException {

    public EmprestimoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}