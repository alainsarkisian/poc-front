package alain.internsmanagementapiconsumer.client;

import alain.internsmanagementapiconsumer.config.OAuthFeignConfig;
import com.alain.dto.controller.InternsManagementHttpApiInterface;
import com.alain.dto.json.Intern;
import feign.Contract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "intern-client",
        url = "http://localhost:8085/",
        configuration = OAuthFeignConfig.class)
public interface InternClient extends InternsManagementHttpApiInterface{

}