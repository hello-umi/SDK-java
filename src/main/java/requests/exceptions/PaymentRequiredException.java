package requests.exceptions;

import requests.Response;

public class PaymentRequiredException extends ResponseException {

    PaymentRequiredException(Response response){
        super(response);
    }

}
