package restfulapi.api.spbstuservice.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, String> {
}
