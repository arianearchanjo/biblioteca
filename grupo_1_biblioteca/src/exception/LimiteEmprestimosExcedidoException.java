// ============================================================
// EXCECAO PERSONALIZADA LimiteEmprestimosExcedidoException
// ============================================================
// Lancada quando um usuario tenta emprestar mais itens do que
// o seu limite pessoal permite.
// ============================================================

package exception;

public class LimiteEmprestimosExcedidoException extends BibliotecaException {

    public LimiteEmprestimosExcedidoException(String mensagem) {
        super(mensagem);
    }
}