import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ShareLaneRegistrationTest {

    public static final String URL_ENTRANCE = "https://www.sharelane.com/";
    public static final String URL_REGISTRATION = "https://www.sharelane.com/cgi-bin/register.py";

    @Test
    public void testPositiveRegistration() {
        open(URL_ENTRANCE);
        $(By.xpath("//*[text()='ENTER']"));
        open(URL_REGISTRATION);
        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("[value=Continue")).click();
        $(By.name("first_name")).sendKeys("Mikhail");
        $(By.name("last_name")).sendKeys("Mikhail");
        $(By.name("email")).sendKeys("privet@iii.com");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys("11111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".confirmation_message")).text();
        Assert.assertEquals(result, "Account is created!");
    }

    @Test
    public void testIncorrectZipCode() {
        open(URL_ENTRANCE);
        $(By.xpath("//*[text()='ENTER']"));
        open(URL_REGISTRATION);
        $(By.name("zip_code")).sendKeys("1111");
        $(By.cssSelector("[value=Continue")).click();
        String result = $(By.cssSelector(".error_message")).text();
        Assert.assertEquals(result, "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void testIncorrectPasswordRepeatedData() {
        open(URL_ENTRANCE);
        $(By.xpath("//*[text()='ENTER']"));
        open(URL_REGISTRATION);
        $(By.name("zip_code")).sendKeys("11111");
        $(By.cssSelector("[value=Continue")).click();
        $(By.name("first_name")).sendKeys("Mikhail");
        $(By.name("last_name")).sendKeys("Mikhail");
        $(By.name("email")).sendKeys("privet@iii.com");
        $(By.name("password1")).sendKeys("11111");
        $(By.name("password2")).sendKeys("111");
        $(By.cssSelector("[value=Register]")).click();
        String result = $(By.cssSelector(".error_message")).text();
        Assert.assertEquals(result, "Oops, error on page. Some of your fields have invalid " +
                "data or email was previously used");
    }
}
