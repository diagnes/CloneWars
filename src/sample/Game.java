package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Nefast on 26/02/16.
 */
public class Game {
    private String name;
    private int type;
    private int classChoice;
    private int gameLvl;
    private boolean quit;
    private boolean save;
    private Good me;
    private Room room;
    private Save savedGame;

    public Game(String name, int type, int classChoice, int gameLvl){
        this.name = name;
        this.type = type;
        this.classChoice = classChoice;
        this.gameLvl = gameLvl;
        this.setQuit(false);
        this.setSave(false);
        this.setSavedGame(null);
    }

    public Game(Save save){
        this.setName(save.getName());
        this.setType(2);
        this.setClassChoice(save.getType());
        this.setGameLvl(save.getRoomLvl());
        this.setQuit(false);
        this.setSave(false);
        this.setSavedGame(save);
    }

    public Good chooseClass(){
        Good[] type = {
                new Jedi(this.name),
                new Sith(this.name),
                new BountyHunters(this.name),
                new ImperialAgent(this.name),
                new Mercenary(this.name),
                new Jawa(this.name)
        };
        return type[this.classChoice - 1];
    }

    public Good chooseClassSave(){
        Good[] type = {
                new Jedi(this.getSavedGame())
        };
        return type[this.classChoice - 1];
    }

    public void gameQuestion(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isOk = false;
        String type = "";
        try {
            while (!isOk) {
                System.out.println("Voulez vous quitter la partie ? O/N");
                type = br.readLine();
                if (type.matches("[O]")) {
                    this.setQuit(true);
                    isOk = true;
                }
                else if (type.matches("[N]")) {
                    this.setQuit(false);
                    isOk = true;
                }
                else
                    System.out.println("Je n'ai pas compris");
            }
            isOk = false;
            while (!isOk) {
                System.out.println("Voulez vous sauvegarder ? O/N");
                type = br.readLine();
                if (type.matches("[O]")) {
                    this.saveGame();
                    System.out.println("La partie a bien été enregistrer !");
                    isOk = true;
                }
                else if (type.matches("[N]")) {
                    isOk = true;
                }
                else
                    System.out.println("Je n'ai pas compris");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void saveGame(){
        Save save = new Save();
        try {
            save.openWrite();
            System.out.println("Je suis la 1");
            save.save(this.getMe(), this.getRoom(), this.getClassChoice());
            System.out.println("Je suis la 2");
            save.closeWrite();
        } catch (Exception e) {
            System.out.println("Le probleme est : " + e.getMessage());
        }
    }

    public void constructPerso(){
        if (this.type == 1) {
            this.setMe(chooseClass());
        }
        else {
            this.setMe(chooseClassSave());
        }
    }

    public int start(){
        constructPerso();
        this.getMe().getIn();
        while (!this.quit) {
            this.setRoom(new Room(this));
            try {
                this.getRoom().launcher();
                this.setGameLvl(this.getRoom().getRoomLvl());
            }catch (Exception e) {
                System.out.println(e.getMessage());
                gameQuestion();
                if (this.save) {
                    this.saveGame();
                }
            }
        }
        return 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getClassChoice() {
        return classChoice;
    }

    public void setClassChoice(int classChoice) {
        this.classChoice = classChoice;
    }

    public int getGameLvl() {
        return gameLvl;
    }

    public void setGameLvl(int gameLvl) {
        this.gameLvl = gameLvl;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public Good getMe() {
        return me;
    }

    public void setMe(Good me) {
        this.me = me;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Save getSavedGame() {
        return savedGame;
    }

    public void setSavedGame(Save savedGame) {
        this.savedGame = savedGame;
    }
}
