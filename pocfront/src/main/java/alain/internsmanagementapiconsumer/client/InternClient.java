package alain.internsmanagementapiconsumer.client;

import alain.internsmanagementapiconsumer.config.OAuthFeignConfig;
import com.alain.dto.controller.InternsManagementHttpApiInterface;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "intern-client",
        url = "http://localhost:8085",
        path = "api/v1/interns-management-ms/",
        configuration = OAuthFeignConfig.class)
public interface InternClient extends InternsManagementHttpApiInterface{}