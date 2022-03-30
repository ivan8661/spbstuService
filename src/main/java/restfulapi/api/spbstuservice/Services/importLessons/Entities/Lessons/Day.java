package restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Day {

    @Override
    public String toString() {
        return "Day{" +
                "weekday=" + weekday +
                ", date='" + date + '\'' +
                ", lessons=" + lessonSpbstus +
                '}';
    }

    @JsonProperty("weekday")
    private Integer weekday;
    @JsonProperty("date")
    private String date;
    @JsonProperty("lessons")
    private List<LessonSpbstu> lessonSpbstus;

    @JsonProperty("weekday")
    public Integer getWeekday() {
        return weekday;
    }

    @JsonProperty("weekday")
    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("lessons")
    public List<LessonSpbstu> getLessons() {
        return lessonSpbstus;
    }

    @JsonProperty("lessons")
    public void setLessons(List<LessonSpbstu> lessonSpbstus) {
        this.lessonSpbstus = lessonSpbstus;
    }




}
