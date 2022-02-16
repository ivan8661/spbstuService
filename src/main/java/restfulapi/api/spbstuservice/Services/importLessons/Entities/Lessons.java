package restfulapi.api.spbstuservice.Services.importLessons.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

public class Lessons {
    @JsonProperty("week")
    private Week week;
    @JsonProperty("days")
    private List<Day> days = null;
    @JsonProperty("week")
    public Week getWeek() {
        return week;
    }
    @JsonProperty("week")
    public void setWeek(Week week) {
        this.week = week;
    }
    @JsonProperty("days")
    public List<Day> getDays() {
        return days;
    }
    @JsonProperty("days")
    public void setDays(List<Day> days) {
        this.days = days;
    }

    public static class Week {

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

    public static class Day {

        @JsonProperty("weekday")
        private Integer weekday;
        @JsonProperty("date")
        private String date;
        @JsonProperty("lessons")
        private List<Lesson> lessons = null;

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
        public List<Lesson> getLessons() {
            return lessons;
        }

        @JsonProperty("lessons")
        public void setLessons(List<Lesson> lessons) {
            this.lessons = lessons;
        }

        public static class Lesson {

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
            private List<Groups.Group> groups = null;
            @JsonProperty("teachers")
            private List<Teachers.Teacher> teachers = null;
            @JsonProperty("auditories")
            private List<Auditory> auditories = null;
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

            public static class Auditory {

                @JsonProperty("id")
                private Integer id;
                @JsonProperty("name")
                private String name;
                @JsonProperty("building")
                private Building building;

                @JsonProperty("id")
                public Integer getId() {
                    return id;
                }

                @JsonProperty("id")
                public void setId(Integer id) {
                    this.id = id;
                }

                @JsonProperty("name")
                public String getName() {
                    return name;
                }

                @JsonProperty("name")
                public void setName(String name) {
                    this.name = name;
                }

                @JsonProperty("building")
                public Building getBuilding() {
                    return building;
                }

                @JsonProperty("building")
                public void setBuilding(Building building) {
                    this.building = building;
                }

                public static class Building {

                    @JsonProperty("id")
                    private Integer id;
                    @JsonProperty("name")
                    private String name;
                    @JsonProperty("abbr")
                    private String abbr;
                    @JsonProperty("address")
                    private String address;

                    @JsonProperty("id")
                    public Integer getId() {
                        return id;
                    }

                    @JsonProperty("id")
                    public void setId(Integer id) {
                        this.id = id;
                    }

                    @JsonProperty("name")
                    public String getName() {
                        return name;
                    }

                    @JsonProperty("name")
                    public void setName(String name) {
                        this.name = name;
                    }

                    @JsonProperty("abbr")
                    public String getAbbr() {
                        return abbr;
                    }

                    @JsonProperty("abbr")
                    public void setAbbr(String abbr) {
                        this.abbr = abbr;
                    }

                    @JsonProperty("address")
                    public String getAddress() {
                        return address;
                    }

                    @JsonProperty("address")
                    public void setAddress(String address) {
                        this.address = address;
                    }

                }


            }

            public static class TypeObj {

                @JsonProperty("id")
                private Integer id;
                @JsonProperty("name")
                private String name;
                @JsonProperty("abbr")
                private String abbr;

                @JsonProperty("id")
                public Integer getId() {
                    return id;
                }

                @JsonProperty("id")
                public void setId(Integer id) {
                    this.id = id;
                }

                @JsonProperty("name")
                public String getName() {
                    return name;
                }

                @JsonProperty("name")
                public void setName(String name) {
                    this.name = name;
                }

                @JsonProperty("abbr")
                public String getAbbr() {
                    return abbr;
                }

                @JsonProperty("abbr")
                public void setAbbr(String abbr) {
                    this.abbr = abbr;
                }

            }

        }

    }

}

