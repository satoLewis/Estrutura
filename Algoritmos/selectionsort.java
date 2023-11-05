package Algoritmos;

public class selectionsort {
  private int tamanho;
  private int trocas;
  private int iteracoes;

  public int getTrocas() {
    return trocas;
  }

  public int getIteracoes() {
    return iteracoes;
  }

  public void selection(int[] vetor) {
    for (int i : vetor) {
      tamanho++;
    }
    for (int i = 0; i < tamanho; i++) {
      int minIndex = i;
      for (int j = i + 1; j < tamanho; j++) {
        iteracoes++;
        if (vetor[j] < vetor[minIndex]) {
          minIndex = j;
        }
      }
      int temp = vetor[minIndex];
      vetor[minIndex] = vetor[i];
      vetor[i] = temp;
      trocas++;
    }
  }
}
