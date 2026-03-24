import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class RemoveAccountTest {
    @BeforeEach
    void setUp() {
        ArcadeDMS arcadedms = new ArcadeDMS();
    }

    @Test
    @DisplayName("Remove Account Test")
    void testRemoveAccount() {
        ArcadeDMS dms = new ArcadeDMS();

        ArcadeAccount acc = new ArcadeAccount(
                "12345", "Micah", LocalDate.now(), 10, 50
        );

        dms.addAccount(acc);

        boolean removed = dms.removeAccountById("12345");

        assertTrue(removed);
        assertEquals(0, dms.getAccounts().size());
    }
}