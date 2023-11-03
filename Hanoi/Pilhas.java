package Hanoi;

import java.util.Random;
import java.util.Scanner;

class Node {
  private Integer informacao;
  private Node proximo;

  public Node() {
    this.informacao = null;
    this.proximo = null;
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
}

public class Pilhas {
  private Node topo;
  private static int jogadas = 0;

  public Pilhas() {
    this.topo = null;
  }

  public void inserir(int valor) {
    Node no = new Node();
    no.setInformacao(valor);
    no.setProximo(topo);
    topo = no;
  }

  public int getJogadas() {
    return jogadas;
  }

  public void remover() {
    if (topo == null) {
      System.out.println("Pilha vazia");
      return;
    } else {
      topo = topo.getProximo();
    }
  }

  public int tamanhoPilha() {
    int tam = 0;
    Node atual = topo;
    while (atual != null) {
      tam++;
      atual = atual.getProximo();
    }
    return tam;
  }

  public void imprimir() {
    Node atual = topo;
    while (atual != null) {
      System.out.print(atual.getInformacao() + "\n");
      atual = atual.getProximo();
    }
    System.out.println("");
  }

  public void movimentar(Pilhas p1, int modo) {
    if (topo == null) {
    } else {
      int valor = topo.getInformacao();
      if (podeMover(valor, p1, modo)) {
        p1.inserir(valor);
        remover();
        jogadas++;
      } else {
      }
    }
  }

  public boolean podeMover(int valor, Pilhas p1, int modo) {
    if (modo == 1) {
      return p1.topo == null || valor < p1.topo.getInformacao();
    } else if (modo == 2) {
      return p1.topo == null || valor > p1.topo.getInformacao();
    }
    return false;
  }

  public boolean verificaPilhaCrescente() {
    Node atual = topo;
    while (atual != null && atual.getProximo() != null) {
      if (atual.getInformacao() > atual.getProximo().getInformacao()) {
        return false;
      }
      atual = atual.getProximo();
    }
    return true;
  }

  public boolean verificaPilhaDecrescente() {
    Node atual = topo;
    while (atual != null && atual.getProximo() != null) {
      if (atual.getInformacao() < atual.getProximo().getInformacao()) {
        return false;
      }
      atual = atual.getProximo();
    }
    return true;
  }

  public static void resolverCrescente(int tam, Pilhas p1, Pilhas p2, Pilhas p3) {
    if (tam > 0) {
      resolverCrescente(tam - 1, p1, p3, p2);
      p1.movimentar(p2, 1);
      resolverCrescente(tam - 1, p3, p2, p1);
      p3.movimentar(p2, 1);
    }
  }

  public static void resolverDecrescente(int tam, Pilhas p1, Pilhas p2, Pilhas p3) {
    if (tam > 0) {
      resolverDecrescente(tam - 1, p1, p3, p2);
      p1.movimentar(p2, 2);
      resolverDecrescente(tam - 1, p3, p2, p1);
      p3.movimentar(p2, 2);
    }
  }

  public static Pilhas obterPilha(int valor, Pilhas p1, Pilhas p2, Pilhas p3) {
    switch (valor) {
      case 1:
        return p1;
      case 2:
        return p2;
      case 3:
        return p3;
      default:
        return null;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Escolha o tamanho da pilha1:");
    int tam = scanner.nextInt();
    Pilhas pilha1 = new Pilhas();
    Pilhas pilha2 = new Pilhas();
    Pilhas pilha3 = new Pilhas();

    int opcao = -1;
    Random r = new Random();

    for (int i = 0; i < tam; i++) {
      int n = r.nextInt(100);
      pilha1.inserir(n);
    }

    System.out.println("\nModo de jogo: \n1. Crescente\n2. Decrescente");
    int modo = scanner.nextInt();
    System.out.println("\nOrganize de forma ordenada os elementos na pilha 2");

    System.out.println("\nPilha 1");
    pilha1.imprimir();
    System.out.println("\nPilha 2");
    pilha2.imprimir();
    System.out.println("\nPilha 3");
    pilha3.imprimir();

    while (opcao != 0) {
      System.out.println(
          "\nEscolha uma opcao: \n1. Movimentar\n2. Resolver automaticamente\n0. Sair");
      opcao = scanner.nextInt();

      switch (opcao) {
        case 1:
          System.out.println("\nPilha de retirada: \n1. Pilha1\n2. Pilha2\n3. Pilha3");
          int pop = scanner.nextInt();
          Pilhas pilhaPop = obterPilha(pop, pilha1, pilha2, pilha3);

          System.out.println("\nPilha de insercao: \n1. Pilha1\n2. Pilha2\n3. Pilha3");
          int push = scanner.nextInt();
          Pilhas pilhaPush = obterPilha(push, pilha1, pilha2, pilha3);

          pilhaPop.movimentar(pilhaPush, modo);
          System.out.println("\nPilha 1");
          pilha1.imprimir();
          System.out.println("\nPilha 2");
          pilha2.imprimir();
          System.out.println("\nPilha 3");
          pilha3.imprimir();

          if (modo == 1) {
            if (pilha2.verificaPilhaCrescente() && pilha2.tamanhoPilha() == tam) {
              System.out.println(
                  "Fim de jogo.\nPilha crescente ordenada, numero de jogadas: "
                      + pilha2.getJogadas());
              opcao = 0;
            }
          } else if (modo == 2) {
            if (pilha2.verificaPilhaDecrescente() && pilha2.tamanhoPilha() == tam) {
              System.out.println(
                  "Fim de jogo.\nPilha decrescente ordenada, numero de jogadas: "
                      + pilha2.getJogadas());
              opcao = 0;
            }
          }

          break;
        case 2:
          if (modo == 1) {
            resolverCrescente(tam, pilha1, pilha2, pilha3);
            System.out.println("Pilha 1");
            pilha1.imprimir();
            System.out.println("Pilha 2");
            pilha2.imprimir();
            System.out.println("Pilha 3");
            pilha3.imprimir();
            if (pilha2.verificaPilhaCrescente() && pilha2.tamanhoPilha() == tam) {
              System.out.println(
                  "Fim de jogo.\nPilha crescente ordenada, numero de jogadas: "
                      + pilha2.getJogadas());
              opcao = 0;
            }
          } else if (modo == 2) {
            resolverDecrescente(tam, pilha1, pilha2, pilha3);
            System.out.println("Pilha 1");
            pilha1.imprimir();
            System.out.println("Pilha 2");
            pilha2.imprimir();
            System.out.println("Pilha 3");
            pilha3.imprimir();
            if (pilha2.verificaPilhaDecrescente() && pilha2.tamanhoPilha() == tam) {
              System.out.println(
                  "Fim de jogo.\nPilha decrescente ordenada, numero de jogadas: "
                      + pilha2.getJogadas());
              opcao = 0;
            }
          }
        case 0:
          break;
        default:
          System.out.println("Opção invalida.");
          break;
      }
    }
    scanner.close();
  }
}
