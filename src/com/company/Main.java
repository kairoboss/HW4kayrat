package com.company;
import java.util.Random;

public class Main {

    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 250, 270, 230};
    public static int[] heroesDamage = {20, 15, 25};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic"};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }


        }
    public static void medicHeal(){
        int n = (int)Math.floor(Math.random() * heroesHealth.length);
int nothing = 0;
            if (heroesHealth[n] < 100 && heroesHealth[n]>0 && heroesHealth[3]>0){
                Random random = new Random();
                int heal = random.nextInt(100);
                heroesHealth[n] = heal + heroesHealth[n];
                System.out.println("Medic heals mate" + " " + heal);

            }

        }



    public static void changeDefence() {
        Random random = new Random();
        int randomNumber = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomNumber];
        System.out.println("Boss got " + bossDefenceType);
    }

    public static void round() {

        changeDefence();
        heroesHit();

        if (bossHealth > 0) {
            bossHits();
        }
        medicHeal();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Victory!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0) {
            System.out.println("GAME OVER");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health" + heroesHealth[3]);
        System.out.println("________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coef = random.nextInt(8) + 2; // 2,3,4,5,6
                    System.out.println("Critical damage "
                            + heroesDamage[i] * coef);
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }
}
