# Trabalho 2 – Compiladores

Este projeto foi desenvolvido para a disciplina de Compiladores, ministrada pelo Prof. Daniel Lucredio.

O trabalho é cumulativo e, nesta etapa, contempla:

- T1: Analisador Léxico para a linguagem LA;
- T2: Analisador Sintático para a linguagem LA.

O analisador sintático verifica se a estrutura do programa-fonte está de acordo com a gramática da linguagem LA, reportando o primeiro erro sintático encontrado.

---

## Funcionalidades Implementadas

### T1 – Analisador Léxico

- Reconhecimento dos tokens da linguagem LA;
- Identificação de palavras-chave, operadores e delimitadores;
- Reconhecimento de identificadores e literais;
- Tratamento de erros léxicos.

### T2 – Analisador Sintático

- Verificação da estrutura gramatical da linguagem LA;
- Detecção do primeiro erro sintático encontrado;
- Emissão de mensagens de erro no formato especificado pelo corretor automático.

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
- Sistema operacional recomendado: **Ubuntu** (nativo ou via WSL no Windows)
- Parser generator: **ANTLR v4.13.2** (embutido no código do trabalho)

Documentações/Tutoriais das ferramentas:

- Git: https://docs.github.com/en/get-started
- WSL: https://learn.microsoft.com/en-us/windows/wsl/
- Java: https://docs.oracle.com/en/java/javase/17/
- ANTLR: https://www.antlr.org/download.html

---

## 2. Configuração do Ambiente

Antes de executar o projeto, é necessário cloná-lo:

### Clone o repositório

```bash
cd "../<caminho_desejado>"
git clone <repositorio>
```

Recomendamos utilizar um terminal Linux ou WSL no Windows, ambiente no qual o projeto foi desenvolvido e testado.

### Acesse a pasta do projeto

```bash
cd "<caminho_da_clonagem>/CompiladoresT1-T5"
```

### Verifique se os arquivos estão presentes

Arquivos esperados:

- `LALexer.g4`
- `LAParser.g4`
- `main.java`
- pasta `lib`

Verificação:

```bash
dir
```

---

## 3. Geração da Infraestrutura do Compilador (ANTLR)

Nesta etapa, o ANTLR processa as gramáticas presentes no projeto e gera o código Java necessário para execução dos analisadores implementados até o momento.

### 3.1. Analisador Léxico (T1)

Utilizando o arquivo `LALexer.g4`, são gerados arquivos como:

- `LALexer.java`
- `LALexer.tokens`
- `LALexer.interp`

```bash
java -jar lib/antlr-4.13.2-complete.jar LALexer.g4
```

### 3.2. Analisador Sintático (T2)

Utilizando o arquivo `LAParser.g4`, são gerados arquivos como:

- `LAParser.java`
- `LAParser.tokens`
- `LAParser.interp`

```bash
java -jar lib/antlr-4.13.2-complete.jar LAParser.g4
```

---

## 4. Compilação

Este passo realiza a tradução do código-fonte Java.

O compilador Java compila todos os arquivos `.java` do projeto utilizando o ANTLR como dependência.

```bash
javac -cp ".:lib/antlr-4.13.2-complete.jar" *.java
```

---

## 5. Execução

A execução do trabalho é feita através do comando abaixo, fornecendo os arquivos de entrada e saída:

```bash
java -cp ".:lib/antlr-4.13.2-complete.jar" main entrada.txt saida.txt
```

### Saída

#### Programa correto

Arquivo de saída vazio.

#### Erro sintático

```text
Linha X: erro sintatico proximo a LEXEMA
Fim da compilacao
```

---

## 6. Validação com o Corretor Automático

A validação final compara a saída produzida pelo compilador com os resultados esperados dos casos de teste disponibilizados pela disciplina.

Formato geral:

```bash
java -jar compiladores-corretor-automatico.jar <caminho para o compilador executavel> <caminho para o compilador gcc> <caminho para uma pasta temporaria> <caminho para a pasta com os casos de teste> "RAs dos alunos do grupo" "tipoTeste"
```

### Trabalho 2 (T2)

```bash
java -jar compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -cp .:lib/antlr-4.13.2-complete.jar main" gcc ./saida_temp ./testes/casos-de-teste "822447, 823835, 820895" "t2"
```