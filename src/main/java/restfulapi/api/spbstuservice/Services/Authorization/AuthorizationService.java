package restfulapi.api.spbstuservice.Services.Authorization;


import org.springframework.stereotype.Service;
import restfulapi.api.spbstuservice.Entities.User;

@Service
public class AuthorizationService {


    public User regUser(String login, String password) throws Exception {
        SpbstuAuth sPbstuAuth = new SpbstuAuth();
        SpbstuAuth.Result result = sPbstuAuth.auth(login, password);

        return new User(result.name, result.secondName, result.avatar, result.id, result.cookie, result.group);
    }
}
