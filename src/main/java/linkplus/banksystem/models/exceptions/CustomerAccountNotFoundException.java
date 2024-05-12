package linkplus.banksystem.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerAccountNotFoundException extends RuntimeException{
    public CustomerAccountNotFoundException(Long id) {
        super(String.format("Account with id: %d is not found", id));
    }
}
