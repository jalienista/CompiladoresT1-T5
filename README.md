# Trabalho 1 e 2 – Analisadores Léxico e Sintático

No T1, foi desenvolvido um **Analisador Léxico** para a linguagem **LA (Linguagem Algorítmica)**, na disciplina de Compiladores, ministrada pelo Prof. Daniel Lucredio.

Esse programa é responsável pelo processo de tokenização do código-fonte, sendo projetado não apenas para o reconhecimento de padrões válidos, mas também para o tratamento de exceções.

No T2, o projeto foi expandido com a implementação de um **Analisador Sintático**, que verifica se a estrutura do programa-fonte está de acordo com a gramática da linguagem LA, reportando o primeiro erro sintático encontrado.

---

## Membros do grupo

- João Manoel Ribeiro Machado - 822447  
- Julia Campanelli Granja - 823835  
- Kevyn Marques - 820895  

---

## 1. Pré-requisitos

Ferramentas necessárias:

- Linguagem: **Java JDK 17+**
- Controle de versionamento: **Git**
- Sistema operacional recomendado: **Ubuntu** (Nativo ou via WSL no Windows)
- Parser generator: **ANTLR v4.13.2** (embutido no código do trabalho)

Documentações/Tutoriais das ferramentas:
- Git: _https://docs.github.com/en/get-started_
- WSL: _https://learn.microsoft.com/en-us/windows/wsl/_
- Java: _https://docs.oracle.com/en/java/javase/17/_
- ANTLR: _https://www.antlr.org/download.html_

---

## 2. Configuração do Ambiente

Antes de efetivamente executar os códigos do programa, é necessário cloná-lo:

- Clone o repositório:
```bash
cd "../<caminho_desejado>"
git clone <repositório>
```
_Recomendando executar no terminal Linux ou WSL no Windows, ambiente onde vamos rodar a aplicação. Isso se dá pela clonagem  de repositório ser feita de forma diferente no Bash do Linux e no Powershell, afetando caminhos, desempenho e configurações._

- No terminal, acesse a pasta do projeto:
```bash
cd "<caminho_da_clonagem/CompiladoresT1-T5>
```

- Verifique se os seguintes arquivos estão presentes:
  - `LALexer.g4`
  - `LAParser.g4`
  - `main.java`
  - pasta `lib`
- Através do comando `dir`
```bash
dir
```

---

## 3. Geração da Infraestrutura Léxica e Sintática (ANTLR)

Nesta etapa, o ANTLR processa as gramáticas e gera o código Java necessário para os analisadores.

### 3.1. Analisador Léxico (T1)

Utilizando o arquivo LALexer.g4, são gerados LALexer.java (classe do analisador léxico), LALexer.tokens (tokens reconhecidos) e LALexer.interp (arquivo interno do ANTLR).

```bash
java -jar lib/antlr-4.13.2-complete.jar LALexer.g4
```

### 3.2. Analisador Sintático (T2)

Utilizando o arquivo LAParser.g4, são gerados LAParser.java (classe do analisador sintático), LAParser.tokens e LAParser.interp.

```bash
java -jar lib/antlr-4.13.2-complete.jar LAParser.g4
```

---

## 4. Compilação

Este passo realiza a tradução do código-fonte Java.
Ele faz isso executando o compilador Java, compilando todos os arquivos ".java" da pasta e utilizando como dependência o ANTLR, o que gera os arquivos .class necessários para execução.

```bash
javac -cp ".:lib/antlr-4.13.2-complete.jar" *.java
```

---

## 5. Execução e Validação

### 5.1. Execução Manual

A execução do trabalho é feita através do comando abaixo, fornecendo dois argumentos obrigatórios (arquivo de entrada e arquivo de saída):

```bash
java -cp ".:lib/antlr-4.13.2-complete.jar" main entrada.txt saida.txt
```

**Comportamento da saída:**
- **Erro Léxico (T1):** imprime a linha e a mensagem do erro, encerrando a execução.
- **Erro Sintático (T2):** imprime `Linha X: erro sintatico proximo a LEXEMA` seguido de `Fim da compilacao`.
- **Programa correto:** arquivo de saída vazio.

### 5.2. Validação com o Corretor Automático

A execução do corretor é feita através do formato de comando mostrado abaixo:
```bash
java -jar compiladores-corretor-automatico.java <caminho para o compilador executavel> <caminho para o compilador gcc> <caminho para uma pasta temporaria> <caminho para a pasta com os casos de teste> "RAs dos alunos do grupo" "tipoTeste (t1|t2|t3|t4|t5|gabarito, em qualquer combinação)"
```

A validação final compara a saída do analisador com os casos de teste esperados, o que pode ser feito através do código abaixo:

**T1 (37 casos):**
```bash
java -jar compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -cp .:lib/antlr-4.13.2-complete.jar main" gcc ./saida_temp ./testes/casos-de-teste "822447, 823835, 820895" "t1"
```

**T2 (62 casos):**
```bash
java -jar compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -cp .:lib/antlr-4.13.2-complete.jar main" gcc ./saida_temp ./testes/casos-de-teste "822447, 823835, 820895" "t2"
```
