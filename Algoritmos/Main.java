package Algoritmos;

import java.io.FileWriter;
import java.io.IOException;
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

    try (FileWriter csvWriter = new FileWriter("resultados_algoritmos.csv")) {
      csvWriter.append(
          "Tamanho do Vetor;Algoritmo;Tempo de Execucao (ms);Media de Trocas;"
              + "Media de Iteracoes\n");

      System.out.println("\nBubbleSort");
      for (int q : quantidades) {
        int sumIte = 0;
        int sumTro = 0;
        double sumTempo = 0;
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
        String tipo = "BubbleSort";
        System.out.println("Tamanho do vetor: " + q);
        System.out.println("Media de tempo: " + sumTempo / rodadas + " ms");
        System.out.println("Media de trocas: " + sumTro / rodadas);
        System.out.println("Media de iteracoes: " + sumIte / rodadas);
        System.out.println("------");

        csvWriter.append(
            String.format(
                "%d;%s;%.2f;%d;%d\n",
                q, tipo, sumTempo / (double) rodadas, sumTro / rodadas, sumIte / rodadas));
      }

      System.out.println("\nQuickSort");
      for (int q : quantidades) {
        int sumIte = 0;
        int sumTro = 0;
        double sumTempo = 0;
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
        String tipo = "QuickSort";
        System.out.println("Tamanho do vetor: " + q);
        System.out.println("Media de tempo: " + sumTempo / rodadas + " ms");
        System.out.println("Media de trocas: " + sumTro / rodadas);
        System.out.println("Media de iteracoes: " + sumIte / rodadas);
        System.out.println("------");

        csvWriter.append(
            String.format(
                "%d;%s;%.2f;%d;%d\n",
                q, tipo, sumTempo / (double) rodadas, sumTro / rodadas, sumIte / rodadas));
      }

      System.out.println("\nSelectionSort");
      for (int q : quantidades) {
        int sumIte = 0;
        int sumTro = 0;
        double sumTempo = 0;
        for (int i = 0; i < rodadas; i++) {
          int[] valores = new int[q];
          for (int j = 0; j < q; j++) {
            valores[j] = r.nextInt(100);
          }

          selectionsort vetor = new selectionsort();
          long comeco = System.nanoTime();
          vetor.selection(valores);
          long fim = System.nanoTime();

          sumTempo += (fim - comeco) / 1e6;
          sumIte += vetor.getIteracoes();
          sumTro += vetor.getTrocas();
        }
        String tipo = "SelectionSort";
        System.out.println("Tamanho do vetor: " + q);
        System.out.println("Media de tempo: " + sumTempo / rodadas + " ms");
        System.out.println("Media de trocas: " + sumTro / rodadas);
        System.out.println("Media de iteracoes: " + sumIte / rodadas);
        System.out.println("------");

        csvWriter.append(
            String.format(
                "%d;%s;%.2f;%d;%d\n",
                q, tipo, sumTempo / (double) rodadas, sumTro / rodadas, sumIte / rodadas));
      }
    } catch (IOException e) {
      System.out.println("Ocorreu um erro ao escrever no arquivo CSV.");
      e.printStackTrace();
    }
  }
}

