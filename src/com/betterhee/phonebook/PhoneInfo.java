package com.betterhee.phonebook;

import java.util.Scanner;

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

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "PhoneInfo [name=" + name + ", phoneNumber=" + phoneNumber + ", birthday=" + birthday + "]";
	}

}

class PhoneBookManager {
	private PhoneInfo[] phoneInfoArray;
	private Scanner sc;
	final int MAX_CNT = 100;
	int curCnt = 0;

	public PhoneBookManager() {
		phoneInfoArray = new PhoneInfo[MAX_CNT];
		sc = new Scanner(System.in);
		showMenu();
	}

	public void showMenu() {
		System.out.println("선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 프로그램 종료");
		choiceMenu();
	}

	public void choiceMenu() {
		System.out.print("선택: ");
		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			addPhoneInfo();
			showMenu();
			break;
		case 2:
			search(sc.nextLine());
			showMenu();
			break;
		case 3:
			deletePhoneInfo(sc.nextLine());
			showMenu();
			break;
		case 4:
			System.out.println("프로그램 종료");
			break;
		}
	}

	public void addPhoneInfo() {
		System.out.print("이름: ");
		String name = sc.nextLine();

		System.out.print("전화번호: ");
		String phoneNumber = sc.nextLine();

		System.out.print("생년월일: ");
		String birthday = sc.nextLine();

		// phoneInfoArray[phoneInfoArray.length] = new PhoneInfo(name, phoneNumber,
		// birthday);
		phoneInfoArray[curCnt++] = new PhoneInfo(name, phoneNumber, birthday);
		System.out.println(phoneInfoArray[curCnt - 1].getName());
		System.out.println(phoneInfoArray[curCnt - 1]);
		System.out.println("데이터 입력이 완료되었습니다.");
		return;
	}

	public void search(String name) {
		System.out.println("데이터 검색을 시작합니다..");

		// for (PhoneInfo pi : phoneInfoArray) {
		// if (name.compareTo(pi.getName()) == 0) {
		// System.out.println(pi);
		// System.out.println("데이터 검색이 완료되었습니다.");
		// return;
		// }
		// }
		for (int i = 0; i < curCnt; i++) {
			if (name.compareTo(phoneInfoArray[i].getName()) == 0) {
				System.out.println(phoneInfoArray[i]);
				System.out.println("데이터 검색이 완료되었습니다.");
				return;
			}
		}
		System.out.println("검색한 데이터가 없습니다.");
		return;
	}

	public void deletePhoneInfo(String name) {
		System.out.println("데이터 삭제를 시작합니다..");

		// for (int i = 0; i < phoneInfoArray.length; i++) {
		// if (phoneInfoArray[i].getName() == name) {
		// while (i < phoneInfoArray.length - 1) {
		// phoneInfoArray[i] = phoneInfoArray[++i];
		// phoneInfoArray[phoneInfoArray.length-1] = null;
		// break;
		// }
		// }
		// }
		for (int i = 0; i < curCnt; i++) {
			if (name.compareTo(phoneInfoArray[i].getName()) == 0) {
				while (i < curCnt) {
					phoneInfoArray[i] = phoneInfoArray[++i];
					curCnt--;
					System.out.println("데이터 삭제가 완료되었습니다.");
					return;
				}
			}
		}

		System.out.println("삭제할 데이터가 없습니다.");
	}

	public static void main(String[] args) {
		PhoneBookManager pbm = new PhoneBookManager();
	}
}