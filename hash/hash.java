package hash;

public class hash {
  private Node[] tabela;
  private int tamanho;
  private int countInserir;
  private int countBuscar;
  private int i;

  public hash(int tamanho) {
    this.tamanho = tamanho;
    this.tabela = new Node[tamanho];
  }

  public int getCountInserir(){
    return countInserir;
  }

  public int getCountBuscar(){
    return countBuscar;
  }

  public static int pow(int base, int expoente) {
    int resultado = 1;
    for (int i = 0; i < expoente; i++) {
      resultado *= base;
    }
    return resultado;
  }

  public int hashing(int valor) {
    return valor % tamanho;
  }

  public int hashingMultiplicacao(int valor) {
    double constante = 0.6180339887; 
    double resultado = valor * constante;
    resultado = resultado - (long) resultado; 
    return (int) (tamanho * resultado);
  }
  public int hashingDobramento(int valor) {
    int partes = 4;
    int soma = 0;
    while (valor > 0) {
      soma += valor % pow(10, partes);
      valor /= pow(10, partes);
    }
    return soma % tamanho;
  }

  public void inserir(int valor, int x) {
    Node no = new Node();
    no.setInformacao(valor);
    switch (x) {
      case 1: i = hashing(valor); break;
      case 2: i = hashingMultiplicacao(valor); break;
      case 3: i = hashingDobramento(valor); break;
      default: i = 0; break;
    }
    if (tabela[i] == null) {
      tabela[i] = no;
    } else {
      countInserir++;
      Node atual = tabela[i];
      Node penultimo = null;
      while (true) {
        if (atual.getProximo() == null) {
          atual.setProximo(no);
          break;
        } else if (atual.getProximo() != null
            && atual.getProximo().getInformacao() < no.getInformacao()) {
          atual = atual.getProximo();
        } else {
          penultimo = atual.getProximo();
          atual.setProximo(no);
          no.setProximo(penultimo);
          break;
        }
      }
    }
  }

  public double tempoInserir(int[] valores, int x) {
    long comeco = System.nanoTime();
    for (int valor : valores) {
      inserir(valor, x);
    }
    long fim = System.nanoTime();
    return (fim - comeco) / 1e6;
  }

  public Node buscar(int valor, int x) {
    switch (x) {
      case 1: i = hashing(valor); break;
      case 2: i = hashingMultiplicacao(valor); break;
      case 3: i = hashingDobramento(valor); break;
      default: i = 0; break;
    }
    Node atual = tabela[i];
    while (atual.getProximo() != null && atual.getInformacao() != valor) {
      countBuscar++;
      atual = atual.getProximo();
    }
    return null;
  }

  public double tempoBuscar(int valor, int x) {
    long comeco = System.nanoTime();
    buscar(valor, x);
    long fim = System.nanoTime();
    return (fim - comeco) / 1e6;
  }
}
