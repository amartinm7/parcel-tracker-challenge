package es.amm.intrastructure;

public class HttpParams {

    public static final String URI_API          = "/api";
    public static final String URI_REGISTER     = "/register";
    public static final String URI_PUSH         = "/push";

    static final String[] URI_AUTH_WHITELIST = {
            "/",
            "/actuator/**",
            "/src/**",
            "/v1/**",
            "/v2/**",
            "/v3/**",
            "/index.html",
            "/images/**",
            "/api/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};

    public static final String X_PASSWORD                   = "X-Password";
    public static final String USER_NAME                    = "username";
    public static final String USER_NAME_FROM               = "usernameFrom";
    public static final String USER_NAME_TO                 = "usernameTo";
    public static final String PASSWORD                     = "password";

}
