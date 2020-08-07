package logs;

import defaults.FilesPath;
import model.Game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameEventLogs {
    private Game game;

    public GameEventLogs(Game game) {
        this.game = game;
        creatLogFile();
    }

    public void creatLogFile() {
        String[] logHeader = new String[2];
        try {
            Writer writer = new FileWriter(
                    FilesPath.GAME_EVENT_LOGS_PATH + "/battle#" + game.getID() + ".log");
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            logHeader[0] = "BATTLE_ID:" + game.getID();
            logHeader[1] = "CREATED_AT:" + dateFormat.format(date);
            for (int i = 0; i < logHeader.length; i++) {
                writer.write(logHeader[i] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToLogBody(int playerID,String event, String eventDescription) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(FilesPath.GAME_EVENT_LOGS_PATH + "/battle#" + game.getID() + ".log", true));
            writer.newLine();
            writer.write("player#"+playerID+":\"" + event + "\" @ " + dateFormat.format(date) + " ---> " + eventDescription);
            writer.close();
        } catch (Exception e) {

        }
    }
}
