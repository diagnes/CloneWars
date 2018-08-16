package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Menu {

    private String[] classChoice;

    public Menu(){
        this.setClassChoice(new String[]{"Jedi", "Sith", "BountyHunters", "ImperialAgent", "Mercenary","Jawa"});
    }

    public String checkName(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isOk = false;
        String name = "";
        try {
            while (!isOk) {
                System.out.println("Entrez votre non de combattant");
                name = br.readLine();
                if (name.matches("[a-zA-Z]+"))
                    isOk = true;
                else
                    System.out.println("Entrez votre nom sans accent ou chiffre seulement des lettres !!!");
            }
            System.out.println("Bienvenue " + name);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return name;
    }

    public void listClass(){

        System.out.println("Ils possedent la force");
        System.out.println("1 - Jedi             2 - Sith");
        System.out.println("Ils sont de grand combatant");
        System.out.println("3 - BountyHunters    4 - ImperialAgent");
        System.out.println("5 - Mercenary        6 - Jawa");
    }

    public void choosenClass(int choice){
        System.out.println("Vous avez choisis la class des " + classChoice[choice - 1]);
    }

    public int checkClass(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isOk = false;
        String name = "";
        try {
            System.out.println("Maintenant choisis ta classe de combattant");
            while (!isOk) {
                listClass();
                name = br.readLine();
                if (name.matches("[0-9]"))
                    if (this.getClassChoice().length >= Integer.parseInt(name) && Integer.parseInt(name) > 0)
                        isOk = true;
                    else
                        System.out.println("La class demandé n'existe pas ");
                else
                    System.out.println("Entrer le numero de la class svp...");
            }
            choosenClass(Integer.parseInt(name));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(name);
    }

    public int checkSave(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isOk = false;
        String type = "";
        try {
            System.out.println("Bienvenue sur Clone Wars");
            while (!isOk) {
                System.out.println("1 - Nouvelle partie     2 - Continuer");
                type = br.readLine();
                if (type.matches("[1-2]"))
                    isOk = true;
                else
                    System.out.println("Entrer le numero de la commande svp...");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(type);
    }

    public Game loadGame() throws Exception {
        Save game = new Save();
        String[] split;
        Game loaded = null;

        game.open();
        if (game.load())
            loaded = new Game(game);
        else
            throw new Exception("Aucune partie n'a été trouvé");
        game.close();
        return loaded;
    }

    public void start() {
        int type;
        Game game;
        try {
            type = this.checkSave();
            if (type == 1) {
                game = new Game(this.checkName(), type, this.checkClass(), 1);
            }else{
                game = this.loadGame();
            }
            game.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String[] getClassChoice() {
        return classChoice;
    }

    public void setClassChoice(String[] classChoice) {
        this.classChoice = classChoice;
    }
}
