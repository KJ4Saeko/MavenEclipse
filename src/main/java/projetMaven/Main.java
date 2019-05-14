package projetMaven;

import projetMaven.Personne;

public class Main {
        public static void main(String[] args){
            Personne p1 = new Personne();
            Personne p2 = new Personne("Houang", "Lucas", 22, "Homme");

            System.out.println("Avant modification : " + p1.toString());
            p1.setPrenom("Yolo");
            System.out.println("Après modification : " +p1.toString());
            System.out.println("Après modificati : " +p2.toString());

        }

}