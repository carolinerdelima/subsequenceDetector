public class DetectorSubsequencia {

    public boolean hasTrend(String[] S, String[] S_line) {
        // Índice pra percorrer S
        int indiceS = 0;

        // Índice pra percorrer S_line
        int indiceSLinha = 0;

        while (indiceS < S.length && indiceSLinha < S_line.length) {
            if (S[indiceS].equals(S_line[indiceSLinha])) {
                // Avança se o evento corresponde
                indiceSLinha++;
            }

            // Sempre avança em S
            indiceS++;
        }

        // Se percorreu todo o S_line, então é subsequência
        return indiceSLinha == S_line.length;
    }

    // Testes de funcionamento
    public static void main(String[] args) {
        DetectorSubsequencia detector = new DetectorSubsequencia();

        /**
         * Caso 1: Subsequência válida
         * Deve retornar: true
         */ 
        String[] S1 = {"buy Amazon", "buy Google", "buy Apple", "buy Google", "buy Google", "buy NVIDIA"};
        String[] S_line1 = {"buy Google", "buy Apple", "buy Google", "buy NVIDIA"};
        System.out.println("Caso 1: " + detector.hasTrend(S1, S_line1));

        /**
         * Caso 2: S_line não é subsequência por ser uma ordem incorreta
         * Deve retornar: false
         */
        String[] S2 = {"buy Amazon", "buy Google", "buy Apple", "buy Google", "buy Google", "buy NVIDIA"};
        String[] S_line2 = {"buy Google", "buy NVIDIA", "buy Google"};
        System.out.println("Caso 2: " + detector.hasTrend(S2, S_line2));

        /**
         * Caso 3: S_line é subsequência completa de S
         * Deve retornar: true
         */
        String[] S3 = {"buy Google", "buy Apple", "buy Google", "buy NVIDIA"};
        String[] S_line3 = {"buy Google", "buy Apple", "buy Google", "buy NVIDIA"};
        System.out.println("Caso 3: " + detector.hasTrend(S3, S_line3));

        /**
         * Caso 4: S_line é subsequência parcial do começo
         * Deve retornar: true
         */
        String[] S_line4 = {"buy Google", "buy Apple"};
        System.out.println("Caso 4: " + detector.hasTrend(S1, S_line4));

        /**
         * Caso 5: S_line é subsequência parcial do fim
         * Deve retornar: true
         */
        String[] S_line5 = {"buy Google", "buy NVIDIA"};
        System.out.println("Caso 5: " + detector.hasTrend(S1, S_line5));

        /**
         * Caso 6: S_line não existe em S
         * Deve retornar: false
         */
        String[] S_line6 = {"buy Tesla"};
        System.out.println("Caso 6: " + detector.hasTrend(S1, S_line6));

        /**
         * Caso 7: S_line vazia (sempre subsequência)
         * Deve retornar: true
         */
        String[] S_line7 = {};
        System.out.println("Caso 7: " + detector.hasTrend(S1, S_line7));

        /**
         * Caso 8: S vazia e S_line não vazia
         * Deve retornar: false
         */
        String[] S8 = {};
        String[] S_line8 = {"buy Google"};
        System.out.println("Caso 8: " + detector.hasTrend(S8, S_line8));

        /**
         * Caso 9: Ambos vazios, então é uma subsequência válida
         * Deve retornar: true
         */
        String[] S9 = {};
        String[] S_line9 = {};
        System.out.println("Caso 9: " + detector.hasTrend(S9, S_line9));

        /**
         * Caso 10: Eventos repetidos em S, mas S_line exige ordem específica
         * Deve retornar: true
         */
        String[] S10 = {"buy Google", "buy Google", "buy Google"};
        String[] S_line10 = {"buy Google", "buy Google"};
        System.out.println("Caso 10: " + detector.hasTrend(S10, S_line10));

        /**
         * Caso 11: Ordem errada em S_line
         * Deve retornar: false
         */
        String[] S11 = {"buy Amazon", "buy Google", "buy Apple"};
        String[] S_line11 = {"buy Apple", "buy Google"};
        System.out.println("Caso 11: " + detector.hasTrend(S11, S_line11));

        /**
         * Caso 12: Sequência grande
         * 100 tipos de ações, repetindo
         * Deve retornar: true
         */
        String[] S12 = new String[1000000];
        for (int i = 0; i < S12.length; i++) {
            S12[i] = "buy Teste" + (i % 100);
        }

        String[] S_line12 = {"buy Teste1", "buy Teste2", "buy Teste3"};

        long tempoInicial12 = System.nanoTime();
        System.out.println("Caso 12: " + detector.hasTrend(S12, S_line12));
        long tempoFinal12 = System.nanoTime();
        System.out.println("Tempo de execução (nanosegundos) Caso 12: " + (tempoFinal12 - tempoInicial12));

        /**
         * Caso 13: Pior caso, onde não existe
         * Deve retornar: false
         */
        String[] S_line13 = {"buy NaoExiste1", "buy NaoExiste2"};

        long tempoInicial13 = System.nanoTime();
        System.out.println("Caso 13 (pior caso): " + detector.hasTrend(S12, S_line13));
        long tempoFinal13 = System.nanoTime();
        System.out.println("Tempo de execução (nanosegundos) Caso 13: " + (tempoFinal13 - tempoInicial13));
    }
    
}
