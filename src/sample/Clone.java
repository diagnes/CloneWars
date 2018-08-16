package sample;

import java.util.Random;

/**
 * Created by Nefast on 26/02/16.
 */
public class Clone extends Enemmy implements HasNoForce {

    // Construction de la base de notre Class Clone
    public Clone(int lvl){
        this.setHp(100 + ((lvl - 1) * 10));
        this.setDef(100 + ((lvl - 1) * 5));
        this.setPower(100 + ((lvl - 1) * 5));
        this.setForce(0);
        this.setIntelligence(100 + ((lvl - 1) * 5));
        this.setLvl(lvl);
        this.setXp(0);
    }

    public int statLvlUp(int lvlb, int lvla){
        this.setHp(this.getHp() + (10 * (lvla - lvlb)));
        this.setDef(this.getDef() + (5 * (lvla - lvlb)));
        this.setPower(this.getPower() + (5 * (lvla - lvlb)));
        this.setIntelligence(this.getIntelligence() + (5 * (lvla - lvlb)));
        this.setLvl(lvla);

        return 1;
    }

    public int armedAttack(Personnage ennemy){
        int newHp;
        int atck;
        System.out.println("Le Clone attaque avec son arme : ");

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
        System.out.println("Le Clone vous charge : ");

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
        System.out.println("Un clone lvl" + this.getLvl() + "  apparait");
    }

    public int attack(Personnage me) {
        Clone ennemy = this;
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
