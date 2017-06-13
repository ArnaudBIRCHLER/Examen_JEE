package fr.aifcc.master.stock_api;

/**
 * Classe abstraite mère de représentation des objets en base de données.
 * Tous les objets qui viennent de la base de données hérite de cette classe.
 * @author Arnaud -- Sebastien
 */
public abstract class Item
{

    /**
     * L'identifiant unique de l'objet
     * */
    protected long id;

    /**
     * @return L'identifiant unique
     * */
    public long getId()
    {
        return this.id;
    }

    /**
     * @param id
     * Le nouvel identifiant.
     * */
    public void setId( long id )
    {
        this.id = id;
    }

}
