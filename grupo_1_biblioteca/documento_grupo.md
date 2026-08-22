# PROGRAMAÇÃO ORIENTADA A OBJETOS

## MODELO DE ENTREGA DO GRUPO — ETAPA 2 • SEMANA 21/08

| Campo | Valor |
|---|---|
| **Grupo** | 1 |
| **Domínio** | Biblioteca |
| **Integrantes** | Ariane, Caio, Eduardo, Lucas Gabriel, Guilherme, Ryan |
| **Nome do arquivo ZIP** | grupo_1_biblioteca_etapa2.zip |

**Entrega no AVA:** o grupo deverá enviar UM único arquivo .zip contendo o programa Java, a modelagem atualizada, evidência de execução e este documento preenchido.

---

## 1. Estrutura obrigatória do arquivo ZIP

**Exemplo:** grupo_1_biblioteca_etapa2.zip

```
grupo_1_biblioteca_etapa2/
├── src/
│   ├── Autor.java
│   ├── Livro.java
│   ├── Usuario.java
│   ├── Emprestimo.java
│   └── Main.java
├── modelagem/
│   └── modelagem_etapa2.png (ou .pdf/.md)
├── execucao/
│   └── saida_console.txt
└── documento_grupo.docx
```

---

## 2. O que deve existir no programa Java

- Pelo menos 3 classes de domínio (Autor, Livro, Usuario, Emprestimo = 4 classes).
- Atributos privados com encapsulamento.
- Construtores explícitos em todas as classes de domínio.
- Getters necessários para consulta.
- Pelo menos 1 método de negócio real por classe (não contar getters/setters).
- Pelo menos 2 validações de estado no código.
- Uma classe Main que crie pelo menos dois objetos da mesma classe com estados diferentes.
- Execução no console demonstrando estado, comportamento e validações.
- Não utilizar herança, interfaces ou recursos ainda não estudados.

---

## 3. Resumo da modelagem do grupo (Etapa 2)

| Classe | Responsabilidade | Principais atributos | Principais métodos |
|---|---|---|---|
| Autor | Representa o autor de um livro (nome e nacionalidade) | nome, nacionalidade | getNome(), getNacionalidade() |
| Livro | Representa um livro do acervo, controla disponibilidade e guarda seus autores | titulo, autores (ArrayList\<Autor\>), isbn, disponivel | emprestar(), devolver(), getNomesAutores() |
| Usuario | Representa um usuário da biblioteca e controla seus empréstimos ativos dentro de um limite pessoal | nome, matricula, qtdEmprestimosAtivos, limiteEmprestimos | podeEmprestar(), registrarEmprestimo(), registrarDevolucao() |
| Emprestimo | Liga um livro a um usuário e controla o prazo e o status da devolução (ativo/devolvido/atrasado) | livro, usuario, dataEmprestimo, dataDevolucaoPrevista, ativo | estaAtrasado(), finalizar() |
| Main | Demonstra a modelagem criando objetos, executando operações válidas e inválidas, e imprimindo um relatório no console | (nenhum atributo próprio) | main() |

---

## 4. Mudanças da Etapa 2 em relação à Etapa 1

| O que mudou | Por que mudou |
|---|---|
| Criada a classe **Autor** (nome + nacionalidade) | O professor solicitou suporte a vários autores por livro na Etapa 1 |
| `Livro.autor` (String) virou `Livro.autores` (ArrayList\<Autor\>) | Um livro pode ter mais de um autor |
| Adicionado `getNomesAutores()` no Livro | Método utilitário para exibir nomes dos autores no console |
| Adicionada classe Autor no Main com 3 objetos (Machado, Exupery, Coelho) | Demonstrar a nova classe e a relação 1 para muitos com Livro |
| Adicionadas 2 tentativas inválidas no Main (limite atingido + livro já emprestado) | Atividade 8: testar validações de estado inválido |
| Adicionado teste de proteção por método de negócio (devolver() em livro disponível) | Atividade 8: mostrar que emprestar()/devolver() protegem melhor que um setter |

---

## 5. Decisões de modelagem

**1. Por que a classe Autor foi criada?**

Na Etapa 1, o autor era uma String dentro de Livro. O professor solicitou que um livro pudesse ter mais de um autor. Para isso, criamos a classe Autor como entidade própria, com nome e nacionalidade, e substituímos a String por um ArrayList\<Autor\> dentro de Livro. Isso permite representar a relação de vários autores por livro de forma limpa.

**2. Por que os atributos continuam privados?**

Todos os atributos de Autor, Livro, Usuario e Emprestimo são private. Isso protege o estado interno: ninguém de fora pode alterar diretamente um atributo (ex.: marcar um livro como disponível sem passar pelo método devolver()). Os getters permitem consulta, e os métodos de negócio (emprestar(), devolver(), registrarEmprestimo(), etc.) controlam as mudanças de estado.

