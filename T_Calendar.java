import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.Locale;

/**
 * @author Cruz Guo
 * @version 1.3.1
 * This Java application is a modern calendar where the current date is determined based on the time zone entered.
 */

public class T_Calendar {
    public static void main(String[] args) {
        System.out.println("All dates should be entered in numeric form.");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Year: ");
        int selectedYear = scan.nextInt();
        System.out.println("Enter the Month: ");
        int selectMonth = scan.nextInt() - 1; //We subtract one because January starts as 0 in Index

        System.out.println("Enter the Month To Do List. This is Optional. If you do not wish to add anything, type in 0.");
        int doMonth = scan.nextInt() - 1;
        ToDoList first = new ToDoList(doMonth);
        String monthlyDo = first.monthlyDo();

        Calendar cal = new GregorianCalendar();
        int cDay = cal.get(Calendar.DATE);
        int cMonth = cal.get(Calendar.MONTH);
        int cYear = cal.get(Calendar.YEAR);

        GregorianCalendar gCal = new GregorianCalendar(selectedYear, selectMonth, 1);
        int days = gCal.getActualMaximum(Calendar.DATE);
        int startInWeek = gCal.get(Calendar.DAY_OF_WEEK);

        gCal = new GregorianCalendar(selectedYear, selectMonth, days);
        int totalWeeks = gCal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        boolean isLeapYear = gCal.isLeapYear(selectedYear);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        System.out.println();

        Instant instant = Instant.now();
        System.out.println("Enter a TimeZone in the format Continent/City");
        Set< String > ids = ZoneId.getAvailableZoneIds();
        System.out.printf("Available zone ids %s%n", ids);
        scan.nextLine();
        String timeZone = scan.nextLine();
        ZoneId z = ZoneId.of(timeZone) ;
        ZonedDateTime zdt = instant.atZone(z) ;
        Locale l = Locale.US;
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(l);
        System.out.println(zdt.format(f));
        System.out.printf("%n%n%n%n");

        LocalDate ld = LocalDate.now( z ) ;
        int dayOfMonth = ld.getDayOfMonth();

        System.out.println(months[selectMonth] + " / " + selectedYear);
        System.out.println();
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        int count = 1;
        for (int i = 1; i <= totalWeeks; i++) {
            System.out.println();
            for (int j = 1; j <= 7; j++) {
                if (count < startInWeek || (count - startInWeek + 1) > 31) {
                    System.out.print("   ");
                    System.out.print(" ");
                } else {
                    if (dayOfMonth == (count - startInWeek + 1) && cMonth == selectMonth && cYear == selectedYear) {
                        System.out.print("'" + getDay(count - startInWeek + 1, isLeapYear, selectMonth) + "'");
                    } else {
                        System.out.print(" ");
                        System.out.print(getDay(count - startInWeek + 1, isLeapYear, selectMonth));
                        System.out.print(" ");
                    }
                }
                count++;
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Monthly To-Do List: ");
        if (selectMonth == doMonth) {
            System.out.println(monthlyDo);
        }
    }
    private static String getDay(int i, boolean isLeapYear, int month) {
        String sDate = Integer.toString(i);
        if (sDate.length() == 1) {
            sDate = "0" + sDate;
            return sDate;
        } else if (sDate.compareTo("29") > 0 && isLeapYear && month == 1) {
            return "";
        } else if (sDate.compareTo("28") > 0 && !isLeapYear && month == 1) {
            return "";
        } else if (sDate.compareTo("30") > 0 && (month == 3 || month == 5 || month == 8 || month == 11)) {
            return "";
        } else {
            return sDate;
        }
    }
}
