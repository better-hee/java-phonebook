package com.betterhee.phonebook;

import java.util.Scanner;

interface MENU {
	int ADD = 1, SEARCH = 2, DELETE = 3, EXIT = 4;
}

interface RELATION {
	int COMMON = 1, UNIVERSITY = 2, COMPANY = 3;
}

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
	private static PhoneBookHandler instance = null;
	private PhoneInfo[] phoneInfoList;
	private int numOfPhoneInfo;
	public Scanner sc = new Scanner(System.in);

	private PhoneBookHandler() {
		phoneInfoList = new PhoneInfo[100];
		numOfPhoneInfo = 0;
	}

	public static PhoneBookHandler getInstance() {
		if (instance == null)
			instance = new PhoneBookHandler();
		return instance;
	}

	public int indexOf(String name) {

		for (int i = 0; i < numOfPhoneInfo; i++) {
			if (phoneInfoList[i].getName().equals(name))
				return i;
		}

		return -1;
	}

	public void add(int choice) throws MenuChoiceException {

		System.out.print("이름: ");
		String name = sc.nextLine();
		System.out.print("전화: ");
		String phoneNumber = sc.nextLine();

		switch (choice) {
		case RELATION.COMMON:
			phoneInfoList[numOfPhoneInfo++] = new PhoneInfo(name, phoneNumber);
			break;
		case RELATION.UNIVERSITY:
			System.out.print("전공: ");
			String major = sc.nextLine();
			System.out.print("학년: ");
			int year = sc.nextInt();
			sc.nextLine();
			phoneInfoList[numOfPhoneInfo++] = new PhoneUnivInfo(name, phoneNumber, major, year);
			break;
		case RELATION.COMPANY:
			System.out.print("회사: ");
			String company = sc.nextLine();
			phoneInfoList[numOfPhoneInfo++] = new PhoneCompanyInfo(name, phoneNumber, company);
			break;
		default:
			throw new MenuChoiceException(choice);
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

class MenuChoiceException extends Exception {

	public MenuChoiceException(Object choice) {
		super("잘못된 메뉴 선택: " + choice.toString());
	}
}

class PhoneBook {
	public static void main(String[] args) {
		PhoneBookHandler handler = PhoneBookHandler.getInstance();

		while (true) {
			System.out.println("*** 메뉴 선택 ***");
			System.out.println("1. 데이터 입력");
			System.out.println("2. 데이터 검색");
			System.out.println("3. 데이터 삭제");
			System.out.println("4. 프로그램 종료");
			System.out.print("선택: ");

			int choice = handler.sc.nextInt();
			handler.sc.nextLine();

			try {
				switch (choice) {
				case MENU.ADD:
					System.out.println("1. 일반, 2. 대학, 3.회사");
					System.out.print("선택>> ");
					int type = handler.sc.nextInt();
					handler.sc.nextLine();
					handler.add(type);
					break;

				case MENU.SEARCH:
					System.out.print("이름: ");
					handler.search(handler.sc.nextLine());
					break;

				case MENU.DELETE:
					System.out.print("이름: ");
					handler.delete(handler.sc.nextLine());
					break;

				case MENU.EXIT:
					System.out.println("프로그램을 종료합니다..");
					handler.sc.close();
					return;

				default:
					throw new MenuChoiceException(choice);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
}