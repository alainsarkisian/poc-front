package alain.internsmanagementapiconsumer.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Configuration {
    private String username;
    private String password;
    private String realm;
    @JsonProperty("auth-server-url")
    private String baseUrl;
    @JsonProperty("resource")
    private String clientId;
}
