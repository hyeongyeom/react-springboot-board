package board.boardspring.domain.entitiy;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="user",uniqueConstraints={
        @UniqueConstraint(columnNames = "email")
})
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto created ID
    private Long id;
    @NotNull
    @Column(length = 30,nullable = false)
    private String email;
    @NotNull
    @Column(length = 20,nullable = false)
    private String password;
    @NotNull
    @Column(length = 10,nullable = false)
    private String nickname;


}