package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by Nefast on 26/02/16.
 */
public class Jedi extends Good implements HasForce {

    // Construction de la base de notre Class Jedi
    public Jedi(String name){
        this.setName(name);
        this.setHp(100);
        this.setDef(200);
        this.setPower(100);
        this.setForce(200);
        this.setIntelligence(200);
        this.setLvl(1);
        this.setXp(0);
    }

    public Jedi(Save game){
        this.setName(game.getName());
        this.setHp(100 + (20 * (game.getLvl() - 1)));
        this.setDef(200 + (5 * (game.getLvl() - 1)));
        this.setPower(100 + (5 * (game.getLvl() - 1)));
        this.setForce(200 + (5 * (game.getLvl() - 1)));
        this.setIntelligence(200 + (5 * (game.getLvl() - 1)));
        this.setLvl(game.getLvl());
        this.setXp(game.getXp());
    }

    public void getIn() {
        if (this.getXp() == 0)
            System.out.println("Bienvenue valeureux Jedi " + this.getName() + ". L'aventure peux enfin commencé");
        else
            System.out.println("Bienvenue valeureux Jedi " + this.getName() + ". Votre aventure peux continuer");
    }



    public int statLvlUp(int lvlb, int lvla){
        this.setHp(this.getHp() + (20 * (lvla - lvlb)));
        this.setDef(this.getDef() + (5 * (lvla - lvlb)));
        this.setPower(this.getPower() + (15 * (lvla - lvlb)));
        this.setIntelligence(this.getIntelligence() + (10 * (lvla - lvlb)));
        this.setForce(this.getForce() + (20 * (lvla - lvlb)));
        this.setLvl(lvla);
        System.out.println("- Hp : " + this.getHp() + "HP");
        System.out.println("- Defense : " + this.getDef());
        System.out.println("- Force : " + this.getForce());
        System.out.println("- Power : " + this.getPower());
        System.out.println("- Intelligence : " + this.getIntelligence());
        return 1;
    }

    public int physicalAttack(Personnage ennemy){
        int newHp;
        int atck;
        System.out.println("Vous chargez votre adversaire : ");

        atck = (int)(this.getPower() - ennemy.getDef() * 0.4);
        newHp = (atck <= 0) ? ennemy.getHp() : (ennemy.getHp() - atck <= 0) ? 0 : ennemy.getHp() - atck;
        ennemy.setHp(newHp);

        System.out.println("Vous infligez " + atck + "point de dégat");
        return 1;
    }

    public void mainStat(){
        int newLvl = (int)((50/3)*(Math.pow(this.getLvl(),3) - (6 * Math.pow(this.getLvl(),2)) + (17 * this.getLvl())) * 2);
        System.out.println("- Name : " + this.getName());
        System.out.println("- Level : " + this.getLvl());
        System.out.println("- Hp : " + this.getHp() + "HP");
        System.out.println("- Defense : " + this.getDef());
        System.out.println("- Power : " + this.getPower());
        System.out.println("- Force : " + this.getForce());
        System.out.println("- Intelligence : " + this.getIntelligence());
        System.out.println("- XP : " + this.getXp());
        System.out.println("- XP Niveau Suivant : " + newLvl);
    }

    public int statAttack(Personnage ennemy){
        System.out.println("- Name : " + this.getName());
        System.out.println("- Level : " + this.getLvl());
        System.out.println("- Hp : " + this.getHp() + "HP");
        System.out.println("- Defense : " + this.getDef());
        System.out.println("- Power : " + this.getPower());
        System.out.println("- Force : " + this.getForce());
        System.out.println("- Intelligence : " + this.getIntelligence());
        System.out.println("- XP : " + this.getXp());
        this.attack(ennemy);
        return 1;
    }

    public int ennemyStat(Personnage ennemy){
        System.out.println("- Level : " + ennemy.getLvl());
        System.out.println("- Hp : " + ennemy.getHp() + "HP");
        System.out.println("- Defense : " + ennemy.getDef());
        System.out.println("- Power : " + ennemy.getPower());
        System.out.println("- Force : " + ennemy.getForce());
        System.out.println("- Intelligence : " + ennemy.getIntelligence());
        return 1;
    }

    public int forceAttack(Personnage ennemy){
        int atck;
        int newHp;

        System.out.println("Vous attaquez avec la force : ");

        atck = (int)((this.getForce() + this.getIntelligence()) * 0.7) - (int)((ennemy.getDef() + ennemy.getIntelligence()) * 0.3);
        newHp = (ennemy.getHp() - atck <= 0) ? 0 : ennemy.getHp() - atck ;
        ennemy.setHp(newHp);

        System.out.println("Vous infligez " + atck + "point de dégat");
        return 1;
    }



    public void inFight() {
        System.out.println("Vous engagé le combat");
    }

    public void rest() {
        this.setHp(97 + (3 * this.getLvl()));
    }

    public boolean checkLife() {
        return (this.getHp() < (100 + ((this.getLvl() - 1) * 20)));
    }

    public int checkCommand(int taille) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String regex = "[0-9]";
        boolean isOk = false;
        String attack = "";

        try {
            while (!isOk) {
                System.out.println("Choisissez le numéro d'une attaque :");
                System.out.println("1 - ForceAttack       2 - PhysicalAttack");
                System.out.println("3 - Stat              4 - Enemmy Stat");
                attack = br.readLine();
                if (attack.matches(regex))
                    if (taille >= Integer.parseInt(attack) && Integer.parseInt(attack) > 0)
                        isOk = true;
                    else
                        System.out.println("L'attaque n'existe pas..");
                else
                    System.out.println("Veuiller taper le numéro d'une attaque");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(attack) - 1;
    }

    public int attack(Personnage ennemy) {
        Jedi me = this;
        Attack[] attacks = new Attack[] {
                me::forceAttack,
                me::physicalAttack,
                me::statAttack,
                me::ennemyStat,
        };
        try {
            attacks[checkCommand(attacks.length)].attack(ennemy);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return 0;
    }


    public int action(Personnage ennemy){
        this.attack(ennemy);
        return 1;
    }

}
