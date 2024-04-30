package com.mbb.assignment.service.entity;

import com.mbb.assignment.service.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The MBBLoanApplicationEntity class is used to define MBB_LOAN_APPLICATION table.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "MBB_LOAN_APPLICATION")
public class MBBLoanApplicationEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7863861111403092424L;

    /**
     * Id
     */
    public static final String ID = "id";

    /**
     * Applicant Name
     */
    public static final String APPLICANT_NAME = "applicantName";

    /**
     * Status
     */
    public static final String AMOUNT = "amount";

    /**
     * Status
     */
    public static final String CCRIS_SCORE = "ccrisScore";

    /**
     * Status
     */
    public static final String CTOS_SCORE = "ctosScore";

    /**
     * Status
     */
    public static final String STATUS = "status";

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", length = 40, nullable = false)
    private String id;

    /**
     * applicantName
     */
    @Column(name = "APPLICANT_NAME", length = 500)
    private String applicantName;

    /**
     * code
     */
    @Column(name = "AMOUNT", length = 50, nullable = false)
    private BigDecimal amount;

    /**
     * ccrisScore
     */
    @Column(name = "CCRIS_SCORE", length = 50)
    private BigDecimal ccrisScore;

    /**
     * ctosScore
     */
    @Column(name = "CTOS_SCORE", length = 50)
    private BigDecimal ctosScore;

    /**
     * status
     */
    @Column(name = "STATUS", length = 50, nullable = false)
    private String status;

}