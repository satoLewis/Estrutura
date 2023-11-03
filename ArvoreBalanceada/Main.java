package ArvoreBalanceada;
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    Random r = new Random();

    int[] quantidades = {100, 500, 1000, 10000, 20000};

    for (int quantidade : quantidades) {
      ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
      ArvoreBalanceada arvoreAVL = new ArvoreBalanceada();

      int[] valores = new int[quantidade];
      for (int i = 0; i < quantidade; i++) {
        valores[i] = r.nextInt(20000);
      }
      System.out.println("Arvore Binaria");
      double tempoInserir = arvoreBinaria.tempoInserir(valores);
      System.out.println("Tempo para inserir " + quantidade + " números: " + tempoInserir + " ms");

      int numeroBuscar = valores[r.nextInt(quantidade)];
      double tempoBuscar = arvoreBinaria.tempoBuscar(numeroBuscar);
      System.out.println("Tempo para buscar o número " + numeroBuscar + ": " + tempoBuscar + " ms");

      int numeroRemover = valores[r.nextInt(quantidade)];
      double tempoRemover = arvoreBinaria.tempoRemover(numeroRemover);
      System.out.println(
          "Tempo para remover o número " + numeroRemover + ": " + tempoRemover + " ms\n");

      System.out.println("Arvore AVL");
      double tempoInserirAVL = arvoreAVL.tempoInserir(valores);
      System.out.println(
          "Tempo para inserir " + quantidade + " números: " + tempoInserirAVL + " ms");

      double tempoBuscarAVL = arvoreAVL.tempoBuscar(numeroBuscar);
      System.out.println(
          "Tempo para buscar o número " + numeroBuscar + ": " + tempoBuscarAVL + " ms");

      double tempoRemoverAVL = arvoreAVL.tempoRemover(numeroRemover);
      System.out.println(
          "Tempo para remover o número " + numeroRemover + ": " + tempoRemoverAVL + " ms\n");
    }
  }
}
