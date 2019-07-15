package swingy.dot.com.model.enemies;

import swingy.dot.com.model.character.SwingyCharacterEnemy;

public abstract class SwingyEnemy extends SwingyCharacterEnemy {
    public SwingyEnemy (String ename,int ex, int ey) {
        this.eName = ename;
        this.ex = ex;
        this.ey = ey;
    }
}
