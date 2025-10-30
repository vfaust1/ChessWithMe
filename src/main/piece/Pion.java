package main.piece;

import java.util.Scanner;
import main.Couleur;
import main.Plateau;

public class Pion extends Piece {
    private boolean premierCoup = true;

    public Pion(Couleur color) {
        super(color, PieceName.PION);
    }

    public boolean pionMange(boolean estBlanc, Piece[][] plateau, int[] oldPosition, int[] newPosition) {
        int oldX = oldPosition[0];
        int oldY = oldPosition[1];
        int newX = newPosition[0];
        int newY = newPosition[1];

        // Vérifie que la nouvelle position est bien sur le plateau
        if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
            return false;
        }

        // Mouvement du pion blanc (avance vers le bas de la matrice si [0][0] est en
        // haut à gauche)
        if (estBlanc) {
            if (newX == oldX - 1 && Math.abs(newY - oldY) == 1) {
                Piece cible = plateau[newX][newY];
                return cible != null && cible.getColor() != Couleur.BLANC;
            }
        }
        // Mouvement du pion noir (avance vers le haut de la matrice)
        else {
            if (newX == oldX + 1 && Math.abs(newY - oldY) == 1) {
                Piece cible = plateau[newX][newY];
                return cible != null && cible.getColor() != Couleur.NOIR;
            }
        }
        return false;
    }

    public boolean moveIsOk(Piece[][] plateau, int[] oldPosition, int[] newPosition) {
        if(newPosition[0] == oldPosition[0] && newPosition[1] == oldPosition[1]) return false;
        boolean estBlanc = super.getColor() == Couleur.BLANC;
        int maxCase = premierCoup ? 2 : 1;
        if (oldPosition[0] >= 0 && oldPosition[0] < 8 && oldPosition[1] >= 0 && oldPosition[1] < 8) {
            if (estBlanc) {
                if (oldPosition[0] - maxCase >= 0 && plateau[oldPosition[0] - maxCase][oldPosition[1]] == null) {
                    if (newPosition[0] >= 0 && newPosition[0] <= 7 && (newPosition[0] == oldPosition[0] - 1 || newPosition[0] == oldPosition[0] - maxCase)
                            && newPosition[1] == oldPosition[1]) {
                        return true;
                }
            }
            } else {
                if (oldPosition[0] + 1 < 8 && plateau[oldPosition[0] + 1][oldPosition[1]] == null) {
                    if (newPosition[0] >= 0 && newPosition[0] <= 7 && (newPosition[0] == oldPosition[0] + 1 || newPosition[0] == oldPosition[0] + maxCase)
                            && newPosition[1] == oldPosition[1]) {
                        return true;
                    }
                }
            }
            return pionMange(estBlanc, plateau, oldPosition, newPosition);
        }
        return false;
    }

    public boolean promotion(Plateau plateau, int[] position) {
        String co1 = "";
        Piece[][] plat = plateau.getPlateau();
        if(position[0] == 0 || position[0] == 7){
            System.out.println("Votre pion peut être promu :\n1. Reine\n2. Tour\n3. Fou\n4. Cavalier");
            Scanner sc = new Scanner(System.in); 
            co1 = sc.nextLine();
            if(co1.equals("1") || co1.equals("Reine") || co1.equals("Dame") || co1.equals("reine") || co1.equals("dame") || co1.equals("REINE") || co1.equals("DAME")){
                plat[position[0]][position[1]] = new Dame(this.color);
            }
            else if(co1.equals("2") || co1.equals("Tour") || co1.equals("tour") || co1.equals("TOUR")){
                plat[position[0]][position[1]] = new Tour(this.color);
            }
            else if(co1.equals("3") || co1.equals("Fou") || co1.equals("fou") || co1.equals("FOU")){
                plat[position[0]][position[1]] = new Fou(this.color);
            }
            else if(co1.equals("4") || co1.equals("Cavalier") || co1.equals("cavalier") || co1.equals("CAVALIER")){
                plat[position[0]][position[1]] = new Cavalier(this.color);
            }
            else{
                plat[position[0]][position[1]] = new Dame(this.color);
            }
            plateau.setPlateau(plat);
            return true;
        }
        return false;
    }

    public boolean move(Plateau plateau, int[] oldPosition, int[] newPosition) {
        if(this.color == Couleur.BLANC) {
            if(oldPosition[0] != 6) {
                this.premierCoup = false;
            }
        } else {
            if(oldPosition[0] != 1) {
                this.premierCoup = false;
            }
        }
        Piece[][] plat = plateau.getPlateau();
        if (moveIsOk(plateau.getPlateau(), oldPosition, newPosition)) {
            Piece temp = plat[newPosition[0]][newPosition[1]];
            plateau.getPlateau()[newPosition[0]][newPosition[1]] = this;
            if((plateau.getRoi(color).echec(plat, plateau.getPositionRoi(this.getColor())) == false) || plateau.getRoi(color).echecPos(plat, plateau.getPositionRoi(this.getColor())) == newPosition) {
                plateau.getPlateau()[oldPosition[0]][oldPosition[1]] = null;
                promotion(plateau, newPosition);
                return true;
            }else {
                plateau.getPlateau()[newPosition[0]][newPosition[1]] = temp;
                plateau.getPlateau()[oldPosition[0]][oldPosition[1]] = this;
            }
        }
        System.out.println("Mouvement impossible pour le pion");
        return false;
    }

    public void setPremierCoup(boolean premierCoup) {
        this.premierCoup = premierCoup;
    }
}