package com.mbb.assignment.service.mappers;

import com.mbb.assignment.service.dto.request.MBBLoanApplicationRequestModel;
import com.mbb.assignment.service.dto.response.MBBLoanApplicationResponseModel;
import com.mbb.assignment.service.entity.MBBLoanApplicationEntity;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * The MBBLoanApplicationMapper is used to map from/to MBB Loan Application's Entity/Model.
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Mapper(componentModel = "spring")
@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public abstract class MBBLoanApplicationMapper {

    /**
     * This method is used for map from MBBLoanApplicationEntity to MBBLoanApplicationResponseModel.
     *
     * @param entity MBB Loan Application Entity
     * @return model MBB Loan Application Response Model
     * @author little940301@gmail.com
     * @since 1.0
     */
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract MBBLoanApplicationResponseModel map(MBBLoanApplicationEntity entity);

    /**
     * This method is used for map from MBBLoanApplicationRequestModel to MBBLoanApplicationEntity.
     *
     * @param model MBB Loan Application Request Model
     * @return entity MBB Loan Application Entity
     * @author little940301@gmail.com
     * @since 1.0
     */
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract MBBLoanApplicationEntity map(MBBLoanApplicationRequestModel model);

    /**
     * This method is used for map from MBBLoanApplicationEntity to existing MBBLoanApplicationRequestModel.
     *
     * @param model MBB Loan Application Request Model.
     * @param entity MBB Loan Application Entity.
     * @return entity MBB Loan Application Entity.
     * @author little940301@gmail.com
     * @since 1.0
     */
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract MBBLoanApplicationEntity mapExists(MBBLoanApplicationRequestModel model, @MappingTarget MBBLoanApplicationEntity entity);

}
