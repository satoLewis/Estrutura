package ArvoreBalanceada;
class Node {
  private Integer informacao;
  private Node direita;
  private Node esquerda;

  public Node() {
    this.informacao = null;
    this.direita = null;
    this.esquerda = null;
  }

  public Integer getInformacao() {
    return informacao;
  }

  public void setInformacao(Integer informacao) {
    this.informacao = informacao;
  }

  public Node getDireita() {
    return direita;
  }

  public void setDireita(Node direita) {
    this.direita = direita;
  }

  public Node getEsquerda() {
    return esquerda;
  }

  public void setEsquerda(Node esquerda) {
    this.esquerda = esquerda;
  }
}

public class ArvoreBalanceada {
  Node raizInicial;

  public ArvoreBalanceada() {
    this.raizInicial = null;
  }

  public void inserir(int valor) {
    Node no = new Node();
    no.setInformacao(valor);

    if (raizInicial == null) {
      raizInicial = no;
    } else {
      Node atual = raizInicial;
      while (true) {
        if (no.getInformacao() < atual.getInformacao()) {
          if (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
          } else {
            atual.setEsquerda(no);
            break;
          }
        } else {
          if (atual.getDireita() != null) {
            atual = atual.getDireita();
          } else {
            atual.setDireita(no);
            break;
          }
        }
      }
    }
    raizInicial = balancear(raizInicial);
  }

  public double tempoInserir(int[] valores) {
    long comeco = System.nanoTime();
    for (int valor : valores) {
      inserir(valor);
    }
    long fim = System.nanoTime();
    return (fim - comeco) / 1e6;
  }

  public Node buscar(int elemento) {
    Node atual = raizInicial;
    while (atual != null && atual.getInformacao() != elemento) {
      if (atual.getInformacao() > elemento) {
        atual = atual.getEsquerda();
      } else {
        atual = atual.getDireita();
      }
    }
    return null;
  }

  public double tempoBuscar(int valor) {
    long comeco = System.nanoTime();
    buscar(valor);
    long fim = System.nanoTime();
    return (fim - comeco) / 1e6;
  }

  private Node removerN(Node raiz, int valor) {
    if (raiz == null) {
      return null;
    }
    if (valor < raiz.getInformacao()) {
      raiz.setEsquerda(removerN(raiz.getEsquerda(), valor));
    } else if (valor > raiz.getInformacao()) {
      raiz.setDireita(removerN(raiz.getDireita(), valor));
    } else {
      if (raiz.getEsquerda() == null && raiz.getDireita() == null) {
        raiz = null;
      } else if (raiz.getEsquerda() == null) {
        raiz = raiz.getDireita();
      } else if (raiz.getDireita() == null) {
        raiz = raiz.getEsquerda();
      } else {
        int valorMin = encontrarMenor(raiz.getDireita());
        raiz.setInformacao(valorMin);
        raiz.setDireita(removerN(raiz.getDireita(), valorMin));
      }
    }
    return balancear(raiz);
  }

  private int encontrarMenor(Node raiz) {
    while (raiz.getEsquerda() != null) {
      raiz = raiz.getEsquerda();
    }
    return raiz.getInformacao();
  }

  public double tempoRemover(int valor) {
    long comeco = System.nanoTime();
    removerN(raizInicial, valor);
    long fim = System.nanoTime();
    return (fim - comeco) / 1e6;
  }

  public static int altura(Node no) {
    if (no == null) {
      return -1;
    }
    int esquerda = altura(no.getEsquerda());
    int direita = altura(no.getDireita());
    if (esquerda > direita) {
      return 1 + esquerda;
    }
    return 1 + direita;
  }

  public int FB(Node no) {
    return altura(no.getEsquerda()) - altura(no.getDireita());
  }

  public Node rotacaoDireita(Node raiz) {
    Node novaRaiz = raiz.getEsquerda();
    Node temp = novaRaiz.getDireita();
    novaRaiz.setDireita(raiz);
    raiz.setEsquerda(temp);

    return novaRaiz;
  }

  public Node rotacaoEsquerda(Node raiz) {
    Node novaRaiz = raiz.getDireita();
    Node temp = novaRaiz.getEsquerda();
    novaRaiz.setEsquerda(raiz);
    raiz.setDireita(temp);

    return novaRaiz;
  }

  public Node balancear(Node raiz) {
    if (raiz == null) {
      return null;
    }

    if (FB(raiz) > 1) {
      if (FB(raiz.getEsquerda()) < 0) {
        raiz.setEsquerda(rotacaoEsquerda(raiz.getEsquerda()));
      }
      raiz = rotacaoDireita(raiz);

    } else if (FB(raiz) < -1) {
      if (FB(raiz.getDireita()) > 0) {
        raiz.setDireita(rotacaoDireita(raiz.getDireita()));
      }
      raiz = rotacaoEsquerda(raiz);
    }
    return raiz;
  }
}
