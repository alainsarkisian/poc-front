package alain.internsmanagementapiconsumer.controller;

import alain.internsmanagementapiconsumer.client.InternClient;
import com.alain.dto.json.Intern;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InternController {
    private final InternClient internClient;

    @GetMapping("interns/{id}")
    public Intern getAnInternById(@PathVariable long id){
        return this.internClient.getAnInternById(id);
    }
    @PostMapping("/interns")
    public String addAnIntern(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        com.alain.dto.json.Intern internGenerated = new com.alain.dto.json.Intern();
        internGenerated.setFirstName(firstName);
        internGenerated.setLastName(lastName);
        return this.internClient.addAnIntern(internGenerated);
        //return "ok";
    }
/*
    @GetMapping("/interns/{id}")
    public com.alain.dto.json.Intern getAnInternById(@PathVariable Long id) throws InterruptedException {
        return this.internClient.getAnInternById(id);
    }*/
}
