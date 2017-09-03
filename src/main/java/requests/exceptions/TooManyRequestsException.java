package requests.exceptions;

import requests.Response;

public class TooManyRequestsException extends ResponseException {

    TooManyRequestsException(Response response){
        super(response);
    }

}
