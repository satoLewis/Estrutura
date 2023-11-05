package Algoritmos;

public class shellsort {
  private int tamanho;
  private int iteracoes;
  private int trocas;

  public int getIteracoes() {
    return iteracoes;
  }

  public int getTrocas() {
    return trocas;
  }

  private int calcH(int i) {
    if (i == 1) {
      return 1;
    } else {
      return 3 * calcH(i - 1) + 1;
    }
  }

  public void shell(int[] vetor) {
    for (int i : vetor) {
      tamanho++;
    }

    int x = 1;
    while (calcH(x) < tamanho) {
      x++;
    }
    int n_inc = x - 2;
    int[] incrementos = new int[n_inc];

    for (int i = 1; i <= n_inc; i++) {
      incrementos[n_inc - i] = calcH(i);
    }

    int j, k, span, y;
    for (int incr : incrementos) {
      span = incr;
      for (j = span; j < tamanho; j++) {
        y = vetor[j];
        for (k = j - span; k >= 0 && y < vetor[k]; k -= span) {
          vetor[k + span] = vetor[k];
          trocas++;
        }
        vetor[k + span] = y;
        iteracoes++;
      }
    }
  }
}
