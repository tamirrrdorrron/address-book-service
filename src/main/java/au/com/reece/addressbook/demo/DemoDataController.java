package au.com.reece.addressbook.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Profile("development")
@RestController
public class DemoDataController {

    @Autowired
    DemoDataService demoDataService;

    @GetMapping(path="/init")
    public @ResponseBody
    ResponseEntity<String> initializeTestData() {
        return ResponseEntity.ok(demoDataService.initialize());
    }

    @GetMapping(path="/cleanup")
    public @ResponseBody ResponseEntity<String> cleanupTestData() {
        return ResponseEntity.ok(demoDataService.cleanup());
    }

}
