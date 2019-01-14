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

}
