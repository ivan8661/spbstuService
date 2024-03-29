package restfulapi.api.spbstuservice.Controllers;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import restfulapi.api.spbstuservice.Entities.DatabaseEntities.ScheduleUpdate;
import restfulapi.api.spbstuservice.Entities.News;
import restfulapi.api.spbstuservice.Exceptions.UserException;
import restfulapi.api.spbstuservice.Exceptions.UserExceptionType;
import restfulapi.api.spbstuservice.Repositories.ScheduleUpdateRepository;
import restfulapi.api.spbstuservice.Services.importLessons.ImportService;

import java.util.LinkedList;
import java.util.Optional;

@RestController
@ControllerAdvice
public class UniversityInfoController {


    private final ScheduleUpdateRepository scheduleUpdateRepository;

    private final ImportService importService;


    @Autowired
    public UniversityInfoController(ScheduleUpdateRepository scheduleUpdateRepository,
                                    ImportService importService) {
            this.scheduleUpdateRepository = scheduleUpdateRepository;
            this.importService = importService;
    }

    @GetMapping("/universityInfo")
    public ResponseEntity<String> universityInfo() throws JSONException, UserException {

        JSONObject universityInfo = new JSONObject();
        ScheduleUpdate scheduleUpdate = scheduleUpdateRepository.findByName("SPBSTU");


        universityInfo.put("_id", "SPBSTU");
        universityInfo.put("name", "СПбПУ");
        universityInfo.put("serviceName", "lk.spbstu");
        if(scheduleUpdate==null) {
            universityInfo.put("referenceDate", System.currentTimeMillis()/1000);
            universityInfo.put("referenceWeek", "odd");
            new Thread(importService::generalImportByCronTime);
        } else {
            universityInfo.put("referenceDate", scheduleUpdate.getSyncTime());
            universityInfo.put("referenceWeek", scheduleUpdate.getWeek());
        }
        return ResponseEntity.ok().body(universityInfo.toString());
    }

    @GetMapping("/newsSources")
    public ResponseEntity<LinkedList<News>> sourceInfo() {

        LinkedList<News> news = new LinkedList<>();
        news.add(new News("-61195360", "Санкт-Петербургский политехнический университет", true, false));
        news.add(new News("-170022924", "Типичный СПбПУ", true, true));
        news.add(new News("-58950918", "Подслушано СПбПУ", true, true));
        news.add(new News("-88039223", "Цитатки", true, false));

        return ResponseEntity.ok().body(news);
    }


}
