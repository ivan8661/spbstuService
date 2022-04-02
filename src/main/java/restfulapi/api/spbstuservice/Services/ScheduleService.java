package restfulapi.api.spbstuservice.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Lesson;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Subject;
import restfulapi.api.spbstuservice.Entities.ListAnswer;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Exceptions.UserExceptionType;
import restfulapi.api.spbstuservice.Repositories.LessonRepository;
import restfulapi.api.spbstuservice.Repositories.ProfessorRepository;
import restfulapi.api.spbstuservice.Repositories.PupilGroupRepository;
import restfulapi.api.spbstuservice.Repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ScheduleService {

    private final LessonRepository lessonRepository;
    private final PupilGroupRepository pupilGroupRepository;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;


    @Autowired
    public ScheduleService(LessonRepository lessonRepository, PupilGroupRepository pupilGroupRepository,
                           ProfessorRepository professorRepository, SubjectRepository subjectRepository) {
        this.lessonRepository = lessonRepository;
        this.pupilGroupRepository = pupilGroupRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    public ListAnswer<Lesson> getLessons(String scheduleUserId) throws UserException {

        Optional<PupilGroup> pupilGroup = pupilGroupRepository.findById(scheduleUserId);
        Optional<Professor> professor = professorRepository.findById(scheduleUserId);

        if(pupilGroup.isPresent()){
            List<Lesson> lessons = lessonRepository.getAllByGroups(pupilGroup.get());
            return new ListAnswer<>(lessons, lessons.size());
        }
        if(professor.isPresent()){
            List<Lesson> lessons = lessonRepository.getAllByProfessors(professor.get());
            return new ListAnswer<>(lessons, lessons.size());
        }
        throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, "ScheduleUser Doesn't exist!", null);
    }

    public Lesson getLesson(String lessonId) throws UserException {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if(lesson.isPresent()) {
            return lesson.get();
        } else {
            throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, "Lesson doesn't exist!", null);
        }
    }

    public Subject getSubject(String subjectId) throws UserException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(subject.isPresent()) {
            return subject.get();
        } else {
            throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, "Subject doesn't exist!", null);
        }
    }

    public List<Subject> getSubjects(Set<String> subjects) {
        List<Subject> subjectList = new ArrayList<>();
        for(String subjectId : subjects) {
            Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);
            subjectOpt.ifPresent(subjectList::add);
        }
        return subjectList;
    }






}
