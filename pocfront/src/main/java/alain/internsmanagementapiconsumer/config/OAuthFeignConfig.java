package alain.internsmanagementapiconsumer.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.util.SystemPropertiesJsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
@Configuration
public class OAuthFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() throws IOException {
        InputStream configStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("keycloak.json");
        ObjectMapper mapper = new ObjectMapper(new SystemPropertiesJsonParserFactory());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        alain.internsmanagementapiconsumer.config.Configuration configuration = mapper.readValue(configStream, alain.internsmanagementapiconsumer.config.Configuration.class);
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(configuration.getBaseUrl())
                .realm(configuration.getRealm())
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(configuration.getClientId())
                .username(configuration.getUsername())
                .password(configuration.getPassword()).build();

        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + keycloak.tokenManager().getAccessToken().getToken());
        };
    }


}
