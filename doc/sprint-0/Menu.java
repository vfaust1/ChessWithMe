import java.util.Scanner;

public class Menu{

    // Affichage du menu principal et gestion des choix de l'utilisateur
    public static void main(String[] args) {
        afficherMenu();
        Scanner menu = new Scanner(System.in);
        boolean quitter = false;

        while (!quitter) {
            afficherMenu();
            int choix = menu.nextInt();

            if (choix == 1) {
                quitter = true;
                afficherJouer();
            } else if (choix == 2) {
                quitter = true;
                afficherHistorique();
            } else if (choix == 3) {
                quitter = true;
                afficherCredits();
            } else if (choix == 4) {
                quitter = true;
                System.out.println("\n                            Au revoir !\n\n");
            } else {
                System.out.println("     Choix invalide. Veuillez réessayer.");
            }
        }
        menu.close();
    }

    // afficher les différentes sections du menu
    public static void afficherMenu(){
        System.out.println("\n\n                            Bienvenue sur ChessWithMe !\n\n                                 1. Jouer\n                                 2. Historique\n                                 3. Crédits\n                                 4. Quitter");
        System.out.print("\n\n  Veuillez choisir une option :   [1/2/3/4] \n\n");
    }

    // afficher l'historique des parties jouées
    public static void afficherHistorique(){
        System.out.println("                           Historique\n");
    }

    // afficher les options de jeu
    public static void afficherJouer(){
        System.out.println("\n\n                            Vous avez choisi de jouer !\n\n                                 1. vs Adversaire\n                                 2. vs Ordinateur\n\n");
    }

    // afficher les crédits du projet
    public static void afficherCredits(){
        System.out.println("\n\n                            Crédits\n\n   Réalisé par :\n   - \n   - \n   - \n   - \n   - \n   - \n\n");
    }
}