## Descrição do Problema 1
Alguns de seus amigos entraram no crescente campo da mineração de dados de séries temporais, onde procuramos padrões em sequências de eventos que ocorrem ao longo do tempo. As ordens de compra nas bolsas de valores são uma fonte de dados com uma ordenação natural no tempo. Dada uma longa sequência S de tais eventos, seus amigos querem uma maneira eficiente de detectar certos “padrões” neles – por exemplo, eles podem querer saber se os quatro eventos

``` S = {“buy Google”, “buy Apple”, “buy Google”, “buy NVIDIA”} ```

ocorrem nesta sequência S, em ordem, mas não necessariamente consecutivamente.

Eles começam com uma coleção de eventos possíveis (por exemplo, as transações possíveis) e uma
sequência S de n desses eventos. Um determinado evento pode ocorrer múltiplas vezes em S (por exemplo, as ações do Google podem ser compradas muitas vezes em uma única sequência S). Diremos que uma sequência S' é uma subsequência de S se houver uma maneira de deletar certos eventos de S de modo que os eventos restantes, em ordem, sejam iguais à sequência S'.

Assim, por exemplo, a sequência de quatro eventos acima é uma subsequência da sequência

``` S = {“ buy Amazon”, “buy Google”, “buy Apple”, “buy Google”, “buy Google”, “buy NVIDIA”} ```

``` S' = {“buy Google”, “buy Apple”, “buy Google”, “buy NVIDIA”} ```

O objetivo deles é ser capaz de imaginar sequências curtas e detectar rapidamente se são subsequências de S. Portanto, forneça um algoritmo que receba duas sequências de eventos S' de comprimento m e S de comprimento n, cada uma possivelmente contendo um evento mais de uma vez, e decide se S' é uma subsequência de S.

O método implementado retorna verdadeiro se S_line (S') é
uma subsequência de S e deve respeitar a seguinte assinatura:

``` boolean hasTrend(String[] S, String[] S_line) ```

## Descrição do Problema 2
Até 1969 matemáticos acreditavam sem impossível resolver um problema de multiplicação de matrizes em tempo menor que Θ(n³). Procure pelos trabalhos de V. Strassen e responda se isso ainda é verdade.

O método implementado retorna a matriz resultante das matrizes A e B passadas por parâmetro e deve respeitar a seguinte assinatura:

``` int[][] multiply(int[][] A, int[][] B) ```