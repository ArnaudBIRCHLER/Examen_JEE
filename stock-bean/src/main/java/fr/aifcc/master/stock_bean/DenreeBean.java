package fr.aifcc.master.stock_bean;

import java.util.*;
import javax.faces.bean.*;
import fr.aifcc.master.stock_api.*;
import fr.aifcc.master.stock_impl.*;

/**
 * Lien entre la partie métier et la page web.
 * @author Arnaud BIRCHLER et Sebastien GUIGNARD
 */
@ManagedBean( name = "denreeBean" )
@ViewScoped         // Tant qu'on reste sur la même page cette instance n'est pas déchargé de la mémoire
public class DenreeBean
{


    /**
     * Constructeur sans arguments conforme à la norme java bean
     * */
    public DenreeBean()
    {}
    /**
     * L'instance d'application directory bean sera injecté directement par JSF
     * */
    @ManagedProperty( value = "#{stockBean}" )
    private StockBean stockBean;

    /**
     * Une instance de denree vide
     * */
    private Denree denree = new Denree();

    public Denree getDenree()
        throws StockException
    {
        return this.denree;
    }


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
     * La mise à jour de la personne se fait grâce à l'instance de Person
     * qui est stocké.
     * @throws DirectoryException
     * Un problème c'est produit lors de la mise à jours des données.
     * */
    public void saveDenree()
        throws StockException
    {
        DatabaseStock dir = this.stockBean.getDatabaseStock();
        if(this.denree.getQuantity() >= 0){
            if(!this.denree.getNom().equals("")){
                if(!this.denree.getCategorie().equals("")){
                    dir.ajouterDenree( this.denree );
                    this.denree.setNom("");
                    this.denree.setCategorie("");
                    this.denree.setQuantity(0);
                }
            }
        }
        
    }

}
