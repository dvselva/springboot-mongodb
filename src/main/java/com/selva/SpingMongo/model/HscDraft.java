package com.selva.SpingMongo.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="hsc")
public class HscDraft {

    @Id
    private String id;

    private Integer hscDraftId;
    private Date createDateTimestamp;

    @NotNull(message="createdByUser can not be null")
    private String createdByUserId;
    private Date lastModifiedTimestamp;
    private String lastModifiedByUserId;
    private Integer updatedVersionNumber;
    private String providerFullName;
    private String ndbMpin;
    private String federalTaxID;
    private String memberIDType;
    private String memberIDText;
    private String memberFirstName;
    private String memberLastName;
    private String serviceSettingType;
    private String placeOfServiceCode;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private Object hscDraftLargeObject;
}
