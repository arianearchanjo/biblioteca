// ============================================================
// CLASSE USUARIO
// ============================================================
// Representa um usuario da biblioteca.
// Controla a quantidade de emprestimos ativos dentro de um limite pessoal.
// Encapsulamento rigoroso com validacao nos setters.
// ============================================================

package model;

import java.util.Objects;

public class Usuario {

    private String nome;
    private String matricula;
    private int qtdEmprestimosAtivos;
    private int limiteEmprestimos;

    public Usuario(String nome, String matricula, int limiteEmprestimos) {
        setNome(nome);
        setMatricula(matricula);
        setLimiteEmprestimos(limiteEmprestimos);
        this.qtdEmprestimosAtivos = 0;
    }

    // GETTERS
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getQtdEmprestimosAtivos() {
        return qtdEmprestimosAtivos;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    // SETTERS COM VALIDACAO
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuario nao pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matricula do usuario nao pode ser nula ou vazia.");
        }
        this.matricula = matricula.trim();
    }

    public void setLimiteEmprestimos(int limiteEmprestimos) {
        if (limiteEmprestimos <= 0) {
            throw new IllegalArgumentException("Limite de emprestimos deve ser maior que zero.");
        }
        this.limiteEmprestimos = limiteEmprestimos;
    }

    // METODOS DE COMPORTAMENTO
    public boolean podeEmprestar() {
        return qtdEmprestimosAtivos < limiteEmprestimos;
    }

    public boolean registrarEmprestimo() {
        if (podeEmprestar()) {
            qtdEmprestimosAtivos++;
            return true;
        }
        return false;
    }

    public void registrarDevolucao() {
        if (qtdEmprestimosAtivos > 0) {
            qtdEmprestimosAtivos--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(matricula, usuario.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return nome + " (Matricula: " + matricula + ", Emprestimos: " + qtdEmprestimosAtivos + "/" + limiteEmprestimos + ")";
    }
}