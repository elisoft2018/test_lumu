package org.lumu.testlumu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.StringTokenizer;

public class TestWord {

    private WebDriver driver;
    String phrase = "lumu lumu lumu lumu lumu illuminates illuminates attacks and adversaries lumu illuminates all attacks and adversaries";
    String phraseEmpty = "";

    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://wordcounter.net/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void frecuencyWord() throws Exception {

        int totalLumu = 0;
        int totalIlluminates = 0;
        int totalAttacks = 0;
        int totalAdversaries = 0;
        int totalAnd = 0;
        int totalAll = 0;

        String[] words = phrase.split("\\s+|\\n|,");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("lumu")) {

                totalLumu++;


            } else if (words[i].equals("illuminates")) {

                totalIlluminates++;


            } else if (words[i].equals("attacks")) {

                totalAttacks++;


            } else if (words[i].equals("adversaries")) {

                totalAdversaries++;


            } else if (words[i].equals("and")) {

                totalAnd++;


            } else if (words[i].equals("all")) {

                totalAll++;


            }


        }
        System.out.println("word frequency: ");
        System.out.println("lumu: " + totalLumu);
        System.out.println("totalIlluminates: " + totalIlluminates);
        System.out.println("Attacks: " + totalAttacks);
        System.out.println("Adversaries: " + totalAdversaries);
        System.out.println("And: " + totalAnd);
        System.out.println("All: " + totalAll);

    }

    @Test
    public void testPhrase() throws Exception {
        this.setUp();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/form/div[1]/div/span/textarea")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/form/div[1]/div/span/textarea")).sendKeys(phrase);
        Thread.sleep(5000);
        int totalCharacters = phrase.length();
        StringTokenizer st = new StringTokenizer(phrase);
        int totalWords = st.countTokens();
        String resultExpect = totalWords + " words " +totalCharacters + " characters";
        System.out.println("Resultado Calculado por el test: " + resultExpect);
        String resultText = driver.findElement(By.cssSelector("#editor_container > div:nth-child(7) > div > div:nth-child(1) > h4 > span")).getText();
        System.out.println("Resultado Calculado por el Sitio: " + resultText);
        Assert.assertEquals(resultExpect, resultText);
        this.tearDown();

    }

    @Test
    public void testFailed() throws Exception {
        this.setUp();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/form/div[1]/div/span/textarea")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/form/div[1]/div/span/textarea")).sendKeys(phrase);
        Thread.sleep(5000);
        String text = driver.findElement(By.cssSelector("#editor_container > div:nth-child(7) > div > div:nth-child(1) > h4 > span")).getText();
        Assert.assertEquals(text, phraseEmpty);

        this.tearDown();

    }

//    public void report() throws Exception {
//        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls -lumu");
//        pb.command()
//    }

    public void tearDown() throws Exception {
        driver.quit();
    }
}
