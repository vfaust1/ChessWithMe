package main;
import java.util.Scanner;
public class Chess{
    public static void main(String[] args) {
        Plateau plateau = new Plateau();

        plateau.getPlateau()[1][0].move(plateau, new int[] { 1, 0 }, new int[] { 2, 0 });
        System.out.println(plateau);
        while(true){
            Joueur j1 = new Joueur("a",Couleur.BLANC);
            j1.demanderDeplacement(plateau, null, null);
            System.out.println(plateau);
        }
        
    }

    public String choixDeplacement() {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            try {
                System.out.println("Choisissez une pièce à déplacer (ex: a2 ou 2a): ");
                input = scanner.nextLine().trim();

                // Vérification longueur exacte = 2
                if (input.length() != 2) {
                    throw new IllegalArgumentException("Format de position invalide. Utilisez le format 'a2'.");
                }

                char c1 = input.charAt(0);
                char c2 = input.charAt(1);

                boolean c1IsLetter = (c1 >= 'a' && c1 <= 'h') || (c1 >= 'A' && c1 <= 'H');
                boolean c1IsDigit  = (c1 >= '1' && c1 <= '8');

                boolean c2IsLetter = (c2 >= 'a' && c2 <= 'h') || (c2 >= 'A' && c2 <= 'H');
                boolean c2IsDigit  = (c2 >= '1' && c2 <= '8');

                // Vérifie qu'il y a exactement 1 lettre et 1 chiffre
                if ((c1IsLetter && c2IsDigit) || (c1IsDigit && c2IsLetter)) {
                    System.out.println("Entrée valide : " + input);
                    return input;
                } else {
                    throw new IllegalArgumentException("Position invalide. Utilisez une position comme 'a2' ou '2a'.");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                // boucle continue → redemande à l’utilisateur
            }
        }
    }


}