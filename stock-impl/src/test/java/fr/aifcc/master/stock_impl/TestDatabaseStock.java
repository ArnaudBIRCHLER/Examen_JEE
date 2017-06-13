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
 * @author Arnaud BIRCHLER et Sebastien GUIGNARD
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
        insererDenree( 2, "Cafe", "Boisson", 200 );
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
        supprimerDenree(1);
        supprimerDenree(2);
        supprimerDenree(3);
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

    @Test
    public void testCoca() throws Exception {
        Denree denree = database.getDenree(1); 
        assertEquals("Erreur ce n'est pas la Denree attendue " , "Coca-cola", denree.getNom());
        assertEquals("Erreur ce n'est pas la Denree attendue " , "Boisson", denree.getCategorie());
        assertEquals("Erreur ce n'est pas la Denree attendue " , 100, denree.getQuantity());
    }

    @Test
    public void testCafe() throws Exception {
        Denree denree = database.getDenree(2); 
        assertEquals("Erreur ce n'est pas la Denree attendue " , "Cafe", denree.getNom());
        assertEquals("Erreur ce n'est pas la Denree attendue " , "Boisson", denree.getCategorie());
        assertEquals("Erreur ce n'est pas la Denree attendue " , 200, denree.getQuantity());
    }

    @Test
    public void testNbDenree() throws Exception {
        Collection<Denree> denrees =  new ArrayList<Denree>() ;
        denrees = database.getListeDenree(0,2); 
        assertEquals("Erreur ce n'est pas le nombre de  Denree attendue " , 2, denrees.size());
    }

    @Test
    public void testAjoutDenree() throws Exception {
        Denree denree = new Denree() ;
        denree.setId(3);
        denree.setNom("Chocolat");
        denree.setCategorie("Boisson");
        denree.setQuantity(1000);
        database.ajouterDenree(denree); 

        Denree denreeTest = database.getDenree(3); 
        assertEquals("Erreur ce n'est pas la Denree attendue " , denreeTest.getNom(), denree.getNom());
        assertEquals("Erreur ce n'est pas la Denree attendue " , denreeTest.getCategorie(), denree.getCategorie());
        assertEquals("Erreur ce n'est pas la Denree attendue " , denreeTest.getQuantity(), denree.getQuantity());
    }

    @Test
    public void testUpdateDenree() throws Exception {
        Denree denreeTest = database.getDenree(2); 
        denreeTest.setNom("The");
        database.mAjDenree(denreeTest);

        denreeTest = null ;

        denreeTest = database.getDenree(2);
        assertEquals("Erreur ce n'est pas la Denree attendue " , "The", denreeTest.getNom());
        assertEquals("Erreur ce n'est pas la Denree attendue " , "Boisson", denreeTest.getCategorie());
        assertEquals("Erreur ce n'est pas la Denree attendue " , 200, denreeTest.getQuantity());
    }

    public static void insererDenree( long id, String nom, String categorie, int quantite )
        throws SQLException
    {
        System.out.println( ">>>>>>>>>>>>>> INSERTION d'une entree dans la table Denree <<<<<<<<<<<<<" );
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
        System.out.println( ">>>>>>>>>>>>>> SUPPRESSION d'une entree dans la table Denree <<<<<<<<<<<<<" );
        String requete = "DELETE FROM Denree WHERE id=?";
        PreparedStatement insert = connection.prepareStatement( requete );
        insert.setLong( 1, id );
        insert.executeUpdate();
    }
}