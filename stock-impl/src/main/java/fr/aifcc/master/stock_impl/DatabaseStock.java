package fr.aifcc.master.stock_impl;

import java.util.*;
import java.sql.*;
import fr.aifcc.master.stock_api.*;

/**
 * Implémentation concrète de la classe d'accès aux données.
 * La technologie de stockage choisie est une base de données classique.
 * @author Arnaud BIRCHLER et Sebastien GUIGNARD
 */

public class DatabaseStock implements StockInterface    
{
    /**
     * L'objet de connections à la base de données.
     * */
    private final Connection connection;

    /**
     * Le nom des différentes table de la base de données.
     * */
    private final String TABLE_NAME = "Denree";

    /**
     * Les noms des différents champs de la base de données.
     * */
    private final String TABLE_ID = "ID";
    private final String NAME = "nom";
    private final String CATEGORTIE = "categorie";
    private final String QUANTITY = "quantite";

    /**
     * Initialise une connexion à la base de données
     * @param driverClass
     * le nom du driver de la BDD.
     * @param databaseURL
     * l'URL vers la base de données.
     * */

    public DatabaseStock(String driverClass, String dataBaseUrl, String user, String pswd) throws StockException
    {
        try
        {
            Class.forName(driverClass);
            this.connection = DriverManager.getConnection(dataBaseUrl, user, pswd);     
        }
        catch(ClassNotFoundException notFound)
        {
            throw new StockException("Le driver de connexion n'existe pas !!!");
        }
        catch(SQLException sqlDefault)
        {
            throw new StockException("Tu n'y connais rien en SQL ou quoi ???");
        }
    }

    @Override
    public synchronized Collection<Denree> getListeDenree( long offset, long limit ) throws StockException
    {
        Collection<Denree> denrees =  new ArrayList<Denree>() ;
        ResultSet res;
        String requete  = " ";

        try
        {
            requete = "SELECT * FROM " + TABLE_NAME +" LIMIT ?,?" ;

            PreparedStatement statement = connection.prepareStatement(requete); 
            statement.setLong(1, offset);
            statement.setLong(2, limit);
            res = statement.executeQuery(); 

            if(!res.next()){
                res.close();
                throw new StockException("C'est quoi ce bordel \n Y'a rien dans la base !!!");
            }
            else 
            {
                do
                    {
                        Denree denree = new Denree();
                        denree.setId(res.getLong(1));
                        denree.setNom(res.getString(2));
                        denree.setCategorie(res.getString(3));
                        denree.setQuantity(res.getInt(4));
                        denrees.add(denree) ;
                    }
                while(res.next());
                res.close();
            }
        }
        catch(SQLException sqlDefault)
        {
            throw new StockException("Tu n'y connais rien en SQL ou quoi ???");
        }
        return denrees ;
    }

    @Override
    public synchronized Denree getDenree( long id ) throws StockException
    {
        ResultSet res;
        try
        {
            String requete = "SELECT * FROM " + TABLE_NAME +" WHERE ID=?";      
            PreparedStatement statement = connection.prepareStatement(requete);
            statement.setLong(1,id);

            res = statement.executeQuery();
            int taille = res.getFetchSize();

            if(!res.next())
            {
                res.close();
                throw new StockException("C'est quoi ce bordel \n Y'a rien dans la base !!!");
            }
            else if(taille != 0)
            {
                res.close();
                throw new StockException("C'est quoi ce bordel \n Y'a trop d'éléments !!!");
            }
            else 
            {
                Denree denree = new Denree();
                denree.setId(res.getLong(1));
                denree.setNom(res.getString(2));
                denree.setCategorie(res.getString(3));
                denree.setQuantity(res.getInt(4));
                res.close();
                return denree ;
            }
        }
        catch(SQLException sqlDefault)
        {
            throw new StockException("Tu n'y connais rien en SQL ou quoi ???");
        }
    }

    @Override
    public synchronized void mAjDenree( Denree denree ) throws StockException
    {
        try
        {
            String requete = "UPDATE " + this.TABLE_NAME + " SET "
                + this.NAME + " = ?, "  
                + this.CATEGORTIE + " = ?, " 
                + this.QUANTITY + " = ? WHERE "
                + this.TABLE_ID + " = ?";
            // Une requête doit TOUJOURS être préparé pour éviter les injections SQL.
            PreparedStatement requeteDenree = this.connection.prepareStatement( requete );

            requeteDenree.setString( 1, denree.getNom() );
            requeteDenree.setString( 2, denree.getCategorie() );
            requeteDenree.setInt( 3, denree.getQuantity() );
            requeteDenree.setLong( 4, denree.getId() );

            requeteDenree.executeUpdate();
        }
        catch ( SQLException e )
        {
            throw new StockException( "La mise à jour via la requête SQL à "
                    + "échoué ou à subit une erreur \n===> " + e.getMessage() );
        }
    }

    @Override
    public synchronized void ajouterDenree( Denree denree ) throws StockException
    {
        try
        {
            String requete = "INSERT INTO " + this.TABLE_NAME + " ( "
                + this.TABLE_ID + ", " 
                + this.NAME + ", "
                + this.CATEGORTIE + ", "
                + this.QUANTITY + " ) VALUES ( ?, ?, ?, ? )";
            // Une requête doit TOUJOURS être préparé pour éviter les injections SQL.
            PreparedStatement requeteDenree = this.connection.prepareStatement( requete );
            
            requeteDenree.setLong( 1, denree.getId() );
            requeteDenree.setString( 2, denree.getNom() );
            requeteDenree.setString( 3, denree.getCategorie() );
            requeteDenree.setInt( 4, denree.getQuantity() );

            requeteDenree.executeUpdate();
        }
        catch ( SQLException e )
        {
            throw new StockException( "Echec SQL Insertion denree\n" + e.getMessage() );
        }
    }

    @Override
    public synchronized Collection<Denree> getDenreeRecherche(String denreeName) throws StockException{
        try
        {

            ResultSet resultats;

            String requete = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + NAME + " LIKE ?  ";
            // Une requête doit TOUJOURS être préparé pour éviter les injections SQL.
            PreparedStatement requetePrepare = this.connection.prepareStatement( requete );

            requetePrepare.setString( 1, denreeName+"%" );

            resultats = requetePrepare.executeQuery();

            Collection<Denree> denrees = new LinkedList<>();

            while ( resultats.next() )
            {
                denrees.add( getDenree( resultats.getLong( 1 ) ) );
            }
            resultats.close();

            return denrees;

        }
        catch ( SQLException e )
        {
            throw new StockException( "La récupération du résultat de la"
                    + " recherche des denrees à échoué.\n" + e.getMessage() );
        }
    }

    @Override
    public synchronized void dispose() throws StockException
    {
        try
        {
            this.connection.close();
        }
        catch ( SQLException e )
        {
            throw new StockException( e );
        }
    }
}