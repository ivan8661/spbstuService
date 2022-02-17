package restfulapi.api.spbstuservice.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Subject;

import java.util.List;
import java.util.Set;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, String> {


    Subject findByName(String name);

}
