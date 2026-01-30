package jc.team.crm.entity.note;

import jakarta.persistence.*;
import jc.team.crm.entity.abs.BaseNoteEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
public class NoteEntity extends BaseNoteEntity {
}
