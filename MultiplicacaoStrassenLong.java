public class MultiplicacaoStrassenLong {

    public long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        return strassen(A, B, n);
    }

    private long[][] strassen(long[][] A, long[][] B, int n) {
        long[][] resultado = new long[n][n];

        // Caso base: matriz 1x1
        if (n == 1) {
            resultado[0][0] = A[0][0] * B[0][0];
        } else {
            int novoTamanho = n / 2;

            long[][] A11 = new long[novoTamanho][novoTamanho];
            long[][] A12 = new long[novoTamanho][novoTamanho];
            long[][] A21 = new long[novoTamanho][novoTamanho];
            long[][] A22 = new long[novoTamanho][novoTamanho];

            long[][] B11 = new long[novoTamanho][novoTamanho];
            long[][] B12 = new long[novoTamanho][novoTamanho];
            long[][] B21 = new long[novoTamanho][novoTamanho];
            long[][] B22 = new long[novoTamanho][novoTamanho];

            // Dividindo as matrizes em 4 submatrizes
            dividirMatriz(A, A11, 0, 0);
            dividirMatriz(A, A12, 0, novoTamanho);
            dividirMatriz(A, A21, novoTamanho, 0);
            dividirMatriz(A, A22, novoTamanho, novoTamanho);

            dividirMatriz(B, B11, 0, 0);
            dividirMatriz(B, B12, 0, novoTamanho);
            dividirMatriz(B, B21, novoTamanho, 0);
            dividirMatriz(B, B22, novoTamanho, novoTamanho);

            // Calculando os 7 produtos de Strassen
            long[][] M1 = strassen(somar(A11, A22), somar(B11, B22), novoTamanho);
            long[][] M2 = strassen(somar(A21, A22), B11, novoTamanho);
            long[][] M3 = strassen(A11, subtrair(B12, B22), novoTamanho);
            long[][] M4 = strassen(A22, subtrair(B21, B11), novoTamanho);
            long[][] M5 = strassen(somar(A11, A12), B22, novoTamanho);
            long[][] M6 = strassen(subtrair(A21, A11), somar(B11, B12), novoTamanho);
            long[][] M7 = strassen(subtrair(A12, A22), somar(B21, B22), novoTamanho);

            // Calculando os quadrantes do resultado
            long[][] C11 = somar(subtrair(somar(M1, M4), M5), M7);
            long[][] C12 = somar(M3, M5);
            long[][] C21 = somar(M2, M4);
            long[][] C22 = somar(subtrair(somar(M1, M3), M2), M6);

            // Juntando os quadrantes na matriz resultante
            juntarMatriz(C11, resultado, 0, 0);
            juntarMatriz(C12, resultado, 0, novoTamanho);
            juntarMatriz(C21, resultado, novoTamanho, 0);
            juntarMatriz(C22, resultado, novoTamanho, novoTamanho);
        }

        return resultado;
    }

    // Funções auxiliares
    private void dividirMatriz(long[][] origem, long[][] destino, int linha, int coluna) {
        for (int i = 0; i < destino.length; i++) {
            for (int j = 0; j < destino.length; j++) {
                destino[i][j] = origem[i + linha][j + coluna];
            }
        }
    }

    private void juntarMatriz(long[][] origem, long[][] destino, int linha, int coluna) {
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i + linha][j + coluna] = origem[i][j];
            }
        }
    }

    private long[][] somar(long[][] A, long[][] B) {
        int n = A.length;
        long[][] resultado = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                resultado[i][j] = A[i][j] + B[i][j];
        return resultado;
    }

    private long[][] subtrair(long[][] A, long[][] B) {
        int n = A.length;
        long[][] resultado = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                resultado[i][j] = A[i][j] - B[i][j];
        return resultado;
    }

    // Testando a implementação
    public static void main(String[] args) {
        MultiplicacaoStrassenLong strassen = new MultiplicacaoStrassenLong();
    
        long[][][] testesA = {
            {{1000, 2000}, {3000, 4000}},
            {{1500, 2500}, {3500, 4500}},
            {{5000, 6000}, {7000, 8000}},
            {{1234, 5678}, {9101, 1121}},
            {{9999, 8888}, {7777, 6666}},
            {{1111, 2222}, {3333, 4444}},
            {{3210, 6540}, {9870, 1230}},
            {{4000, 5000}, {6000, 7000}},
            {{8000, 7000}, {6000, 5000}},
            {{4321, 1234}, {5678, 4321}}
        };
    
        long[][][] testesB = {
            {{4000, 3000}, {2000, 1000}},
            {{3500, 2500}, {1500, 500}},
            {{8000, 7000}, {6000, 5000}},
            {{1111, 2222}, {3333, 4444}},
            {{5555, 4444}, {3333, 2222}},
            {{1234, 2345}, {3456, 4567}},
            {{1111, 2222}, {3333, 4444}},
            {{7000, 6000}, {5000, 4000}},
            {{3000, 4000}, {5000, 6000}},
            {{8765, 9876}, {6543, 7654}}
        };
    
        for (int k = 0; k < testesA.length; k++) {
            System.out.println("Caso " + (k + 1) + ":");
    
            long tempoInicial = System.nanoTime();
            long[][] resultado = strassen.multiply(testesA[k], testesB[k]);
            long tempoFinal = System.nanoTime();
    
            for (int i = 0; i < resultado.length; i++) {
                for (int j = 0; j < resultado[0].length; j++) {
                    System.out.print(resultado[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Tempo de execução (nanosegundos): " + (tempoFinal - tempoInicial));
            System.out.println();
        }
    }

}
