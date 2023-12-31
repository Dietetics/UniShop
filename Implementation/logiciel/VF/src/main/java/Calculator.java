import java.util.List;

/**
 * The Calculator class represents a simple calculator.
 * It provides basic arithmetic operations such as addition, subtraction, multiplication, and division.
 */
public class Calculator {

    /**
     * Additionne deux entiers.
     *
     * @param a Le premier entier.
     * @param b Le deuxième entier.
     * @return La somme des deux entiers.
     */
    public int add(int a, int b) {
        return a + b;
    }


    /**
     * Soustrait le deuxième entier du premier.
     *
     * @param a Le premier entier.
     * @param b Le deuxième entier.
     * @return La différence entre le premier et le deuxième entier.
     */
    public int subtract(int a, int b) {
        return a - b;
    }


    /**
     * Multiplie deux entiers.
     *
     * @param a Le premier entier.
     * @param b Le deuxième entier.
     * @return Le produit des deux entiers.
     */
    public int multiply(int a, int b) {
        return a * b;
    }




    /**
     * Divise le premier entier par le deuxième entier.
     *
     * @param a Le numérateur.
     * @param b Le dénominateur (ne doit pas être zéro).
     * @return Le résultat de la division.
     * @throws IllegalArgumentException Si le dénominateur est zéro.
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Calcule le nombre d'éléments dans une liste.
     *
     * @param liste La liste dont on veut compter les éléments.
     * @return Le nombre d'éléments dans la liste.
     */
    public static int calculNb(List<String> liste){
        int length = liste.size();
        return length;
    }

    /**
     * Calcule la moyenne d'une liste de nombres représentés en tant que chaînes de caractères.
     *
     * @param liste La liste de nombres sous forme de chaînes de caractères.
     * @return La moyenne des nombres dans la liste.
     */
    public static double calculMoyen(List<String> liste) {
        if (liste == null || liste.isEmpty()) {
            return 0;
        }
        int somme = 0;
        for (String str : liste) {
            try {
                // Convertir chaque chaine en int
                int nombre = Integer.parseInt(str);
                // Ajouter a la somme
                somme += nombre;
            } catch (NumberFormatException e) {
                // Gerer les chaines qui ne sont pas des nombres
                System.err.println("La chaîne '" + str + "' n'est pas un nombre entier, donc elle n'est pas utiliser pour le calcul.");
            }
        }
        // Calculer la moyenne
        double moyen = (double) somme / liste.size();
        return moyen;
    }
}
