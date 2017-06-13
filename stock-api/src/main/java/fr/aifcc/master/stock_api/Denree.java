package fr.aifcc.master.stock_api;

/**
 * Représente une denree stocké dans la base de données
 * @author 
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
     * @return 
     * */
    public String getNom()
    {
        return this.nom;
    }

    /**
     * @param 
     * */
    public void setNom( String nom )
    {
        this.nom = nom;
    }

    /**
     * @return 
     * */
    public String getCategorie()
    {
        return this.categorie;
    }

    /**
     * @param categorie
     * 
     * */
    public void setCategorie( String categorie )
    {
        this.categorie = categorie;
    }



    /**
     * @return 
     * */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * @param quantity
     * 
     * */
    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

}
