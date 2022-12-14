package board.boardspring.domain.entitiy;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name="user",uniqueConstraints={
        @UniqueConstraint(columnNames = "email")
})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto created ID
    private Long id;
    @NotNull
    @Column(length = 30,nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(length = 10,nullable = false)
    private String nickname;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();


}