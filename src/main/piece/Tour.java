package main.piece;

import main.Couleur;
import main.Plateau;

public class Tour extends Piece {
    public Tour(Couleur color) {
        super(color, PieceName.TOUR);
    }


    public boolean moveIsOk(Piece[][] plateau, int[] oldPosition, int[] newPosition) {
        if(newPosition[0] == oldPosition[0] && newPosition[1] == oldPosition[1]) return false;
        if(newPosition[0] < 8 && newPosition[0] >= 0 && newPosition[1] < 8 && newPosition[1] >= 0) {
            if ((oldPosition[0] == newPosition[0] || oldPosition[1] == newPosition[1]) && super.maxDepl(plateau, oldPosition, newPosition)){
                return true;
            } else if ((oldPosition[0] == newPosition[0] || oldPosition[1] == newPosition[1]) && super.mange(plateau, oldPosition, newPosition)){
                return true;
            }
        } 
        return false;
    }

    public boolean move(Plateau plateau, int[] oldPosition, int[] newPosition) {
        Piece[][] plat = plateau.getPlateau();
        if (moveIsOk(plateau.getPlateau(), oldPosition, newPosition) && newPosition != oldPosition) {
            Piece temp = plat[newPosition[0]][newPosition[1]];
            plateau.getPlateau()[newPosition[0]][newPosition[1]] = this;
            if((plateau.getRoi(color).echec(plat, plateau.getPositionRoi(this.getColor())) == false) || plateau.getRoi(color).echecPos(plat, plateau.getPositionRoi(this.getColor())) == newPosition) {
                plateau.getPlateau()[oldPosition[0]][oldPosition[1]] = null;
                return true;
            }else {
                plateau.getPlateau()[newPosition[0]][newPosition[1]] = temp;
                plateau.getPlateau()[oldPosition[0]][oldPosition[1]] = this;
            }
        }
        System.out.println("Mouvement impossible pour la tour");
        return false;
    }
}