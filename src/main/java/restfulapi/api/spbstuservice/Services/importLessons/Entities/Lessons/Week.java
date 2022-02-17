package restfulapi.api.spbstuservice.Services.importLessons.Entities.Lessons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Week {

    @JsonProperty("date_start")
    private String dateStart;
    @JsonProperty("date_end")
    private String dateEnd;
    @JsonProperty("is_odd")
    private Boolean isOdd;

    @JsonProperty("date_start")
    public String getDateStart() {
        return dateStart;
    }

    @JsonProperty("date_start")
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    @JsonProperty("date_end")
    public String getDateEnd() {
        return dateEnd;
    }

    @JsonProperty("date_end")
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    @JsonProperty("is_odd")
    public Boolean getIsOdd() {
        return isOdd;
    }

    @JsonProperty("is_odd")
    public void setIsOdd(Boolean isOdd) {
        this.isOdd = isOdd;
    }

}
