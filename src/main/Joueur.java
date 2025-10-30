package main;

import java.util.Scanner;
import main.historiqueMouvement.Historique;
import main.piece.*;
public class Joueur {
    /* Attributs */
    private String pseudo;
    private Couleur couleur;

    /* Constructeurs */
    public Joueur(String pseudo, Couleur coul) {
        this.pseudo = pseudo;
        this.couleur = coul;
    }

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
    }

    /* Getter */
    public String getPseudo() {
        return this.pseudo;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    /* Methodes */
    @Override
    public String toString() {
        return " " + this.pseudo + " : " + this.getCouleur();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Joueur others = (Joueur) obj;
        if (this.pseudo != others.pseudo) {
            return false;
        }
        return true;
    }

    public int[] choixDeplacement(String entree, Historique historique) {
        Scanner scanner = new Scanner(System.in);
        String input;
        char c1;
        char c2;
        int[] tabco = new int[2];
        Plateau plateau;

        while (true) {
            try {
                System.out.println(entree);
                input = scanner.nextLine().trim();

                if(input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("leave") || input.equalsIgnoreCase("quitter")){
                    System.out.println("Partie terminée.");
                    Menu.start();
                }

                if(input.equalsIgnoreCase("reset") || input.equalsIgnoreCase("restart") || input.equalsIgnoreCase("recommencer")){
                    System.out.println("Partie réinitialisée.");
                    return new int[]{-1, -1}; // Indique une réinitialisation
                }
                
                if (input.equalsIgnoreCase("hisotique") || input.equalsIgnoreCase("history") || input.equalsIgnoreCase("histoire")){
                    historique.afficher();
                    System.out.print("\033[2J");
                    System.out.flush();
                    throw new IllegalArgumentException("Historique affiché. Veuillez entrer une position valide.");
                }

                // Vérification longueur exacte = 2
                if (input.length() != 2) {
                    throw new IllegalArgumentException("Format de position invalide. Utilisez le format 'a2'.");
                }

                c1 = input.charAt(0);
                c2 = input.charAt(1);

                boolean c1IsLetter = (c1 >= 'a' && c1 <= 'h') || (c1 >= 'A' && c1 <= 'H');
                boolean c1IsDigit  = c1 >= '1' && c1 <= '8';

                boolean c2IsLetter = (c2 >= 'a' && c2 <= 'h') || (c2 >= 'A' && c2 <= 'H');
                boolean c2IsDigit  = c2 >= '1' && c2 <= '8';

                // Vérifie qu'il y a exactement 1 lettre et 1 chiffre
                if (c1IsLetter && c2IsDigit) {
                    System.out.println("Entrée valide : " + input);
                    tabco[0] = input.charAt(1) - '0' - 1;
                    tabco[1] = Character.toLowerCase(input.charAt(0)) - 'a';       
                    return tabco;
                }else if(c1IsDigit && c2IsLetter){
                    tabco[0] = input.charAt(0) - '0' - 1;
                    tabco[1] = Character.toLowerCase(input.charAt(1)) - 'a';
                    return tabco;
                } else {
                    throw new IllegalArgumentException("Position invalide. Utilisez une position comme 'a2' ou '2a'.");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                // boucle continue → redemande à l’utilisateur
            }
        }
    }

    public boolean demanderDeplacement(Plateau plateau, int [] co1,int []co2) {


        if(plateau.getPlateau()[co1[0]][co1[1]] == null){
            ChessWithMe.clearConsole();
            System.out.println("Case vide");
            return false;
        }

        if(!plateau.getPlateau()[co1[0]][co1[1]].getColor().equals(this.getCouleur())){
            ChessWithMe.clearConsole();
            System.out.println("Mauvaise couleur");
            return false;
        }
        
        return plateau.getPlateau()[co1[0]][co1[1]].move(plateau,co1,co2);
    }

    public void echecJoueur(Plateau plateau){
        Roi roi = plateau.getRoi(this.getCouleur());
        if (roi == null) {
            System.out.println("Erreur : Le roi de la couleur " + this.getCouleur() + " est introuvable.");
            return;
        }
        if (roi.echec(plateau.getPlateau(), plateau.getPositionRoi(this.getCouleur()))) {
            System.out.println("Echec !");
            if (roi.echecEtMat(plateau.getPlateau(), plateau.getPositionRoi(this.getCouleur()))) {
                System.out.println("Echec et mat ! " + this.getPseudo() + " a perdu.");
            }
        }
    }
}
