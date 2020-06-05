package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> list = new LinkedList<>();
        ArrayList<Integer> t=  new ArrayList<>();
        Map<LocalDate,Integer> map = new HashMap<LocalDate, Integer>();
        LocalDate localDate ;
        for (UserMeal i : meals)
        {
            localDate=(LocalDate.of(i.getDateTime().getYear(), i.getDateTime().getMonthValue(), i.getDateTime().getDayOfMonth()));
            if(map.isEmpty()){map.put(localDate,i.getCalories());            }
            else{ if(map.get(localDate)==null){ map.put(localDate,i.getCalories()); }
                  else {map.put(localDate, map.get(localDate) +i.getCalories());}
            }
        }

        for (UserMeal i : meals)
        {
            LocalTime time =LocalTime.of(i.getDateTime().getHour(), i.getDateTime().getMinute());
            localDate=(LocalDate.of(i.getDateTime().getYear(), i.getDateTime().getMonthValue(), i.getDateTime().getDayOfMonth()));
            if(time.isAfter(startTime)&& time.isBefore(endTime)) {
                boolean excess =map.get(localDate) <= caloriesPerDay;
                list.add( new UserMealWithExcess(i.getDateTime() , i.getDescription() , i.getCalories() , excess));
            }
        }
        return list;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
