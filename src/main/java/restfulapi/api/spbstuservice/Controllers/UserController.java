package restfulapi.api.spbstuservice.Controllers;

import org.json.JSONObject;
import restfulapi.api.spbstuservice.Entities.User;
import restfulapi.api.spbstuservice.Services.Authorization.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class UserController {

        private AuthorizationService authorizationService;

        @Autowired
        public UserController(AuthorizationService authorizationService) {
            this.authorizationService = authorizationService;
        }

        @PostMapping("/auth")
        public ResponseEntity<User> regUser(@RequestBody String regData) throws Exception {
            JSONObject reg = new JSONObject(regData);
            String login = reg.optString("serviceLogin");
            String password = reg.optString("servicePassword");
            User user = authorizationService.regUser(login, password);
            return ResponseEntity.ok().body(user);
        }
    }
