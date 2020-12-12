package framework.tests;

import framework.basetests.BaseTest;
import org.junit.jupiter.api.Test;


public class SberTest extends BaseTest {

    @Test
    public void startTest() {
        app.getStartPage()
                .closeCookieContent()
                .selectBaseMenu("Ипотека")
                .selectSubMenu("Ипотека на готовое жильё")
                .fillField("Стоимость недвижимости", "5 180 000")
                .fillField("Первоначальный взнос", "3 058 000")
                .fillField("Срок кредита", "30")
                .installationOfAdditionalServices("Страхование жизни", false)
                .installationOfAdditionalServices("Молодая семья", true)
                .checkValueField("Сумма кредита", "2 122 000 ₽")
                .checkValueField("Ежемесячный платеж", "16 017 ₽")
                .checkValueField("Необходимый доход", "20 618 ₽")
                .checkValueField("Процентная ставка", "11%");
    }
}
