import java.util.Scanner;
import java.util.Random; // Import nécessaire pour l'IA

public class Morpion {
    private static char[][] plateau = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private static char joueurActuel = 'X';
    private static Random random = new Random(); // Instance pour l'IA

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean jeuEnCours = true;

        System.out.println("--- Morpion : Humain (X) vs Robot (O) ---");

        while (jeuEnCours) {
            afficherPlateau();
            
            if (joueurActuel == 'X') {
                jouerCoupHumain(scanner);
            } else {
                jouerCoupRobot();
            }
            
            if (verifierVictoire()) {
                afficherPlateau();
                System.out.println("Félicitations ! Le joueur " + joueurActuel + " a gagné !");
                jeuEnCours = false;
            } else if (estPlateauPlein()) {
                afficherPlateau();
                System.out.println("Match nul !");
                jeuEnCours = false;
            } else {
                joueurActuel = (joueurActuel == 'X') ? 'O' : 'X';
            }
        }
        scanner.close();
    }

    private static void afficherPlateau() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(plateau[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Le joueur humain saisit ses coordonnées
    private static void jouerCoupHumain(Scanner scanner) {
        int ligne, col;
        while (true) {
            System.out.print("Ton tour (X), entre ligne et colonne (0-2) : ");
            if (scanner.hasNextInt()) {
                ligne = scanner.nextInt();
                col = scanner.nextInt();
                if (ligne >= 0 && ligne < 3 && col >= 0 && col < 3 && plateau[ligne][col] == ' ') {
                    plateau[ligne][col] = 'X';
                    break;
                }
            } else {
                scanner.next(); // Vide la mauvaise saisie
            }
            System.out.println("Coup invalide. Réessaye.");
        }
    }

    // Le robot joue aléatoirement
    private static void jouerCoupRobot() {
        System.out.println("Le robot (O) réfléchit...");
        int ligne, col;
        while (true) {
            ligne = random.nextInt(3); // Génère 0, 1 ou 2
            col = random.nextInt(3);
            
            if (plateau[ligne][col] == ' ') {
                plateau[ligne][col] = 'O';
                System.out.println("Le robot a joué en : " + ligne + " " + col);
                break;
            }
        }
    }

    private static boolean verifierVictoire() {
        for (int i = 0; i < 3; i++) {
            if ((plateau[i][0] == joueurActuel && plateau[i][1] == joueurActuel && plateau[i][2] == joueurActuel) ||
                (plateau[0][i] == joueurActuel && plateau[1][i] == joueurActuel && plateau[2][i] == joueurActuel)) {
                return true;
            }
        }
        return (plateau[0][0] == joueurActuel && plateau[1][1] == joueurActuel && plateau[2][2] == joueurActuel) ||
               (plateau[0][2] == joueurActuel && plateau[1][1] == joueurActuel && plateau[2][0] == joueurActuel);
    }

    private static boolean estPlateauPlein() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (plateau[i][j] == ' ') return false;
            }
        }
        return true;
    }
}
