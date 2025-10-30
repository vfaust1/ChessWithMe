package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.Joueur;

/* Classe qui va permettre l'importation des données basées sur les formulaires des élèves */
public class Gestion {
    private static final String PATH = System.getProperty("user.dir")+File.separator+"groupe-15"+File.separator+"res"+File.separator; 
    private static final String SOURCE = "import.csv";
    // private static final String EXPORT = "export.csv";
    private static final String SERIAL_PATH = "historique.json"; 

    /* Méthode static permettant l'importation des données des joueurs*/
    public static ArrayList<String> importData(){
        ArrayList<String> tableImport = new ArrayList<String>();
        try(Scanner scan = new Scanner(new File(PATH+SOURCE))){
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                tableImport.add(line);
            }
        }catch(FileNotFoundException fnfe){
            System.err.println("Fichier introuvable : "+fnfe);
        }catch(Exception e){
            System.err.println("Une produite s'est produite : "+e);
        }
        return tableImport; 
    }

    /* Affichage d'une ArrayList */
    public static <T> void displayArrayList(ArrayList<T> liste){
            for(T t : liste){
                System.out.println(t);
            }
    }
    

    public static void exportHistorique(HistoriqueJ h){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH+SERIAL_PATH))) {
            oos.writeObject(h);
        }catch (IOException ioe){
            System.err.println("Erreur pendant la sauvegarde : "+ioe);
        }
    }

    public static HistoriqueJ importHistorique(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH+SERIAL_PATH))){
            return (HistoriqueJ) ois.readObject(); 
        } catch(IOException ioe){
            System.err.println("Erreur pendant l'importation des données : "+ioe);
        }catch(ClassNotFoundException ce){
            System.err.println("Aucune correspondance trouvé : "+ce);
        }
        return null; 
    }

    // public static void main(String[] args) {
    //     ArrayList<String> tableImport = Gestion.importData(); 
    //     // Gestion.displayArrayList(tableImport);
    //     ArrayList<Student> studentsList= Gestion.createStudents(tableImport); 
    //     // Gestion.displayArrayList(studentsList);
    //     Groupe france = Gestion.createGroup(studentsList, Country.FRA); 
    //     Groupe italie = Gestion.createGroup(studentsList, Country.ITA);

    //     Groupe allemagne = Gestion.createGroup(studentsList, Country.ALL);
    //     Gestion.displayArrayList(allemagne.getStudentsList());
        
    //     Groupe espagne = Gestion.createGroup(studentsList, Country.ESP);
    //     Gestion.displayArrayList(espagne.getStudentsList());

    //     // System.out.println("-------------");
    //     // System.out.println(france.toStringAll());
    //     // System.out.println("====");
    //     // System.out.println(italie.toStringAll());
        
    //     Voyage voyage= new Voyage(LocalDate.now(), allemagne, espagne);
    //     voyage.affectationCalcul();
        
    //     System.out.println("---------------");
    //     System.out.println(voyage.getAffectations().toString());
    //     Gestion.exportData(voyage.getAffectations());

        
    //     Voyage voyage2 = new Voyage(LocalDate.now(), italie, italie);

    //     ArrayList<Voyage> listeVoyages = new ArrayList<>(); 
    //     listeVoyages.add(voyage);
    //     listeVoyages.add(voyage2); 

    //     Historique h = new Historique(listeVoyages); 
    //     System.out.println(h.toString());
    //     Gestion.exportHistorique(h); 
    //     System.out.println("====");
    //     Historique h2 = Gestion.importHistorique();
    //     System.out.println(h2.toString());
    // }

}