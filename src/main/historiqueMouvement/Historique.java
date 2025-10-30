package main.historiqueMouvement;

import main.Plateau;

public interface Historique {
    void add(String move);
    void revert(Plateau plateau);
    void emptying();
    Plateau afficher();
}
