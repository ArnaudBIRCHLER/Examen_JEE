package fr.aifcc.master.stock_api;

/**
 * @author Arnaud BIRCHLER et Sebastien GUIGNARD
 */

public class StockException extends Exception
{

    /**
     * Message par défaut de l'exception directory.
     * */
    public StockException()
    {
        super( "Erreur dans la gestion des denrees." );
    }

    /**
     * Le message personnalisé de l'exception.
     * @param s
     * */
    public StockException( String s )
    {
        super( s );
    }

    /**
     * L'exception levé à encapsuler à l'intérieur.
     * @param t
     * */
    public StockException( Throwable t )
    {
        super( t );
    }

    /**
     * Le message personnalisé de l'exception.
     * @param s
     * Une exception déjà levée.
     * @param t
     * */
    public StockException( String s, Throwable t )
    {
        super( s, t );
    }

}
