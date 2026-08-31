# PROGRAMAÇÃO ORIENTADA A OBJETOS

## MODELO DE ENTREGA DO GRUPO — ETAPA 3 • SEMANA 02/09

| Campo | Valor |
|---|---|
| **Grupo** | 1 |
| **Domínio** | Biblioteca |
| **Integrantes** | Ariane, Caio, Eduardo, Lucas Gabriel, Guilherme, Ryan |
| **Nome do arquivo ZIP** | grupo_1_biblioteca_etapa3.zip |

**Entrega no AVA:** o grupo deverá enviar UM único arquivo .zip contendo o programa Java, a modelagem atualizada, evidência de execução e este documento preenchido.

---

## 1. Estrutura obrigatória do arquivo ZIP

**Exemplo:** grupo_1_biblioteca_etapa3.zip

```
grupo_1_biblioteca_etapa3/
├── src/
│   ├── ItemBiblioteca.java
│   ├── Autor.java
│   ├── Livro.java
│   ├── Revista.java
│   ├── Usuario.java
│   ├── Emprestimo.java
│   └── Main.java
├── modelagem/
│   └── modelagem_etapa3.md (ou .png/.pdf)
├── execucao/
│   └── saida_console.txt
└── documento_grupo.docx
```

---

## 2. Hierarquia escolhida e justificativa "é um"

**Hierarquia:** `ItemBiblioteca` é a superclasse; `Livro` e `Revista` são as subclasses.

**Justificativa:** tanto livro quanto revista são itens que a biblioteca empresta: têm título, ficam disponíveis ou emprestados e passam pelos mesmos processos (emprestar/devolver). A frase "Livro é um ItemBiblioteca" e "Revista é um ItemBiblioteca" faz sentido, então a herança representa uma generalização coerente do domínio — e não apenas reaproveitamento de um atributo com o mesmo nome.

### Atividade 1 — "É UM" x "TEM UM" (6 relações)

| Relação | É UM (herança)? | TEM UM / outra relação? |
|---|---|---|
| Livro é um ItemBiblioteca | [x] | [ ] |
| Revista é um ItemBiblioteca | [x] | [ ] |
| Livro tem um/mais autores | [ ] | [x] (composição, 1 para muitos) |
| Emprestimo envolve um Livro | [ ] | [x] (referência) |
| Emprestimo envolve um Usuario | [ ] | [x] (referência) |
| Usuario tem um limite de empréstimos | [ ] | [x] (atributo, não-herança) |

**Justificativa de duas delas:**
- *Livro é um ItemBiblioteca:* compartilha título, disponibilidade e os métodos emprestar/devolver — comportamento comum a qualquer item emprestável.
- *Livro tem um autor:* um autor não "é um" livro; a relação é de pertencimento (1 para muitos). Por isso usamos `ArrayList<Autor>` dentro de `Livro` (composição), e não herança.

---

## 3. O que foi movido para a superclasse e o que ficou nas subclasses

| Atributo/método | Onde ficou | Por quê |
|---|---|---|
| `titulo`, `disponivel` | `ItemBiblioteca` | Comuns a todos os itens. |
| `emprestar()`, `devolver()` | `ItemBiblioteca` | Comportamento comum a todos os itens. |
| `autores`, `isbn`, `anoPublicado` | `Livro` | Específicos de livro; revista não tem. |
| `editora`, `edicao` | `Revista` | Específicos de revista; livro não tem. |

---

## 4. Exemplo de sobrecarga

No `ItemBiblioteca` existem dois métodos com o mesmo nome `exibirResumo`, mas assinaturas diferentes:

```java
public void exibirResumo() { ... }              // sem parâmetro
public void exibirResumo(boolean detalhado) { ... } // com parâmetro
```

O parâmetro `detalhado` controla o quanto mostramos. Ao chamar `item.exibirResumo(true)` e `item.exibirResumo(false)` no Main, o Java escolhe a versão certa pela assinatura.

---

## 5. Exemplo de sobrescrita e polimorfismo

O método `exibirResumo()` é sobrescrito (`@Override`) em `Livro` (mostra o ano) e em `Revista` (mostra a edição). No Main criamos referências da superclasse apontando para objetos das subclasses e chamamos o mesmo método:

