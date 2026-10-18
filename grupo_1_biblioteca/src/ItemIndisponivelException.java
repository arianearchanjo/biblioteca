// ============================================================
// EXCECAO PERSONALIZADA ItemIndisponivelException
// ============================================================
// Excecao lancada quando uma operacao de emprestimo nao pode ser
// realizada porque o item nao esta disponivel.
//
// Decisao de modelagem:
// - Uma excecao propria e mais clara do que retornar boolean falso,
//   porque obriga o chamador a tratar a situacao de erro
//   explicitamente (try/catch), separando o fluxo normal do fluxo
//   de erro.
// - A regra de negocio que provoca essa excecao e: tentar emprestar
//   um item que ja esta emprestado (disponivel == false).
// ============================================================

public class ItemIndisponivelException extends Exception {

    public ItemIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
