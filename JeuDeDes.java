import java.util.Random;
import java.util.Scanner;

public class JeuDeDes
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		boolean jeuActif = true;

		while (jeuActif)
		{
			int nombreDes;

			// Choix du nombre de dés (sécurisé)
			do
			{
				System.out.print("\nChoisissez le nombre de dés (entre 2 et 5) : ");
				while (!scanner.hasNextInt())
				{
					System.out.print("Entrée invalide. Entrez un nombre entre 2 et 5 : ");
					scanner.next();
				}
				nombreDes = scanner.nextInt();
			}
			while (nombreDes < 2 || nombreDes > 5);

			scanner.nextLine();

			boolean partieEnCours = true;

			while (partieEnCours)
			{
				String choix;

				// Vérification du choix lancer/quitter
				do
				{
					System.out.print("\nTapez 'l' pour lancer les dés ou 'q' pour quitter : ");
					choix = scanner.nextLine().trim().toLowerCase();

					if (!choix.equals("l") && !choix.equals("q"))
					{
						System.out.println("x Entrée invalide !");
					}

				}
				while (!choix.equals("l") && !choix.equals("q"));

				if (choix.equals("q"))
				{
					jeuActif = false;
					break;
				}

				int[] des = new int[nombreDes];

				// Lancer des dés
				System.out.println("\nLancer des dés...");
				for (int i = 0; i < nombreDes; i++)
				{
					des[i] = random.nextInt(6) + 1;
					System.out.println("Dé " + (i + 1) + " : " + des[i]);
				}

				// Vérifier si tous les dés sont identiques
				boolean tousEgaux = true;
				for (int i = 1; i < nombreDes; i++)
				{
					if (des[i] != des[0])
					{
						tousEgaux = false;
						break;
					}
				}

				if (tousEgaux)
				{
					System.out.println("\nVictoire ! Tous les dés sont identiques !");

					String rejouer;

					// Vérification du choix rejouer
					do
					{
						System.out.print("Voulez-vous rejouer ? (o/n) : ");
						rejouer = scanner.nextLine().trim().toLowerCase();

						if (!rejouer.equals("o") && !rejouer.equals("n"))
						{
							System.out.println("x Entrée invalide !");
						}

					}
					while (!rejouer.equals("o") && !rejouer.equals("n"));

					if (rejouer.equals("o"))
					{
						partieEnCours = false;
					}
					else
					{
						jeuActif = false;
						partieEnCours = false;
					}
				}
				else
				{
					System.out.println("\nx Raté ! Réessayez...");
				}
			}
		}

		System.out.println("\nMerci d'avoir joué !");
		scanner.close();
	}
}
