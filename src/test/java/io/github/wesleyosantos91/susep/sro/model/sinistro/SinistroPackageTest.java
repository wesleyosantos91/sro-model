package io.github.wesleyosantos91.susep.sro.model.sinistro;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class SinistroPackageTest {

    @Test
    void deveCriarSinistro() {
        var obj = new Sinistro(1, LocalDate.now(), LocalDate.now(), LocalDate.now(), 
            LocalDate.now(), null, List.of(), List.of(), List.of(), List.of(), List.of());
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarDocumentoAfetado() {
        var obj = new DocumentoAfetado("APOL1", null, null);
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarCoberturaAfetada() {
        var obj = new CoberturaAfetada(null, "0531", null, null, null, null, null, null);
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarVistoriaRural() {
        var obj = new VistoriaRural("SP", null, "BRA");
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarJustificativaNegativa() {
        var obj = new JustificativaNegativa(1, null);
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarAutomovel() {
        var obj = new Automovel("OBJ1", null, null, null, null, null);
        assertThat(obj).isNotNull();
    }
}
