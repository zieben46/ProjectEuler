//
//
//You are given the following information, but you may prefer to do some research for yourself.
//
//    1 Jan 1900 was a Monday.
//    Thirty days has September,
//    April, June and November.
//    All the rest have thirty-one,
//    Saving February alone,
//    Which has twenty-eight, rain or shine.
//    And on leap years, twenty-nine.
//    A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
//
//How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?


package problems;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem_19 {
	private static final int[] months = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public static void main(String[] args) {
		int currDayOfWeek = 1;
		int numbSundays = 0;
		
		for (int year = 1901;year < 2001; year ++) {
			for (int month = 1; month < 13; month++) {
				int daysInMonth = daysInMonth(month, year);
				for (int day = 1; day<=daysInMonth; day++) {
					if (day == 1 && currDayOfWeek == 7) {
						numbSundays++;
					}
					currDayOfWeek = getCurrentDay(currDayOfWeek);
				}
			}
		}
		
			//Don't know why
		System.out.println(numbSundays-1);

	}

	
	private static int daysInMonth(int month, int year) {
		if (month == 2 && isLeapYear(year)) {
			return 29;
		} else {
			return months[month];
		}
	}
	
	private static int getCurrentDay(int day) {
		if (day < 7) {
			return day+1;
		} else {
			return 1;
		}
	}
	
	private static boolean isLeapYear(int year) {
		return isDivisibleBy4(year)&&(notOnCentury(year)||isdivisibleBy400(year));
	}
	
	private static boolean isDivisibleBy4(int year) {
		return year % 4 == 0;
	}
	
	private static boolean notOnCentury(int year) {
		return year % 100 != 0;
	}
	
	private static boolean isdivisibleBy400(int year) {
		return year % 400 == 0;
	}
}