package linkplus.banksystem.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DepositException extends RuntimeException{
    public DepositException() {
        super("You can't deposit to another account, if you want to do that u need to select transaction");
    }
}
