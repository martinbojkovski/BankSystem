package linkplus.banksystem.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WithdrawException extends RuntimeException{
    public WithdrawException(){
        super("You can't withdraw funds from another account");
    }
}
