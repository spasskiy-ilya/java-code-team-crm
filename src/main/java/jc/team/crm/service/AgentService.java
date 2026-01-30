package jc.team.crm.service;

import jc.team.crm.dto.AgentDetailDto;
import jc.team.crm.dto.UpdateAgentInfoDto;
import jc.team.crm.dto.UpdateArchiveDto;
import jc.team.crm.dto.UpdateContractDto;
import jc.team.crm.entity.AgentEntity;
import jc.team.crm.mapper.AgentDetailMapper;
import jc.team.crm.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepository agentRepository;
    private final AgentDetailMapper mapper;

    public AgentDetailDto getAgentDetail(Long agentId) {
        AgentEntity agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new IllegalArgumentException("Агент не найден"));
        return mapper.toAgentDetailDto(agent);
    }

    @Transactional
    public void updateAgentInfo(UpdateAgentInfoDto dto) {
        AgentEntity agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("Агент не найден"));

        if (agent.getInfo() != null) {
            agent.getInfo().setName(dto.getFirstName());
            agent.getInfo().setSecondName(dto.getLastName());
            agent.getInfo().setFatherName(dto.getFatherName());
            agent.getInfo().setBirthdayDate(dto.getBirthdayDate());
            agent.getInfo().setCountry(dto.getCountry());
            agent.getInfo().setAddress(dto.getAddress());
            agent.getInfo().setPhoneNumber(dto.getPhoneNumber());
            agent.getInfo().setTelegramNikName(dto.getTelegram());
            agent.getInfo().setEmail(dto.getEmail());
            agent.getInfo().setPortalLogin(dto.getPortalLogin());
            agent.getInfo().setPortalPassword(dto.getPortalPassword());
            agent.getInfo().setDiscordLogin(dto.getDiscordLogin());
        }

        agentRepository.save(agent);
    }

    @Transactional
    public void updateContract(UpdateContractDto dto) {
        AgentEntity agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("Агент не найден"));

        if (agent.getAgentHistory() == null) {
            throw new IllegalArgumentException("История агента не найдена");
        }

        var contractHistory = agent.getAgentHistory().getContractHistory();
        if (contractHistory == null) {
            throw new IllegalArgumentException("Договор не найден");
        }

        if (dto.getSigned() != null) {
            contractHistory.setSigned(dto.getSigned());
        }
        if (dto.getSignDate() != null) {
            contractHistory.setSignDate(dto.getSignDate());
        }
        if (dto.getReSignDate() != null) {
            contractHistory.setReSignDate(dto.getReSignDate());
        }
        if (dto.getBrokeDate() != null) {
            contractHistory.setBrokeDate(dto.getBrokeDate());
        }

        agentRepository.save(agent);
    }

    @Transactional
    public void updateArchiveInfo(UpdateArchiveDto dto) {
        AgentEntity agent = agentRepository.findById(dto.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("Агент не найден"));

        if (agent.getAgentHistory() == null) {
            throw new IllegalArgumentException("История агента не найдена");
        }

        var archiveHistory = agent.getAgentHistory().getArchiveHistory();
        if (archiveHistory == null) {
            throw new IllegalArgumentException("Информация об архивации не найдена");
        }

        if (dto.getArchiveDate() != null) {
            archiveHistory.setArchiveDate(dto.getArchiveDate());
        }
        if (dto.getReason() != null) {
            archiveHistory.setReason(dto.getReason());
        }

        agentRepository.save(agent);
    }

    public UpdateAgentInfoDto prepareUpdateAgentInfoDto(Long agentId) {
        AgentDetailDto agentDetail = getAgentDetail(agentId);
        UpdateAgentInfoDto dto = new UpdateAgentInfoDto();
        dto.setAgentId(agentId);
        dto.setFirstName(agentDetail.getFirstName());
        dto.setLastName(agentDetail.getLastName());
        dto.setFatherName(agentDetail.getFatherName());
        dto.setBirthdayDate(agentDetail.getBirthdayDate());
        dto.setCountry(agentDetail.getCountry());
        dto.setAddress(agentDetail.getAddress());
        dto.setPhoneNumber(agentDetail.getPhoneNumber());
        dto.setTelegram(agentDetail.getTelegram());
        dto.setEmail(agentDetail.getEmail());
        dto.setPortalLogin(agentDetail.getPortalLogin());
        dto.setPortalPassword(agentDetail.getPortalPassword());
        dto.setDiscordLogin(agentDetail.getDiscordLogin());
        return dto;
    }

    public UpdateContractDto prepareUpdateContractDto(Long agentId) {
        AgentDetailDto agentDetail = getAgentDetail(agentId);
        UpdateContractDto dto = new UpdateContractDto();
        dto.setAgentId(agentId);
        if (agentDetail.getContractInfo() != null) {
            dto.setSigned(agentDetail.getContractInfo().getSigned());
            dto.setSignDate(agentDetail.getContractInfo().getSignDate());
            dto.setReSignDate(agentDetail.getContractInfo().getReSignDate());
            dto.setBrokeDate(agentDetail.getContractInfo().getBrokeDate());
        }
        return dto;
    }

    public UpdateArchiveDto prepareUpdateArchiveDto(Long agentId) {
        AgentDetailDto agentDetail = getAgentDetail(agentId);
        UpdateArchiveDto dto = new UpdateArchiveDto();
        dto.setAgentId(agentId);
        if (agentDetail.getArchiveInfo() != null) {
            dto.setArchiveDate(agentDetail.getArchiveInfo().getArchiveDate());
            dto.setReason(agentDetail.getArchiveInfo().getReason());
        }
        return dto;
    }
}
