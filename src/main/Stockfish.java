package main;

import java.io.*;

public class Stockfish{
    private Process engineProcess;
    private BufferedReader processReader;
    private BufferedWriter processWriter;

    public Stockfish(String pathToEngine) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(pathToEngine);
        pb.redirectErrorStream(true);
        engineProcess = pb.start();

        processReader = new BufferedReader(new InputStreamReader(engineProcess.getInputStream()));
        processWriter = new BufferedWriter(new OutputStreamWriter(engineProcess.getOutputStream()));

        sendCommand("uci");
        waitFor("uciok");
    }

    private void sendCommand(String command) throws IOException {
        processWriter.write(command + "\n");
        processWriter.flush();
    }

    private void waitFor(String expected) throws IOException {
        String line;
        while ((line = processReader.readLine()) != null) {
            if (line.contains(expected)) break;
        }
    }

    public String getBestMove(String fen, int depth) throws IOException {
        sendCommand("position fen " + fen);
        sendCommand("go depth " + depth);
        String line;
        while ((line = processReader.readLine()) != null) {
            if (line.startsWith("bestmove")) {
                return line.split(" ")[1]; // ex: "e2e4"
            }
        }
        return null;
    }

    public void close() throws IOException {
        sendCommand("quit");
        processReader.close();
        processWriter.close();
        engineProcess.destroy();
    }
}