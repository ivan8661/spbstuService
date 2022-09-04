package restfulapi.api.spbstuservice.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PupilGroupRepository extends CrudRepository<PupilGroup, String> {

    List<PupilGroup> getAllBy();


    @Override
    void deleteAll();

    Set<PupilGroup> getAllByUniversityGroupIdIn(List<Integer> id);

    Optional<PupilGroup> getById(String id);

    Optional<PupilGroup> findPupilGroupByName(String name);

    Page<PupilGroup> findAll(Specification<PupilGroup> spc, Pageable pageable);

    List<PupilGroup> findAll(Specification<PupilGroup> spc);


}
