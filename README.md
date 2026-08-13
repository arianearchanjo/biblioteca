# Biblioteca - Atividade POO (Grupo 1)

Projeto de Programação Orientada a Objetos sobre o domínio **Biblioteca**.

## Integrantes

Ariane, Caio, Eduardo, Lucas Gabriel, Guilherme e Ryan.

## O que o projeto faz

Demonstra a modelagem de uma biblioteca com três classes principais:

- **Livro** - controla se o livro está disponível ou emprestado.
- **Usuario** - controla a quantidade de empréstimos ativos dentro de um limite pessoal.
- **Emprestimo** - liga um livro a um usuário e controla o prazo de devolução (ativo, devolvido ou atrasado).

A classe `Main` cria objetos, altera o estado deles e imprime um mini-relatório no console.

## Estrutura

```
grupo_1_biblioteca/
├── src/
│   ├── Livro.java
│   ├── Usuario.java
│   ├── Emprestimo.java
│   └── Main.java
├── modelagem/
│   └── modelagem.md / modelagem.png
├── execucao/
│   └── saida_console.txt
└── documento_grupo.md
```

## Como compilar e executar

```bash
cd grupo_1_biblioteca/src
javac Livro.java Usuario.java Emprestimo.java Main.java
java Main
```

Ou, a partir da pasta do projeto:

```bash
cd grupo_1_biblioteca
javac -d bin src/*.java
java -cp bin Main
```
