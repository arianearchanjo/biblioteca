// ============================================================
// CLASSE EMPRESTIMO
// ============================================================
// Representa o emprestimo de um item a um usuario.
// Registra quando o item saiu, quando deve voltar e o status.
// Encapsulamento rigoroso com validacao nos setters.
// ============================================================

package model;

import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo {

    private ItemBiblioteca item;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private boolean ativo;

    public Emprestimo(ItemBiblioteca item, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        setItem(item);
        setUsuario(usuario);
        setDataEmprestimo(dataEmprestimo);
        setDataDevolucaoPrevista(dataDevolucaoPrevista);
        this.ativo = true;
    }

    // GETTERS
    public ItemBiblioteca getItem() {
        return item;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // SETTERS COM VALIDACAO
    public void setItem(ItemBiblioteca item) {
        if (item == null) {
            throw new IllegalArgumentException("Item do emprestimo nao pode ser nulo.");
        }
        this.item = item;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario do emprestimo nao pode ser nulo.");
        }
        this.usuario = usuario;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        if (dataEmprestimo == null) {
            throw new IllegalArgumentException("Data de emprestimo nao pode ser nula.");
        }
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        if (dataDevolucaoPrevista == null) {
            throw new IllegalArgumentException("Data de devolucao prevista nao pode ser nula.");
        }
        if (dataDevolucaoPrevista.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("Data de devolucao prevista nao pode ser anterior a data de emprestimo.");
        }
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    // METODOS DE COMPORTAMENTO
    public boolean estaAtrasado() {
        if (!ativo) {
            return false;
        }
        LocalDate hoje = LocalDate.now();
        return hoje.isAfter(dataDevolucaoPrevista);
    }

    public void finalizar() {
        if (ativo) {
            ativo = false;
            item.devolver();
            usuario.registrarDevolucao();
        }
    }

    public long getDiasAtraso() {
        if (!ativo || !estaAtrasado()) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(dataDevolucaoPrevista, LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(item, that.item) && Objects.equals(usuario, that.usuario) && Objects.equals(dataEmprestimo, that.dataEmprestimo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, usuario, dataEmprestimo);
    }

    @Override
    public String toString() {
        String status = ativo ? (estaAtrasado() ? "ATRASADO" : "ATIVO") : "DEVOLVIDO";
        return "Emprestimo: " + item.getTitulo() + " -> " + usuario.getNome() +
               " | De: " + dataEmprestimo + " | Prevista: " + dataDevolucaoPrevista +
               " | Status: " + status;
    }
}