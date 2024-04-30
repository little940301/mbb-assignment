package com.mbb.assignment.service.entity.base;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The BaseEntity class is used to define extra information attributes of product entity
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * created time
     */
    @NotNull
    @CreatedDate
    @Column(name = "CREATED_TIME", nullable = false, length = 25)
    private LocalDateTime createdTime = LocalDateTime.now();

    /**
     * modified time
     */
    @LastModifiedDate
    @Column(name = "MODIFIED_TIME", length = 25)
    private LocalDateTime modifiedTime;

    /**
     * created by user
     */
    @NotNull
    @CreatedBy
    @Column(name = "CREATED_BY_USER", nullable = false, length = 40)
    private String createdByUser = "SYSTEM";

    /**
     * modified by user
     */
    @LastModifiedBy
    @Column(name = "MODIFIED_BY_USER", length = 40)
    private String modifiedByUser;

    /**
     * version id
     */
    @Version
    @Column(name = "VERSION_ID")
    private long versionId = 0;
}
