package com.mbb.assignment.service.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * The MBBLoanApplicationStatusEnum used for loan application status
 *
 * @author little940301@gmail.com
 * @version 1.0
 * @since 1.0
 */
public enum MBBLoanApplicationStatusEnum {

	PENDING("PENDING", "Loan Application is submitted"),
	PROCESSING("PROCESSING", "Loan Application is processing"),
	APPROVED("APPROVED", "Loan Application is approved");

	/**
	 * name
	 */
	private final String name;

	/**
	 * description
	 */
	private final String description;

	MBBLoanApplicationStatusEnum(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	private static final Map<String, MBBLoanApplicationStatusEnum> LOOKUP = new HashMap<String, MBBLoanApplicationStatusEnum>();

	static {
		for (MBBLoanApplicationStatusEnum mbbLoanApplicationStatus : EnumSet
				.allOf(MBBLoanApplicationStatusEnum.class)) {
			LOOKUP.put(mbbLoanApplicationStatus.getName(), mbbLoanApplicationStatus);
		}
	}

	public static MBBLoanApplicationStatusEnum fromName(String name) {
		return LOOKUP.get(name);
	}

}
