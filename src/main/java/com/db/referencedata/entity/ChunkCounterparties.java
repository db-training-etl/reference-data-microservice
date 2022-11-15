package com.db.referencedata.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ChunkCounterparties {
    @Id
    Integer processId;
    @NotNull
    Integer numChunks;
    @NotNull
    Integer size;
    @NotNull
    List<Counterparty> data;
}
