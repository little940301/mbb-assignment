package com.mbb.assignment.service.repository;

import com.mbb.assignment.service.entity.MBBLoanApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The MBBLoanApplicationRepository is used to handle DB operations for MBBLoanApplicationEntity.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface MBBLoanApplicationRepository extends CrudRepository<MBBLoanApplicationEntity, String>, JpaSpecificationExecutor<MBBLoanApplicationEntity> {

    /**
     * Find MBB Loan Application by MBB Loan Application Id based on its state.
     *
     * @param applicantName MBB Loan Application Applicant Name
     * @return Optional<MBBLoanApplicationEntity> MBB Loan Application Entity.
     * @author little940301@gmail.com
     * @since 1.0
     */
    Optional<MBBLoanApplicationEntity> findByApplicantName(String applicantName);

}