/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson de Jesus
 */
import javax.swing.JOptionPane;
import java.util.Comparator;
import java.util.Collections;

import java.io.*;
import java.util.ArrayList;

public class Desenha {

    ArrayList<Vertice> vertices;
    ArrayList<Aresta> arestas;
    ArrayList<Cor> cores;

    public static void main(String[] args) {
        Desenha p = new Desenha();
        p.start();

    }

    private void start() { 

        //instaciando as listas de vertices, arestas, cores
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
        cores = new ArrayList<Cor>();

        // pede o número de vértices e popula a lista de vértices
        // vertice a,b,c,d,e
        int qvertices;
        qvertices = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade de vértices:"));
        int c;
        for (int i = 0; i < qvertices; i++) {
            c = 65 + i;
            char ca = (char) c;
            vertices.add(new Vertice(ca));
        }
        
        //definir as aresta com as regras
        // pede as arestas com as regras de valores invalidos
        
        c = 65 + vertices.size() - 1;
        char ultimo = (char) c;

        String a, atemp;
        a = "";
        boolean valido;
        valido = true;
        do {
            valido = true;
            a = JOptionPane.showInputDialog(null, "Defina as arestas dos vértices A..." + ultimo + " separando por vírgulas (exemplo: AB,AC,BC):", a);
            a = a.toUpperCase();
            a = a.replace(" ", "");
            a = a.replace(";", ",");
            a = a.replace(".", ",");
            if (a.substring(0, 1).equals(",")) {
                a = a.substring(1);
            }
            if (a.substring(a.length() - 1).equals(",")) {
                a = a.substring(0, a.length() - 1);
            }

            atemp = a.replace(",", "");

            char cv[] = atemp.toCharArray();

            for (char d : cv) {
                if (d > ultimo) {
                    JOptionPane.showMessageDialog(null, "Vértice " + d + " inválido.");
                    valido = false;
                }
            }

            atemp = a;

            //regras para evitar valores invalidos, a letra de um vertice que não exite
            //não colocar 3 vertices no mesmo seguimento
            do {
                String av = "";
                if (atemp.indexOf(",") > -1) {
                    av = atemp.substring(0, atemp.indexOf(","));
                    atemp = atemp.substring(atemp.indexOf(",") + 1);
                } else {
                    av = atemp;
                    atemp = "";
                }
                if (av.length() > 2) {
                    JOptionPane.showMessageDialog(null, "Arestas são definidas de um vértice até outro. Corrija " + av + ".");
                    valido = false;
                }

            } while (atemp.length() > 0);

        } while (!valido);

        // popula a lista de arestas segundo a lista que foi colocado
        atemp = a;

        do {
            String av = "";
            if (atemp.indexOf(",") > -1) {
                av = atemp.substring(0, atemp.indexOf(","));
                atemp = atemp.substring(atemp.indexOf(",") + 1);
            } else {
                av = atemp;
                atemp = "";
            }
            
            //adicionando a lista de aresta e separando uma por umas
            arestas.add(new Aresta(buscavertice(av.substring(0, 1)), buscavertice(av.substring(1, 2))));

        } while (atemp.length() > 0);

        // ordena os vertices em ordem decrescente de grau
        Collections.sort(vertices, new ComparatorVertices(false));

        // Colorir vértices, iniciando pelo primeiro da lista (de maior grau)
        // popula lista de cores
        populacores();

        
        //para cada vertice vai chamar a funçao colore_vertice
        for (Vertice vi : vertices) {

            // Colorir vértice
            Colore_Vertice(vi);
        }

        // imprime o grafo sem cor no arquivo original.gif
        printgrafo(false);

        // imprime o grafo colorido no arquivo colorido.gif
        printgrafo(true);

        JOptionPane.showMessageDialog(null, "Seus grafos foram criados nos arquivos original.gif e colorido.gif.");

    }

    private Vertice buscavertice(String n) {
        Vertice retorno;
        retorno = null;
        for (Vertice v : vertices) {
            if (v.getnome().equals(n)) {
                retorno = v;
                break;
            }
        }
        return (retorno);
    }

    private void populacores() {
        cores.clear();
        cores.add(new Cor("#ff0000", "#ffffff"));
        cores.add(new Cor("#ff00ff", "#000000"));
        cores.add(new Cor("#0000ff", "#ffffff"));
        cores.add(new Cor("#00ffff", "#000000"));
        cores.add(new Cor("#00ff00", "#000000"));
        cores.add(new Cor("#ffff00", "#000000"));
        cores.add(new Cor("#7f0000", "#ffffff"));
        cores.add(new Cor("#7f007f", "#ffffff"));
        cores.add(new Cor("#00007f", "#ffffff"));
        cores.add(new Cor("#007f7f", "#ffffff"));
        cores.add(new Cor("#007f00", "#ffffff"));
        cores.add(new Cor("#827f00", "#000000"));
        cores.add(new Cor("#000000", "#ffffff"));
        cores.add(new Cor("#333333", "#ffffff"));
        cores.add(new Cor("#4c4c4c", "#ffffff"));
        cores.add(new Cor("#b2b2b2", "#000000"));
        cores.add(new Cor("#ffffff", "#000000"));
    }

    // Verificação para colorir o vertice
    private void Colore_Vertice(Vertice vk) {
        Cor c;
        c = null;
        if (!vk.colorido()) {
            for (Cor cc : cores) {
                boolean podeusarcor;
                podeusarcor = true;
                for (Vertice vj : vk.getadjacencia()) {
                    if (vj.colorido()) {
                        if (vj.getcor().equals(cc)) {
                            podeusarcor = false;
                            break;
                        }
                    }
                }
                if (podeusarcor) {
                    c = cc;
                    break;
                }
            }
            //cor disponivel para o vertice
            vk.setcor(c);
            //para cada vertice v esta chamado a função colore vertice 
            for (Vertice vj : vk.getadjacencia()) {
                Colore_Vertice(vj);
            }
        }
    }

    private void printgrafo(boolean colorido) {
        String saida;
        if (colorido) {
            saida = "colorido.gif";
        } else {
            saida = "original.gif";
        }

        // instancia o objeto para print do grafo
        //API
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());

        for (Vertice v : vertices) {
            if (colorido) {
                gv.addln(v.getnome() + " [ color=\"" + v.getcor().getcor() + "\", fontcolor=\"" + v.getcor().getcorfonte() + "\", style=filled];");
            } else {
                gv.addln(v.getnome() + ";");
            }
        }

        for (Aresta aa : arestas) {
            gv.addln(aa.getorigem().getnome() + " -> " + aa.getdestino().getnome());
        }
        /*     
	     gv.addln("A [ color=Red, style=filled];");
         */

        gv.addln(gv.end_graph());

        System.out.println(gv.getDotSource());

        File out = new File(saida);
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource()), out);
    }

}

// implementação de comparator parar utilizar sort da lista
//controla a ordenação de uma lista <ArrayList>
class ComparatorVertices implements Comparator {

    boolean crescente = true;

    public ComparatorVertices(boolean crescente) {
        this.crescente = crescente;
    }

    public int compare(Object o1, Object o2) {
        Vertice p1 = (Vertice) o1;
        Vertice p2 = (Vertice) o2;
        if (crescente) {
            return p1.getgrau() < p2.getgrau() ? -1 : (p1.getgrau() > p2.getgrau() ? +1 : 0);
        } else {
            return p1.getgrau() < p2.getgrau() ? +1 : (p1.getgrau() > p2.getgrau() ? -1 : 0);
        }
    }
}

