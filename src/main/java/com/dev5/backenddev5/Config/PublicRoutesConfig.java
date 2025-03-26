package com.dev5.backenddev5.Config;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class PublicRoutesConfig {

    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/api/login/**",
            "/api/register/**",
            "/api/keep-alive/**"
    );

    public List<String> getPublicPaths() {
        return PUBLIC_PATHS;
    }
}
