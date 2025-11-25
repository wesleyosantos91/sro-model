package io.github.wesleyosantos91.susep.sro.model.movimentopremio;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class MovimentoPremioPackageTest {

    @Test
    void deveCriarMovimentoPremio() {
        var obj = new MovimentoPremio("7d9b5524-d047-40ee-8f9c-62c0c8e87d8e", null, "12345", LocalDate.now(), 
            LocalDate.now(), 2, "APOL1", null, null, "MOV1", "BRL", 
            1000.0, 1000.0, LocalDate.now(), null, null, 1, null, "0531", 
            "OBJ1", "COB1", LocalDate.now().minusDays(1), LocalDate.now(), 
            null, null, 1000.0, 1000.0, null, null, null);
        assertThat(obj).isNotNull();
    }
}
