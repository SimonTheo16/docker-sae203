import java.util.Random;
import java.util.Scanner;

public class JeuDesBatons
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		boolean jeuActif = true;

		while (jeuActif)
		{
			int batons;

			do
			{
				System.out.print("\nChoisissez le nombre de bâtons (entre 10 et 30) : ");
				while (!scanner.hasNextInt())
				{
					System.out.print("Entrée invalide. Entrez un nombre entre 10 et 30 : ");
					scanner.next();
				}
				batons = scanner.nextInt();
			}
			while (batons < 10 || batons > 30);

			scanner.nextLine();

			boolean partieEnCours = true;

			while (partieEnCours)
			{
				System.out.println("\nIl reste " + batons + " bâton(s)");

				int choixJoueur = 0;
				boolean entreeValide = false;

				// Tour du joueur avec gestion de 'q'
				while (!entreeValide)
				{
					System.out.print("Combien de bâtons prenez-vous ? (1 à 3) ou 'q' pour quitter : ");
					String saisie = scanner.nextLine().trim().toLowerCase();

					if (saisie.equals("q"))
					{
						jeuActif = false;
						partieEnCours = false;
						break;
					}

					try
					{
						choixJoueur = Integer.parseInt(saisie);

						if (choixJoueur >= 1 && choixJoueur <= 3 && choixJoueur <= batons)
						{
							entreeValide = true;
						}
						else
						{
							System.out.println("x Choix invalide !");
						}
					}
					catch (NumberFormatException e)
					{
						System.out.println("x Entrée invalide !");
					}
				}

				if (!jeuActif || !partieEnCours)
				{
					break;
				}

				batons -= choixJoueur;

				if (batons == 0)
				{
					System.out.println("\nVous avez pris le dernier bâton... Vous avez perdu !");
					partieEnCours = false;
					continue;
				}

				// Tour du robot
				int choixrobot = random.nextInt(3) + 1;

				if (choixrobot > batons)
				{
					choixrobot = batons;
				}

				System.out.println("Le robot prend " + choixrobot + " bâton(s)");
				batons -= choixrobot;

				if (batons == 0)
				{
					System.out.println("\nLe robot a pris le dernier bâton... Vous avez gagné !");
					partieEnCours = false;
				}
			}

			if (!jeuActif)
			{
				break;
			}

			String rejouer;

			do
			{
				System.out.print("\nVoulez-vous rejouer ? (o/n) : ");
				rejouer = scanner.nextLine().trim().toLowerCase();

				if (!rejouer.equals("o") && !rejouer.equals("n"))
				{
					System.out.println("x Entrée invalide !");
				}

			}
			while (!rejouer.equals("o") && !rejouer.equals("n"));

			if (rejouer.equals("n"))
			{
				jeuActif = false;
			}
		}

		System.out.println("\nMerci d'avoir joué !");
		scanner.close();
	}
}
