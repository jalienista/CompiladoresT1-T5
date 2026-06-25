# Trabalho 5 – Compiladores

Este projeto foi desenvolvido para a disciplina de Compiladores, ministrada pelo Prof. Daniel Lucrédio.

O trabalho é cumulativo e, nesta etapa, contempla:

- T1: Analisador Léxico
- T2: Analisador Sintático
- T3: Analisador Semântico
- T4: Gerador de Código Intermediário (Three-Address Code)
- **T5: Gerador de Código Final (C)**

O gerador de código final traduz o programa‑fonte em LA para código C equivalente, produzindo um programa executável que pode ser compilado com GCC e executado, mantendo o mesmo comportamento de entrada/saída do programa original.

---

## Funcionalidades Implementadas

### T1 – Analisador Léxico
- Reconhecimento dos tokens da linguagem LA;
- Identificação de palavras‑chave, operadores e delimitadores;
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
- Suporte a comandos de atribuição, leitura, escrita, desvio condicional e laços.

### T5 – Gerador de Código Final (C)
- Geração de código C compilável com GCC a partir do programa LA;
- Mapeamento de tipos e estruturas da linguagem LA para C (variáveis, vetores, procedimentos, etc.);
- Inclusão automática dos cabeçalhos necessários (`stdio.h`, `stdlib.h`, `string.h`);
- Geração da função `main()` com as declarações e comandos correspondentes;
- O código gerado não precisa ser idêntico aos exemplos, mas sua execução deve produzir exatamente as mesmas entradas/saídas esperadas.

---

## Membros do grupo

- João Manoel Ribeiro Machado - 822447
- Julia Campanelli Granja - 823835
- Kevyn Marques - 820895

---

## 1. Pré‑requisitos

Ferramentas necessárias:

- Linguagem: **Java JDK 17+**
- Compilador C: **GCC** (para compilar o código gerado, utilizado pelo corretor automático)
- Controle de versionamento: **Git**
- Sistema operacional recomendado: **Ubuntu** (nativo ou via WSL no Windows)
- Parser generator: **ANTLR v4.13.2** (embutido no código do trabalho)

Documentações/Tutoriais das ferramentas:

- Git: https://docs.github.com/en/get-started
- WSL: https://learn.microsoft.com/en-us/windows/wsl/
- Java: https://docs.oracle.com/en/java/javase/17/
- ANTLR: https://www.antlr.org/download.html
- GCC: https://gcc.gnu.org/

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

Arquivos esperados para o T5 (além dos já existentes):

- `LALexer.g4`
- `LAParser.g4`
- `main.java`
- `TabelaDeSimbolos.java`
- `Escopos.java`
- `LASemanticoUtils.java`
- `LASemantico.java`
- `GeradorCodigo.java` (ou classe equivalente)
- `CodigoTAC.java` (opcional, dependendo da implementação)
- **`GeradorC.java`** (classe responsável pela geração do código C final)
- pasta `lib`

Verificação:

```bash
ls
```

---

## 3. Geração da Infraestrutura do Compilador (ANTLR)

Nesta etapa, o ANTLR processa as gramáticas e gera o código Java necessário.  
Para o T5, é obrigatório utilizar a opção `-visitor`, que habilita o padrão Visitor utilizado pela análise semântica e geração de código.

### 3.1. Analisador Léxico

```bash
java -jar lib/antlr-4.13.2-complete.jar -visitor LALexer.g4
```

### 3.2. Analisador Sintático

```bash
java -jar lib/antlr-4.13.2-complete.jar -visitor LAParser.g4
```

Os comandos acima gerarão, entre outros, os arquivos `LALexer.java`, `LAParser.java`, `LAParserBaseVisitor.java` e `LAParserVisitor.java`, que são utilizados por todas as etapas seguintes.

---

## 4. Compilação

Este passo traduz todo o código‑fonte Java do projeto, incluindo as classes auxiliares da análise semântica e dos geradores de código.

```bash
javac -cp ".:lib/antlr-4.13.2-complete.jar" *.java
```

---

## 5. Execução

A execução do trabalho é feita através do comando abaixo, fornecendo os arquivos de entrada e saída (obrigatoriamente dois argumentos):

```bash
java -cp ".:lib/antlr-4.13.2-complete.jar" main entrada.txt saida.txt
```

### Comportamento da saída

- **Se a entrada contiver erro léxico, sintático ou semântico**:  
  O arquivo de saída conterá as mensagens de erro correspondentes (uma por linha, no caso de erros semânticos). Nenhum código C é gerado.

- **Se a entrada for semanticamente correta**:  
  O arquivo de saída conterá o código C gerado, que pode ser compilado com GCC e executado.

#### Exemplo de saída (código C gerado)

Para o programa LA:

```
algoritmo
declare
  x: literal
leia(x)
escreva(x)
fim_algoritmo
```

O arquivo de saída poderá conter:

```c
#include <stdio.h>
#include <stdlib.h>
int main() {
  char x[80];
  gets(x);
  printf("%s", x);
  return 0;
}
```

---

## 6. Validação com o Corretor Automático

A validação final compara a saída produzida pelo compilador com os resultados esperados. Para o T5, o corretor automático **compila o código C gerado** com GCC, executa o programa resultante e verifica se o comportamento (entrada/saída) é idêntico ao esperado.

Formato geral:

```bash
java -jar compiladores-corretor-automatico.jar <caminho para o compilador executavel> <caminho para o compilador gcc> <caminho para uma pasta temporaria> <caminho para a pasta com os casos de teste> "RAs dos alunos do grupo" "tipoTeste"
```

### Trabalho 5 (T5)

```bash
java -jar compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -cp .:lib/antlr-4.13.2-complete.jar main" gcc ./saida_temp ./testes/casos-de-teste "822447, 823835, 820895" "t5"
```

**Nota:** O corretor utiliza o GCC para compilar o código gerado; certifique‑se de que o GCC está instalado e acessível no PATH.

---

## Observações 

- O gerador de código C só é acionado se o programa não apresentar nenhum erro léxico, sintático ou semântico.
- O código C gerado é compatível com o padrão C99 e deve compilar sem avisos com GCC.
- A implementação suporta todos os construtos da linguagem LA previstos nos casos de teste (declarações, atribuições, leitura, escrita, decisões, laços, procedimentos, vetores, etc.).
- O código foi desenvolvido e testado no ambiente Linux/WSL; em outros sistemas, ajustes nos caminhos podem ser necessários.