package restfulapi.api.spbstuservice.Services;


import GetGraphQL.QueryParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.PupilGroup;
import restfulapi.api.spbstuservice.Entities.ListAnswer;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Exceptions.UserExceptionType;
import restfulapi.api.spbstuservice.Repositories.PupilGroupRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private PupilGroupRepository pupilGroupRepository;


    public ListAnswer<PupilGroup> getGroups(Map<String, String> params) throws NoSuchFieldException {
        System.out.println("параметры:" + params);
        if(params.get("q").equals("")) {
            params.remove("q");
        }
        QueryParametersBuilder<PupilGroup> queryBuilder = new QueryParametersBuilder<>(params, PupilGroup.class);
        Specification<PupilGroup> spc = queryBuilder.getSpecification(null);
        Pageable pageable = queryBuilder.getPage();
        Page<PupilGroup> page = pupilGroupRepository.findAll(spc, pageable);
        return new ListAnswer<>(page);
    }

    public PupilGroup getGroup(String id) throws UserException {
        Optional<PupilGroup> group = pupilGroupRepository.findById(id);
        if(group.isPresent()) {
            return group.get();
        } else {
            throw new UserException(UserExceptionType.OBJECT_NOT_FOUND);
        }
    }


}
