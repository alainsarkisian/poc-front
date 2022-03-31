package alain.internsmanagementapiconsumer.client;

import alain.internsmanagementapiconsumer.config.OAuthFeignConfig;
import com.alain.dto.controller.InternsManagementHttpApiInterface;
import com.alain.dto.json.Intern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "intern-client",
        url = "http://localhost:8085/api/v1/interns-management-ms/",
        configuration = OAuthFeignConfig.class)
public interface InternClient {
    @GetMapping("interns/{id}")
    public Intern getAnInternById(@PathVariable long id);

    @PostMapping("/interns")
    public String addAnIntern(Intern intern);
}