import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ArcadeDMSTest {

    //Create object to test
    @BeforeEach
    void setUp() {
        ArcadeDMS arcadedms = new ArcadeDMS();
    }


        @Test
        @DisplayName("Add Account Test")
        void testAddAccount() {
            ArcadeDMS dms = new ArcadeDMS();

            ArcadeAccount acc = new ArcadeAccount(
                    "12345", "Micah", LocalDate.now(), 10, 50
            );

            dms.addAccount(acc);

            assertEquals(1, dms.getAccounts().size());
            assertEquals("Micah", dms.getAccounts().get(0).getCustomerName());
        }
    }