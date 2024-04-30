package com.mbb.assignment.service.services;

import com.mbb.assignment.service.constants.ErrorCodesConstant;
import com.mbb.assignment.service.dto.request.MBBLoanApplicationRequestModel;
import com.mbb.assignment.service.dto.response.MBBLoanApplicationResponseModel;
import com.mbb.assignment.service.entity.MBBLoanApplicationEntity;
import com.mbb.assignment.service.enums.MBBLoanApplicationStatusEnum;
import com.mbb.assignment.service.exception.base.ItemDuplicateException;
import com.mbb.assignment.service.exception.base.ItemNotFoundException;
import com.mbb.assignment.service.exception.base.ResourceNotFoundException;
import com.mbb.assignment.service.mappers.MBBLoanApplicationMapper;
import com.mbb.assignment.service.repository.MBBLoanApplicationRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

/**
 * The MBBLoanApplicationService class will be used for MBB Loan Application functions.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class MBBLoanApplicationService {

    /**
     * MBBLoanApplicationRepository
     */
    private final MBBLoanApplicationRepository mbbLoanApplicationRepository;

    /**
     * MBBLoanApplicationMapper
     */
    private final MBBLoanApplicationMapper mbbLoanApplicationMapper;

    /**
     * messageSource
     */
    private final MessageSource messageSource;

    /**
     * This constructor is used to initialize instances created in MBBLoanApplicationService.
     *
     * @param mbbLoanApplicationRepository       instance of MBBLoanApplicationRepository
     * @param mbbLoanApplicationMapper               instance of MBBLoanApplicationMapper
     * @param messageSource                            instance of MessageSource
     * @author little940301@gmail.com
     * @since 1.0
     */
    public MBBLoanApplicationService(MBBLoanApplicationRepository mbbLoanApplicationRepository, MBBLoanApplicationMapper mbbLoanApplicationMapper, MessageSource messageSource) {
        this.mbbLoanApplicationRepository = mbbLoanApplicationRepository;
        this.mbbLoanApplicationMapper = mbbLoanApplicationMapper;
        this.messageSource = messageSource;
    }

    /**
     * Create MBB Loan Application
     *
     * @param mbbLoanApplicationRequestModel MBB Loan Application Request Model.
     * @return mbbLoanApplicationResponseModel MBB Loan Application Response.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public MBBLoanApplicationResponseModel createMBBLoanApplication(
            MBBLoanApplicationRequestModel mbbLoanApplicationRequestModel) {
        if (mbbLoanApplicationRepository.findByApplicantName(
                mbbLoanApplicationRequestModel.getApplicantName()).isPresent()) {
            throw new ItemDuplicateException(messageSource
                    .getMessage(ErrorCodesConstant.LOAN_APPLICATION_DUPLICATE,
                            new Object[]{}, Locale.getDefault()));
        }
        mbbLoanApplicationRequestModel.setStatus(MBBLoanApplicationStatusEnum.PENDING.getName());
        MBBLoanApplicationEntity mbbLoanApplicationEntity =
                mbbLoanApplicationRepository.save(mbbLoanApplicationMapper.map(mbbLoanApplicationRequestModel));
        return mbbLoanApplicationMapper.map(mbbLoanApplicationEntity);
    }

    /**
     * Update MBB Loan Application
     *
     * @param mbbLoanApplicationRequestModel MBB Loan Application Request Model.
     * @return mbbLoanApplicationResponseModel MBB Loan Application Response.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public MBBLoanApplicationResponseModel updateMBBLoanApplication(
            MBBLoanApplicationRequestModel mbbLoanApplicationRequestModel) {

        if (StringUtils.isEmpty(mbbLoanApplicationRequestModel.getId())) {
            throw new ItemNotFoundException(messageSource
                    .getMessage(ErrorCodesConstant.LOAN_APPLICATION_NOT_FOUND,
                            new Object[]{}, Locale.getDefault()));
        }

        Optional<MBBLoanApplicationEntity> mbbLoanApplicationEntityOpt = mbbLoanApplicationRepository.findById(mbbLoanApplicationRequestModel.getId());
        if (!mbbLoanApplicationEntityOpt.isPresent()) {
            throw new ItemNotFoundException(messageSource
                    .getMessage(ErrorCodesConstant.LOAN_APPLICATION_NOT_FOUND,
                            new Object[]{}, Locale.getDefault()));
        } else if (!mbbLoanApplicationEntityOpt.get().getApplicantName().equals(mbbLoanApplicationRequestModel.getApplicantName()) &&
                mbbLoanApplicationRepository.findByApplicantName(mbbLoanApplicationRequestModel.getApplicantName()).isPresent()) {
            throw new ItemDuplicateException(messageSource
                    .getMessage(ErrorCodesConstant.LOAN_APPLICATION_DUPLICATE,
                            new Object[]{}, Locale.getDefault()));
        }
        MBBLoanApplicationEntity mbbLoanApplicationEntity = mbbLoanApplicationMapper.mapExists(mbbLoanApplicationRequestModel, mbbLoanApplicationEntityOpt.get());
        mbbLoanApplicationEntity.setModifiedByUser("SYSTEM");
        mbbLoanApplicationEntity.setModifiedTime(LocalDateTime.now());
        mbbLoanApplicationEntity = mbbLoanApplicationRepository.save(mbbLoanApplicationEntity);
        return mbbLoanApplicationMapper.map(mbbLoanApplicationEntity);
    }

    /**
     * Get List of MBB Loan Application(s)
     *
     * @param mbbLoanApplicationRequestModel MBB Loan Application Request Model.
     * @return mbbLoanApplicationResponseModel MBB Loan Application Response.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Page<MBBLoanApplicationResponseModel> getMBBLoanApplication(MBBLoanApplicationRequestModel mbbLoanApplicationRequestModel, Pageable pageable) {
        Page<MBBLoanApplicationEntity> mbbLoanApplicationEntity = mbbLoanApplicationRepository.findAll(getEnquirySpecification(mbbLoanApplicationRequestModel), pageable);
        if (mbbLoanApplicationEntity.isEmpty()) {
            throw new ItemNotFoundException(messageSource
                    .getMessage(ErrorCodesConstant.LOAN_APPLICATION_NOT_FOUND,
                            new Object[]{}, Locale.getDefault()));
        }
        return mbbLoanApplicationEntity.map(mbbLoanApplicationMapper::map);
    }

    /**
     * Delete MBB Loan Application by Id
     *
     * @param id             MBB Loan Application Id.
     * @return mbbLoanApplicationResponseModel MBB Loan Application Response.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean deleteMBBLoanApplicationById(String id) {
        MBBLoanApplicationEntity mbbLoanApplicationEntity = mbbLoanApplicationRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(messageSource.getMessage(ErrorCodesConstant.LOAN_APPLICATION_NOT_FOUND,
                        new Object[]{}, Locale.getDefault())));
        mbbLoanApplicationRepository.delete(mbbLoanApplicationEntity);
        return true;
    }

    /**
     * Get MBB Loan Application by id
     *
     * @param id MBB Loan Application Id.
     * @return mbbLoanApplicationResponseModelModel MBB Loan Application Response Model.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @Transactional
    public MBBLoanApplicationResponseModel getMBBLoanApplicationById(String id) throws ResourceNotFoundException {
        Optional<MBBLoanApplicationEntity> mbbLoanApplicationEntity = mbbLoanApplicationRepository.findById(id);
        return mbbLoanApplicationEntity.map(mbbLoanApplicationMapper::map).orElseThrow(() -> new ItemNotFoundException(messageSource
                .getMessage(ErrorCodesConstant.LOAN_APPLICATION_NOT_FOUND,
                        new Object[]{}, Locale.getDefault())));
    }

    /**
     * Prepare MBB Loan Application by Criteria
     *
     * @param enquiry MBB Loan Application Request Model
     * @return Specification Specification criteria.
     * @author little940301@gmail.com
     * @since 1.0
     */
    private Specification<MBBLoanApplicationEntity> getEnquirySpecification(MBBLoanApplicationRequestModel enquiry) {
        return (Root<MBBLoanApplicationEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (!StringUtils.isEmpty(enquiry.getId())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(MBBLoanApplicationEntity.ID), enquiry.getId())
                );
            }
            if (!StringUtils.isEmpty(enquiry.getApplicantName())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(MBBLoanApplicationEntity.APPLICANT_NAME), enquiry.getApplicantName())
                );
            }
            if (!StringUtils.isEmpty(enquiry.getStatus())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(MBBLoanApplicationEntity.STATUS), enquiry.getStatus())
                );
            }
            if (enquiry.getAmount() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(MBBLoanApplicationEntity.AMOUNT), enquiry.getAmount())
                );
            }
            if (enquiry.getCcrisScore() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(MBBLoanApplicationEntity.CCRIS_SCORE), enquiry.getCcrisScore())
                );
            }
            if (enquiry.getCtosScore() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get(MBBLoanApplicationEntity.CTOS_SCORE), enquiry.getCtosScore())
                );
            }
            return predicate;
        };
    }

}
