package fr.aifcc.master.stock_service;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionStock
{

    @ExceptionHandler( java.lang.IllegalArgumentException.class )
    @ResponseStatus( value = HttpStatus.BAD_REQUEST )
    @ResponseBody
    public String handleIllegalArgument( Exception e )
    {
        return e.getMessage();
    }

}