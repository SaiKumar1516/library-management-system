package com.lms;

public class Borrower {
	
	private int membershipId;
    private String name;
    private String contactDetails;
    
    
    
	public Borrower( int membershipId,String name, String contactDetails) {
		this.membershipId=membershipId;
		this.name = name;
		this.contactDetails = contactDetails;
		
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContactDetails() {
		return contactDetails;
	}


	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}


	public int getMembershipId() {
		return membershipId;
	}


	public void setMembershipId(int membershipId) {
		this.membershipId = membershipId;
	}
	
	
    
    

}
