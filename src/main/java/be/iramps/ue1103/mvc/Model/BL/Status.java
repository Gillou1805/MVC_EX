package be.iramps.ue1103.mvc.Model.BL;

public class Status {
    private final int id;
    private String nom;

    /**
     * Classe permettant la modélisation d'un status (d'utilisateur).
     * 
     * @param id  Identifiant de la section
     * @param nom Nom de la section
     */
    public Status(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * @return int retourne l'identifiant
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return String retourne le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * @param nom Le nom à mettre
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

}