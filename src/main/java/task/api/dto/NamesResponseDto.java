package task.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class NamesResponseDto {

    @JsonProperty
    private List<String> names;

    public NamesResponseDto() {
    }

    public NamesResponseDto(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }
}
