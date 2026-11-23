// ============================================================
// INTERFACE EMPRESTAVEL
// ============================================================
// Contrato para qualquer item da biblioteca que possa ser
// emprestado e devolvido.
// ============================================================

package model;

import exception.ItemIndisponivelException;

public interface Emprestavel {

    void emprestar() throws ItemIndisponivelException;

    void devolver();

    boolean isDisponivel();
}