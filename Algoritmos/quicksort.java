package Algoritmos;

public class quicksort {
  private int trocas;
  private int iteracoes;
  public int getTrocas() {
    return trocas;
  }

  public int getIteracoes() {
    return iteracoes;
  }
  public void quick(int[] vetor, int inferior, int superior) {
    int pivo;
    iteracoes++;

    if (inferior >= superior) {
      return;
    }

    pivo = particiona(vetor, inferior, superior);
    quick(vetor, inferior, pivo - 1);
    quick(vetor, pivo + 1, superior);
  }

  public int particiona(int[] vetor, int inferior, int superior) {
    int pivo = vetor[inferior];
    int pinferior = inferior + 1;
    int psuperior = superior;

    while (true) {
      while (pinferior <= psuperior && vetor[pinferior] <= pivo) {
        pinferior++;
        iteracoes++;
      }
      while (psuperior >= pinferior && vetor[psuperior] > pivo) {
        psuperior--;
        iteracoes++;
      }
      if (pinferior > psuperior) {
        break;
      } else {
        int temp = vetor[pinferior];
        vetor[pinferior] = vetor[psuperior];
        vetor[psuperior] = temp;
        trocas++;
      }
    }

    int temp = vetor[inferior];
    vetor[inferior] = vetor[psuperior];
    vetor[psuperior] = temp;
    trocas++;

    return psuperior;
  }

  public static void main(String[] args) {
    int[] array = {12, 9,99, 10};

    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
    quicksort qs = new quicksort();
    qs.quick(array, 0, array.length - 1);
    System.out.println("\n");
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
  }
}
