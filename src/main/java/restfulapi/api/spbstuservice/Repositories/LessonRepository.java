package restfulapi.api.spbstuservice.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Lesson;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, String> {

    List<Lesson> getAllBy();

    List<Lesson> getAllByGroups(PupilGroup pupilGroup);

    List<Lesson> getAllByProfessors(Professor professor);


}
