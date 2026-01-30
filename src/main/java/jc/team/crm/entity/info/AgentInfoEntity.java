package jc.team.crm.entity.info;

import jakarta.persistence.*;
import jc.team.crm.entity.AgentEntity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "agent_info")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_id")
    private AgentEntity agent;

    private String name;

    private String secondName;

    private String fatherName;

    private LocalDate birthdayDate;

    private String country;

    private String address;

    private String phoneNumber;

    private String telegramNikName;

    private String email;

    private String portalLogin;

    private String portalPassword;

    private String discordLogin;

    private LocalDate registrationDate;
}
