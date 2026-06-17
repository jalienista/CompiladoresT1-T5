# Trabalho 4 – Compiladores

Este projeto foi desenvolvido para a disciplina de Compiladores, ministrada pelo Prof. Daniel Lucredio.

O trabalho é cumulativo e, nesta etapa, contempla:

- T1: Analisador Léxico
- T2: Analisador Sintático
- T3: Analisador Semântico
- **T4: Gerador de Código Intermediário (Three-Address Code)**

O gerador de código intermediário traduz o programa-fonte em LA para uma representação de três endereços (TAC - Three-Address Code), que serve como base para as etapas posteriores de otimização e geração de código final.

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

### T4 – Gerador de Código Intermediário (TAC)
- Geração de código de três endereços para programas LA semanticamente corretos;
- Suporte a comandos de atribuição, leitura, escrita, desvio condicional e laços;
- Compatibilidade com a saída esperada pelo corretor automático.

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

Arquivos esperados para o T4:

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
Para o T4, é obrigatório utilizar a opção `-visitor`, que habilita o padrão Visitor utilizado pela análise semântica e geração de código.

### 3.1. Analisador Léxico

```bash
java -jar lib/antlr-4.13.2-complete.jar -visitor LALexer.g4
```

### 3.2. Analisador Sintático

```bash
java -jar lib/antlr-4.13.2-complete.jar -visitor LAParser.g4
```

Os comandos acima gerarão, entre outros, os arquivos `LALexer.java`, `LAParser.java`, `LAParserBaseVisitor.java` e `LAParserVisitor.java`, que são utilizados pela análise semântica e geração de código.

---

## 4. Compilação

Este passo traduz todo o código-fonte Java do projeto, incluindo as classes auxiliares da análise semântica e do gerador de código.

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

- **Programa com erro léxico, sintático ou semântico**:  
  Apenas as mensagens de erro são impressas, conforme o comportamento dos T1, T2 e T3.

- **Programa semanticamente correto**:  
  O arquivo de saída conterá o código de três endereços gerado, com uma instrução por linha.

---

## 6. Validação com o Corretor Automático

A validação final compara a saída produzida pelo compilador com os resultados esperados dos casos de teste disponibilizados pela disciplina.

Formato geral:

```bash
java -jar compiladores-corretor-automatico.jar <caminho para o compilador executavel> <caminho para o compilador gcc> <caminho para uma pasta temporaria> <caminho para a pasta com os casos de teste> "RAs dos alunos do grupo" "tipoTeste"
```

### Trabalho 4 (T4)

```bash
java -jar compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -cp .:lib/antlr-4.13.2-complete.jar main" gcc ./saida_temp ./testes/casos-de-teste "822447, 823835, 820895" "t4"
```

---

## Observações

- O gerador de código intermediário só é executado se o programa não apresentar erros léxicos, sintáticos ou semânticos.
- O código de três endereços gerado utiliza variáveis temporárias (`t1`, `t2`, ...) para armazenar resultados intermediários de expressões.
- A estrutura do TAC segue o padrão de instruções:
  - `x = y` (atribuição simples)
  - `x = y op z` (operação binária)
  - `x = y` (atribuição de variável)
  - `if x goto L` (desvio condicional)
  - `goto L` (desvio incondicional)
  - `param x` (passagem de parâmetro)
  - `call p, n` (chamada de procedimento)
  - `return` (retorno de procedimento)
- O código foi desenvolvido e testado no ambiente Linux/WSL; em outros sistemas, ajustes nos caminhos podem ser necessários.