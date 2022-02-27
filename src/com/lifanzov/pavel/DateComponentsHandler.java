package com.lifanzov.pavel;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DateComponentsHandler {

	protected Boolean isAllEqual = true;
	protected Boolean isBetweenAllEqual = true;
	protected Boolean isPeriodicity = false;
	protected int between = 0;
	protected List<Integer> uniqValues;
	protected String cronRangeValue = null;

//	
	protected void dateProcess(final int current, final int next, final long betweenComponents, final int i) {

		if (i == 0) {

			between = Math.abs(next - current);
			uniqValues = new LinkedList<>();
			uniqValues.add(current);
		}

		if (current != next) {
			isAllEqual = false;
			if (isBetweenAllEqual && between != Math.abs(next - current)) {
				isBetweenAllEqual = false;
			}

			if (!uniqValues.contains(next) && !isPeriodicity) {
				uniqValues.add(next);
			} else if (!isPeriodicity) {
				isPeriodicity = betweenComponents > 0;
			}
		}

	}

//	for dayOfweek:
	public void dateProcess(final DayOfWeek current, final DayOfWeek next) {
		if (current != next) {
			isAllEqual = false;
		}
	}

//	
	protected void cronPrepare(final String lastElement, final boolean prevComponentsAllEqual) {

		if (isAllEqual) {
			if (prevComponentsAllEqual) {
				cronRangeValue = lastElement;
			} else {
				cronRangeValue = "*";
			}
		} else if (isBetweenAllEqual) {
			cronRangeValue = between == 1 ? "*" : "0/" + String.valueOf(between);
		} else if (isPeriodicity) {
			int size = uniqValues.size();
			if ((double) (uniqValues.get(size - 1) - uniqValues.get(0)) / (size - 1) == 1) {

				cronRangeValue = uniqValues.get(0) + "-" + uniqValues.get(size - 1);
			} else {
				cronRangeValue = uniqValues.stream().map(Object::toString).collect(Collectors.joining(","));
			}
		} else {
			cronRangeValue = "*";
		}

	}

}
