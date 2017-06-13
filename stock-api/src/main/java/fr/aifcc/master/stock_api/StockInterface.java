package fr.aifcc.master.stock_api;

import java.util.Collection;

/**
 * Interface de gestion des accès aux données.
 * Permet d'abstraire la couche d'accès aux données pour ne pas dépendre
 * d'un type de stockage en particulier.
 * @author Arnaud BIRCHLER et Sebastien GUIGNARD
 */

public interface StockInterface
{

    /**
     * Permet de récupérer un ensemble de personnes dans un ordre arbitraire.
     * Le sous-ensemble des personnes à récupérer est donné par les paramètres offset et limit.
     * L'ordre est arbitraire mais il est garanti qu'il sera le même pour tous les appels.
     * @param offset
     * Index de la première personne à récupérer
     * @param limit
     * Nombre maximum de personnes à récupérer
     * @return La collection de personnes trouvé en base de donnée
     * */
    public Collection<Denree> getListeDenree( long offset, long limit ) throws StockException;

    /**
     * Retourne une personne donnée par son identifiant (id)
     * @param id
     * L'identifiant de la personne à trouver dans la base de données
     * @return La personne trouvée, ou null si l'identifiant n'existe pas
     * */
    public Denree getDenree( long id ) throws StockException;

    /**
     * Met à jour les informations de la personne dans la base de données
     * @param personne
     * La personne modifié.
     * */
    public void mAjDenree( Denree denree ) throws StockException;

    /**
     * Permet d'ajouter une relation entre deux personnes
     * @param id
     * L'identifiant de la personne
     * @param autreId
     * L'identifiant de l'autre personne
     * @param label
     * Le type de la relation
     * */
    public void ajouterDenree( Denree denree ) throws StockException;

    /**
     * Libère les ressources occupées par cette instance d'annuaire
     * */
    public void dispose() throws StockException;

}
