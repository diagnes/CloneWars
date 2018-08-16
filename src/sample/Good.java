package sample;

/**
 * Created by Nefast on 02/03/16.
 */
public abstract class Good extends Personnage{
    public abstract int statLvlUp(int lvlb, int lvla);
    public abstract void rest();
    public abstract void mainStat();
    public abstract void getIn();
    public abstract boolean checkLife();

    public int allStat(Personnage ennemy){
        return ennemy.getIntelligence() + ennemy.getPower() + ennemy.getDef() + ennemy.getForce() + getHp();
    }

    public int lvlUp(Personnage ennemy){
        int xp = this.getXp();
        int lvl = this.getLvl();
        int newLvl = (int)((50/3)*(Math.pow(lvl,3) - (6 * Math.pow(lvl,2)) + (17 * lvl)) * 2);
        int xpGain = (int)(this.allStat(ennemy) / 5);
        int newXp = xp + xpGain;

        if (newXp >= newLvl){
            this.setLvl(lvl + 1);
            this.setXp(newXp - newLvl);
            System.out.println("Vous gagnez " + xpGain + "xp");
            System.out.println("Vous avez atteint le niveau " + this.getLvl());
            this.statLvlUp(this.getLvl() - 1, this.getLvl());
        }else{
            this.setXp(newXp);
            System.out.println("Vous gagnez " + xpGain + "xp");
        }
        return 1;
    }
}
