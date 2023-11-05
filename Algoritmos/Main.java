package Algoritmos;

import java.util.Random;

public class Main {

  public static int getLength(int vetor[]) {
    int length = 0;
    for (int i : vetor) {
      length++;
    }
    return length;
  }

  public static void main(String[] args) {
    Random r = new Random();
    int[] quantidades = {50, 500, 1000, 5000, 10000};
    int rodadas = 5;

    System.out.println("BubbleSort");
    for (int q : quantidades) {
      int sumIte = 0;
      int sumTro = 0;
      long sumTempo = 0;
      for (int i = 0; i < rodadas; i++) {
        int[] valores = new int[q];
        for (int j = 0; j < q; j++) {
          valores[j] = r.nextInt(100);
        }

        bubblesort vetor = new bubblesort();
        long comeco = System.nanoTime();
        vetor.bubble(valores);
        long fim = System.nanoTime();

        sumTempo += (fim - comeco) / 1e6;
        sumIte += vetor.getIteracoes();
        sumTro += vetor.getTrocas();
      }
      System.out.println("Tamanho do vetor: " + q);
      System.out.println("Media de tempo: " + sumTempo / (double) rodadas + " ms");
      System.out.println("Media de trocas: " + sumTro / rodadas);
      System.out.println("Media de iteracoes: " + sumIte / rodadas);
      System.out.println("------");
    }

    System.out.println("QuickSort");
    for (int q : quantidades) {
      int sumIte = 0;
      int sumTro = 0;
      long sumTempo = 0;
      for (int i = 0; i < rodadas; i++) {
        int[] valores = new int[q];
        for (int j = 0; j < q; j++) {
          valores[j] = r.nextInt(100);
        }

        quicksort vetor = new quicksort();
        long comeco = System.nanoTime();
        vetor.quick(valores, 0, getLength(valores) - 1);
        long fim = System.nanoTime();

        sumTempo += (fim - comeco) / 1e6;
        sumIte += vetor.getIteracoes();
        sumTro += vetor.getTrocas();
      }
      System.out.println("Tamanho do vetor: " + q);
      System.out.println("Media de tempo: " + sumTempo / (double) rodadas + " ms");
      System.out.println("Media de trocas: " + sumTro / rodadas);
      System.out.println("Media de iteracoes: " + sumIte / rodadas);
      System.out.println("------");
    }
  }
}

