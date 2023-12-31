import java.util.List;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    // calculer le nb de valeur dans la liste
    public static int calculNb(List<String> liste){
        int length = liste.size();
        return length;
    }

    // calculer la moyenne dune liste de string qui est en fait que des int dedans
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
