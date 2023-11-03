package hash;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

  public static void main(String[] args) {
    Random r = new Random();
    int[] quantidades = {5000, 10000, 20000, 50000, 500000};
    int[] conjuntos = {20000, 100000, 500000, 1000000, 2000000};
    int min = 100000000;
    int max = 999999999;

    try (FileWriter csvWriter = new FileWriter("resultados_hash.csv")) {
      csvWriter.append(
          "Tamanho da Tabela;Conjunto;Tipo de Hash;Tempo para Inserir (ms);Colisoes;Tempo para"
              + " Buscar (ms);Comparacoes\n");

      for (int quantidade : quantidades) {
        System.out.println("\n\nTamanho da tabela: " + quantidade + "\n\n");
        for (int conjunto : conjuntos) {
          int[] valores = new int[conjunto];
          for (int i = 0; i < conjunto; i++) {
            valores[i] = r.nextInt(max - min) + min;
          }
          int numeroBuscar = valores[r.nextInt(conjunto)];
          for (int i = 1; i <= 3; i++) {
            hash Tabela = new hash(quantidade);
            String tipoHash = "";
            double tempoInserir = Tabela.tempoInserir(valores, i);
            int colisoes = Tabela.getCountInserir();
            double tempoBuscar = Tabela.tempoBuscar(numeroBuscar, i);
            int comparacoes = Tabela.getCountBuscar();

            switch (i) {
              case 1:
                tipoHash = "Sobra da divisão";
                break;
              case 2:
                tipoHash = "Multiplicacao";
                break;
              case 3:
                tipoHash = "Dobramento";
                break;
            }

            System.out.println("Tipo de Hash: " + tipoHash);
            System.out.println(
                "Tempo para inserir " + conjunto + " números: " + tempoInserir + " ms");
            System.out.println("Colisoes: " + colisoes);
            System.out.println(
                "Tempo para buscar o número " + numeroBuscar + ": " + tempoBuscar + " ms");
            System.out.println("Comparacoes: " + comparacoes + "\n");

            csvWriter.append(
                String.format(
                    "%d;%d;%s;%.2f;%d;%.2f;%d\n",
                    quantidade,
                    conjunto,
                    tipoHash,
                    tempoInserir,
                    colisoes,
                    tempoBuscar,
                    comparacoes));
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Ocorreu um erro ao escrever no arquivo CSV.");
      e.printStackTrace();
    }
  }
}
