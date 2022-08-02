package restfulapi.api.spbstuservice.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Building;

@Repository
public interface BuildingRepository extends CrudRepository<Building, String> {
    @Override
    void deleteAll();
}
