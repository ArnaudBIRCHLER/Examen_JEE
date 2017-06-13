package fr.aifcc.master.stock_api;

/**
 * @author PIVARD Julien
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
     * @param s
     * Le message personnalisé de l'exception.
     * */
    public StockException( String s )
    {
        super( s );
    }

    /**
     * @param t
     * L'exception levé à encapsuler à l'intérieur.
     * */
    public StockException( Throwable t )
    {
        super( t );
    }

    /**
     * @param s
     * Le message personnalisé de l'exception.
     * @param t
     * Une exception déjà levée.
     * */
    public StockException( String s, Throwable t )
    {
        super( s, t );
    }

}
