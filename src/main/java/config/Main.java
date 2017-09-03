package config;

import brands.Brand;

public class Main {

    public static void main(String[] args){
        Config.setAuthToken("6db56a4067c9c23f986c2eb5f7c0bc88fe4dab55");
        Config.setAgentId(1);

        System.out.println(Brand.get());
    }

}
