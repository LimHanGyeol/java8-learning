package com.tommy.java8learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeTest {

    @Test
    @DisplayName("기계용 시간을 Instant 로 구현")
    @Description("기준시 UTC, GMT")
    void instant() {
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
        assertThat(zonedDateTime).isNotNull();
    }

    @Test
    @DisplayName("인류용 시간을 LocalDateTime 으로 구현")
    void localDateTime() {
        LocalDateTime now = LocalDateTime.now();
        assertThat(now).isNotNull();
    }

    @Test
    @DisplayName("LocalDateTime.of()를 이용하여 특정 시간대 구현")
    void localDateTimeOf() {
        LocalDateTime birthDay =
                LocalDateTime.of(1995, Month.JANUARY, 9, 9, 57, 0);
        System.out.println(birthDay);
        assertThat(birthDay).isNotNull();
    }

    @Test
    @DisplayName("특정 Zone 의 시간대를 보고 싶을 경우")
    void zonedDateTime() {
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);
        assertThat(nowInKorea).isNotNull();
    }

    @Test
    @DisplayName("instant 도 zonedDateTime 으로 나타낼 수 있다.")
    @Description("ZonedDateTime 에서 Instant, LocalDateTime 으로 변환이 가능하다.")
    void zonedDateTimeIsInstant() {
        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime);
    }

    @Test
    @DisplayName("인류용 시간인 Period 로 날짜 비교")
    void getPeriod() {
        LocalDate today = LocalDate.now();
        LocalDate nextYearBirthDay = LocalDate.of(2021, Month.JANUARY, 9);

        Period period = Period.between(today, nextYearBirthDay);
        System.out.println(period);
        assertThat(period).isEqualTo(Period.parse("P2M8D"));

        Period until = today.until(nextYearBirthDay); // until 로도 기간의 차이를 구할 수 있다.
        System.out.println(until);
        assertThat(period).isEqualTo(Period.parse("P2M8D"));
    }

    @Test
    @DisplayName("기계용 시간인 Instant 로 Duration 을 이용한 날짜 비교")
    void getInstant() {
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration duration = Duration.between(now, plus);
        System.out.println(duration);
        assertThat(duration).isEqualTo(Duration.parse("PT10S"));
    }

    @Test
    @DisplayName("날짜 Formatting")
    void formatting() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
        System.out.println(now.format(formatter));
        assertThat(now.format(formatter)).isEqualTo("2020.11.01.");
    }

    @Test
    @DisplayName("LocalDate 파싱")
    void parse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
        LocalDate parse = LocalDate.parse("1995.01.09.", formatter);
        System.out.println(parse);
        assertThat(parse).isEqualTo("1995-01-09");
    }

    @Test
    @DisplayName("레거시 API 호환")
    @Description("Instant 로 바꿀 수 있으면 최신 API 를 LocalDateTime 등으로도 바꿀 수 있다.")
    void legacyApi() {
        Date date = new Date();
        Instant instant = date.toInstant();
        Date newDate = Date.from(instant);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());

        GregorianCalendar from = GregorianCalendar.from(dateTime);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
    }

    @Test
    @DisplayName("추가적인 연산")
    @Description("새로운 Date API 는 추가적인 인스턴스를 생성해주어 새롭게 인스턴스를 할당 해야 한다.")
    void operator() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(10, ChronoUnit.DAYS);
    }
}
