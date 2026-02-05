package jc.team.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDetailDto {
    private Long id;
    private String fullName;
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
    private LocalDate registrationDate;
    
    private ContractDto contractInfo;
    private ArchiveDto archiveInfo;
    private WorkHistoryDto workHistoryInfo;
    private List<HistoryStageDto> historyStages;
    private List<NoteDto> notes;
}
