import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileloadTest {

    @BeforeEach
    void setUp() {
        ArcadeDMS arcadedms = new ArcadeDMS();
    }

    @Test
    void testFileLoad() throws Exception {
        File tempFile = File.createTempFile("testFile", ".txt");

        FileWriter writer = new FileWriter(tempFile);
        writer.write("12345 - Micah - 2026-02-14 - 10 - 50\n");
        writer.close();

        List<String> lines = Files.readAllLines(tempFile.toPath());

        assertNotNull(lines);
        assertFalse(lines.isEmpty());
        assertTrue(lines.get(0).contains("Micah"));

        tempFile.delete();
    }
}