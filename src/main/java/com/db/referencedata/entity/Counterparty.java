package com.db.referencedata.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Counterparty {
    @Id
    Integer counterpartyId;
    @NotNull
    String counterpartyName;
    @NotNull
    String source;
    @NotNull
    String entity;
}
