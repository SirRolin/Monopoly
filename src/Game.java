import model.Player;
import util.FileIO;
import util.TextUI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//Todo: - Rename class to Game,
//      - Change fields and methodnames so that it reflects precisely the Game class in the class diagram
//      - Add the setup method:
//      a. loads or prompts for gamedata,
//      b. creates the model.Player objects
//      (reuse the code from the main method)

public class Game {
    private int maxPlayers;
    private TextUI ui;
    private FileIO io;
    private ArrayList<Player> Players = new ArrayList<>();
    public Game(int maxPlayers){
        this.maxPlayers = maxPlayers;
    }


    //todo: this method is no longer needed. Remove

    public Player registerPlayer(String name, int amount){
        Player c = new Player(name);
        Players.add(c);
        return c;
    }

    public void setup() {

        int count = 0;

        FileIO io = new FileIO();
        ArrayList<String> data = io.readGameData("src/data.csv");

        if (data.size() > 0) {


            for (String s : data) {
                String[] line = s.split(",");
                String name = line[0];
                int balance = Integer.parseInt(line[1].trim());
                Player c = registerPlayer(name, 4);
                c.receiveAmount(balance);
            }

        }
    }
    private void endGame(String path, ArrayList<Player> players) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(path);

                writer.write("name, balance \n");

                for (Player c: players) {
                    writer.write(c.getName()+","+c.getBalance()+"\n");
                }


                writer.close();



            }catch(IOException e){


            }

        }



    public void displayPlayers(){
        for (Player c: Players) {
            System.out.println(c);
        }
    }
    public Player getPlayer(int i){
       return Players.get(i);
    }
    public ArrayList<Player> getPlayers(){
        return Players;
    }
    //todo: add endGame method
}
