package jc.team.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentRegistrationDto {

    private String lastName;

    private String firstName;

    private String telegramLogin;

    private String portalLogin;

    private String portalPassword;
}
