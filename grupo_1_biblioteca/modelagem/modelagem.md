# Modelagem — Domínio Biblioteca (Grupo 1)

## Diagrama de classes (Mermaid)

```mermaid
classDiagram
    class Livro {
        -String titulo
        -String autor
        -String isbn
        -boolean disponivel
        +Livro(titulo, autor, isbn)
        +emprestar() boolean
        +devolver() boolean
        +getTitulo() String
        +getAutor() String
        +getIsbn() String
        +isDisponivel() boolean
    }

    class Usuario {
        -String nome
        -String matricula
        -int qtdEmprestimosAtivos
        -int limiteEmprestimos
        +Usuario(nome, matricula, limiteEmprestimos)
        +podeEmprestar() boolean
        +registrarEmprestimo() boolean
        +registrarDevolucao() void
        +getNome() String
        +getMatricula() String
        +getQtdEmprestimosAtivos() int
        +getLimiteEmprestimos() int
    }

    class Emprestimo {
        -Livro livro
        -Usuario usuario
        -String dataEmprestimo
        -String dataDevolucaoPrevista
        -boolean ativo
        +Emprestimo(livro, usuario, dataEmprestimo, dataDevolucaoPrevista)
        +estaAtrasado() boolean
        +finalizar() void
        +getLivro() Livro
        +getUsuario() Usuario
        +isAtivo() boolean
    }

    Emprestimo --> Livro : referencia
    Emprestimo --> Usuario : referencia
```

## Diagrama de classes (texto estruturado estilo UML)

```
+-----------------------------+
|            Livro            |
+-----------------------------+
| - titulo : String           |
| - autor : String            |
| - isbn : String             |
| - disponivel : boolean      |
+-----------------------------+
| + emprestar() : boolean     |
| + devolver() : boolean      |
+-----------------------------+

+-----------------------------+
|          Usuario            |
+-----------------------------+
| - nome : String             |
| - matricula : String        |
| - qtdEmprestimosAtivos : int|
| - limiteEmprestimos : int   |
+-----------------------------+
| + podeEmprestar() : boolean |
| + registrarEmprestimo() : boolean |
| + registrarDevolucao() : void     |
+-----------------------------+

+-----------------------------+
|         Emprestimo          |
+-----------------------------+
| - livro : Livro             |
| - usuario : Usuario         |
| - dataEmprestimo : String   |
| - dataDevolucaoPrevista : String |
| - ativo : boolean           |
+-----------------------------+
| + estaAtrasado() : boolean  |
| + finalizar() : void        |
+-----------------------------+
```

## Justificativa das classes

| Classe | Por que existe |
|---|---|
| `Livro` | Identidade própria (título/autor/ISBN) e comportamento de ser emprestado ou devolvido. |
| `Usuario` | Identidade própria (nome/matrícula) e comportamento de controlar seus empréstimos dentro de um limite. |
| `Emprestimo` | Liga um livro a um usuário em um momento específico, com data prevista de devolução e status ativo/devolvido/atrasado. |

## Relacionamentos

- `Emprestimo` **referencia** um `Livro` e um `Usuario` (não é herança: cada classe tem responsabilidades distintas).
- `Livro` e `Usuario` não dependem de `Emprestimo`: o empréstimo coordena o estado dos dois ao ser criado e finalizado.
