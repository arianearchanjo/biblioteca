// ============================================================
// EXCECAO PERSONALIZADA IsbnDuplicadoException
// ============================================================
// Lancada quando se tenta cadastrar um livro com ISBN ja existente.
// ============================================================

package exception;

public class IsbnDuplicadoException extends BibliotecaException {

    public IsbnDuplicadoException(String mensagem) {
        super(mensagem);
    }
}