**3. Por que não criamos um setter para disponivel no Livro?**

Em vez de setDisponivel(boolean), o Livro oferece emprestar() e devolver(). Esses métodos verificam o estado atual antes de alterar: emprestar() só funciona se o livro estiver disponível, e devolver() só funciona se estiver emprestado. Isso é mais seguro que um setter genérico, que permitiria colocar o livro em qualquer estado sem validação.

---

## 6. Uso da IA pelo grupo

**Como usamos a IA:** o grupo primeiro discutiu e definiu a modelagem (classe Autor, substituir String por lista de autores). Depois consultamos a IA para revisar o código e validar se estava tudo correto. Cada sugestão da IA foi analisada pelo grupo antes de aceitar ou rejeitar.

| Sugestão/pergunta feita à IA | Aceitamos? | Justificativa do grupo |
|---|---|---|
| Perguntamos se a classe Autor com nome e nacionalidade era suficiente ou se precisava de mais atributos | Sim | O grupo concordou que nome e nacionalidade são suficientes para o escopo atual. Se precisar de mais dados (ex.: data de nascimento), é só adicionar depois |
| Sugeriu adicionar getNomesAutores() como método auxiliar | Sim | O grupo viu utilidade porque evita repetir o loop de impressão em vários pontos do Main |
| Sugeriu usar Comparable ou ordenar autores por nome | Não | O grupo rejeitou porque não precisamos ordenar autores nesse momento. Seria uma funcionalidade extra que o projeto não precisa agora |
| Sugeriu criar uma exceção personalizada para estados inválidos | Não | O grupo rejeitou porque exceção personalizada ainda não foi estudado na disciplina. Usamos retornos boolean para sinalizar erro, que é mais simples |

---

## 7. Alteração/desafio realizado em sala

**1. Qual alteração ou desafio o professor solicitou?**

O professor solicitou a **adição do atributo ano publicado (`anoPublicado`, tipo `int`) na entidade Livro**, para registrar o ano em que cada livro do acervo foi publicado. Seguindo o padrão já praticado na disciplina (como aconteceu com a classe Autor na semana 12/08), a alteração foi **registrada agora e só será efetuada na próxima etapa**.

**2. O que o grupo precisou alterar na modelagem ou no código?**

Nesta etapa nada foi alterado no código nem na modelagem: a solicitação ficou registrada para ser implementada na próxima entrega, conforme o padrão. Registro do planejamento feito pelo grupo (o que muda, onde muda e por que muda):

| O que muda | Onde muda |
|---|---|
| Novo atributo `private int anoPublicado` | `src/Livro.java` |
| Construtor passa a receber o ano de publicação (`this.anoPublicado = anoPublicado;`) | `src/Livro.java` |
| Novo getter `getAnoPublicado()` para consulta | `src/Livro.java` |
| Criar os livros passando o ano (`new Livro(titulo, autores, isbn, anoPublicado)`) e exibir o ano nos relatórios do console | `src/Main.java` |
| Adicionar `anoPublicado` na classe Livro do diagrama | `modelagem/` |
| Atualizar a tabela de atributos da seção 3 | `documento_grupo.md` |

**Por que muda:** o ano de publicação é uma informação natural do domínio Biblioteca, foi solicitado pelo professor (cliente) e o Livro é quem deve conhecer os próprios dados de publicação.

---

## 8. Checklist antes de enviar

- [x] O arquivo está nomeado no padrão grupo_1_biblioteca_etapa2.zip.
- [x] A pasta src contém todos os arquivos .java necessários (Autor, Livro, Usuario, Emprestimo, Main).
- [x] A pasta modelagem contém a modelagem/UML atualizada da Etapa 2.
- [x] A pasta execucao contém evidência de execução do programa atualizado.
- [x] O documento_grupo.md está preenchido para a Etapa 2.
- [x] O projeto compila e roda sem erros.
- [x] A Main cria pelo menos 2 objetos da mesma classe com estados diferentes.
- [x] Existem pelo menos 2 operações inválidas demonstradas no console.
- [x] Pelo menos 1 método de negócio protege o estado melhor que um setter genérico.
- [x] A alteração solicitada em sala (ano publicado no Livro) está registrada no documento para ser aplicada na próxima etapa.
- [x] O grupo está preparado para explicar a solução e cada integrante fará sua entrega individual separadamente.

**Importante:** a entrega do ZIP representa o produto coletivo. A compreensão de cada integrante será verificada também pela entrega individual e, quando solicitado, pela apresentação/defesa em sala.
