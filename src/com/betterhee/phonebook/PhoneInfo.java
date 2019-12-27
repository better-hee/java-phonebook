package com.betterhee.phonebook;

import java.util.Scanner;

class PhoneInfo {
	private String name;
	private String phoneNumber;

	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;

	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "PhoneInfo [name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}

}

class PhoneUnivInfo extends PhoneInfo {
	private String major;
	private int year;

	public PhoneUnivInfo(String name, String phoneNumber, String major, int year) {
		super(name, phoneNumber);
		this.major = major;
		this.year = year;
	}

	@Override
	public String toString() {
		return "PhoneUnivInfo [major=" + major + ", year=" + year + ", toString()=" + super.toString() + "]";
	}
}

class PhoneCompanyInfo extends PhoneInfo {
	private String company;

	public PhoneCompanyInfo(String name, String phoneNumber, String company) {
		super(name, phoneNumber);
		this.company = company;
	}

	@Override
	public String toString() {
		return "PhoneCompanyInfo [company=" + company + ", toString()=" + super.toString() + "]";
	}
}

class PhoneBookHandler {
	private PhoneInfo[] phoneInfoList;
	private int numOfPhoneInfo;
	public Scanner sc = new Scanner(System.in);

	public PhoneBookHandler() {
		phoneInfoList = new PhoneInfo[100];
		numOfPhoneInfo = 0;
	}

	public int indexOf(String name) {

		for (int i = 0; i < numOfPhoneInfo; i++) {
			if (phoneInfoList[i].getName().equals(name))
				return i;
		}

		return -1;
	}

	public void add(int choice) {
		System.out.print("이름: ");
		String name = sc.nextLine();
		System.out.print("전화: ");
		String phoneNumber = sc.nextLine();

		switch (choice) {
		case 1:
			phoneInfoList[numOfPhoneInfo++] = new PhoneInfo(name, phoneNumber);
			break;
		case 2:
			System.out.print("전공: ");
			String major = sc.nextLine();
			System.out.print("학년: ");
			int year = sc.nextInt();
			sc.nextLine();
			phoneInfoList[numOfPhoneInfo++] = new PhoneUnivInfo(name, phoneNumber, major, year);
			break;
		case 3:
			System.out.print("회사: ");
			String company = sc.nextLine();
			phoneInfoList[numOfPhoneInfo++] = new PhoneCompanyInfo(name, phoneNumber, company);
			break;
		}

		System.out.println("입력 완료!");
	}

	public void search(String name) {
		int idx = indexOf(name);

		if (idx == -1) {
			System.out.println(name + "없음");
			return;
		}

		System.out.println(phoneInfoList[idx]);
		System.out.println("검색 완료!");
	}

	public void delete(String name) {
		int idx = indexOf(name);

		if (idx < 0) {
			System.out.println(name + "없음");
			return;
		}

		for (int i = idx; i < numOfPhoneInfo; i++) {
			phoneInfoList[i] = phoneInfoList[i + 1];
		}

		phoneInfoList[--numOfPhoneInfo] = null;
		System.out.println("삭제 완료!");
	}

}

class PhoneBook {
	public static void main(String[] args) {
		PhoneBookHandler handler = new PhoneBookHandler();

		while (true) {
			System.out.println("*** 메뉴 선택 ***");
			System.out.println("1. 데이터 입력");
			System.out.println("2. 데이터 검색");
			System.out.println("3. 데이터 삭제");
			System.out.println("4. 프로그램 종료");
			System.out.print("선택: ");

			int choice = handler.sc.nextInt();
			handler.sc.nextLine();

			switch (choice) {
			case 1:
				System.out.println("1. 일반, 2. 대학, 3.회사");
				System.out.print("선택>> ");
				int type = handler.sc.nextInt();
				handler.sc.nextLine();
				handler.add(type);
				break;
			case 2:
				System.out.print("이름: ");
				handler.search(handler.sc.nextLine());
				break;

			case 3:
				System.out.print("이름: ");
				handler.delete(handler.sc.nextLine());
				break;

			case 4:
				System.out.println("프로그램을 종료합니다..");
				handler.sc.close();
				return;
			}
		}
	}
}