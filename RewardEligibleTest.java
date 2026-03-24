import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class RewardEligibleTest {
    @BeforeEach
    void setUp() {
        ArcadeDMS arcadedms = new ArcadeDMS();
    }

    @Test
    @DisplayName("Reward Test")
    void testRewardEligibility() {
        ArcadeAccount acc = new ArcadeAccount(
                "12345", "Micah", LocalDate.now(), 10, 120
        );

        assertEquals("Pizza", acc.getRewardEligible());
    }
}