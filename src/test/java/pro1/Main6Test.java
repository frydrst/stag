package pro1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main6Test
{
    @Test
    void test01()
    {
        // TODO 6.2: Oprav test
        assertEquals(
                126493,
                Main6.idOfBestTeacher("KIKM",2024)
        );
    }
}