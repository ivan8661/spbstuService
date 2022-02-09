package restfulapi.api.spbstuservice.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, String> {
}
