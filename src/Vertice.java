/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
/**
 *
 * @author Robson de Jesus
 */

/**
 * Todos os atributos e metodos para cada vertice
 * 
 */
public class Vertice {
//colocando a lista de adjacencia nele mesmo e a cor.
    private String nome;
    private ArrayList<Vertice> adjacencia;
    private Cor cor;

    public Vertice() {
        nome = "";
        adjacencia = new ArrayList<Vertice>();
        cor = null;
    }

    public Vertice(char n) {
        nome = "" + n;
        adjacencia = new ArrayList<Vertice>();
        cor = null;
    }

    public void setnome(String n) {
        nome = n;
    }

    public String getnome() {
        return (nome);
    }

    public void addAdjacencia(Vertice v) {
        adjacencia.add(v);
    }

    public int getgrau() {
        return (adjacencia.size());
    }

    public void setcor(Cor c) {
        cor = c;
    }

    public Cor getcor() {
        return (cor);
    }

    public boolean colorido() {
        if (cor == null) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Vertice> getadjacencia() {
        return adjacencia;
    }

}

