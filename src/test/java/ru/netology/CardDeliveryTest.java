package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    private String generatorDate(int addDays, String pattern){
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
 @Test
    public void loadingTheForm (){
        open("http:localhost:9999");
        $("[data-test-id='city' input]").setValue("Новосибирск");
        String planDate = generatorDate(4,"dd.MM.yyyy");
     $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.DELETE);
     $("[data-test-id='date'] input").setValue(planDate);
     $("[data-test-id='name'] input").setValue("Петров Иван");
     $("[data-test-id='phone'] input").setValue("+79156239846");
     $("[data-test-id='agreement']").click();
     $("button.button").click();
     $ (".notification__content")
        .shouldBe(Condition.visible, Duration.ofSeconds(15))
        .shouldHave(Condition.exactText("Встреча успешно забронирована на "+ planDate));
 }
}
