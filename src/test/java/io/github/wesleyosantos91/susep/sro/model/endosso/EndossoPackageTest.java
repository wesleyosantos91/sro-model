package io.github.wesleyosantos91.susep.sro.model.endosso;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class EndossoPackageTest {

    @Test
    void deveCriarEndosso() {
        var obj = new Endosso("7d9b5524-d047-40ee-8f9c-62c0c8e87d8e", null, "12345", LocalDate.now(), LocalDate.now(), 
            2, 1, "APOL1", null, null, "END1", null, 1, 2, 1, 
            LocalDate.now(), LocalDate.now().minusDays(1), LocalDate.now(), 
            null, null, "0001", null, null, "BRL", 100000.0, 100000.0, null,
            List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), null, null);
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarEndossoAssociado() {
        var obj = new EndossoAssociado("END_ASSOC1");
        assertThat(obj).isNotNull();
    }
}
