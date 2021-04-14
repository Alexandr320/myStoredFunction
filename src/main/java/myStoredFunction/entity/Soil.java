package myStoredFunction.entity;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Soil {

    private Long id;
    private String uuidT;
    private String pName;
    private String pType;
    private Integer pTypeFk;

}
