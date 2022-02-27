package com.lifanzov.pavel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DatesToCronConverterImpl implements DatesToCronConverter {

	private final int SECOND = 0;
	private final int MINUTE = 1;
	private final int HOUR = 2;
	private final int DAY = 3;
	private final int MONTH = 4;
	private final int DOW = 5;
	private final int DATE_COMPONENT_AMOUNT = 6;

	private DateComponentsHandler[] dateComponentsHandler;

	@Override
	public String convert(final List<String> listOfStringDates) throws DatesToCronConvertException,
			DateTimeParseException, IllegalArgumentException, IndexOutOfBoundsException {

		System.out.println(getImplementationInfo());
		
		if (listOfStringDates.size() < 2) {
			throw new DatesToCronConvertException("Two or more dates expected");
		}

		ArrayList<LocalDateTime> listOfDates = convertStringToDateTime(listOfStringDates);
		Collections.sort(listOfDates);

		analyzeDates(listOfDates);
		return collectCrone();
	}

//	search():
	private void analyzeDates(final ArrayList<LocalDateTime> listOfDates) throws IndexOutOfBoundsException {

		dateComponentsHandler = new DateComponentsHandler[DATE_COMPONENT_AMOUNT];

		for (int i = 0; i < dateComponentsHandler.length; i++) {
			dateComponentsHandler[i] = new DateComponentsHandler();
		}

		for (int i = 0; i < listOfDates.size() - 1; i++) {
			LocalDateTime current;
			LocalDateTime next;

			try {
				current = listOfDates.get(i);
				next = listOfDates.get(i + 1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e.toString());
				throw e;
			}

			dateComponentsHandler[SECOND].dateProcess(current.getSecond(), next.getSecond(),
					ChronoUnit.SECONDS.between(current, next), i);

			dateComponentsHandler[MINUTE].dateProcess(current.getMinute(), next.getMinute(),
					ChronoUnit.MINUTES.between(current, next), i);

			dateComponentsHandler[HOUR].dateProcess(current.getHour(), next.getHour(),
					ChronoUnit.HOURS.between(current, next), i);

			dateComponentsHandler[DAY].dateProcess(current.getDayOfMonth(), next.getDayOfMonth(),
					ChronoUnit.DAYS.between(current, next), i);

			dateComponentsHandler[MONTH].dateProcess(current.getMonthValue(), next.getMonthValue(),
					ChronoUnit.MONTHS.between(current, next), i);

			dateComponentsHandler[DOW].dateProcess(current.getDayOfWeek(), next.getDayOfWeek());

//			 last iteration:
			if (listOfDates.size() == i + 2) {
				parsingDateComponents(next);
			}
		}

	}

//
	private void parsingDateComponents(final LocalDateTime lastElement) {

		dateComponentsHandler[SECOND].cronPrepare(String.valueOf(lastElement.getSecond()),
				isPreviousComponentsAllEqual(SECOND) || dateComponentsHandler[SECOND].cronRangeValue != "*");

		dateComponentsHandler[MINUTE].cronPrepare(String.valueOf(lastElement.getMinute()),
				isPreviousComponentsAllEqual(MINUTE) || dateComponentsHandler[SECOND].cronRangeValue != "*");

		dateComponentsHandler[HOUR].cronPrepare(String.valueOf(lastElement.getHour()),
				isPreviousComponentsAllEqual(HOUR) || dateComponentsHandler[MINUTE].cronRangeValue != "*");

		dateComponentsHandler[DAY].cronPrepare(String.valueOf(lastElement.getDayOfMonth()),
				isPreviousComponentsAllEqual(DAY) || dateComponentsHandler[HOUR].cronRangeValue != "*");

		dateComponentsHandler[MONTH].cronPrepare(String.valueOf(lastElement.getMonthValue()),
				isPreviousComponentsAllEqual(MONTH) || dateComponentsHandler[DAY].cronRangeValue != "*");

		if (dateComponentsHandler[DOW].isAllEqual && dateComponentsHandler[DAY].cronRangeValue == "*") {
			dateComponentsHandler[DOW].cronRangeValue = lastElement.getDayOfWeek().toString().substring(0, 3);
		} else {
			dateComponentsHandler[DOW].cronRangeValue = "*";
		}

	}

//
	private String collectCrone() throws DatesToCronConvertException {

		String cron = "";

		for (int i = 0; i < dateComponentsHandler.length; i++) {
			cron = cron + dateComponentsHandler[i].cronRangeValue + " ";
		}

		cron = cron.trim();
		if (cron == "* * * * * *") {
			throw new DatesToCronConvertException("Unable to convert dates to Cron");
		}
		return cron;
	}

//	
	private boolean isPreviousComponentsAllEqual(final int componentIndex) {
		boolean prevComponentsAllEqual = true;

		for (int i = 0; i < componentIndex; i++) {
			if (!dateComponentsHandler[i].isAllEqual) {
				prevComponentsAllEqual = false;
				break;
			}
		}
		return prevComponentsAllEqual;
	}

//	
	private ArrayList<LocalDateTime> convertStringToDateTime(final List<String> listOfStringDates)
			throws DateTimeParseException, IllegalArgumentException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);

		ArrayList<LocalDateTime> dates = new ArrayList<>();

		for (int i = 0; i < listOfStringDates.size(); i++) {
			dates.add(LocalDateTime.parse(listOfStringDates.get(i), formatter));
		}

		return dates;
	}

	@Override
	public String getImplementationInfo() {
		String Name = "Лифанцов Павел Юрьевич";
		String className = this.getClass().getSimpleName();
		String packetName = this.getClass().getPackageName();
		String gitHubLink = "https://github.com/Paul-Lv/DatesToCron.git";

		return Name + ", " + className + ", " + packetName + ", " + gitHubLink;
	}

}
