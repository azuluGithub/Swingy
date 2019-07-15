package swingy.dot.com.model.factory;

import swingy.dot.com.model.color.Color;
import swingy.dot.com.model.heroes.*;
import swingy.dot.com.model.heroes.SwingyHero;

import javax.validation.*;
import java.util.Set;

public class SwingyFactory {

    private static SwingyHero sh;

    public static SwingyHero newSwingyHero(String name, String type){
        if (type.equalsIgnoreCase("Hunter")) {
            sh = new Hunter(name);
            validateHero(sh);
            return (sh);
        } else if (type.equalsIgnoreCase("Fighter")) {
            sh = new Fighter(name);
            validateHero(sh);
            return (sh);
        } else if (type.equalsIgnoreCase("Warrior")) {
            sh = new Warrior(name);
            validateHero(sh);
            return (sh);
        } else {
            invalidType();
        }
        return null;
    }

    public static SwingyHero newSwingyHero(String name, String type, int level, int xp, String armor, String weapon){
        if (type.equalsIgnoreCase("Hunter")) {
            sh = new Hunter(name, type, level, xp, armor, weapon);
            validateHero(sh);
            return (sh);
        } else if (type.equalsIgnoreCase("Fighter")) {
            sh = new Fighter(name, type, level, xp, armor, weapon);
            validateHero(sh);
            return (sh);
        } else if (type.equalsIgnoreCase("Warrior")) {
            sh = new Warrior(name, type, level, xp, armor, weapon);
            validateHero(sh);
            return (sh);
        } else {
            invalidType();
        }
        return null;
    }

    private static void validateHero(SwingyHero swingyHero) {
        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        //It validates bean instances
        Validator validator = factory.getValidator();
        //Validate bean
        Set<ConstraintViolation<SwingyHero>> constraintViolations = validator.validate(swingyHero);
        //Show errors
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<SwingyHero> violation : constraintViolations) {
                System.out.println(Color.RED + "Error:: " + violation.getMessage() + Color.RESET);
                System.exit(1);
            }
        }
    }

    private static void invalidType() {
        System.out.println(Color.RED + "Error:: Invalid type. Choose \"Hunter\" or \"Warrior\" or \"Fighter\"" + Color.RESET);
        System.exit(1);
    }
}
