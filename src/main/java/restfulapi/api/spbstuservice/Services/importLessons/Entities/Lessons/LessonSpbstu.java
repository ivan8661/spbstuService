package restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons;

import com.fasterxml.jackson.annotation.JsonProperty;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Groups;
import restfulapi.api.spbstuservice.Services.importLessons.Entities.Teachers;

import java.util.List;

public class LessonSpbstu {

    @JsonProperty("subject")
    private String subject;
    @JsonProperty("subject_short")
    private String subjectShort;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("additional_info")
    private String additionalInfo;
    @JsonProperty("time_start")
    private String timeStart;
    @JsonProperty("time_end")
    private String timeEnd;
    @JsonProperty("parity")
    private Integer parity;
    @JsonProperty("typeObj")
    private TypeObj typeObj;
    @JsonProperty("groups")
    private List<Groups.Group> groups;
    @JsonProperty("teachers")
    private List<Teachers.Teacher> teachers;
    @JsonProperty("auditories")
    private List<Auditory> auditories;
    @JsonProperty("webinar_url")
    private String webinarUrl;
    @JsonProperty("lms_url")
    private String lmsUrl;

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("subject_short")
    public String getSubjectShort() {
        return subjectShort;
    }

    @JsonProperty("subject_short")
    public void setSubjectShort(String subjectShort) {
        this.subjectShort = subjectShort;
    }

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("additional_info")
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @JsonProperty("additional_info")
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @JsonProperty("time_start")
    public String getTimeStart() {
        return timeStart;
    }

    @JsonProperty("time_start")
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    @JsonProperty("time_end")
    public String getTimeEnd() {
        return timeEnd;
    }

    @JsonProperty("time_end")
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @JsonProperty("parity")
    public Integer getParity() {
        return parity;
    }

    @JsonProperty("parity")
    public void setParity(Integer parity) {
        this.parity = parity;
    }

    @JsonProperty("typeObj")
    public TypeObj getTypeObj() {
        return typeObj;
    }

    @JsonProperty("typeObj")
    public void setTypeObj(TypeObj typeObj) {
        this.typeObj = typeObj;
    }

    @JsonProperty("groups")
    public List<Groups.Group> getGroups() {
        return groups;
    }

    @JsonProperty("groups")
    public void setGroups(List<Groups.Group> groups) {
        this.groups = groups;
    }

    @JsonProperty("teachers")
    public List<Teachers.Teacher> getTeachers() {
        return teachers;
    }

    @JsonProperty("teachers")
    public void setTeachers(List<Teachers.Teacher> teachers) {
        this.teachers = teachers;
    }

    @JsonProperty("auditories")
    public List<Auditory> getAuditories() {
        return auditories;
    }

    @JsonProperty("auditories")
    public void setAuditories(List<Auditory> auditories) {
        this.auditories = auditories;
    }

    @JsonProperty("webinar_url")
    public String getWebinarUrl() {
        return webinarUrl;
    }

    @JsonProperty("webinar_url")
    public void setWebinarUrl(String webinarUrl) {
        this.webinarUrl = webinarUrl;
    }

    @JsonProperty("lms_url")
    public String getLmsUrl() {
        return lmsUrl;
    }

    @JsonProperty("lms_url")
    public void setLmsUrl(String lmsUrl) {
        this.lmsUrl = lmsUrl;
    }



}

