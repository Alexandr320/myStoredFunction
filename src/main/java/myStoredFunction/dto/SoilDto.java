package myStoredFunction.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class SoilDto {

    private int id;
    private String uuid_t;
    private String p_name;
    private String p_type;
    private int p_type_fk;
}
