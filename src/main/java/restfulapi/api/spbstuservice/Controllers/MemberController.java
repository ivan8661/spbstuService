package restfulapi.api.spbstuservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;
import restfulapi.api.spbstuservice.Entities.ListAnswer;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Services.GroupService;
import restfulapi.api.spbstuservice.Services.ProfessorService;

import java.util.Map;

@RestController
@ControllerAdvice
public class MemberController {

    private final ProfessorService professorService;

    private final GroupService groupService;

    @Autowired
    public MemberController(ProfessorService professorService, GroupService groupService) {
        this.professorService = professorService;
        this.groupService = groupService;
    }

    @GetMapping("/professors")
    public ResponseEntity<ListAnswer<Professor>> professors(@RequestParam Map<String, String> params) throws NoSuchFieldException {
        return ResponseEntity.ok().body(professorService.getProfessors(params));
    }

    @GetMapping("/professors/{professorId}")
    public ResponseEntity<Professor> professors(@PathVariable("professorId") String professorId) throws UserException {
        return ResponseEntity.ok().body(professorService.getProfessor(professorId));
    }

    @GetMapping("/groups")
    public ResponseEntity<ListAnswer<PupilGroup>> groups(@RequestParam Map<String, String> params) throws NoSuchFieldException {
        return ResponseEntity.ok().body(groupService.getGroups(params));
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<PupilGroup> group(@PathVariable("groupId") String groupId) throws UserException {
        return ResponseEntity.ok().body(groupService.getGroup(groupId));
    }





}
