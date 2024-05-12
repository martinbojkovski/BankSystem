package linkplus.banksystem.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BankNotFoundException extends RuntimeException{

    public BankNotFoundException(){
        super("The bank doesn't exist");
    }
}
