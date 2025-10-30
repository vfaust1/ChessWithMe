package main.historiqueMouvement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import main.Plateau;

public class Mouvement implements Historique {
    ArrayList<String> hist = new ArrayList<>();
    String tempString = "";
    Plateau plateau;

    public Mouvement(Plateau plateau) {
        this.plateau = plateau;
    }

    @Override
    public void add(String move) {
        if(tempString.length() == 2){
            tempString = tempString + move;
            hist.add(tempString);
            tempString = "";
        }else{
            tempString = tempString + move;
        }
    }

    public void emptying(){
        tempString = "";    
    }

    @Override
    public void revert(Plateau plateau) {
        if (!hist.isEmpty()) {
            String lastMove = hist.remove(hist.size() - 1);
        }

    }

    @Override
    public Plateau afficher() {
        ArrayList<String> temp = new ArrayList<>();

        for (int i = hist.size() - 1; i >= 0; i--) {
            temp.add(hist.get(i));
        }
        System.out.print("\033[2J");
        System.out.flush();
        
        System.out.println("Historique des mouvements :");
        for (String move : hist) {
            System.out.println("" + move.charAt(0) +  move.charAt(1) + "->" + move.charAt(2) + move.charAt(3));
        }
        System.out.println("Appuyez sur Entrée pour continuer...");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return plateau;
    }
    
}
