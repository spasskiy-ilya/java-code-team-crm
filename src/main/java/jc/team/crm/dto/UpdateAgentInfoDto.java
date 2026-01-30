package jc.team.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAgentInfoDto {
    private Long agentId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private LocalDate birthdayDate;
    private String country;
    private String address;
    private String phoneNumber;
    private String telegram;
    private String email;
    private String portalLogin;
    private String portalPassword;
    private String discordLogin;
}
