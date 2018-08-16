package sample;

import java.util.Random;

/**
 * Created by Nefast on 26/02/16.
 */
public class Room {

    private int roomLvl;
    private Good me;
    private Enemmy ennemy;
    private Factory generate;
    private boolean quit;
    private String[] history;
    private String[] unlock;
    private String[] action;
    private Game game;
    private Move move;

    public Room(Game game){
        this.ennemy = null;
        this.roomLvl = game.getGameLvl();
        this.me = game.getMe();
        this.quit = false;
        this.game = game;
        this.history = new String[]{
                "Vous etes enfin arrivé.\nLe garde que vous avez menacé vous a dit que le trésor n'etait pas ici mais dans le vaiseaux RP19.\nTrouvez la carte permettant d'acceder au teleporteur\nPour acceder au vaisseaux RP19.",
                "Pas de trésor a part des ennemis.\nVous ecoutez une conversation le trésor a été déplacé au vaisseaux mère.\nTrouvez la carte permettant d'acceder au teleporteur\nPour acceder au vaisseaux mére.",
                "Le vaisseau mére est remplis d'ennemis et voila le trésor " + this.me.getName() + ". Trouver les clés du coffre pour obtenir un pouvoir extraordinaire",
                "Vous passez au vaisseau suivant por essayer vos nouveaux pouvoir"
        };
        this.unlock = new String[]{
                "La carte du vaisseau RP19 que vous cherchiez tant vien d'apparaitre... Il est peut être temps de continuer",
                "La carte du vaisseau mere est enfin entre vos main... Le trésor est a portée de main",
                "Vous arrachez les clefs du coffre a vos ennemis... Foncez recuperer vos véritable pouvoir",
                "Plus personne ne peux rien contre vous ici..."
        };
        this.action = new String[]{
                "Vous utilisez la carte pour acceder au Vaisseau RP19",
                "Vous utilisez la carte pour acceder au Vaisseau mère",
                "Vous utilisez les clefs pour ouvrir le coffre... Une puissance incroyable vous envahit",
                "Vous soufflez un grand coup, vous accedez au vaisseau " + this.getRoomLvl()
        };
        if (this.roomLvl == this.me.getLvl())
            this.start();
    }


    public void start(){
        if (this.roomLvl < this.history.length){
            System.out.println(this.history[this.roomLvl - 1]);
        }else{
            System.out.println(this.history[history.length - 1]);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkState(){
        if (this.getRoomLvl() < this.me.getLvl()){
            System.out.println(this.unlock[this.getRoomLvl()]);
        }
    }

    public int launcher() throws Exception{
        this.generate = new Factory(this.getRoomLvl());

        while (!this.quit){
            try {
                this.move = new Move(this);
                if (this.move.getMoveMake() == 2) {
                    this.ennemy = this.generate.createEnnemy();
                    this.ennemy.inFight();
                    this.me.inFight();
                    while (!this.ennemy.defeat() && !this.me.defeat()) {
                        if (!this.me.defeat())
                            this.me.attack(ennemy);
                        if (!this.ennemy.defeat())
                            this.ennemy.attack(this.me);
                    }
                    if (!this.me.defeat()) {
                        this.me.lvlUp(ennemy);
                        this.checkState();
                    } else {
                        throw new Exception("Vous avez été vaincu...");
                    }
                }
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
        return 1;
    }

    public void where(){
        System.out.println("Vous êtes au niveau " + this.getRoomLvl());
        System.out.println(this.history[this.getRoomLvl()]);
    }

    public int getRoomLvl() {
        return roomLvl;
    }

    public void setRoomLvl(int roomLvl) {
        this.roomLvl = roomLvl;
    }

    public Good getMe() {
        return me;
    }

    public void setMe(Good me) {
        this.me = me;
    }

    public Enemmy getEnnemy() {
        return ennemy;
    }

    public void setEnnemy(Enemmy ennemy) {
        this.ennemy = ennemy;
    }

    public Factory getGenerate() {
        return generate;
    }

    public void setGenerate(Factory generate) {
        this.generate = generate;
    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public String[] getHistory() {
        return history;
    }

    public void setHistory(String[] history) {
        this.history = history;
    }

    public String[] getUnlock() {
        return unlock;
    }

    public void setUnlock(String[] unlock) {
        this.unlock = unlock;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String[] getAction() {
        return action;
    }

    public void setAction(String[] action) {
        this.action = action;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
