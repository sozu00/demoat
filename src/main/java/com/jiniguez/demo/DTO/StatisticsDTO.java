package com.jiniguez.demo.DTO;

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
	
	public Integer getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}

	public Integer getConsultationsAmount() {
		return consultationsAmount;
	}

	public void setConsultationsAmount(Integer consultationsAmount) {
		this.consultationsAmount = consultationsAmount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
