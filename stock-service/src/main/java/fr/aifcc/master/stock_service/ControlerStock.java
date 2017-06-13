package fr.aifcc.master.stock_service ;
import org.springframework.web.bind.annotation.*;
import fr.aifcc.master.stock_api.*;
import fr.aifcc.master.stock_impl.*;
import java.util.*;



@RestController
public class ControlerStock {
	private DatabaseStock dataBaseStock ;


	public ControlerStock(DatabaseStock database){
		this.dataBaseStock = database;
	}




    @RequestMapping( "/denree" )    
    public Denree denree( @RequestParam( value="id" ) long id) throws StockException 
    {
    	return dataBaseStock.getDenree(id);
    }


    @RequestMapping( "/denrees" )    
    public Collection<Denree> denrees( @RequestParam( value="offset" ) long offset,@RequestParam( value="limit" ) long limit)
    throws StockException 
    {
    	return dataBaseStock.getListeDenree(offset,limit);
    }
	
}
