package requests.exceptions;

import requests.Response;

public class ForbiddenException extends ResponseException {

    ForbiddenException(Response response){
        super(response);
    }

}
