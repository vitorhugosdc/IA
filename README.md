# JGammon 

## Sobre o Trabalho

JGammon é um projeto desenvolvido para a disciplina de Inteligência Artificial, cujo objetivo era criar uma inteligência artificial para jogar o famoso jogo de gamão. A IA implementada pode ser encontrada em `src/jgam/ai/IAProgramadaPelaEquipeVitor_ra116426.java`.

## IA Implementada

A classe `IAProgramadaPelaEquipeVitor_ra116426` implementa uma IA com as seguintes características:
- **Heurística**: Avalia o estado do tabuleiro considerando posições ofensivas e defensivas.
- **Movimentação**: Seleciona o movimento baseado na melhor avaliação heurística.
- **Decisões de Jogo**: Decide entre rolar ou dobrar, e entre aceitar ou recusar uma oferta de dobrar.

Buscou-se implementar uma IA balanceada possuindo tanto características ofensivas como defensivas.

## Tecnologia Utilizada

- Java - JDK Amazon Corretto 1.8.0_412

## Como Executar o Projeto

### Clonar o Repositório

```bash
git clone https://github.com/vitorhugosdc/IA
cd IA
```
### Compilar e Executar o Projeto

1. Abra o projeto na sua IDE de escolha.
2. Compile o projeto.
3. Execute a classe `JGammon` localizada em `src/jgam/JGammon.java`.

# Resultados

A IA desenvolvida foi testada contra três diferentes IAs: Agressive_AI, Blocker_AI e Runner_AI. Os resultados obtidos estão presentes na tabela abaixo:

| Adversário       | Vitórias | Derrotas | Taxa de Vitória |
|------------------|----------|----------|-----------------|
| **Agressive_AI** | 145      | 55       | 72,5%           |
| **Blocker_AI**   | 116      | 84       | 58%             |
| **Runner_AI**    | 112      | 88       | 56%             |
