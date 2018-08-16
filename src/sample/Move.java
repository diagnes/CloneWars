package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Nefast on 02/03/16.
 */
public class Move {

    private Room room;
    private Good me;
    private MoveFunction[] move;
    private int moveMake;

    public Move(Room room) throws Exception {
        this.move = new MoveFunction[]{
                this::takeRest,
                this::explore,
                this::previousRoom,
                this::nextRoom,
                this::checkStat,
                this::quit
        };
        this.me = room.getMe();
        this.room = room;
        this.moveMake = 0;

        this.makeMove();
    }

    public void makeMove() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isOk = false;
        String name = "";
        try {
            while (!isOk) {
                System.out.println(this.me.getName() + " que voulez-vous faire ?");
                System.out.println("1 - Se reposer          2 - Explorer");
                if (this.me.getLvl() > this.room.getRoomLvl())
                    System.out.println("3 - Niveau Precedent    4 - Niveau Suivant");
                else
                    System.out.println("3 - Niveau Precedent    4 - Niveau Suivant (Impossible)");
                System.out.println("5 - Mes stats           6 - Quitter");
                name = br.readLine();
                if (name.matches("[1-6]+")){
                    this.moveMake = Integer.parseInt(name);
                    this.move[this.moveMake - 1].move();
                    isOk = true;
                }
                else
                    System.out.println("Je n'ai pas bien compris...");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public void takeRest() throws Exception{
        if (this.me.checkLife()) {
            this.me.rest();
            System.out.println("Vous vous reposez en attendant vos prochaine battaille");
        }else {
            System.out.println("Je pense que vous vous êtes assez reposé...");
        }
    }

    public void checkStat() throws Exception{
        this.me.mainStat();
        this.room.where();
    }

    public void explore() throws Exception{
        System.out.println("Vous explorez les environs...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void nextRoom() throws Exception{
        if(this.me.getLvl() > this.room.getRoomLvl()){
            System.out.println(this.room.getAction()[this.room.getRoomLvl() - 1]);
            this.room.setRoomLvl(this.room.getRoomLvl() + 1);
            this.room.setQuit(true);
        }else {
            System.out.println("Vous devez d'abord remplir votre missions ici !!!");
        }
    }

    public void previousRoom() throws Exception{
        if(this.room.getRoomLvl() - 1 > 0 ){
            System.out.println("Vous revennez sur vos pas...");
            this.room.setRoomLvl(this.room.getRoomLvl() - 1);
            this.room.setQuit(true);
        }else {
            System.out.println("Vous êtes au point de départ");
        }
    }

    public void quit() throws Exception{
        throw new Exception("Vous activez la force, détruisez le vaisseaux et vous envola loin des lieux....");
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Good getMe() {
        return me;
    }

    public void setMe(Good me) {
        this.me = me;
    }

    public MoveFunction[] getMove() {
        return move;
    }

    public void setMove(MoveFunction[] move) {
        this.move = move;
    }

    public int getMoveMake() {
        return moveMake;
    }

    public void setMoveMake(int moveMake) {
        this.moveMake = moveMake;
    }
}
