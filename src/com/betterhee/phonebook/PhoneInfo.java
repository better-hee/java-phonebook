package com.betterhee.phonebook;

class PhoneInfo {
	private String name;
	private String phoneNumber;
	private String birthday;
	
	public PhoneInfo(String name, String phoneNumber, String birthday) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
	}

	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = null;
	}

	@Override
	public String toString() {
		return "PhoneInfo [name=" + name + ", phoneNumber=" + phoneNumber + ", birthday=" + birthday + "]";
	}

	public static void main(String[] args) {
		PhoneInfo pi1 = new PhoneInfo("김", "010-0000-0000", "96-01-01");
		PhoneInfo pi2 = new PhoneInfo("이", "010-1111-1111");
		
		System.out.println(pi1);
		System.out.println(pi2);
	}
}
