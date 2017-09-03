package requests.exceptions;

import requests.Response;

public class NotFoundException extends ResponseException {

    NotFoundException(Response response){
        super(response);
    }

}
