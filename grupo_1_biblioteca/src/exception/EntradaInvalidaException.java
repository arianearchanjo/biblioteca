// ============================================================
// EXCECAO PERSONALIZADA EntradaInvalidaException
// ============================================================
// Lancada quando a entrada do usuario e invalida (ex.: letras em campo numerico).
// ============================================================

package exception;

public class EntradaInvalidaException extends BibliotecaException {

    public EntradaInvalidaException(String mensagem) {
        super(mensagem);
    }
}