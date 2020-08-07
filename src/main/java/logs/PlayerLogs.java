package logs;

import defaults.FilesPath;
import model.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerLogs {

    public static void creatLogFile(Player player) {
        String[] logHeader = new String[3];
        try {
            Writer writer = new FileWriter(
                    FilesPath.PLAYER_LOGS_PATH + "/" + player.getUserName() + "-"
                            + String.valueOf(player.getId()) + ".log");
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            logHeader[0] = "USER:" + player.getUserName();
            logHeader[1] = "CREATED_AT:" + dateFormat.format(date);
            logHeader[2] = "PASSWORD:" + player.getPassword();
            for (int i = 0; i < logHeader.length; i++) {
                writer.write(logHeader[i] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addToLogBody(String event, String eventDescription, Player player) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(FilesPath.PLAYER_LOGS_PATH + "/" + player.getUserName() + "-"
                            + String.valueOf(player.getId()) + ".log", true));
            writer.newLine();
            writer.write("\"" + event + "\" @ " + dateFormat.format(date) + " ---> " + eventDescription);
            writer.close();
        } catch (Exception e) {

        }
    }
}
