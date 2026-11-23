// ============================================================
// EXCECAO BASE BIBLIOTECAEXCEPTION
// ============================================================
// Excecao base para todas as excecoes de negocio da biblioteca.
// Permite capturar todas as excecoes especificas com um unico catch.
// ============================================================

package exception;

public class BibliotecaException extends Exception {

    public BibliotecaException(String mensagem) {
        super(mensagem);
    }

    public BibliotecaException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}