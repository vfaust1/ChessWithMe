# ChessWithMe

ChessWithMe est un petit moteur/interface d'échecs en Java conçu pour jouer en console sur Linux.

Le projet propose :
- une représentation du plateau et des pièces,
- une logique de déplacement des pièces et de validation basique,
- un menu et une boucle de jeu pour jouer à deux joueurs ou contre un bot,
- une intégration minimale avec Stockfish (via exécution d'un binaire externe) pour obtenir un meilleur coup.

Ce README explique comment compiler et lancer le projet, la structure du dépôt et des conseils pour utiliser Stockfish sur votre machine.

## Prérequis

- Java 8 ou supérieur installé (JDK). Assurez-vous que `javac` et `java` sont disponibles dans votre PATH.
- (Optionnel) Un binaire Stockfish compatible avec votre OS si vous voulez activer le Bot fort. Le dépôt contient `engines/stockfish-linux` mais sur Windows vous devrez télécharger la version Windows et mettre à jour le chemin.

## Structure du dépôt

- `src/main/` : code source Java
  - `ChessWithMe.java` : point d'entrée principal avec boucle de jeu et option bot
  - `Plateau.java` : représentation du plateau, FEN et application de coups UCI
  - `Stockfish.java` : wrapper minimal pour communiquer avec un binaire Stockfish via processus
  - `Bot.java` : (présent dans le projet) logique qui utilise Stockfish pour choisir un coup
  - `piece/` : classes des pièces (`Piece.java`, `Roi.java`, `Dame.java`, `Tour.java`, etc.)
  - `historiqueMouvement/` : historique et mouvements
- `engines/` : emplacement prévu pour les moteurs externes (Stockfish binaire)
- `test/` : tests unitaires (ex : `ChessWitMeTest.java` utilisant JUnit 5)
- `doc/` : documentation et sprints

## Compiler le projet

Dans une invite PowerShell, positionnez-vous à la racine du projet (`ChessWithMe`) puis :

```powershell
# Compiler tous les fichiers Java et placer les .class dans un dossier `out`
mkdir out; javac -d out -sourcepath src $(Get-ChildItem -Recurse -Filter "*.java" -Path src | ForEach-Object { $_.FullName })
```

Remarque : la commande ci-dessus utilise une substitution PowerShell pour compiler tous les fichiers `.java` récursivement. Sur d'autres shells (bash) la syntaxe diffère.

## Exécuter le jeu

Après compilation vous pouvez lancer la classe principale `main.ChessWithMe` :

```powershell
java -cp out main.ChessWithMe
```

Le programme démarre en mode console, demande les pseudos et la couleur, puis affiche le plateau. Vous pouvez jouer en entrant des coordonnées de la forme `a2` ou `2a`.

## Tests

Le projet contient un test JUnit 5 `test/ChessWitMeTest.java`. Pour exécuter les tests localement, installez JUnit 5 et exécutez les tests via votre IDE ou via la ligne de commande avec un runner (ex : Maven/Gradle) si vous en avez configuré un. Il n'y a pas de configuration de build automatique (pom.xml/gradle) fournie dans le dépôt.