package fr.aifcc.master.stock_api;

/**
 * Classe abstraite mère de représentation des objets en base de données.
 * Tous les objets qui viennent de la base de données hérite de cette classe.
 * @author Arnaud BIRCHLER et Sebastien GUIGNARD
 */

public abstract class Item
{
    /**
     * L'identifiant unique de l'objet
     * */
    protected long id;

    /**
     * retourne l'id'(get).
     * @return
     * */
    public long getId()
    {
        return this.id;
    }

    /**
     * set l'id'(set).
     * @param id
     *
     * */
    public void setId( long id )
    {
        this.id = id;
    }
}