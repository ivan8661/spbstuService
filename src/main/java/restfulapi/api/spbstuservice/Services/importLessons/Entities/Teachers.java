package restfulapi.api.spbstuservice.Services.importLessons.Entities;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teachers {

        @JsonProperty("teachers")
        private List<Teacher> teachers = null;
        @JsonProperty("teachers")
        public List<Teacher> getTeachers() {
            return teachers;
        }

        @JsonProperty("teachers")
        public void setTeachers(List<Teacher> teachers) {
            this.teachers = teachers;
        }

    @Override
    public String toString() {
        return "Teachers{" +
                "teachers=" + teachers +
                '}';
    }

    public static class Teacher {





        public Teacher() {
        }

        @JsonProperty("id")
        private int id;
        @JsonProperty("oid")
        private int oid;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("middle_name")
        private String middleName;
        @JsonProperty("last_name")
        private String lastName;
        @JsonProperty("grade")
        private String grade;
        @JsonProperty("chair")
        private String chair;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("id")
        public int getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(int id) {
            this.id = id;
        }

        @JsonProperty("oid")
        public int getOid() {
            return oid;
        }

        @JsonProperty("oid")
        public void setOid(int oid) {
            this.oid = oid;
        }

        @JsonProperty("full_name")
        public String getFullName() {
            return fullName;
        }

        @JsonProperty("full_name")
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @JsonProperty("first_name")
        public String getFirstName() {
            return firstName;
        }

        @JsonProperty("first_name")
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @JsonProperty("middle_name")
        public String getMiddleName() {
            return middleName;
        }

        @JsonProperty("middle_name")
        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        @JsonProperty("last_name")
        public String getLastName() {
            return lastName;
        }

        @JsonProperty("last_name")
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @JsonProperty("grade")
        public String getGrade() {
            return grade;
        }

        @JsonProperty("grade")
        public void setGrade(String grade) {
            this.grade = grade;
        }

        @JsonProperty("chair")
        public String getChair() {
            return chair;
        }

        @JsonProperty("chair")
        public void setChair(String chair) {
            this.chair = chair;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "id='" + id + '\'' +
                    ", oid='" + oid + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", middleName='" + middleName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", grade='" + grade + '\'' +
                    ", chair='" + chair + '\'' +
                    ", additionalProperties=" + additionalProperties +
                    '}';
        }
    }
}
