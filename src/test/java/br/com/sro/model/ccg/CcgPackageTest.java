package br.com.sro.model.ccg;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class CcgPackageTest {

    @Test
    void deveCriarCcg() {
        var obj = new Ccg(LocalDate.now().plusYears(1), List.of(), List.of(), List.of());
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarTomador() {
        var obj = new Tomador("12345678900", 1, 1, "Razão Social", BigDecimal.TEN);
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarColateral() {
        var obj = new Colateral(1, BigDecimal.TEN, "SP", "BRA");
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarFiador() {
        var obj = new Fiador("12345678900", 1, "Razão Social");
        assertThat(obj).isNotNull();
    }
}
