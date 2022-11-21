package com.db.referencedata.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class ChunkBooks {
    @Id
    Integer processId;
    @NotNull
    Integer numChunks;
    @NotNull
    Integer size;
    @NotNull
    List<Book> chunkList;
}
