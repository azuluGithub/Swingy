package swingy.dot.com.model.factory;

import swingy.dot.com.model.enemies.Goblin;
import swingy.dot.com.model.enemies.*;

public class EnemyFactory {
    public static SwingyEnemy newEnemy(String ename, int ex, int ey){

        if (ename.equals("Goblin")) {
            return (new Goblin(ename,ex,ey));
        }
        else if (ename.equals("Vergetta")) {
            return (new Vergetta(ename,ex,ey));
        }
        else if (ename.equals("Undertaker")) {
            return (new Undertaker(ename,ex,ey));
        }
        else if (ename.equals("Danger")) {
            return (new Danger(ename,ex,ey));
        }
        return null;
    }
}
