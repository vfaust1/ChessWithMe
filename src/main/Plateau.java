package main;

import main.piece.Cavalier;
import main.piece.Dame;
import main.piece.Fou;
import main.piece.Piece;
import main.piece.Pion;
import main.piece.Roi;
import main.piece.Tour;

public class Plateau {

    private Piece[][] plateau;
    private String[][] plateauCouleur;

    public Plateau() {
        this.plateau = new Piece[8][8];
        this.plateauCouleur = new String[8][8];
        this.initPlateauCouleur();
        this.initPlateau();
    }

    public Plateau(Demo type) {
        this.plateau = new Piece[8][8];
        this.plateauCouleur = new String[8][8];
        this.initPlateauCouleur();
        if(type == Demo.PROMOTION){
            this.initPromotion();
        }else if(type == Demo.ECHEC){
            this.initEchec();
        }else if(type == Demo.ECHECETMAT){
            this.initEchecEtMat();
        }else if(type == Demo.GAME){
            this.initPlateau();
        }else if(type == Demo.PION){
            this.initPion();
        }else if(type == Demo.TUTO){
            this.initTuto();
        }
    }

    public void initPlateau() {
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                this.plateau[i][k] = null;
            }
        }
        for (int i = 0; i < 8; i++) {
            this.placerPiece(1, i, new Pion(Couleur.NOIR));
            this.placerPiece(6, i, new Pion(Couleur.BLANC));
        }
        this.placerPiece(0, 0, new Tour(Couleur.NOIR));
        this.placerPiece(0, 1, new Cavalier(Couleur.NOIR));
        this.placerPiece(0, 2, new Fou(Couleur.NOIR));
        this.placerPiece(0, 3, new Dame(Couleur.NOIR));
        this.placerPiece(0, 4, new Roi(Couleur.NOIR));
        this.placerPiece(0, 5, new Fou(Couleur.NOIR));
        this.placerPiece(0, 6, new Cavalier(Couleur.NOIR));
        this.placerPiece(0, 7, new Tour(Couleur.NOIR));

        this.placerPiece(7, 0, new Tour(Couleur.BLANC));
        this.placerPiece(7, 1, new Cavalier(Couleur.BLANC));
        this.placerPiece(7, 2, new Fou(Couleur.BLANC));
        this.placerPiece(7, 3, new Dame(Couleur.BLANC));
        this.placerPiece(7, 4, new Roi(Couleur.BLANC));
        this.placerPiece(7, 5, new Fou(Couleur.BLANC));
        this.placerPiece(7, 6, new Cavalier(Couleur.BLANC));
        this.placerPiece(7, 7, new Tour(Couleur.BLANC));
    }

    public void initPlateauVierge() {
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                this.plateau[i][k] = null;
            }
        }
    }

    public void initPlateauCouleur() {
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                if ((i + k) % 2 == 0) {
                    this.plateauCouleur[i][k] = "⬛";
                } else {
                    this.plateauCouleur[i][k] = "⬜";
                }
            }
        }
    }

    public void initPromotion(){
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                this.plateau[i][k] = null;
            }
        }
        this.placerPiece(1, 1, new Pion(Couleur.BLANC));
        this.placerPiece(7, 7, new Roi(Couleur.NOIR));
        this.placerPiece(0, 7, new Roi(Couleur.BLANC));
    }

    public void initEchec(){
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                this.plateau[i][k] = null;
            }
        }
        this.placerPiece(0, 4, new Tour(Couleur.NOIR));
        this.placerPiece(0, 3, new Tour(Couleur.NOIR));
        this.placerPiece(0, 5, new Roi(Couleur.NOIR));
        this.placerPiece(3, 0, new Dame(Couleur.BLANC));
        this.placerPiece(4, 4, new Roi(Couleur.BLANC));
    }

    public void initEchecEtMat(){
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                this.plateau[i][k] = null;
            }
        }
        this.placerPiece(0, 5, new Tour(Couleur.BLANC));
        this.placerPiece(1, 6, new Tour(Couleur.BLANC));
        this.placerPiece(0, 0, new Roi(Couleur.BLANC));
        this.placerPiece(7, 7, new Roi(Couleur.NOIR));
    }

    public void initPion(){
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                this.plateau[i][k] = null;
            }
        }
        this.placerPiece(1, 5, new Pion(Couleur.NOIR));
        this.placerPiece(6, 5, new Pion(Couleur.BLANC));
        this.placerPiece(7, 5, new Roi(Couleur.BLANC));
        this.placerPiece(0, 5, new Roi(Couleur.NOIR));
    }

    public void initTuto(){
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                this.plateau[i][k] = null;
            }
        }
        this.placerPiece(1, 4, new Pion(Couleur.NOIR));
        this.placerPiece(6, 4, new Pion(Couleur.BLANC));
        this.placerPiece(7, 3, new Dame(Couleur.BLANC));
        this.placerPiece(7, 4, new Roi(Couleur.BLANC));
        this.placerPiece(0, 4, new Roi(Couleur.NOIR));
    }

    @Override
    public String toString() {
        int lenght = 20;
        String s = bigEspace(lenght) + "  a b c d e f g h  ";
        s += "\n";
        for (int i = 0; i < 8; i++) {
            s += bigEspace(lenght) + (i+1) + " ";
            for (int k = 0; k < 8; k++) {
                if (plateau[i][k] == null) {
                    s += plateauCouleur[i][k];
                } else {
                    s += plateau[i][k].getPiece() + " ";
                }
            }
            s += " " + (i+1) + "\n";
        }
        s += bigEspace(lenght) + "  a b c d e f g h  ";
        return s;
    }

    public static String bigEspace(int n){
        return " ".repeat(n);
    }

    public void placerPiece(int i, int k, Piece piece) {
        this.plateau[i][k] = piece;
    }

    public Piece[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Piece[][] plateau) {
        this.plateau = plateau;
    }

    public String[][] getPlateauCouleur() {
        return plateauCouleur;
    }

    public void setPlateauCouleur(String[][] plateauCouleur) {
        this.plateauCouleur = plateauCouleur;
    }

    public Roi getRoi(Couleur color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece != null && piece.getName() == main.piece.PieceName.ROI && piece.getColor() == color) {
                    return (Roi) piece;
                }
            }
        }
        return null; // Roi non trouvé
    }

    public int[] getPositionRoi(Couleur color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece != null && piece.getName() == main.piece.PieceName.ROI && piece.getColor() == color) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1}; // Roi non trouvé
    }

    // POUR LE BOT 
    public String toFEN() {
        StringBuilder fen = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int empty = 0;
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau[i][j];
                if (piece == null) {
                    empty++;
                } else {
                    if (empty > 0) {
                        fen.append(empty);
                        empty = 0;
                    }
                    String symbol = switch(piece.getName()) {
                        case PION -> piece.getColor() == Couleur.BLANC ? "P" : "p";
                        case TOUR -> piece.getColor() == Couleur.BLANC ? "R" : "r";
                        case CAVALIER -> piece.getColor() == Couleur.BLANC ? "N" : "n";
                        case FOU -> piece.getColor() == Couleur.BLANC ? "B" : "b";
                        case DAME -> piece.getColor() == Couleur.BLANC ? "Q" : "q";
                        case ROI -> piece.getColor() == Couleur.BLANC ? "K" : "k";
                    };
                    fen.append(symbol);
                }
            }
            if (empty > 0) fen.append(empty);
            if (i < 7) fen.append('/');
        }
        // Pour l'instant, on met "w" pour le joueur blanc à jouer, sans infos de roque ou en passant
        fen.append(" w KQkq - 0 1");
        return fen.toString();
    }

    public void appliquerCoupUCI(String move) {
        int fromRow = 8 - Character.getNumericValue(move.charAt(1));
        int fromCol = move.charAt(0) - 'a';
        int toRow = 8 - Character.getNumericValue(move.charAt(3));
        int toCol = move.charAt(2) - 'a';

        plateau[toRow][toCol] = plateau[fromRow][fromCol];
        plateau[fromRow][fromCol] = null;
    }

}