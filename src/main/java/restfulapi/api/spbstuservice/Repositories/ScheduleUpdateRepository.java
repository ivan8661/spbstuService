package restfulapi.api.spbstuservice.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.ScheduleUpdate;

@Repository
public interface ScheduleUpdateRepository extends CrudRepository<ScheduleUpdate, String> {

    @Override
    void deleteAll();

    public ScheduleUpdate findByName(String name);

}
