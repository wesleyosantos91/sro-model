package br.com.sro.model.complauto;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ComplAutoPackageTest {

    @Test
    void deveCriarComplAuto() {
        var obj = new ComplAuto(null, "C1", 4, null, "Desc", 1, 1, 
            BigDecimal.TEN, 1, "M1", 2023, "CAT", "12345678", 1, 
            "12345678", "12345678", BigDecimal.TEN, 5, 
            List.of(), List.of(), List.of());
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarCoberturaAutomovel() {
        var obj = new CoberturaAutomóvel("0531", 1, null, "INT", "PROC", 
            BigDecimal.TEN, BigDecimal.TEN, LocalDate.now(), LocalDate.now().plusYears(1),
            1, 1, 1, BigDecimal.TEN, BigDecimal.TEN, null, null, null, 
            null, null, null, null, null);
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarFranquia() {
        var obj = new Franquia(1, null, BigDecimal.TEN, "Desc", 1);
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarPessoasAssociadasCondutor() {
        var obj = new PessoasAssociadasCondutor("12345678900", 1, LocalDate.now(), 10);
        assertThat(obj).isNotNull();
    }
}
