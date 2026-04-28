import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bataille {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Création du paquet de 52 cartes
        List<Integer> paquet = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int valeur = 0; valeur < 13; valeur++) {
                paquet.add(valeur);
            }
        }

        // 2. Utilisation de ta méthode personnalisée pour mélanger
        System.out.println("Mélange du paquet en cours...");
        melangerLePaquet(paquet);

        // 3. Distribution
        List<Integer> mainJoueur = new ArrayList<>(paquet.subList(0, 26));
        List<Integer> mainRobot = new ArrayList<>(paquet.subList(26, 52));

        System.out.println("--- Début de la Bataille ! ---");

        while (!mainJoueur.isEmpty() && !mainRobot.isEmpty()) {
            System.out.println("\nAppuie sur ENTREE pour jouer (Cartes restantes - Toi: " + mainJoueur.size() + " | Robot: " + mainRobot.size() + ")");
            scanner.nextLine();

            int carteJ = mainJoueur.remove(0);
            int carteR = mainRobot.remove(0);

            System.out.println("Tu poses : " + nomCarte(carteJ));
            System.out.println("Le Robot pose : " + nomCarte(carteR));

            if (carteJ > carteR) {
                System.out.println("-> Tu gagnes le pli !");
                mainJoueur.add(carteJ);
                mainJoueur.add(carteR);
            } else if (carteR > carteJ) {
                System.out.println("-> Le Robot gagne le pli !");
                mainRobot.add(carteR);
                mainRobot.add(carteJ);
            } else {
                System.out.println("-> ÉGALITÉ ! Les cartes sont perdues.");
            }
        }

        if (mainJoueur.isEmpty()) {
            System.out.println("\nGAME OVER : Le robot a pris toutes tes cartes !");
        } else {
            System.out.println("\nVICTOIRE : Tu as battu le robot !");
        }
        
        scanner.close();
    }

    /**
     * Méthode de mélange personnalisée (Fisher-Yates)
     */
    private static void melangerLePaquet(List<Integer> listeAChoisir) {
        Random random = new Random();
        for (int i = listeAChoisir.size() - 1; i > 0; i--) {
            // Choix d'un index aléatoire entre 0 et i inclus
            int j = random.nextInt(i + 1);
            
            // Échange des éléments (Swap)
            int temp = listeAChoisir.get(i);
            listeAChoisir.set(i, listeAChoisir.get(j));
            listeAChoisir.set(j, temp);
        }
    }

    private static String nomCarte(int valeur) {
        String[] noms = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi", "As"};
        return noms[valeur];
    }
}
