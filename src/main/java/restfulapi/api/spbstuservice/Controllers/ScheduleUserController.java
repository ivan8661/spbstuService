package restfulapi.api.spbstuservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import restfulapi.api.spbstuservice.Entities.ScheduleUser;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Services.ScheduleUserService;

public class ScheduleUserController {

    @Autowired
    private ScheduleUserService scheduleUserService;

    @GetMapping(path = "/scheduleUsers/{id}")
    public ResponseEntity<ScheduleUser> getScheduleUser(@PathVariable("id") String scheduleUserId) throws UserException {
        return ResponseEntity.ok().body(scheduleUserService.getScheduleUser(scheduleUserId));
    }
}
