package restfulapi.api.spbstuservice.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Lesson;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, String> {
}
