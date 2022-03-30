package restfulapi.api.spbstuservice.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.UpdateInfo;

@Repository
public interface UpdateInfoRepository extends CrudRepository<UpdateInfo, String> {
}
