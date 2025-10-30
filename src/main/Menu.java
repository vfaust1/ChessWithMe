package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void start() {
        Scanner menu = new Scanner(System.in);
        boolean quitter = false;    

        while (!quitter) {
            ChessWithMe.clearConsole();
            afficherTitre();
            afficherMenu();
            try {
                System.out.print("Votre choix : ");
                int choix = menu.nextInt();
                menu.nextLine();

                switch (choix) {
                    case 1:
                        ChessWithMe.clearConsole();
                        afficherJouer(menu); 
                        break;
                    case 2:
                        ChessWithMe.clearConsole();
                        afficherDemo(menu);
                        break;
                    case 3:
                        ChessWithMe.clearConsole();
                        afficherHistorique(menu);
                        break;
                    case 4:
                        ChessWithMe.clearConsole();
                        afficherCredits(menu);
                        break; 
                    case 5:
                        ChessWithMe.clearConsole();
                        afficherRegles(menu);
                        break;
                    case 6:
                        ChessWithMe.clearConsole();
                        quitter = true;
                        afficherAuRevoirASCII();
                        break;
                    default:
                        System.out.println("\n     Choix invalide. Veuillez réessayer.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n     Entrée invalide. Veuillez entrer un nombre.\n");
                menu.nextLine(); // Vider l’entrée incorrecte
            }
        }

        menu.close();
    }

    // Affiche les choix du menu principal
    public static void afficherMenu() {
        System.out.println("\n\n                       1. Jouer");
        System.out.println("                       2. Demo");
        System.out.println("                       3. Historique");
        System.out.println("                       4. Crédits");
        System.out.println("                       5. Règles");
        System.out.println("                       6. Quitter");
        System.out.println();
    }

    // Affiche le sous-menu pour choisir le type d'adversaire
    public static void afficherJouer(Scanner scanner) {
        boolean retour = false;

        while (!retour) {
            afficherTitre();
            System.out.println("\n\n                                 1. vs Adversaire");
            System.out.println("                                 2. vs Ordinateur");
            System.out.println("                                 3. Retour au menu principal\n");

            try {
                System.out.print("Votre choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // vider le buffer

                switch (choix) {
                    case 1:
                        System.out.println("Démarrage de la partie contre un autre joueur...\n");
                        ChessWithMe.start(Demo.GAME);
                        break;
                    case 2:
                        System.out.println("Démarrage de la partie contre le Bot...\n");
                        ChessWithMe.startBot(Demo.GAME);
                        break;
                    case 3:
                        retour = true;
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                scanner.nextLine(); // vider l’entrée incorrecte
            }
        }
    }


    public static void afficherDemo(Scanner scanner) {
        boolean retour = false;

        while (!retour) {
            afficherDemoAscii();
            System.out.println("\n\n                                 1. Promotion");
            System.out.println("                                 2. Echec");
            System.out.println("                                 3. Echec et Mat");
            System.out.println("                                 4. Deplacements des pions");
            System.out.println("                                 5. Tutoriel\n");
            System.out.println("                                 6. Retour au menu principal\n");

            try {
                System.out.print("Votre choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // vider le buffer

                switch (choix) {
                    case 1:
                        System.out.println("Démarrage de la démo de promotion...\n");
                        ChessWithMe.start(Demo.PROMOTION);
                        break;
                    case 2:
                        System.out.println("Démarrage de la démo d'échec...\n");
                        ChessWithMe.start(Demo.ECHEC);
                        break;
                    case 3:
                        System.out.println("Démarrage de la démo d'échec et mat...\n");
                        ChessWithMe.start(Demo.ECHECETMAT);
                        break;
                    case 4:
                        System.out.println("Démarrage de la démo de déplacement des pions...\n");
                        ChessWithMe.start(Demo.PION);
                        break;
                    case 5:
                        System.out.println("Démarrage de la démo de tutoriel...\n");
                        ChessWithMe.start(Demo.TUTO);
                        break;
                    case 6:
                        retour = true;
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.\n");
                scanner.nextLine(); // vider l’entrée incorrecte
            }
        }
    }

    // Affiche l'historique des parties
    public static void afficherHistorique(Scanner scanner) {
        afficherHistoriqueAscii();
        String nom = "Joueur1";
        Couleur couleur = Couleur.BLANC;
        Joueur j1 = new Joueur(nom,couleur);
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(j1);
        HistoriqueJ historique = new HistoriqueJ(joueurs);

        System.out.println("Historique des parties " + historique.toString() + " :\n");

        System.out.println("Appuyez sur Entrée pour revenir au menu principal...");
        scanner.nextLine();
    }

    // Affiche les crédits du jeu
    public static void afficherCredits(Scanner scanner) {
        afficherCreditsAscii();
        System.out.println("   Réalisé par :\n");
        System.out.println("   - Sulivan");
        System.out.println("   - Camille                               UwU");
        System.out.println("   - Manon");
        System.out.println("   - Matheo");
        System.out.println("   - Nathan");
        System.out.println("   - Valentin\n");

        System.out.println("Appuyez sur Entrée pour revenir au menu principal...");
        scanner.nextLine();
    }

    // Affiche les regles des jeux des echecs simplifié
    public static void afficherRegles(Scanner scanner) {
        afficherReglesAscii();
        System.out.println("\n\n   Règles du jeu d'échecs simplifiées :\n");
        System.out.println("   - Chaque joueur commence avec 8 pions, 2 tours, 2 cavaliers, 2 fous, 1 dame et 1 roi.");
        System.out.println("   - Les pions avancent d'une case, mais capturent en diagonale.");
        System.out.println("   - Les tours se déplacent en ligne droite horizontalement ou verticalement.");
        System.out.println("   - Les cavaliers se déplacent en 'L', deux cases dans une direction puis une case perpendiculairement.");
        System.out.println("   - Les fous se déplacent en diagonale sur n'importe quelle distance.");
        System.out.println("   - Les dames se déplacent sur la distance qu'elles souhaitent dans toutes les directions.");
        System.out.println("   - Les rois se déplacent d'une case dans toutes les directions.");
        System.out.println("   - Attention seul le cavalier peut sauter au dessus des autres pièces.");
        System.out.println("   - Le but du jeu est de mettre le roi adverse en échec et mat.");
        System.out.println("\n   Appuyez sur Entrée pour revenir au menu principal...");
        scanner.nextLine();
    }

    // Affiche le titre du jeu en ASCII
    public static void afficherTitre() {
        System.out.println("   ____ _                    __        __  _   _       __  __      ");
        System.out.println("  / ___| |__   ___  ___ ___  \\ \\      / (_) |_| |__   |  \\/  | ___ ");
        System.out.println(" | |   | '_ \\ / _ \\/ __/ __|  \\ \\ /\\ / /| | __| '_ \\  | |\\/| |/ _ \\");
        System.out.println(" | |___| | | |  __/\\__ \\__ \\   \\ V  V / | | |_| | | | | |  | |  __/");
        System.out.println("  \\____|_| |_|\\___||___/___/    \\_/\\_/  |_|\\__|_| |_| |_|  |_|\\___|");
        System.out.println("                                                                    ");
    }

    // Affiche "Crédits" en ASCII
    public static void afficherCreditsAscii() {
        System.out.println("   ____       __     _   _       ");
        System.out.println("  / ___|_ __ /_/  __| (_) |_ ___ ");
        System.out.println(" | |   | '__/ _ \\/ _` | | __/ __|");
        System.out.println(" | |___| | |  __/ (_| | | |_\\__ \\");
        System.out.println("  \\____|_|  \\___|\\__,_|_|\\__|___/");
        System.out.println("                                   ");
    }

    public static void afficherDemoAscii(){
        System.out.println("  _____                       ");
        System.out.println(" |  __ \\                      ");
        System.out.println(" | |  | | ___ _ __ ___   ___  ");
        System.out.println(" | |  | |/ _ \\ '_ ` _ \\ / _ \\ ");
        System.out.println(" | |__| |  __/ | | | | | (_) |");
        System.out.println(" |_____/ \\___|_| |_| |_|\\___/ ");
    }

    // Affiche "Historique" en ASCII
    public static void afficherHistoriqueAscii() {
        System.out.println("  _   _ _     _                           ");
        System.out.println(" | | | (_)___| |_ ___  _ __(_) __ _ _   _  ___");
        System.out.println(" | |_| | / __| __/ _ \\| '__| |/ _` | | | |/ _ \'");
        System.out.println(" |  _  | \\__ \\ || (_) | |  | | (_| | |_| |  __/");
        System.out.println(" |_| |_|_|___/\\__\\___/|_|  |_|\\__, |\\__,_|\\___|");
        System.out.println("                                |_|       ");
    }

    public static void afficherReglesAscii(){
        System.out.println("  _____  __        _           ");
        System.out.println(" |  __ \\ \\_\\      | |          ");
        System.out.println(" | |__) |___  __ _| | ___  ___ ");
        System.out.println(" |  _  // _ \\/ _` | |/ _ \\/ __|");
        System.out.println(" | | \\ \\  __/ (_| | |  __/\\__ \\");
        System.out.println(" |_|  \\_\\___|\\__, |_|\\___||___/");
        System.out.println("             __/ |             ");
        System.out.println("             |___/             ");
    }


    // Affiche "Au revoir" en ASCII
    public static void afficherAuRevoirASCII() {
    System.out.println("     _                                      ");
    System.out.println("    / \\  _   _   _ __ _____   _____ (_)_ __  ");
    System.out.println("   / _ \\| | | | | '__/ _ \\ \\ / / _ \\| | '__|");
    System.out.println("  / ___ \\ |_| | | | |  __/\\ V / (_) | | |    ");
    System.out.println(" /_/   \\_\\__,_| |_|  \\___| \\_/ \\___/|_|_|    ");
    System.out.println("                                             ");
    }

    public static void fakeClear() {
        System.out.println("\n".repeat(50));
    }
    public static void main(String[] args) {
        start();
    }

}

