package Algoritmos;

public class bubblesort {
  private int tamanho;
  private int trocas;
  private int iteracoes;

  public int getTrocas() {
    return trocas;
  }

  public int getIteracoes() {
    return iteracoes;
  }

  public void bubble(int[] vetor) {
    for (int i : vetor) {
      tamanho++;
    }
    for (int i = 0; i < tamanho; i++) {
      for (int j = 1; j < (tamanho - i); j++) {
        iteracoes++;
        if (vetor[j - 1] > vetor[j]) {
          int temp = vetor[j - 1];
          vetor[j - 1] = vetor[j];
          vetor[j] = temp;
          trocas++;
        }
      }
    }
  }

  public static void main(String[] args) {
    bubblesort test = new bubblesort();
    int[] t = {10, 25, 5, 3, 50, 14};
    for (int i = 0; i < t.length; i++) {
      System.out.println(t[i]);
    }
    test.bubble(t);
    System.out.println("\n");
    for (int i = 0; i < t.length; i++) {
      System.out.println(t[i]);
    }
  }
}
