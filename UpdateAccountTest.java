import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class UpdateAccountTest {
    @BeforeEach
    void setUp() {
        ArcadeDMS arcadedms = new ArcadeDMS();
    }

    @Test
    @DisplayName("Update Account Test")
    void testUpdateAccount() {
        ArcadeDMS dms = new ArcadeDMS();

        ArcadeAccount acc = new ArcadeAccount(
                "12345", "Micah", LocalDate.now(), 10, 50
        );

        dms.addAccount(acc);

        boolean updated = dms.updateAccount("12345", 120);

        assertTrue(updated);
        assertEquals(120, dms.getAccounts().get(0).getTokensEarned());
    }
}