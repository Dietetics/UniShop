import java.util.ArrayList;
import java.util.List;

public class ProfilProduit {


    private static String titre, categorie, description, image, video, identifiant, promo;
    private static int quantite, likes,note;
    private static double prix, pointsBoni;
    public ProfilProduit(String titre) {

        this.titre = titre;

        int lineIndex = CSVHandler.findOccurrenceIndex(DatabasePath.getProduitPath(),getTitre(),0);
        String data = CSVHandler.readLineByIndex(DatabasePath.getProduitPath(),lineIndex);

        String categorie = CSVHandler.getColumnValue(data, 1);
        String description = CSVHandler.getColumnValue(data, 2);
        String quantiteTemp = CSVHandler.getColumnValue(data, 3);
        String prixTemp = CSVHandler.getColumnValue(data, 4);
        String pointsBonip = CSVHandler.getColumnValue(data, 5);
        String image = CSVHandler.getColumnValue(data, 6);
        String video = CSVHandler.getColumnValue(data, 7);
        String identifiant = CSVHandler.getColumnValue(data, 8);
        String likesTemp = CSVHandler.getColumnValue(data, 9);
        String noteTemp = CSVHandler.getColumnValue(data, 10);
        String promo = CSVHandler.getColumnValue(data, 11);

        int quantite = Integer.parseInt(quantiteTemp);
        double prix = Double.parseDouble(prixTemp);
        double pointsBoni = Double.parseDouble(pointsBonip);
        int likes = Integer.parseInt(likesTemp);
        int note = Integer.parseInt(noteTemp);


        this.categorie = categorie;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
        this.pointsBoni = pointsBoni;
        this.image = image;
        this.video = video;
        this.identifiant = identifiant;
        this.likes = likes;
        this.note = note;
        this.promo = promo;
    }



    //enregistrer le produit
    public static void modified(){
        List<String[]> userData = new ArrayList<>();

        String quantite1 = Integer.toString(getQuantite());
        String prix1 = Double.toString(getPrix());
        String pointsBoni1 = Double.toString(getPointsBoni());
        String likes1 = Integer.toString(getLikes());
        String note1 = Integer.toString(getNote());


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
                + "\nLikes: " + getLikes()
                + "\nNotes: " + getNote()
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
    public static int getLikes() {
        return likes;
    }
    public static int getNote() {
        return note;
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
    public static void setLikes(int likes) {
        ProfilProduit.likes = likes;
    }

    public static void setNote(int note) {
        ProfilProduit.note = note;
    }
    public static void setPromo(String promo) {
        ProfilProduit.promo = promo;
    }
}