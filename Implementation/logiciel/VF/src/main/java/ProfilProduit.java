import java.util.ArrayList;
import java.util.List;

public class ProfilProduit {

    // ceux pour utilisation intermediaire sont: offerPar, evaluations, nbNotes
    private static String titre, categorie, description, image, video, identifiant, promo;
    private static int quantite, nbLikes, nbNotes, nbAchats;
    private static double prix, pointsBoni, noteMoyen;
    private static List<String> achats, evaluations, likes, notes, offerPar;



    public ProfilProduit(String titre) {

        // path du produit
        this.titre = titre;
        String path = DatabasePath.getProduitInfoPath() + titre;

        // load les datas generales
        String data = CSVHandler.readLineByIndex(path + "/main.csv",1);

        String categorie = CSVHandler.getColumnValue(data, 1);
        String description = CSVHandler.getColumnValue(data, 2);
        String quantiteTemp = CSVHandler.getColumnValue(data, 3);
        String prixTemp = CSVHandler.getColumnValue(data, 4);
        String pointsBonip = CSVHandler.getColumnValue(data, 5);
        String image = CSVHandler.getColumnValue(data, 6);
        String video = CSVHandler.getColumnValue(data, 7);
        String identifiant = CSVHandler.getColumnValue(data, 8);
        String promo = CSVHandler.getColumnValue(data, 11);

        // load les datas intermediaires
        this.achats = load(path, "/achats.csv");
        this.evaluations = load(path, "/evaluations.csv");
        this.likes = load(path, "/likes.csv");
        this.notes = load(path, "/notes.csv");
        this.offerPar = load(path, "/offerPar.csv");

        this.nbNotes = calculNb(notes);
        this.nbAchats = calculNb(achats);


        double prix = Double.parseDouble(prixTemp);
        double pointsBoni = Double.parseDouble(pointsBonip);

        this.categorie = categorie;
        this.description = description;
        int quantite = Integer.parseInt(quantiteTemp);
        quantite = quantite - nbAchats;
        this.quantite = quantite;
        this.prix = prix;
        this.pointsBoni = pointsBoni;
        this.image = image;
        this.video = video;
        this.identifiant = identifiant;
        this.nbLikes = calculNb(likes);
        this.noteMoyen = calculMoyen(notes);
        this.promo = promo;

        modified();
        Database.refreshProduits();
    }

    // csv data enregistrer dans une list de string
    public static List<String> load(String path, String suffixe){
        return CSVHandler.readLinesFromCSV(path + suffixe);
    }

    // calculer le nb de valeur dans la liste
    public static int calculNb(List <String> liste){
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










    //enregistrer le produit
    public static void modified(){
        List<String[]> userData = new ArrayList<>();

        String quantite1 = Integer.toString(getQuantite());
        String prix1 = Double.toString(getPrix());
        String pointsBoni1 = Double.toString(getPointsBoni());
        String likes1 = Integer.toString(getNbLikes());
        String note1 = Double.toString(getNoteMoyen());


        userData.add(new String[]{getTitre(),getCategorie(),getDescription(),quantite1,prix1,pointsBoni1,getImage(),getVideo(),getIdentifiant(),likes1,note1,getPromo()});

        // modifier le data du fichier du produit, qui par la suite affect le fichier principale
        String directoryPath = DatabasePath.getProduitInfoPath() + getTitre() + "/main.csv";
        CSVHandler.coverCSV(directoryPath, userData);

        // refresh le fichier principale base sur les data des fichiers des produits
        Database.refreshProduits();
    }









    // afficher le produit
    public static void display(){
        System.out.println("\n\nVoici les informations du produit: " + getTitre()
                + "\n--------------------------------------- "
                + "\nCategorie: " + getCategorie()
                + "\nDescription: " + getDescription()
                + "\nQuantite: " + getQuantite()
                + "\nPrix: " + getPrix()
                + "\nPoints Bonies: " + getPointsBoni()
                + "\nImages: " + getImage()
                + "\nVideos: " + getVideo()
                + "\nID: " + getIdentifiant()
                + "\nnbLikes: " + getNbLikes()
                + "\nNotes: " + getNoteMoyen()
                + "\nPromo: " + getPromo()
                + "\n-----------------------------------: ");
    }








    public static String getTitre() {
        return titre;
    }
    public static String getCategorie() {
        return categorie;
    }
    public static String getDescription() {
        return description;
    }
    public static int getQuantite() {
        return quantite;
    }
    public static Double getPrix() {
        return prix;
    }
    public static Double getPointsBoni() {
        return pointsBoni;
    }
    public static String getImage() {
        return image;
    }
    public static String getVideo() {
        return video;
    }
    public static String getIdentifiant() {
        return identifiant;
    }
    public static int getNbLikes() {
        return nbLikes;
    }
    public static double getNoteMoyen() {
        return noteMoyen;
    }
    public static String getPromo() {
        return promo;
    }
    public static void setDescription(String description) {
        ProfilProduit.description = description;
    }
    public static void setQuantite(int quantite) {
        ProfilProduit.quantite = quantite;
    }
    public static void setPrix(int prix) {
        ProfilProduit.prix = prix;
    }
    public static void setPointsBoni(int pointsBoni) {
        ProfilProduit.pointsBoni = pointsBoni;
    }
    public static void setImage(String image) {
        ProfilProduit.image = image;
    }
    public static void setVideo(String video) {
        ProfilProduit.video = video;
    }
    public static void setIdentifiant(String identifiant) {
        ProfilProduit.identifiant = identifiant;
    }
    public static void setNbLikes(int nbLikes) {
        ProfilProduit.nbLikes = nbLikes;
    }

    public static void setNoteMoyen(int noteMoyen) {
        ProfilProduit.noteMoyen = noteMoyen;
    }
    public static void setPromo(String promo) {
        ProfilProduit.promo = promo;
    }

    public static int getNbNotes() {
        return nbNotes;
    }

    public static int getNbAchats() {
        return nbAchats;
    }

    public static List<String> getAchats() {
        return achats;
    }

    public static List<String> getEvaluations() {
        return evaluations;
    }

    public static List<String> getLikes() {
        return likes;
    }

    public static List<String> getNotes() {
        return notes;
    }

    // get le revendeur qui a offer ce produit
    public static String getOfferPar() {
        return offerPar.get(0);
    }
}