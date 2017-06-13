/*
 * Dernière modification : Mercredi 07 juin[06] 2017
 * */
package fr.aifcc.master.stock_bean;

import javax.faces.bean.*;

import fr.aifcc.master.stock_api.*;
import fr.aifcc.master.stock_impl.*;

/**
 * Cette classe ne sera jamais instancié manuellement.
 * Toutes ces instanciations seront faites par JSF
 * @author PIVARD Julien
 */
@ManagedBean( name = "stockBean" )
@ApplicationScoped          // L'application est instancié dés que l'app est lancée
public class StockBean
{

    private final DatabaseStock database;

    private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/exam_jee";
    private static final String USER_MYSQL = "root";
    private static final String USER_MYSQL_PASSWORD = "";

    /**
     * Une instance géré par JSF qui donne accès à la BDD.
     * */
    public StockBean()
        throws Exception
    {
        this.database = new DatabaseStock(DRIVER_MYSQL, URL_MYSQL,USER_MYSQL,USER_MYSQL_PASSWORD);
    }

    /**
     * @return L'instance de directory.
     * */
    public DatabaseStock getDatabaseStock()
    {
        return this.database;
    }

}
