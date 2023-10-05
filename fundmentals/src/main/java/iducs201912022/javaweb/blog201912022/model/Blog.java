package iducs201912022.javaweb.blog201912022.model;


import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Blog {
    private long id;
    private String title;
    private String content;
    private String name;
    private String email;
    private String regdate;
}
