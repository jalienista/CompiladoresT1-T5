# Trabalho 1 – Analisador Léxico

No T1, foi desenvolvido um **Analisador Léxico** para a linguagem **LA (Linguagem Algorítmica)**, na disciplina de Compiladores, ministrada pelo Prof. Daniel Lucredio.

Esse programa é responsável pelo processo de tokenização do código-fonte, sendo projetado não apenas para o reconhecimento de padrões válidos, mas também para o tratamento de exceções.

---

## Membros do grupo

- João Manoel Ribeiro Machado - 822447  
- Julia Campanelli Granja - 823835  
- Kevyn Marques - 820895  

---

## 1. Pré-requisitos

Ferramentas necessárias:

- Linguagem: **Java JDK 17+**
- Sistema operacional recomendado: **Ubuntu** (utilizamos via WSL no Windows)
- Parser generator: **ANTLR v4.13.2**

---

## 2. Configuração do Ambiente

Passos antes de rodar o código:

- Clonar o repositório
- Abrir o terminal e acessar a pasta do projeto usando `cd` e `dir`
- Verificar se os seguintes arquivos estão presentes:
  - `LALexer.g4`
  - `main.jar`
  - pasta `lib`

---

## 3. Geração da Infraestrutura Léxica (ANTLR)

Nesta etapa, o ANTLR processa a gramática e gera o código Java necessário para o analisador.

```bash
java -jar lib/antlr-4.13.2-complete.jar LALexer.g4
```

---

## 4. Compilação

Este passo realiza a tradução do código-fonte Java.

```bash
javac -cp ".:lib/antlr-4.13.2-complete.jar" *.java
```

---

## 5. Execução e Validação

A validação final é feita através do corretor automático, que compara a saída do seu
analisador com os casos de teste esperados.

```bash
java -jar
"compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar"
"java -cp .:lib/antlr-4.13.2-complete.jar main" gcc ./saida_temp
./testes/casos-de-teste "822447, 823835, 820895" "t1"
```
