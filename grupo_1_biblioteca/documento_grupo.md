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

| Sugestão/pergunta feita à IA | Aceitamos? | Justificativa do grupo |
|---|---|---|
| Sugeriu armazenar as datas no formato aaaa-mm-dd para compará-las como texto simples (sem biblioteca de datas) | Sim | Aceitamos porque evita o uso de bibliotecas de data e mantém a comparação simples e legível. |
| Sugeriu que emprestar()/devolver() retornem boolean para indicar se a operação foi possível | Sim | Aceitamos porque retornar true/false torna o código fácil de ler e mostra se o estado do objeto mudou. |
| Sugeriu guardar o limite de empréstimos como atributo do próprio Usuario, pois cada usuário pode ter um limite diferente | Sim | Aceitamos porque faz sentido o limite ser de cada usuário (ex.: aluno e professor podem ter limites diferentes). |

---

## 5. Alteração/desafio realizado em sala

**1. Qual alteração ou desafio o professor solicitou?**

N/A — nenhuma alteração solicitada até o momento (primeira entrega).

**2. O que o grupo precisou alterar na modelagem ou no código?**

N/A — primeira entrega, será preenchido em sala quando o professor solicitar. 

---

## 6. Checklist antes de enviar

- [x] O arquivo está nomeado no padrão grupo_X_dominio.zip.
- [x] A pasta src contém todos os arquivos .java necessários.
- [x] A pasta modelagem contém a modelagem/UML da solução.
- [x] A pasta execucao contém uma evidência de que o programa foi executado.
- [x] O documento_grupo.md está preenchido.
- [x] O projeto abre/compila com os arquivos entregues.
- [ ] O grupo está preparado para explicar a solução e cada integrante fará sua entrega individual separadamente.

**Importante:** a entrega do ZIP representa o produto coletivo. A compreensão de cada integrante será verificada também pela entrega individual e, quando solicitado, pela apresentação/defesa em sala.
