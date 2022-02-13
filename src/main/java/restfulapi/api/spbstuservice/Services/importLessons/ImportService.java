package restfulapi.api.spbstuservice.Services.importLessons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.Professor;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Exceptions.UserExceptionType;
import restfulapi.api.spbstuservice.Repositories.BuildingRepository;
import restfulapi.api.spbstuservice.Repositories.ProfessorRepository;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Faculties;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Groups;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Teachers;
import restfulapi.api.spbstuservice.SpbstuServiceApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportService {


    public ProfessorRepository professorRepository;
    public BuildingRepository buildingRepository;

    @Autowired
    public ImportService(ProfessorRepository professorRepository, BuildingRepository buildingRepository) {
        this.professorRepository = professorRepository;
        this.buildingRepository = buildingRepository;
    }


    public void importTeachers() throws UserException {
        Teachers teachers = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/teachers", Teachers.class);
        if (teachers == null || teachers.getTeachers().isEmpty())
            throw new UserException(UserExceptionType.SERVER_ERROR, "teachers in import are missing!");
        List<Professor> professors = teachers
                .getTeachers()
                .stream()
                .map(Professor::new)
                .collect(Collectors.toList());
        professorRepository.saveAll(professors);
    }

    public void importGroups() throws UserException {
        Faculties faculties = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/faculties", Faculties.class);
        if (faculties == null || faculties.getFaculties().isEmpty())
            throw new UserException(UserExceptionType.SERVER_ERROR, "faculties in import are missing!");
        List<Integer> facultiesId = faculties
                .getFaculties()
                .stream()
                .map(Faculties.Faculty::getId)
                .collect(Collectors.toList());
        for (int facultyId : facultiesId) {
            new Thread(() -> importThreadGroups(facultyId)).start();
        }

    }
//
//    private void importGroups() {
//
//    }


    private void importThreadGroups(int facultyId) {
        Groups groups = new RestTemplate().getForObject("https://ruz.spbstu.ru/api/v1/ruz/faculties/"+ facultyId + "/groups", Groups.class);
        
    }
}
