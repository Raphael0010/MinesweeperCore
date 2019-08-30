package app;

import java.util.ArrayList;
import java.util.Random;

/**
 * On génère un plateau de 0 Les mines sont les 1
 */
public class Plateau {
    private ArrayList<Integer> mine = new ArrayList<Integer>();
    private ArrayList<Integer> plateau = new ArrayList<Integer>();

    Plateau() {
        for (int i = 0; (i < 100); i++) {
            plateau.add(0);
        }
    }

    /**
     * Construit le plateau
     */
    public void buildPlateau() {
        for (int i = 0; (i < 10); i++) {
            int random = randomInt(99, 1);
            if (plateau.get(random) == 1) {
                i--;
            } else {
                plateau.set(random, 1);
            }

        }
        this.addMine();
    }

    /**
     * Génère un random int
     */
    public int randomInt(int max, int min) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Permer de remplir la liste pour optenir les positions des mines dans la liste
     * tableau
     */
    private void addMine() {
        for (int i = 0; (i < 100); i++) {
            if (plateau.get(i) == 1) {
                mine.add(i);
            }
        }
    }

    /**
     * Détermine si oui ou non la coordonée donnée est une mine
     */
    public boolean isMine(int x) {
        try {
            if (plateau.get(x) == 1) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Affiche dans le terminal les informations sur le plateau
     */
    public void showPlateau() {
        for (Integer v : plateau) {
            System.out.println(v);
        }
        String pos = "";
        for (Integer v : mine) {
            pos += v + ",";
        }
        System.out.println("Les mines se situe au position : " + pos.substring(0, pos.length() - 1));
    }

    /**
     * Permet de donner le nombre de mine autour de la case donner
     * 
     * @param x
     * @return le nombre de bombe autour
     */
    public String giveAround(int x) {
        String retour = "0";
        int tab = 0;
        int d = x + 1;
        int g = x - 1;
        int b = x + 10;
        int h = x - 10;
        int dh = x - 9;
        int dg = x - 11;
        int bg = x + 9;
        int bd = x + 11;

        if (isMine(d)) {
            tab++;
        }
        if (isMine(g)) {
            tab++;
        }
        if (isMine(b)) {
            tab++;
        }
        if (isMine(h)) {
            tab++;
        }
        if (isMine(dh)) {
            tab++;
        }
        if (isMine(dg)) {
            tab++;
        }
        if (isMine(bg)) {
            tab++;
        }
        if (isMine(bd)) {
            tab++;
        }

        if (tab == 1) {
            retour = "1";
        } else if (tab == 2) {
            retour = "2";
        } else if (tab == 3) {
            retour = "3";
        } else if (tab >= 4) {
            retour = "4";
        }
        return retour;
    }
}