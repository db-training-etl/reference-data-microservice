package com.db.referencedata.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Counterparty {
    @Id
    Integer counterpartyId;
    String counterpartyName;
    String source;
    String entity;
}
