# Biblioteca - ADS 2 (Grupo 1)

Projeto final consolidado de Programação Orientada a Objetos para o domínio **Biblioteca**.

## Integrantes

- Ariane Archanjo
- Caio Melo Canhetti
- Eduardo Henrique Vieira
- Guilherme Monteiro Cardoso
- Lucas Gabriel Leite de Sant'Ana
- Ryan Paulino Machado da Cruz

## Estrutura do Projeto

```
grupo_1_biblioteca/
├── Biblioteca.bat          # ← ATALHO EXECUTÁVEL (duplo clique para rodar)
├── biblioteca.jar          # JAR executável
├── README.md               # Este arquivo
└── src/                    # Código fonte organizado
    ├── main/
    │   └── Main.java       # Ponto de entrada com menu interativo
    ├── model/              # Classes de domínio
    │   ├── Autor.java
    │   ├── Usuario.java
    │   ├── Emprestimo.java
    │   ├── Emprestavel.java
    │   ├── ItemBiblioteca.java
    │   ├── Livro.java
    │   └── Revista.java
    ├── exception/          # Exceções personalizadas
    │   ├── BibliotecaException.java
    │   ├── ItemIndisponivelException.java
    │   ├── ItemNaoEncontradoException.java
    │   ├── UsuarioNaoEncontradoException.java
    │   ├── LimiteEmprestimosExcedidoException.java
    │   ├── IsbnDuplicadoException.java
    │   ├── MatriculaDuplicadaException.java
    │   ├── EmprestimoNaoEncontradoException.java
    │   └── EntradaInvalidaException.java
    └── service/
        └── Biblioteca.java # Regras de negócio e gerenciamento
```

## Como Executar

### Opção 1: Duplo clique no atalho (Windows)
```
Biblioteca.bat
```

### Opção 2: Linha de comando
```bash
java -jar biblioteca.jar
```

### Opção 3: Compilar e rodar
```bash
javac -d bin src/main/Main.java src/model/*.java src/exception/*.java src/service/*.java
java -cp bin main.Main
```

## Requisitos

- **Java JDK 11+** instalado e no PATH
- Windows (para o `.bat`) ou qualquer SO com Java (para `.jar`)

## Funcionalidades do Menu

```
==================================================
           MENU PRINCIPAL - BIBLIOTECA
==================================================
1  - Gerenciar Itens (Livros/Revistas)
2  - Gerenciar Usuarios
3  - Realizar Emprestimo
4  - Realizar Devolucao
5  - Relatorios e Listagens
6  - Buscar Item por Titulo
7  - Buscar Usuario por Matricula
8  - Verificar Emprestimos Atrasados
9  - Recarregar Dados de Exemplo
0  - Sair
```

### Submenus
- **Itens**: Cadastrar Livro/Revista, Listar, Remover
- **Usuários**: Cadastrar, Listar, Remover
- **Empréstimo**: Emprestar com validação de disponibilidade e limite
- **Devolução**: Devolver item emprestado
- **Relatórios**: Catálogo, Usuários, Empréstimos ativos/atrasados, Histórico, Estatísticas

## Conceitos de POO Aplicados

| Conceito | Implementação |
|----------|---------------|
| Encapsulamento | Atributos `private` com getters/setters validados |
| Herança | `ItemBiblioteca` (abstrata) → `Livro`, `Revista` |
| Polimorfismo | `exibirResumo()` sobrescrito; referências `ItemBiblioteca` |
| Classe Abstrata | `ItemBiblioteca` implementa `Emprestavel` |
| Interface | `Emprestavel` define contrato empréstimo/devolução |
| Exceções Personalizadas | 9 exceções em hierarquia (`BibliotecaException` base) |
| Coleções | `List`, `Set` (ISBNs únicos), `Map` (usuários), `Iterator` (remoção segura) |

## Regras de Negócio

1. ISBN único (não permite duplicata)
2. Matrícula única (não permite duplicata)
3. Limite de empréstimos por usuário (configurável)
4. Só empresta itens disponíveis
5. Devolução só para empréstimos ativos do usuário
6. Remoção segura com `Iterator`
7. Tratamento de entrada inválida sem abortar o programa