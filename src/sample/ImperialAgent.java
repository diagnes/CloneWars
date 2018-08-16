package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Nefast on 26/02/16.
 */
public class ImperialAgent extends Good implements HasNoForce {

    // Construction de la base de notre Class ImperialAgent
    public ImperialAgent(String name){
        this.setName(name);
        this.setHp(350);
        this.setDef(100);
        this.setPower(100);
        this.setForce(0);
        this.setIntelligence(100);
        this.setLvl(1);
        this.setXp(0);
    }

    public ImperialAgent(Save game){
        this.setName(game.getName());
        this.setHp(350 + (40 * (game.getLvl() - 1)));
        this.setDef(100 + (5 * (game.getLvl() - 1)));
        this.setPower(100 + (5 * (game.getLvl() - 1)));
        this.setIntelligence(100 + (5 * (game.getLvl() - 1)));
        this.setLvl(game.getLvl());
        this.setXp(game.getXp());
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

    @Override
    public void getIn() {
        if (this.getXp() == 0)
            System.out.println("Bienvenue valeureux ImperialAgent " + this.getName() + ". L'aventure peux enfin commencé");
        else
            System.out.println("Bienvenue valeureux ImperialAgent " + this.getName() + ". Votre aventure peux continuer");
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

    public int armedAttack(Personnage ennemy){
        int atck;
        int newHp;

        System.out.println("Vous attaquez avec toute la puissance de votre arme : ");

        atck = (int)(this.getIntelligence() - ennemy.getDef() * 0.3);
        if (atck <= 0){
            atck = 3;
            System.out.println("Mais cela n'est pas trés efficace ");
        }
        newHp = (atck <= 0) ? ennemy.getHp() : (ennemy.getHp() - atck <= 0) ? 0 : ennemy.getHp() - atck;
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
                System.out.println("1 - ArmedAttack       2 - PhysicalAttack");
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
        ImperialAgent me = this;
        Attack[] attacks = new Attack[] {
                me::armedAttack,
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
}
