package main;


public class Bot {
    private Stockfish engine;

    public Bot() throws Exception {
        // adapte le chemin selon ton OS
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            engine = new Stockfish("./engines/stockfish.exe");
        } else if (os.contains("mac")) {
            engine = new Stockfish("./engines/stockfish-mac");
        } else {
            engine = new Stockfish("./engines/stockfish-linux");
        }
    }

    public String jouer(Plateau plateau) throws Exception {
        String fen = plateau.toFEN();
        String bestMove = engine.getBestMove(fen, 15); // profondeur 15
        return bestMove;
    }

    public void close() throws Exception {
        engine.close();
    }
}
