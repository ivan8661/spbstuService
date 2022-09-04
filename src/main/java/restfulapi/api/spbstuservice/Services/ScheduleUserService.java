package restfulapi.api.spbstuservice.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;
import restfulapi.api.spbstuservice.Entities.ScheduleUser;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Exceptions.UserExceptionType;
import restfulapi.api.spbstuservice.Repositories.ProfessorRepository;
import restfulapi.api.spbstuservice.Repositories.PupilGroupRepository;

import java.util.Optional;

@Service
public class ScheduleUserService {

    private final PupilGroupRepository pupilGroupRepository;

    private final ProfessorRepository professorsRepository;


    @Autowired
    public ScheduleUserService(PupilGroupRepository pupilGroupRepository,
                               ProfessorRepository professorsRepository) {
        this.professorsRepository = professorsRepository;
        this.pupilGroupRepository = pupilGroupRepository;
    }

    public ScheduleUser getScheduleUser(String id) throws UserException {
        Optional<PupilGroup> pupilGroup = pupilGroupRepository.findById(id);
        Optional<Professor> professor = professorsRepository.findById(id);

        if(pupilGroup.isPresent()) {
            return new ScheduleUser(pupilGroup.get().getId(), pupilGroup.get().getName());
        }

        if(professor.isPresent()) {
            return new ScheduleUser(professor.get().getId(), professor.get().getName());
        }

        throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, "ScheduleUser doesn't exist", null);
    }
}
