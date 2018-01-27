package com.jiniguez.demo.DTO;

import lombok.Data;

@Data
public class StatisticsDTO {

	private Integer doctorID;
	
	private Integer consultationsAmount;
	
	private Double totalPrice;
	
    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof StatisticsDTO){
        	retVal = ((StatisticsDTO) v).getDoctorID().equals(this.doctorID);
        }

     return retVal;
  }

	public StatisticsDTO(Integer totalConsultations, Integer doctorID, Double price) {
		consultationsAmount = totalConsultations;
		this.doctorID = doctorID;
		this.totalPrice = price;
	}
}
