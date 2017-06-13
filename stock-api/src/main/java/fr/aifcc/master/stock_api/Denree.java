package fr.aifcc.master.stock_api;

/**
 * Représente une denree stockée dans la base de données
 * @author Arnaud BIRCHLER et Sebastien GUIGNARD
 */

public class Denree extends Item
{

    protected String nom;
    protected String categorie;
    protected int quantity ;

    /**
     * Initialisation d'une denree par défaut.
     * */
    public Denree()
    {
        this.id = 0;
        this.nom = "";
        this.categorie = "";
        this.quantity = 0 ;
    }


    /**
     * Retourne le nom (get).
     * @return 
     * */
    public String getNom()
    {
        return this.nom;
    }


    /**
     * Set le nom (set).
     * @param nom
     * */
    public void setNom( String nom )
    {
        this.nom = nom;
    }


    /**
     * Retourne la catégorie (get).
     * @return 
     * */
    public String getCategorie()
    {
        return this.categorie;
    }


    /**
     * set la catégorie (set).
     * @param categorie
     * */
    public void setCategorie( String categorie )
    {
        this.categorie = categorie;
    }


    /**
     * Retourne la quantité (get).
     * @return 
     * */
    public int getQuantity()
    {
        return this.quantity;
    }


    /**
     * set la catégorie (set).
     * @param quantity
     * 
     * */
    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

}
