// ============================================================
// EXCECAO PERSONALIZADA MatriculaDuplicadaException
// ============================================================
// Lancada quando se tenta cadastrar um usuario com matricula ja existente.
// ============================================================

package exception;

public class MatriculaDuplicadaException extends BibliotecaException {

    public MatriculaDuplicadaException(String mensagem) {
        super(mensagem);
    }
}