package config;

public class Config {

    public static final String BASE_URL = "http://localhost:8080/api/v1/";

    private static String authToken;
    private static int agentId;

    public static void setAuthToken(String authToken1){
        authToken = authToken1;
    }

    public static void setAgentId(int agentId1) {
        agentId = agentId1;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static int getAgentId() {
        return agentId;
    }

}
