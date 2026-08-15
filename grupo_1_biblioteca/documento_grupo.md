# PROGRAMAÇÃO ORIENTADA A OBJETOS

## MODELO DE ENTREGA DO GRUPO — SEMANA 12/08

| Campo | Valor |
|---|---|
| **Grupo** | 1 |
| **Domínio** | Biblioteca |
| **Integrantes** | Ariane, Caio, Eduardo, Lucas Gabriel, Guilherme, Ryan |
| **Nome do arquivo ZIP** | grupo_1_biblioteca.zip |

**Entrega no AVA:** o grupo deverá enviar UM único arquivo .zip contendo o programa Java, a modelagem, evidência de execução e este documento preenchido.

---

## 1. Estrutura obrigatória do arquivo ZIP

**Exemplo:** grupo_1_biblioteca.zip

```
grupo_1_biblioteca/
├── src/
│   ├── Livro.java
│   ├── Usuario.java
│   └── Main.java
├── modelagem/
│   └── modelagem.png (ou .pdf)
├── execucao/
│   └── execucao.png
└── documento_grupo.docx
```

**Observação:** os nomes das classes são apenas exemplo. Entreguem os arquivos correspondentes ao domínio e à solução do grupo.

---

## 2. O que deve existir no programa Java

- Pelo menos duas classes implementadas a partir da modelagem.
- Atributos coerentes com a responsabilidade das classes.
- Pelo menos um método que represente comportamento real do objeto.
- Uma classe Main (ou equivalente) que crie pelo menos dois objetos de uma mesma classe com estados diferentes.
- Execução no console demonstrando estado ou comportamento dos objetos.
- Não utilizar herança, interfaces ou recursos ainda não estudados apenas para sofisticar a solução.

---

## 3. Resumo da modelagem do grupo

| Classe | Responsabilidade | Principais atributos | Principais métodos |
|---|---|---|---|
| Livro | Representa um livro do acervo e controla se ele está disponível ou emprestado | titulo, autor, isbn, disponivel | emprestar(), devolver() |
| Usuario | Representa um usuário da biblioteca e controla seus empréstimos ativos dentro de um limite pessoal | nome, matricula, qtdEmprestimosAtivos, limiteEmprestimos | podeEmprestar(), registrarEmprestimo(), registrarDevolucao() |
| Emprestimo | Liga um livro a um usuário e controla o prazo e o status da devolução (ativo/devolvido/atrasado) | livro, usuario, dataEmprestimo, dataDevolucaoPrevista, ativo | estaAtrasado(), finalizar() |
| Main | Demonstra a modelagem criando objetos, alterando seus estados e imprimindo um mini-relatório no console | (nenhum atributo próprio) | main() |

---

## 4. Uso da IA pelo grupo

**Como usamos a IA:** usamos a IA como apoio para validar a modelagem, revisar o código e gerar artefatos (diagrama de classes e evidência de execução). Antes de aceitar qualquer sugestão, o grupo conferiu se fazia sentido para o domínio. Nenhuma decisão foi aplicada sem o grupo entender o motivo.

| Sugestão/pergunta feita à IA | Aceitamos? | Justificativa do grupo |
|---|---|---|
| Perguntamos se as três classes (Livro, Usuario e Emprestimo) eram suficientes para o domínio ou se faltava alguma classe importante | Sim | Aceitamos porque as três classes cobrem bem o domínio sem criar classes desnecessárias, mantendo o escopo mínimo pedido. |
| Sugeriu que emprestar()/devolver() retornem boolean para indicar se a operação foi possível | Sim | Aceitamos porque retornar true/false deixa o código fácil de ler e mostra o resultado real da ação do objeto. |
| Sugeriu guardar o limite de empréstimos como atributo do próprio Usuario, pois cada usuário pode ter um limite diferente | Sim | Aceitamos porque faz sentido o limite ser de cada usuário (ex.: aluno e professor podem ter limites diferentes). |
| Pedimos à IA para revisar o código e garantir que não havia erros grandes ou erros de lógica | Sim | Aceitamos porque a revisão nos ajudou a encontrar e corrigir falhas antes de entregar o trabalho. |

---

## 5. Alteração/desafio realizado em sala

**1. Qual alteração ou desafio o professor solicitou?**

Nossa apresentação foi feita com qualidade. O professor solicitou uma alteração para a próxima entrega: criar **uma classe de autores com vários autores** (um livro pode ter mais de um autor). Vamos implementar essa alteração na próxima entrega.

**2. O que o grupo precisou alterar na modelagem ou no código?**

Ainda não alteramos a modelagem nem o código, pois a alteração ficou para a próxima entrega. Para isso, vamos precisar criar a classe Autor, relacioná-la ao Livro (permitindo vários autores por livro) e ajustar a modelagem e o código de acordo.

---

## 6. Checklist antes de enviar

- [x] O arquivo está nomeado no padrão grupo_X_dominio.zip.
- [x] A pasta src contém todos os arquivos .java necessários.
- [x] A pasta modelagem contém a modelagem/UML da solução.
- [x] A pasta execucao contém uma evidência de que o programa foi executado.
- [x] O documento_grupo.md está preenchido.
- [x] O projeto abre/compila com os arquivos entregues.
- [x] O grupo está preparado para explicar a solução e cada integrante fará sua entrega individual separadamente.

**Importante:** a entrega do ZIP representa o produto coletivo. A compreensão de cada integrante será verificada também pela entrega individual e, quando solicitado, pela apresentação/defesa em sala.