```java
ItemBiblioteca item1 = new Livro("Dom Casmurro", autores, "9788535910663", 1899);
ItemBiblioteca item2 = new Revista("Superinteressante", "Abril", 250);
item1.exibirResumo(); // Livro: Dom Casmurro | Ano: 1899
item2.exibirResumo(); // Revista: Superinteressante | Edicao: 250
```

Saída real no console:

```
-- POLIMORFISMO --
Livro: Dom Casmurro | Ano: 1899
Revista: Superinteressante | Edicao: 250
```

---

## 6. Alteração surpresa realizada (Atividade 7)

**Qual classe deve mudar:** `Livro`.

**A mudança pertence à superclasse ou somente a uma subclasse?** Somente à subclasse `Livro` — o ano de publicação é um dado específico de livro, não de qualquer item.

**Impacto em construtores ou métodos:** o construtor de `Livro` passou a receber o `int anoPublicado`, foi adicionado o getter `getAnoPublicado()` e a criação dos livros no Main foi ajustada.

**Como evitar duplicação desnecessária:** não duplicamos código — `titulo` e `disponivel` continuam na superclasse; apenas o que é específico do livro (incluindo `anoPublicado`) ficou na subclasse.

```java
public Livro(String titulo, ArrayList<Autor> autores, String isbn, int anoPublicado) {
    super(titulo);
    this.autores = autores;
    this.isbn = isbn;
    this.anoPublicado = anoPublicado;
}
```

---

## 7. O que mudou em relação à Etapa 2

| O que mudou | Por que mudou |
|---|---|
| Criada a superclasse `ItemBiblioteca` | Generalização "é um" para livro e revista |
| Criada a subclasse `Revista` | Segundo tipo de item para demonstrar herança e polimorfismo |
| `Livro` passou a usar `extends ItemBiblioteca` | Herda título/disponibilidade em vez de repetir |
| Adicionado `super(titulo)` nos construtores | Repassar o título à superclasse |
| Adicionado `anoPublicado` (int) no Livro | Alteração surpresa pedida pelo professor |
| Adicionado `exibirResumo()` com `@Override` | Sobrescrita e polimorfismo |
| Adicionado `exibirResumo(boolean)` | Sobrecarga de método |

---

## 8. Herança simples e múltipla em Java (Atividade 3)

**Herança simples:** uma classe herda de uma única superclasse direta. Em Java todo objeto herda, no fim das contas, de `Object`.

**Herança múltipla:** seria uma classe herdar de duas ou mais superclasses ao mesmo tempo.

**Por que `class ClasseC extends ClasseA, ClasseB` NÃO compila em Java:** Java não permite colocar mais de uma classe no `extends`, por isso esse código é inválido. O Java resolve esse tipo de necessidade com interfaces, que serão estudadas depois nesta disciplina.

---

## 9. Uso da IA pelo grupo

> **A preencher pelo grupo.** A sugestão da IA deve ser registrada na tabela e a decisão (aceitar/rejeitar) deve ser tomada pelo grupo com justificativa técnica.

| Sugestão/pergunta feita à IA | Aceitamos? | Justificativa do grupo |
|---|---|---|
|  |  |  |

---

## 10. Checklist antes de enviar (Etapa 3)

- [ ] Continuamos o projeto da Etapa 2.
- [ ] Criamos uma hierarquia coerente e justificamos a relação "é um".
- [ ] O Java Console compila e executa.
- [ ] Temos superclasse e pelo menos duas subclasses.
- [ ] Usamos `extends` e `super(...)` corretamente.
- [ ] Há pelo menos um exemplo de sobrecarga.
- [ ] Há pelo menos um exemplo de sobrescrita com `@Override`.
- [ ] Demonstramos polimorfismo no Main.
- [ ] Registramos uma análise crítica de sugestão da IA.
- [ ] Atualizamos modelagem, evidência de execução e documento do grupo.
- [ ] Adicionamos o atributo `anoPublicado` (int) ao Livro (alteração surpresa).
- [ ] Cada integrante preparou sua entrega individual e consegue defender o trabalho.
