package spring.January.Member;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Entity
@NoArgsConstructor
@Table(name="Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;

    @Column(unique=true)
    private String email;

    @Builder
    public Member(String username, String password, String email){
        this.username=username;
        this.password=password;
        this.email=email;
    }

}
