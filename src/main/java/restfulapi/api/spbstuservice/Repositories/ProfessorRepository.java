package restfulapi.api.spbstuservice.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, String> {

    Set<Professor> getAllByProfessorUniversityIdIn(List<Integer> id);

    Optional<Professor> findById(String id);

    List<Professor> getAllBy();

    Page<Professor> findAll(Specification<Professor> spc, Pageable pageable);

    List<Professor> findAll(Specification<Professor> spc);
}
