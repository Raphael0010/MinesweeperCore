package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import app.Plateau;

public class App implements ActionListener {

    public static void main(String[] args) throws Exception {

        Plateau p = new Plateau();
        p.buildPlateau();
        p.showPlateau();

        JFrame f = new JFrame("Démineur");
        f.setSize(200, 200);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setVisible(true);
        GridLayout grid = new GridLayout(10, 9);
        grid.setHgap(2);
        grid.setVgap(2);
        f.setLayout(grid);
        for (int i = 0; (i < 100); i++) {
            JButton b = new JButton("");
            String index = Integer.toString(i);
            // Dans le cas ou c'est une bombe
            if (p.isMine(i)) {
                b.setName("mine+" + index);
            } else {
                b.setName("ok+" + index);
            }
            b.setBackground(Color.GRAY);
            b.setBorderPainted(false);
            b.setOpaque(true);
            b.setSize(50, 50);
            b.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String couleur = "gris";
                    if (b.getBackground() == Color.BLUE) {
                        b.setBackground(Color.GRAY);
                        b.setBorderPainted(false);
                        b.setText("");
                        b.setOpaque(true);
                        couleur = "bleu";
                    } else {
                        couleur = "gris";
                    }
                    if (couleur != "bleu") {
                        if (e.getButton() == 3) { // if right click
                            System.out.println("Drapeau");
                            b.setText("T");
                            b.setBackground(Color.blue);
                            b.setBorderPainted(false);
                            b.setOpaque(true);
                        }
                    }

                }
            });
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int position = Integer.parseInt(b.getName().split("\\+")[1]);
                    String nbMineAutour = p.giveAround(position);
                    System.out.println("Clique sur le bouton numéro : " + position);
                    System.out.println("Nombre de mine autour : " + p.giveAround(position));
                    b.setText(nbMineAutour.toString());
                    if (b.getName().split("\\+")[0].equals("mine")) {
                        System.out.println("Mine");
                        b.setBackground(Color.red);
                        b.setBorderPainted(false);
                        b.setOpaque(true);
                        JOptionPane.showMessageDialog(f, "Perdu", "Démineur", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    } else {
                        System.out.println("Rien");
                        b.setBackground(Color.green);
                        b.setBorderPainted(false);
                        b.setOpaque(true);
                    }

                }
            });
            f.add(b);
        }

    }

    public void actionPerformed(ActionEvent e) {

    }
}