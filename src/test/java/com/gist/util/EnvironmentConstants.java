package com.gist.util;

public class EnvironmentConstants {
    private static final String GIT_PERSONAL_AUTH_TOKEN_KEY = "git_personal_auth_token";
    private static final String GIT_APP_URL_KEY = "git_app_url";
    private static final String GIT_PORT_NUMBER_KEY = "git_port_number";
    public static final String GIT_PERSONAL_AUTH_TOKEN, GIT_APP_URL;
    public static final String GIT_AUTH_HEADER_KEY= "authorization";
    public static final String GIT_ACCEPT_HEADER_KEY ="accept";
    public static final String GIT_ACCEPT_HEADER="application/vnd.github.v3+json";

    //"accept","application/vnd.github.v3+json"
    public static final Integer GIT_PORT_NUMBER;
    static {
        GIT_PERSONAL_AUTH_TOKEN ="token "+PropertyConfig.getPropertyValue(GIT_PERSONAL_AUTH_TOKEN_KEY);
        GIT_APP_URL= PropertyConfig.getPropertyValue(GIT_APP_URL_KEY);
        GIT_PORT_NUMBER=Integer.parseInt(PropertyConfig.getPropertyValue(GIT_PORT_NUMBER_KEY));
    }
}


