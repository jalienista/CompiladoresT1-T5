# Trabalho 3 – Compiladores

Este projeto foi desenvolvido para a disciplina de Compiladores, ministrada pelo Prof. Daniel Lucredio.

O trabalho é cumulativo e, nesta etapa, contempla:

- T1: Analisador Léxico  
- T2: Analisador Sintático  
- T3: Analisador Semântico  

O analisador semântico verifica regras de contexto, como declaração de identificadores, compatibilidade de tipos e escopo, reportando todos os erros semânticos encontrados.

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
- Emissão de mensagens de erro no formato especificado.

### T3 – Analisador Semântico
- Verificação de escopo e declaração de identificadores;
- Checagem de tipos em declarações, atribuições e expressões;
- Detecção de múltiplos erros semânticos (não interrompe a execução);
- Geração de todas as mensagens de erro no arquivo de saída, uma por linha.

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

Arquivos esperados para o T3:

- `LALexer.g4`
- `LAParser.g4`
- `main.java`
- `TabelaDeSimbolos.java`
- `Escopos.java`
- `LASemanticoUtils.java`
- `LASemantico.java`
- pasta `lib`

Verificação:

```bash
dir
```

---

## 3. Geração da Infraestrutura do Compilador (ANTLR)

Nesta etapa, o ANTLR processa as gramáticas e gera o código Java necessário.  
Para o T3, é obrigatório utilizar a opção `-visitor`, que habilita o padrão Visitor utilizado pela análise semântica.

### 3.1. Analisador Léxico

```bash
java -jar lib/antlr-4.13.2-complete.jar -visitor LALexer.g4
```

### 3.2. Analisador Sintático

```bash
java -jar lib/antlr-4.13.2-complete.jar -visitor LAParser.g4
```

Os comandos acima gerarão, entre outros, os arquivos `LALexer.java`, `LAParser.java`, `LAParserBaseVisitor.java` e `LAParserVisitor.java`, que são utilizados pela análise semântica.

---

## 4. Compilação

Este passo traduz todo o código-fonte Java do projeto, incluindo as classes auxiliares da análise semântica.

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

- **Programa correto** (sem erros léxicos, sintáticos ou semânticos):  
  Arquivo de saída vazio.

- **Erro léxico ou sintático**:  
  Apenas a primeira mensagem de erro é impressa, conforme o comportamento dos T1 e T2.

- **Erros semânticos**:  
  Todas as mensagens de erro são listadas no arquivo de saída, uma por linha, com o formato:

```text
Linha X: erro semântico
Linha Y: outro erro semântico
...
```

Exemplo de erro semântico:

```text
Linha 10: identificador x nao declarado
Linha 15: atribuicao nao compativel para y
```

---

## 6. Validação com o Corretor Automático

A validação final compara a saída produzida pelo compilador com os resultados esperados dos casos de teste disponibilizados pela disciplina.

Formato geral:

```bash
java -jar compiladores-corretor-automatico.jar <caminho para o compilador executavel> <caminho para o compilador gcc> <caminho para uma pasta temporaria> <caminho para a pasta com os casos de teste> "RAs dos alunos do grupo" "tipoTeste"
```

### Trabalho 3 (T3)

```bash
java -jar compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -cp .:lib/antlr-4.13.2-complete.jar main" gcc ./saida_temp ./testes/casos-de-teste "822447, 823835, 820895" "t3"
```

---

## Observações

- O analisador semântico não interrompe a execução ao encontrar erros; todos são reportados.
- A verificação de tipos segue as regras da especificação da linguagem LA (inteiro/real compatíveis, concatenação de literais, etc.).
- O código foi desenvolvido e testado no ambiente Linux/WSL; em outros sistemas, ajustes nos caminhos podem ser necessários.