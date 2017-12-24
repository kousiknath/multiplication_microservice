package microservices.book.socialmultiplication.multiplication.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public final class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private final String alias;

    protected User(){
        this.alias = null;
    }
}
