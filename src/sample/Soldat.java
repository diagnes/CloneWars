package sample;

import java.util.Random;

/**
 * Created by Nefast on 26/02/16.
 */
public class Soldat extends Enemmy implements HasNoForce {

    // Construction de la base de notre Class Clone
    public Soldat(int lvl){
        this.setHp(100 + ((lvl - 1) * 25));
        this.setDef(150 + ((lvl - 1) * 15));
        this.setPower(125 + ((lvl - 1) * 10));
        this.setForce(0);
        this.setIntelligence(125 + ((lvl - 1) * 20));
        this.setLvl(lvl);
        this.setXp(0);
    }

    public int armedAttack(Personnage ennemy){
        int newHp;
        int atck;
        System.out.println("Le Soldat attaque avec son arme : ");

        atck = (int)(this.getIntelligence() - ennemy.getDef() * 0.4);
        if (atck <= 0){
            atck = 3;
            System.out.println("Mais cela n'est pas trés efficace ");
        }
        newHp = (atck <= 0) ? ennemy.getHp() : (ennemy.getHp() - atck <= 0) ? 0 : ennemy.getHp() - atck;
        ennemy.setHp(newHp);

        System.out.println("il vous inflige " + atck + "point de dégat");
        return 1;
    }

    public int physicalAttack(Personnage ennemy){
        int newHp;
        int atck;
        System.out.println("Le Soldat vous charge : ");

        atck = (int)(this.getPower() - ennemy.getDef() * 0.4);
        if (atck <= 0){
            atck = 3;
            System.out.println("Mais cela n'est pas trés efficace ");
        }
        newHp = (atck <= 0) ? ennemy.getHp() : (ennemy.getHp() - atck <= 0) ? 0 : ennemy.getHp() - atck;
        ennemy.setHp(newHp);

        System.out.println("il vous inflige " + atck + "point de dégat");
        return 1;
    }


    public void inFight() {
        System.out.println("Un Soldat lvl" + this.getLvl() + "  apparait");
    }

    public int attack(Personnage me) {
        Soldat ennemy = this;
        Random rand = new Random();
        int randNum = rand.nextInt(30);
        Attack[] attacks = new Attack[] {
                ennemy::armedAttack,
                ennemy::physicalAttack,
        };

        for (int i = 0; i < attacks.length; i++) {
            if (i == randNum % 2){
                attacks[i].attack(me);
                return 1;
            }
        }
        return 0;
    }
}
