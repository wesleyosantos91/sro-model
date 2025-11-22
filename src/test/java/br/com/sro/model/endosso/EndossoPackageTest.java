package br.com.sro.model.endosso;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class EndossoPackageTest {

    @Test
    void deveCriarEndosso() {
        var obj = new Endosso("uuid", null, "12345", LocalDate.now(), LocalDate.now(), 
            2, 1, "APOL1", null, null, "END1", null, 1, 2, 1, 
            LocalDate.now(), LocalDate.now(), LocalDate.now().plusYears(1), 
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
