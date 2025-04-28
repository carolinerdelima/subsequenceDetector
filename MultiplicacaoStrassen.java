public class MultiplicacaoStrassen {

    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        return strassen(A, B, n);
    }

    private int[][] strassen(int[][] A, int[][] B, int n) {
        int[][] resultado = new int[n][n];

        // Caso base: matriz 1x1
        if (n == 1) {
            resultado[0][0] = A[0][0] * B[0][0];
        } else {
            int novoTamanho = n / 2;

            int[][] A11 = new int[novoTamanho][novoTamanho];
            int[][] A12 = new int[novoTamanho][novoTamanho];
            int[][] A21 = new int[novoTamanho][novoTamanho];
            int[][] A22 = new int[novoTamanho][novoTamanho];

            int[][] B11 = new int[novoTamanho][novoTamanho];
            int[][] B12 = new int[novoTamanho][novoTamanho];
            int[][] B21 = new int[novoTamanho][novoTamanho];
            int[][] B22 = new int[novoTamanho][novoTamanho];

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
            int[][] M1 = strassen(somar(A11, A22), somar(B11, B22), novoTamanho);
            int[][] M2 = strassen(somar(A21, A22), B11, novoTamanho);
            int[][] M3 = strassen(A11, subtrair(B12, B22), novoTamanho);
            int[][] M4 = strassen(A22, subtrair(B21, B11), novoTamanho);
            int[][] M5 = strassen(somar(A11, A12), B22, novoTamanho);
            int[][] M6 = strassen(subtrair(A21, A11), somar(B11, B12), novoTamanho);
            int[][] M7 = strassen(subtrair(A12, A22), somar(B21, B22), novoTamanho);

            // Calculando os quadrantes do resultado
            int[][] C11 = somar(subtrair(somar(M1, M4), M5), M7);
            int[][] C12 = somar(M3, M5);
            int[][] C21 = somar(M2, M4);
            int[][] C22 = somar(subtrair(somar(M1, M3), M2), M6);

            // Juntando os quadrantes na matriz resultante
            juntarMatriz(C11, resultado, 0, 0);
            juntarMatriz(C12, resultado, 0, novoTamanho);
            juntarMatriz(C21, resultado, novoTamanho, 0);
            juntarMatriz(C22, resultado, novoTamanho, novoTamanho);
        }

        return resultado;
    }

    // Funções auxiliares
    private void dividirMatriz(int[][] origem, int[][] destino, int linha, int coluna) {
        for (int i = 0; i < destino.length; i++) {
            for (int j = 0; j < destino.length; j++) {
                destino[i][j] = origem[i + linha][j + coluna];
            }
        }
    }

    private void juntarMatriz(int[][] origem, int[][] destino, int linha, int coluna) {
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem.length; j++) {
                destino[i + linha][j + coluna] = origem[i][j];
            }
        }
    }

    private int[][] somar(int[][] A, int[][] B) {
        int n = A.length;
        int[][] resultado = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                resultado[i][j] = A[i][j] + B[i][j];
        return resultado;
    }

    private int[][] subtrair(int[][] A, int[][] B) {
        int n = A.length;
        int[][] resultado = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                resultado[i][j] = A[i][j] - B[i][j];
        return resultado;
    }

    // Testando a implementação
    public static void main(String[] args) {
        MultiplicacaoStrassen strassen = new MultiplicacaoStrassen();
    
        // Lista de casos de teste com valores de unidades, dezenas e centenas
        int[][][] testesA = {
            {{1, 2}, {3, 4}},
            {{10, 20}, {30, 40}},
            {{100, 200}, {300, 400}},
            {{5, 6}, {7, 8}},
            {{15, 25}, {35, 45}},
            {{150, 250}, {350, 450}},
            {{2, 3}, {4, 5}},
            {{12, 24}, {36, 48}},
            {{123, 234}, {345, 456}},
            {{9, 8}, {7, 6}}
        };
    
        int[][][] testesB = {
            {{5, 6}, {7, 8}},
            {{1, 2}, {3, 4}},
            {{5, 6}, {7, 8}},
            {{1, 0}, {0, 1}},
            {{2, 3}, {4, 5}},
            {{6, 7}, {8, 9}},
            {{1, 1}, {1, 1}},
            {{5, 5}, {5, 5}},
            {{9, 8}, {7, 6}},
            {{2, 3}, {4, 5}}
        };
    
        for (int k = 0; k < testesA.length; k++) {
            System.out.println("Caso " + (k + 1) + ":");
    
            long tempoInicial = System.nanoTime();
            int[][] resultado = strassen.multiply(testesA[k], testesB[k]);
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
