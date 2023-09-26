class Node {
  private Integer informacao;
  private Node proximo;
  private Node anterior;

  public Node() {
    this.informacao = null;
    this.proximo = null;
    this.anterior = null;
  }

  public Integer getInformacao() {
    return informacao;
  }

  public void setInformacao(Integer informacao) {
    this.informacao = informacao;
  }

  public Node getProximo() {
    return proximo;
  }

  public void setProximo(Node proximo) {
    this.proximo = proximo;
  }

  public Node getAnterior() {
    return anterior;
  }

  public void setAnterior(Node anterior) {
    this.anterior = anterior;
  }
}

public class arvore {
  private Node raiz;

  public Node getRaiz() {
    return raiz;
  }

  public arvore() {
    this.raiz = null;
  }

  public void inserir(int valor) {
    Node no = new Node();
    no.setInformacao(valor);
    if (raiz == null) {
      raiz = no;
    } else {
      Node atual = raiz;
      while (true) {
        if (no.getInformacao() < atual.getInformacao()) {
          if (atual.getAnterior() != null) {
            atual = atual.getAnterior();
          } else {
            atual.setAnterior(no);
            break;
          }
        } else {
          if (atual.getProximo() != null) {
            atual = atual.getProximo();
          } else {
            atual.setProximo(no);
            break;
          }
        }
      }
    }
  }

  public void removerMenor() {
    Node atual = raiz;
    Node penultimo = null;
    while (atual.getAnterior() != null) {
      penultimo = atual;
      atual = atual.getAnterior();
    }
    penultimo.setAnterior(null);
  }

  public void removerMaior() {
    Node atual = raiz;
    Node penultimo = null;
    while (atual.getProximo() != null) {
      penultimo = atual;
      atual = atual.getProximo();
    }
    penultimo.setProximo(null);
  }

  public void preOrder(Node raiz) {
    if (raiz != null) {
      System.out.println(raiz.getInformacao() + "-");
      preOrder(raiz.getAnterior());
      preOrder(raiz.getProximo());
    }
  }

  public void inOrder(Node raiz) {
    if (raiz != null) {
      inOrder(raiz.getAnterior());
      System.out.println(raiz.getInformacao() + "-");
      inOrder(raiz.getProximo());
    }
  }

  public void posOrder(Node raiz) {
    if (raiz != null) {
      inOrder(raiz.getAnterior());
      inOrder(raiz.getProximo());
      System.out.println(raiz.getInformacao());
    }
  }

  public void remover(int valor) {
    Node atual = raiz;
    Node atualProximo = null;
    Node pai = null;

    while (atual != null && atual.getInformacao() != valor) {
      pai = atual;
      if (valor < atual.getInformacao()) {
        atualProximo = atual;
        atual = atual.getAnterior();
      } else {
        atualProximo = atual;
        atual = atual.getProximo();
      }
    }

    if (atual != null) {
      if (atual.getAnterior() == null && atual.getProximo() == null) {
        if (pai != null) {
          if (atual.getInformacao() < atualProximo.getInformacao()) {
            pai.setAnterior(null);
          } else {
            pai.setProximo(null);
          }
        } else {
          raiz = null;
        }
      }
      else if (atual.getAnterior() == null) { 
        if (pai != null) {
          if (atual.getInformacao() < atualProximo.getInformacao()) {
            pai.setAnterior(atual.getProximo());
          } else {
            pai.setProximo(atual.getProximo());
          }
        } else {
          raiz = atual.getProximo();
        }
      } else if (atual.getProximo() == null) { 
        if (pai != null) {
          if (atual.getInformacao() < atualProximo.getInformacao()) {
            pai.setAnterior(atual.getAnterior());
          } else {
            pai.setProximo(atual.getAnterior());
          }
        } else {
          raiz = atual.getAnterior();
        }
      }
      else {
        Node n = atual.getProximo();
        Node nPai = atual;
        while (n.getAnterior() != null) {
          nPai = n;
          n = n.getAnterior();
        }

        atual.setInformacao(n.getInformacao());

        if (nPai == atual) {
          nPai.setProximo(n.getProximo());
        } else {
          nPai.setAnterior(n.getProximo());
        }
      }
    }
  }

  public static void main(String[] args) {
    arvore a1 = new arvore();

    a1.inserir(5);
    a1.inserir(3);
    a1.inserir(7);
    a1.inserir(2);
    a1.inserir(4);
    a1.inserir(6);
    a1.inserir(8);
    a1.removerMaior();
    a1.removerMenor();
    a1.remover(3);

    System.out.println("Travessia inOrder:");
    a1.inOrder(a1.getRaiz());

    System.out.println("Travessia preOrder:");
    a1.preOrder(a1.getRaiz());

    System.out.println("Travessia posOrder:");
    a1.posOrder(a1.getRaiz());
  }
}
