package com.lp.techDemo.util;


public class InstitutionContractVO {

	
	private String fromUserId;
	
	private String fromUserName;
	
	private String toUserId;
	
	private String toUserName;
	
	private String amount;
	
	private String entrustmentContractId;
	
	private String entrustmentDate;
	
	private String deadline;
	private long applyId;
	
	//签名信息
	
	private String signInfo;
	
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getEntrustmentContractId() {
		return entrustmentContractId;
	}
	public void setEntrustmentContractId(String entrustmentContractId) {
		this.entrustmentContractId = entrustmentContractId;
	}
	public String getEntrustmentDate() {
		return entrustmentDate;
	}
	public void setEntrustmentDate(String entrustmentDate) {
		this.entrustmentDate = entrustmentDate;
	}
	public String getSignInfo() {
		return signInfo;
	}
	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public long getApplyId() {
		return applyId;
	}
	public void setApplyId(long applyId) {
		this.applyId = applyId;
	}
	
	@Override
	public String toString() {
		return "InstitutionContractVO [entrustmentContractId=" + entrustmentContractId + "]";
	}
}
