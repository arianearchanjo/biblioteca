// ============================================================
// EXCECAO PERSONALIZADA UsuarioNaoEncontradoException
// ============================================================
// Lancada quando um usuario nao e encontrado no sistema.
// ============================================================

package exception;

public class UsuarioNaoEncontradoException extends BibliotecaException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}