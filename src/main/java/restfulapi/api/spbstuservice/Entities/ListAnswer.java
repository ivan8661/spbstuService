package restfulapi.api.spbstuservice.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

public class ListAnswer<T> {


    @JsonProperty("totalCount")
    private long totalCount;
    @JsonProperty("items")
    private List<T> listAnswer;

    public ListAnswer(List<T> listAnswer, long totalCount) {
        this.totalCount = totalCount;
        this.listAnswer = listAnswer;
    }

    public ListAnswer(Page<T> paginatedResponse) {
        this.totalCount = paginatedResponse.getTotalElements();
        this.listAnswer = paginatedResponse.getContent();
    }

    public long getTotalCount() {
        return totalCount;
    }

    public List<T> getListAnswer() {
        return listAnswer;
    }
}