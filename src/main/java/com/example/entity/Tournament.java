package com.example.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@RegisterForReflection
@EqualsAndHashCode
@NamedStoredProcedureQuery(
    name = "findActiveTournaments",
    procedureName = "get_tournaments_by_status",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ch_status", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "new_status_", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "limit_", type = Integer.class)
    }
)
@Entity
@Data
@Table(name = "tournaments", schema = "bingo")
public class Tournament {

    @Id
    Long id;
    @Column(name = "creation_time")
    Date creationTime;
    @Column(name = "update_date")
    Date updateDate;
    Integer status;
}
