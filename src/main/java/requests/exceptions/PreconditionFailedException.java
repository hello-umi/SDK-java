package requests.exceptions;

import requests.Response;

public class PreconditionFailedException extends ResponseException {

    PreconditionFailedException(Response response){
        super(response);
    }

}
