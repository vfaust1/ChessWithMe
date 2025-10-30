package main;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriqueJ implements Serializable{
    /*Attributs */
        private ArrayList<Joueur> ancienJoueurs; 
    
    /*Constructeurs */
        public HistoriqueJ(ArrayList<Joueur> j){
            this.ancienJoueurs = j ; 
        }
        public HistoriqueJ(){
            this(new ArrayList<Joueur>()); 
        }

    /*getter */
        public ArrayList<Joueur> getAllJoueurs(){
            return this.ancienJoueurs; 
        }
        public String toString(){ 
            return this.ancienJoueurs.toString(); 
        }
        public boolean equals(Object obj){
            if (this== obj){return true;} 
            if (obj==null) {return false;}
            if(this.getClass()!= obj.getClass()){return false;}
            HistoriqueJ other = (HistoriqueJ)obj ; 
            if(this.ancienJoueurs != other.ancienJoueurs){return false;}
            return true; 
        }
}
