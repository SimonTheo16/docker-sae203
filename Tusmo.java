import java.util.Scanner;

public class Tusmo
{
    public static void main(String[] args)
    {
        final int NBR_ESSAI = 7;
        boolean rejouer = true;
        Scanner sc = new Scanner(System.in);

        while (rejouer)
        {
            char[] tab_mot;
            String mot_Cible;
            String mot;

            boolean mot_trouver;
            int cpt_nbr_mot;

            mot_trouver = false;

            mot_Cible = motCible_alea().toUpperCase();

            tab_mot = new char[mot_Cible.length()];
            mot = String.format("%-" + mot_Cible.length() + "s", mot_Cible.charAt(0));

            tab_mot = tab_mot(mot, mot_Cible);

            cpt_nbr_mot = 1;

            while (cpt_nbr_mot < NBR_ESSAI && !mot_trouver)
            {
                System.out.println("Le mot commence par la lettre : " + mot_Cible.charAt(0));
                System.out.println(affichage_tab(tab_mot, mot_Cible, mot));

                System.out.print("Votre mot saisi est : ");
                mot = sc.nextLine().toUpperCase();
                System.out.println();

                while (mot.length() != mot_Cible.length())
                {
                    if (mot.length() > mot_Cible.length())
                    {
                        System.out.println("Votre mot est trop long !");
                    }
                    else
                    {
                        System.out.println("Votre mot est trop court !");
                    }

                    System.out.print("Votre mot saisi est : ");
                    mot = sc.nextLine().toUpperCase();
                }

                tab_mot = tab_mot(mot, mot_Cible);

                if (mot.equals(mot_Cible))
                {
                    mot_trouver = true;
                    System.out.println(affichage_tab(tab_mot, mot_Cible, mot));
                    System.out.println("Bravo vous avez trouvé le mot !");
                }
                else if (cpt_nbr_mot == NBR_ESSAI - 1)
                {
                    System.out.println(affichage_tab(tab_mot, mot_Cible, mot));
                    System.out.println("Dommage, le mot était : " + mot_Cible);
                }

                cpt_nbr_mot++;
            }

            System.out.print("Voulez-vous rejouer ? (o/n) : ");
            
            String rep = sc.nextLine().trim().toLowerCase();
            
            rejouer = rep.equals("o");
        }

        System.out.println("Merci d'avoir joué !");
    }



    // ------------------------------------------
    //     CHOIX ALEATOIRE D'UN MOT
    // ------------------------------------------
    public static String motCible_alea()
    {
        String[] faciles = {
            "chat", "chien", "maison", "ecole", "livre", "table", "chaise",
            "route", "ville", "soleil", "mer", "fleur", "pomme", "voiture",
            "porte", "fenetre", "ami", "famille", "pain", "eau", "nez",
            "main", "jour", "nuit", "bonjour"
        };

        String[] moyens = {
            "foret", "montagne", "riviere", "nuage", "orage", "hiver",
            "printemps", "ete", "automne", "jardin", "musique", "hopital",
            "marche", "village", "fromage", "pont", "gare", "rue", "quartier",
            "boulangerie", "valise", "bureau", "armoire", "journal", "lettre"
        };

        String[] difficiles = {
            "parapluie", "horloge", "equilibre", "bibliotheque", "cathedrale",
            "labyrinthe", "capitaine", "silhouette", "metaphore", "proverbe",
            "symptome", "algebre", "sarcasme", "delaisser", "precaution",
            "vocation", "paradoxal", "brillance", "miroiter", "signature",
            "providence", "mecanisme", "fragment", "occurrence", "perilleux"
        };

        String[] complexes = {
            "ineluctable", "syllogisme", "antinomie", "epistemologie",
            "ontologie", "cryptographie", "palimpseste", "contingence",
            "heuristique", "anacoluthe", "transcendantal", "hermeneutique",
            "prosopopee", "metempsychose", "consubstantiel", "apodictique",
            "antepenultieme", "procrastination", "immanence", "indefectible",
            "cataphatique", "paronyme", "syncretisme", "desuetude",
            "lexicographie"
        };

        String[][] niveaux = { faciles, moyens, difficiles, complexes };

        int niv     = (int)(Math.random() * 4);
        int motalea = (int)(Math.random() * 25);

        return niveaux[niv][motalea];
    }



    // ------------------------------------------
    //         CREATION DU TABLEAU DE LETTRES
    // ------------------------------------------
    public static char[] tab_mot(String mot, String mot_Cible)
    {
        char[] tab = new char[mot_Cible.length()];

        for (int i = 0; i < mot_Cible.length(); i++)
        {
            if (test_is_lettre(mot.charAt(i)))
            {
                tab[i] = mot.charAt(i);
            }
            else
            {
                tab[i] = ' ';
            }
        }
        return tab;
    }

    public static boolean test_is_lettre(char lettre)
    {
        lettre = Character.toUpperCase(lettre);
        return (lettre >= 'A' && lettre <= 'Z');
    }



    // ------------------------------------------
    //            AFFICHAGE DE LA GRILLE
    // ------------------------------------------
    public static String affichage_tab(char[] tab, String mot_Cible, String mot)
    {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < tab.length; i++) res.append("+---");
        res.append("+\n");

        for (int i = 0; i < tab.length; i++)
        {
            res.append("| ")
               .append(lettre_place(tab[i], i, mot_Cible, mot))
               .append(tab[i])
               .append(" \u001B[0m");
        }

        res.append("|\n");

        for (int i = 0; i < tab.length; i++) res.append("+---");
        res.append("+\n");

        return res.toString();
    }



    // ------------------------------------------
    //        COULEURS (vert, jaune, gris)
    // ------------------------------------------
    public static String lettre_place(char lettre, int pos, String mot_Cible, String mot)
    {
        lettre    = Character.toUpperCase(lettre);
        mot_Cible = mot_Cible.toUpperCase();
        mot       = mot.toUpperCase();

        if (lettre < 'A' || lettre > 'Z')
            return "\u001B[0m";

        int[] compteur = new int[26];

        for (int i = 0; i < mot_Cible.length(); i++)
        {
            char c = Character.toUpperCase(mot_Cible.charAt(i));
            if (c >= 'A' && c <= 'Z')
                compteur[c - 'A']++;
        }

        for (int i = 0; i < mot.length(); i++)
        {
            char mc = mot.charAt(i);
            char tc = mot_Cible.charAt(i);
            if (mc == tc)
                compteur[mc - 'A']--;
        }

        // Vert
        if (lettre == mot_Cible.charAt(pos))
            return "\u001B[32m";

        // Jaune
        int idx = lettre - 'A';
        if (compteur[idx] > 0)
        {
            compteur[idx]--;
            return "\u001B[33m";
        }

        // Gris
        return "\u001B[0m";
    }
}
