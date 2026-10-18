# Documento do Grupo — Etapa 4 (16/10)

**Grupo:** 1
**Projeto/Domínio:** Biblioteca
**Integrantes:** Ariane, Caio, Eduardo, Lucas Gabriel, Guilherme e Ryan

## Papéis (Etapa 4 — 16/10)

| Papel | Integrante(s) |
|---|---|
| Facilitador | Lucas Gabriel |
| Modelador | Ariane |
| Implementador | Eduardo |
| Revisor de IA | Caio, Ryan |
| Apresentador/Defensor | Guilherme |

---

## Perguntas do GRUPO — Etapa 4

**1. Qual classe foi definida como abstrata? Por que ela não deve ser instanciada diretamente?**

A classe abstrata definida foi `ItemBiblioteca`. Ela não deve ser instanciada diretamente porque não existe um "item genérico" sozinho na biblioteca: todo item é, concretamente, um `Livro` ou uma `Revista`. Tornar a classe abstrata impede que alguém crie um `ItemBiblioteca` sem tipo específico, forçando o uso de uma das subclasses concretas.

**2. Quais subclasses concretas foram criadas ou adaptadas? Qual comportamento foi sobrescrito em cada uma?**

As subclasses concretas são `Livro` e `Revista`. Ambas herdam de `ItemBiblioteca` e sobrescrevem o método abstrato `exibirResumo()`: o `Livro` exibe o título e o ano de publicação, enquanto a `Revista` exibe o título e a edição. Elas herdam as implementações concretas de `emprestar()`, `devolver()` e `isDisponivel()` da superclasse (via interface `Emprestavel`).

**3. Qual interface foi criada? Qual contrato ela representa e quais classes a implementam?**

A interface criada foi `Emprestavel`. Ela representa o contrato de qualquer item que possa ser emprestado e devolvido, expondo os métodos `emprestar()`, `devolver()` e `isDisponivel()`. A classe `ItemBiblioteca` implementa essa interface, e as subclasses `Livro` e `Revista` herdam essa implementação por meio dela.

**4. Qual exceção personalizada foi criada? Qual regra de negócio provoca essa exceção?**

A exceção personalizada criada foi `ItemIndisponivelException`. Ela é provocada pela regra de negócio de que não é permitido emprestar um item que já está emprestado (disponível == false) nem um item que não existe no catálogo.

**5. Onde a exceção é lançada (`throw`) e onde é tratada (`try/catch`)? Justifique essa separação.**

A exceção é lançada dentro de `ItemBiblioteca.emprestar()` (quando o item não está disponível ou não foi encontrado) e também em `Biblioteca.emprestarItem()` (quando o item não existe no catálogo). Ela é tratada no `Main`, no fluxo de interação com o usuário, usando blocos `try/catch`. Essa separação faz sentido porque a classe de domínio detecta a violação da regra e lança a exceção, enquanto o `Main` é o ponto de contato com o usuário e sabe como exibir a mensagem de erro de forma amigável.

**6. Onde o grupo utilizou `List`? Por que `List` é adequada para esse caso?**

A `List` foi usada no atributo `catalogo` da classe `Biblioteca` (`List<ItemBiblioteca>`). É adequada porque o catálogo precisa manter os itens na ordem em que foram cadastrados e permite que existam títulos repetidos (vários exemplares de um mesmo livro), ao contrário de um `Set`, que não permitiria duplicidade.

**7. Onde utilizou `Set`? Que duplicidade o `Set` ajuda a impedir?**

O `Set` foi usado no atributo `isbnsCadastrados` da classe `Biblioteca` (`Set<String>`). Ele ajuda a impedir que o mesmo ISBN seja cadastrado mais de uma vez, garantindo que cada código internacional de livro apareça apenas uma vez no sistema.

**8. Onde utilizou `Map`? Qual é a chave e qual é o valor? Por que essa chave faz sentido?**

O `Map` foi usado no atributo `usuarios` da classe `Biblioteca` (`Map<String, Usuario>`). A chave é a matrícula do aluno (String) e o valor é o objeto `Usuario`. Essa chave faz sentido porque a matrícula identifica unicamente cada usuário, permitindo buscar um usuário diretamente pela matrícula sem precisar percorrer toda a lista de cadastros.

**9. Onde foi utilizado `Iterator`? O que esse trecho realiza?**

O `Iterator` foi utilizado no método `Biblioteca.removerItensPorTitulo()`. Esse trecho percorre o catálogo e remove todos os itens com determinado título. Usar o `Iterator` permite remover elementos com segurança durante a percursão, evitando o erro `ConcurrentModificationException` que ocorreria se removêssemos diretamente da lista enquanto iteramos com um `for-each`.

**10. Qual sugestão de IA foi aceita e qual foi rejeitada? Justifique tecnicamente.**

- **Sugestão aceita (exemplo):** usar `Set<String>` para armazenar os ISBNs cadastrados. Justificativa técnica: cada ISBN identifica unicamente um livro, e o `Set` impede automaticamente a inserção de códigos duplicados, o que é exatamente a regra de negócio necessária.
- **Sugestão rejeitada (exemplo):** usar `ArrayList` explícito como tipo do atributo `catalogo` em vez da interface `List`. Justificativa técnica: programar voltado para a interface (`List<ItemBiblioteca>`) deixa o código mais flexível e desacoplado da implementação concreta, facilitando trocar depois por outra implementação da `List` sem alterar o restante do sistema.

---

## Como compilar e rodar

```bash
cd grupo_1_biblioteca
javac -d bin src/*.java
java -cp bin Main
```

## Estrutura de entrega

- Código Java: `grupo_1_biblioteca/src/`
- Modelagem/UML: `grupo_1_biblioteca/modelagem/`
- Evidência de execução: `grupo_1_biblioteca/execucao/saida_console.txt`
- Documento do grupo: `grupo_1_biblioteca/documento_grupo.md` (este arquivo, a ser convertido para `.docx`)
- ZIP final: `grupo_1_biblioteca_etapa4.zip`
