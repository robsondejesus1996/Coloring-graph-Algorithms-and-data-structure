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
 * classe para setar a lista de cores, e depois ir pegando as cores que eu posso utilizar
 */
public class Cor {

    private String cor;
    private String corfonte;

    public Cor(String c, String f) {
        cor = c;
        corfonte = f;
    }

    public String getcor() {
        return cor;
    }

    public void setcor(String c) {
        cor = c;
    }

    public String getcorfonte() {
        return corfonte;
    }

    public void setcorfonte(String c) {
        corfonte = c;
    }

}
