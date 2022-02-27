package com.lifanzov.pavel;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class TestDateToCron {

	@Test
	public void dateToCron_1() throws DatesToCronConvertException {
		String cron = "0 0/30 8-9 * * *";
		System.out.println("***************");

		List<String> dates = new ArrayList<>();
		dates.add("2022-01-25T08:00:00");
		dates.add("2022-01-25T08:30:00");
		dates.add("2022-01-25T09:00:00");
		dates.add("2022-01-25T09:30:00");
		dates.add("2022-01-27T08:00:00");
		dates.add("2022-01-27T08:30:00");
		dates.add("2022-01-27T09:00:00");
		dates.add("2022-01-27T09:30:00");
		dates.add("2022-01-29T08:00:00");
		dates.add("2022-01-29T08:30:00");
		dates.add("2022-01-29T09:00:00");
		dates.add("2022-01-29T09:30:00");
		DatesToCronConverterImpl toCron = new DatesToCronConverterImpl();
		String convertResultCron;

		convertResultCron = toCron.convert(dates);
		for (String string : dates) {
			System.out.println(string);
		}
		System.out.println("EXPECTED CRON == " + cron);
		System.out.println("RECEIVED CRON == " + convertResultCron);
		assertEquals(convertResultCron, cron);

	}

	@Test
	public void dateToCron_2() throws DatesToCronConvertException {
		String cron = "0 * * * * MON";
		System.out.println("***************");
		List<String> dates = new ArrayList<>();
		dates.add("2022-01-24T19:53:00");
		dates.add("2022-01-24T19:54:00");
		dates.add("2022-01-24T19:55:00");
		dates.add("2022-01-24T19:56:00");
		dates.add("2022-01-24T19:57:00");
		dates.add("2022-01-24T19:58:00");
		dates.add("2022-01-24T19:59:00");
		dates.add("2022-01-24T20:00:00");
		dates.add("2022-01-24T20:01:00");
		dates.add("2022-01-24T20:02:00");

		DatesToCronConverterImpl toCron = new DatesToCronConverterImpl();
		String convertResultCron;

		convertResultCron = toCron.convert(dates);
		for (String string : dates) {
			System.out.println(string);
		}
		System.out.println("EXPECTED CRON == " + cron);
		System.out.println("RECEIVED CRON == " + convertResultCron);
		assertEquals(convertResultCron, cron);

	}

	@Test
	public void dateToCron_3() throws DatesToCronConvertException {
		String cron = "5 0 0 1 1 *";
		System.out.println("***************");
		List<String> dates = new ArrayList<>();
		dates.add("2022-01-01T00:00:05");
		dates.add("2022-01-01T00:00:05");
		dates.add("2022-01-01T00:00:05");
		dates.add("2022-01-01T00:00:05");
		dates.add("2022-01-01T00:00:05");
		dates.add("2022-01-01T00:00:05");
		dates.add("2022-01-01T00:00:05");
		dates.add("2022-01-01T00:00:05");

		DatesToCronConverterImpl toCron = new DatesToCronConverterImpl();
		String convertResultCron;

		convertResultCron = toCron.convert(dates);
		for (String string : dates) {
			System.out.println(string);
		}
		System.out.println("EXPECTED CRON == " + cron);
		System.out.println("RECEIVED CRON == " + convertResultCron);
		assertEquals(convertResultCron, cron);

	}

	@Test
	public void dateToCron_4() throws DatesToCronConvertException {
		String cron = "0 0/5 8 25 1 *";
		System.out.println("***************");

		List<String> dates = new ArrayList<>();
		dates.add("2022-01-25T08:05:00");
		dates.add("2022-01-25T08:10:00");
		dates.add("2022-01-25T08:15:00");
		dates.add("2022-01-25T08:20:00");
		dates.add("2022-01-25T08:25:00");
		dates.add("2022-01-25T08:30:00");
		dates.add("2022-01-25T08:35:00");
		dates.add("2022-01-25T08:40:00");
		dates.add("2022-01-25T08:45:00");
		dates.add("2022-01-25T08:50:00");
		dates.add("2022-01-25T08:55:00");
		dates.add("2022-01-25T08:00:00");

		DatesToCronConverterImpl toCron = new DatesToCronConverterImpl();
		String convertResultCron;

		convertResultCron = toCron.convert(dates);
		for (String string : dates) {
			System.out.println(string);
		}
		System.out.println("EXPECTED CRON == " + cron);
		System.out.println("RECEIVED CRON == " + convertResultCron);
		assertEquals(convertResultCron, cron);

	}

	@Test
	public void dateToCron_5() throws DatesToCronConvertException {
		String cron = "0 30 1 * * *";
		System.out.println("***************");

		List<String> dates = new ArrayList<>();
		dates.add("2022-01-25T01:30:00");
		dates.add("2022-01-26T01:30:00");
		dates.add("2022-01-27T01:30:00");
		dates.add("2022-01-28T01:30:00");
		dates.add("2022-01-29T01:30:00");
		dates.add("2022-01-30T01:30:00");
		dates.add("2022-01-31T01:30:00");
		dates.add("2022-02-01T01:30:00");

		DatesToCronConverterImpl toCron = new DatesToCronConverterImpl();
		String convertResultCron;

		convertResultCron = toCron.convert(dates);
		for (String string : dates) {
			System.out.println(string);
		}
		System.out.println("EXPECTED CRON == " + cron);
		System.out.println("RECEIVED CRON == " + convertResultCron);
		assertEquals(convertResultCron, cron);

	}
	
	

}
