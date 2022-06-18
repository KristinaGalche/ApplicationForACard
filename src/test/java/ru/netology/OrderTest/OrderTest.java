package ru.netology.OrderTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class OrderTest {

    @BeforeEach
    public void openApp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSendFormValid() {
        $("[data-test-id=name] input").setValue("Иванова Ольга");
        $("[data-test-id=phone] input").setValue("+79516663322");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldSendFormValid2() {
        $("[data-test-id=name] input").setValue("Иванова-Попова Ольга");
        $("[data-test-id=phone] input").setValue("+79516663322");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldntSendFormInvalidName() {
        $("[data-test-id=name] input").setValue("Ivanov Petr");
        $("[data-test-id=phone] input").setValue("+79516663322");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldntSendFormInvalidName2() {
        $("[data-test-id=name] input").setValue("Маргарита12");
        $("[data-test-id=phone] input").setValue("+79516663322");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    public void shouldntSendFormInvalidPhone() {
        $("[data-test-id=name] input").setValue("Иванова Ольга");
        $("[data-test-id=phone] input").setValue("89516663322");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldntSendFormInvalidPhone2() {
        $("[data-test-id=name] input").setValue("Иванова Ольга");
        $("[data-test-id=phone] input").setValue("9516663322");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }


    @Test
    public void emptyForm() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79516663322");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void emptyForm2() {
        $("[data-test-id=name] input").setValue("Иванова Ольга");
        $("[data-test-id=phone] input").setValue("  ");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldntSendFormNoAgree() {
        $("[data-test-id=name] input").setValue("Иванова Ольга");
        $("[data-test-id=phone] input").setValue("+79516663322");
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
