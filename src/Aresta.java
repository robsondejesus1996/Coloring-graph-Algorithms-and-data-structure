/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robson de Jesus
 */

/**
 * 
 * instaciamento de objetos de aresta
 */
public class Aresta {

    private String nome;
    private Vertice vorigem;
    private Vertice vdestino;

    public Aresta(String n, Vertice o, Vertice d) {
        nome = "";
        vorigem = o;
        vdestino = d;
    }

    public Aresta(Vertice o, Vertice d) {
        vorigem = o;
        vorigem.addAdjacencia(d);
        vdestino = d;
        vdestino.addAdjacencia(o);
    }

    public void setnome(String n) {
        nome = n;
    }

    public String getnome() {
        return (nome);
    }

    public Vertice getorigem() {
        return vorigem;
    }

    public Vertice getdestino() {
        return vdestino;
    }

}
