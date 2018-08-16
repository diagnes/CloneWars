package sample;

/**
 * Created by Nefast on 26/02/16.
 */
public abstract class Personnage {

    //Déclaration des attributs de stat


    private String name;
    private int hp;
    private int def;
    private int power;
    private int force;
    private int intelligence;
    private int lvl;
    private int xp;

    //Construct de Personnage

    public abstract int attack(Personnage perso);
    public abstract void inFight();
    public abstract int physicalAttack(Personnage ennemy);

    //Declaration fonction du personnage


    public boolean defeat(){
        return (this.getHp() <= 0);
    }

    //Déclaration des Getters et Setters

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
