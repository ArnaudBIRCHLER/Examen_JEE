package fr.aifcc.master.stock_bean;

import java.util.*;
import javax.faces.bean.*;

import fr.aifcc.master.stock_api.*;
import fr.aifcc.master.stock_impl.*;

/**
 * Lien entre la partie métier et la page web.
 * @author PIVARD Julien
 */
@ManagedBean( name = "denreesBean" )
@ViewScoped         // Tant qu'on reste sur la même page cette instance n'est pas déchargé de la mémoire
public class DenreesBean
{


    /**
     * L'instance d'application directory bean sera injecté directement par JSF
     * */
    @ManagedProperty( value = "#{stockBean}" )
    private StockBean stockBean;

    /**
     * Le nombre de résultats par page.
     * */
    private long limite = 7;

    /**
     * La première personne des résultats
     * */
    private long position = 1;

    /**
     * Une page suivante est disponible.
     * */
    private boolean pageSuivante = false;

        /**
     * La chaine de la recherche faites par l'utilisateur.
     * On le garde en mémoire pour pouvoir l'afficher après le
     * rechargement de la page dans le champs de recherche.
     * */
    private String chaineRecherche = "";

    /**
     * Le résultat de la recherche.
     * */
    private Collection<Denree> resultatRecherche = null;

    /**
     * Constructeur sans arguments conforme à la norme java bean
     * */
    public DenreesBean()
    {}

    /**
     * @return L'instance de directory bean
     * */
    public StockBean getStockBean()
    {
        return this.stockBean;
    }

    /**
     * @param stockBean
     * L'instance de la directory bean
     * */
    public void setStockBean( StockBean stockBean )
    {
        this.stockBean = stockBean;
    }

    /**
     * Permet de passer à la page suivante de la liste des personnes.
     * La page sur laquelle on se trouve est stocké en interne.
     * */
    public void goToNextPage()
    {
        this.position = this.position + this.limite;
    }

    /**
     * Permet de passer à la page précédente de la liste des personnes.
     * */
    public void goToPreviousPage()
    {
        this.position = this.position - this.limite;
        if ( this.position <= 0 )
        {
            this.position = 1;
        }
    }

    /**
     * @return Il y a une page précédente.
     * */
    public boolean hasPreviousPage()
    {
        return this.position > 1;
    }

    /**
     * @return Il y a une page suivante.
     * */
    public boolean hasNextPage()
    {
        return this.pageSuivante;
    }


    /**
     * @return La recherche effectué par l'utilisateur.
     * */
    public String getChaineRecherche()
    {
        return this.chaineRecherche;
    }

    /**
     * @param chaine
     * La recherche que fait l'utilisateur.
     * */
    public void setChaineRecherche( String chaine )
    {
        this.chaineRecherche = chaine;
    }

    /**
     * Permet d'exécuter la recherche demandé par l'utilisateur.
     * @throws DirectoryException
     * Un problème c'est produit lors de la récupération des données.
     * */
    public void faireRecherche()
        throws StockException
    {
        this.resultatRecherche = this.stockBean.getDatabaseStock().getDenreeRecherche( this.chaineRecherche );
    }

    /**
     * @return La liste des personnes qui correspondent aux résultats de recherche.
     * */
    public Collection<Denree> getResultatRecherche()
    {

        return this.resultatRecherche;
    }

    /**
     * @return Le nombre de résultats dans la recherche.
     * */
    public int getTailleResultat()
    {
        if ( this.resultatRecherche == null )
        {
            return -1;
        }
        return this.resultatRecherche.size();
    }
    
    /**
     * Le nombre de personnes maximum qui sera retourné dépend de la limite de
     * résultats par pages.
     * @return La liste des personnes
     * @throws StockException
     * Une erreur à été levé lors de l'accès aux données.
     * */
    public Collection<Denree> getDenrees()
        throws StockException
    {
        DatabaseStock dir = this.stockBean.getDatabaseStock();
        Collection<Denree> it = dir.getListeDenree( this.position, this.limite );
        this.pageSuivante = it.size() == this.limite;
        return it;
    }

    public void removeOneDenree(int id) throws StockException{
        DatabaseStock dir = this.stockBean.getDatabaseStock();
        Denree denree = dir.getDenree(id) ;
        int quantity = denree.getQuantity() ;
        if (quantity>0){
            denree.setQuantity(quantity - 1);
            dir.mAjDenree(denree);
        }
        
    }

}
