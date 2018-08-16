package sample;

import java.util.Random;

/**
 * Created by Nefast on 02/03/16.
 */
public class Factory {

    private int lvlRoom;

    public Factory(int lvlRoom) {
        this.lvlRoom = lvlRoom;
    }

    public int chance(int rand){
        if (rand % 5 == 0)
            return 2;
        else if (rand % 2 == 0)
            return 1;
        return 0;
    }

    public Enemmy createEnnemy(){
        Random rand = new Random();
        int randNum = rand.nextInt(50);
        int randLvl = rand.nextInt(3) + this.lvlRoom;

        Enemmy[] type = {
                new Clone(randLvl),
                new Droid(randLvl),
                new Soldat(randLvl)
        };
        randNum = chance(randNum);
        return type[randNum];
    }

    public Personnage createBoss(){
        Random rand = new Random();
        int randNum = rand.nextInt(3);
        Personnage[] type = {
                new Clone(this.lvlRoom),
                new Droid(this.lvlRoom),
                new Soldat(this.lvlRoom)
        };
        return type[randNum];
    }

    public int getLvlRoom() {
        return lvlRoom;
    }

    public void setLvlRoom(int lvlRoom) {
        this.lvlRoom = lvlRoom;
    }
}
