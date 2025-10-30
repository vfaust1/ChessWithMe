package main.piece;

import main.Couleur;
import main.Plateau;

public class Roi extends Piece {
    boolean deplacer = false;
    public Roi(Couleur color) {
        super(color, PieceName.ROI);
    }

    public boolean moveIsOk(Piece[][] plateau, int[] oldPosition, int[] newPosition) {
        if(newPosition[0] == oldPosition[0] && newPosition[1] == oldPosition[1]) return false;
        if(newPosition[0] < 8 && newPosition[0] >= 0 && newPosition[1] < 8 && newPosition[1] >= 0) {
            if (Math.abs(oldPosition[0] - newPosition[0]) <= 1 && Math.abs(oldPosition[1] - newPosition[1]) <= 1 && plateau[newPosition[0]][newPosition[1]] == null) {
                return true;
            }
            if (Math.abs(oldPosition[0] - newPosition[0]) <= 1 && Math.abs(oldPosition[1] - newPosition[1]) <= 1 && super.mange(plateau, oldPosition, newPosition)) {
                return true;
            }
        }
        return false;
    }

    public boolean echec(Piece[][] plateau, int[] positionRoi) {
        // Vérifie si une pièce adverse peut capturer le roi à sa position actuelle
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece != null && piece.getColor() != this.getColor()) {
                    if (piece.moveIsOk(plateau, new int[] { i, j }, positionRoi)) {
                        System.out.println("Le roi est en échec");
                        return true; // Le roi est en échec
                    }
                }
            }
        }
        return false; // Le roi n'est pas en échec
    }

    private boolean echec(Piece[][] plateau, int[] positionRoi, boolean entry) {
        // Vérifie si une pièce adverse peut capturer le roi à sa position actuelle
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece != null && piece.getColor() != this.getColor()) {
                    if (piece.moveIsOk(plateau, new int[] { i, j }, positionRoi)) {
                        return true; // Le roi est en échec
                    }
                }
            }
        }
        return false; // Le roi n'est pas en échec
    }

    public int[] echecPos(Piece[][] plateau, int[] positionRoi) {
        // Retourne la position de la pièce qui met le roi en échec, ou null si le roi n'est pas en échec
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece != null && piece.getColor() != this.getColor()) {
                    if (piece.moveIsOk(plateau, new int[] { i, j }, positionRoi)) {
                        return new int[] { i, j }; // Retourne la position de la pièce qui met le roi en échec
                    }
                }
            }
        }
        return new int[] { -1, -1 }; // Le roi n'est pas en échec
    }

    public boolean contreEchec(Piece[][] plateau, int[] positionRoi, int[] positionPiece) {
        // Vérifie si une pièce alliée peut capturer la pièce qui met le roi en échec
        Piece pieceEchec = plateau[positionPiece[0]][positionPiece[1]];
        if (pieceEchec == null || pieceEchec.getColor() == this.getColor()) {
            return false; // Pas de pièce ennemie à la position donnée
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece != null && piece.getColor() == this.getColor() && !(piece instanceof Roi)) {
                    if (piece.moveIsOk(plateau, new int[] { i, j }, positionPiece)) {
                        return true; // Une pièce alliée peut capturer la pièce qui met le roi en échec
                    }
                }
            }
        }
        // Vérifie si une pièce alliée peut se mettre en bouclier
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece != null && piece.getColor() == this.getColor() && !(piece instanceof Roi)) {
                    // Vérifie si la pièce peut se déplacer entre le roi et la pièce qui met en échec
                    int deltaX = Integer.signum(positionPiece[0] - positionRoi[0]);
                    int deltaY = Integer.signum(positionPiece[1] - positionRoi[1]);
                    int x = positionRoi[0] + deltaX;
                    int y = positionRoi[1] + deltaY;
                    while (x != positionPiece[0] || y != positionPiece[1]) {
                        if (piece.moveIsOk(plateau, new int[] { i, j }, new int[] { x, y })) {
                            return true; // Une pièce alliée peut se mettre en bouclier
                        }
                        x += deltaX;
                        y += deltaY;
                    }
                }
            }
        }
        return false; // Aucune pièce alliée ne peut capturer la pièce qui met le roi en échec
    }

    public boolean echecEtMat(Piece[][] plateau, int[] positionRoi) {
        // Vérifie si le roi est en échec et s'il n'a pas de mouvement légal pour s'échapper
        plateau[positionRoi[0]][positionRoi[1]] = null; // Assure que le roi est à sa position actuelle
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (moveIsOk(plateau, positionRoi, new int[] { i, j }) && !echec(plateau, new int[] { i, j }, false)) {
                    plateau[positionRoi[0]][positionRoi[1]] = this;
                    return false; // Le roi peut s'échapper
                }
            }
        }
        plateau[positionRoi[0]][positionRoi[1]] = this;// Remet le roi à sa position actuelle
        int[] positionEchec = echecPos(plateau, positionRoi);
        if (positionEchec[0] != -1 && positionEchec[1] != -1 && contreEchec(plateau, positionRoi, positionEchec)) {
            return false; // Une pièce alliée peut capturer la pièce qui met le roi en échec
        }
        System.out.println("Le roi est en échec et mat");
        return true; // Le roi est en échec et mat
    }

    public boolean move(Plateau plateau, int[] oldPosition, int[] newPosition) {
        Piece[][] plat = plateau.getPlateau();
        if (moveIsOk(plateau.getPlateau(), oldPosition, newPosition)) {
            Piece temp = plateau.getPlateau()[newPosition[0]][newPosition[1]];
            plateau.getPlateau()[newPosition[0]][newPosition[1]] = this;
            if(((plateau.getRoi(color).echec(plat, newPosition)) == false) || plateau.getRoi(color).echecPos(plat, plateau.getPositionRoi(this.getColor())) == newPosition) {
                plateau.getPlateau()[oldPosition[0]][oldPosition[1]] = null;
                this.deplacer = true;
                return true;
            }else {
                plateau.getPlateau()[newPosition[0]][newPosition[1]] = temp;
                plateau.getPlateau()[oldPosition[0]][oldPosition[1]] = this;
            }
        }
        System.out.println("Mouvement impossible pour le roi");
        return false;
    }
}
