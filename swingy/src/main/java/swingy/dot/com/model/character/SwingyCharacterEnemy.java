package swingy.dot.com.model.character;

import java.util.Random;

public abstract class SwingyCharacterEnemy {

    protected String eName;
    protected String eInitials;
    protected String eWeapon;
    protected String eArmor;
    protected String eHelm;
    protected int eAttack;
    protected int eDefense;
    protected int eHp;
    protected int ex;
    protected int ey;

    public void setEname(String ename) {
        this.eName = ename;
    }

    public void setEinit(String einit) {
        this.eInitials = einit;
    }

    public void setEdefense(int edefense) {
        this.eDefense = edefense;
    }

    public void setEhp(int ehp) {
        this.eHp = ehp;
    }

    public void setEattack(int eattack) {
        this.eAttack = eattack;
    }

    public void setEx(int ex) {
        this.ex = ex;
    }

    public void setEy(int ey) {
        this.ey = ey;
    }

    public String getEname() {
        return this.eName;
    }

    public String getEinit() {
        return this.eInitials;
    }

    public int getEdefense() {
        return this.eDefense;
    }

    public int getEhp() {
        return this.eHp;
    }

    public int getEattack() {
        return this.eAttack;
    }

    public int getEx() {
        return this.ex;
    }

    public int getEy() {
        return this.ey;
    }

    public String getEweapon() {
        return this.eWeapon;
    }

    public String getEarmor() {
        return this.eArmor;
    }

    public String getEhelm() {
        return this.eHelm;
    }

    protected void characterEnemyStats() {
        this.eAttack = 50 + new Random().nextInt(50);
        this.eDefense = 50 + new Random().nextInt(50);
        this.eHp = 50 + new Random().nextInt(50);
    }
}
