package restfulapi.api.spbstuservice.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;

@Repository
public interface PupilGroupRepository extends CrudRepository<PupilGroup, String> {
}
