package fr.aifcc.master.stock_impl;

import java.sql.*;
import java.util.*;
import fr.aifcc.master.stock_api.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;

/**
 * Classe de tests unitaire de la gestion d'accès à la BDD.
 *
 * ATTENTION ! Cette classe ne DOIT PAS hériter de la classe « TestCase »
 * sinon certaines annotations ne sont pas disponible et le nom méthode
 * de test devrait toutes commencer par « test ».
 *
 * Si la classe hérite c'est junit 3 qui sera utilisé à la place de junit 4 !
 * @author 
 */

public class TestDatabaseStock
{

    protected static Connection connection;
    protected DatabaseStock database;

    private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/exam_jee_test";
    private static final String USER_MYSQL = "root";
    private static final String USER_MYSQL_PASSWORD = "";

    /**
     * Cette annotation n'est disponible que en junit 4
     *
     * Cette méthode sera exécutée une seule et unique fois AVANT
     * de commencer les tests unitaires.
     * */
    @BeforeClass
    public static void setUpClass()
        throws Exception
    {
        Class.forName( DRIVER_MYSQL );
        connection = DriverManager.getConnection(URL_MYSQL,USER_MYSQL,USER_MYSQL_PASSWORD);

        insererDenree( 1, "Coca-cola", "Boisson", 100 );
        insererDenree( 2, "Café", "Boisson", 200 );
    }

    /**
     * Cette méthode sera exécutée avant CHAQUE test unitaire.
     * */
    @Before
    public void setUp()
        throws StockException
    {
        this.database = new DatabaseStock(DRIVER_MYSQL, URL_MYSQL,USER_MYSQL,USER_MYSQL_PASSWORD);
    }


    /**
     * Cette annotation n'est disponible que en junit 4
     *
     * Cette méthode sera exécutée une seule et unique fois APRÈS
     * que tous les tests unitaires aient été faits
     * */
    @AfterClass
    public static void tearDownClass()
        throws SQLException
    {

        connection.close();
    }


    /**
     * Cette méthode sera exécutée après CHAQUE test unitaire.
     * */
    @After
    public void tearDown()
        throws StockException
    {
        this.database.dispose();
        this.database = null;
    }


    public static void insererDenree( long id, String nom, String categorie, int quantite )
        throws SQLException
    {
        System.out.println( ">>>>>>>>>>>>>> INSERTION d'une entrée dans la table Denree <<<<<<<<<<<<<" );
        String requete = "INSERT INTO Denree ( id, nom, categorie, quantite ) VALUES ( ?, ?, ?, ? )";
        PreparedStatement insert = connection.prepareStatement( requete );
        insert.setLong( 1, id );
        insert.setString( 2, nom );
        insert.setString( 3, categorie );
        insert.setInt( 4, quantite );
        insert.executeUpdate();
    }

    public static void supprimerDenree( long id )
        throws SQLException
    {
        System.out.println( ">>>>>>>>>>>>>> SUPPRESSION d'une entrée dans la table Denree <<<<<<<<<<<<<" );
        String requete = "DELETE FROM Denree WHERE id=?";
        PreparedStatement insert = connection.prepareStatement( requete );
        insert.setLong( 1, id );
        insert.executeUpdate();
    }

}
