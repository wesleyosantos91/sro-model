package br.com.sro.model.movimentosinistro;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class MovimentoSinistroPackageTest {

    @Test
    void deveCriarMovimentoSinistro() {
        var obj = new MovimentoSinistro("12345", "0531", "SIN1", "MOV1", "APOL1", 
            null, null, null, null, BigDecimal.TEN, BigDecimal.TEN, "BRL", 
            1, 1, 1, 1, 1, 2, 1, "uuid", LocalDate.now(), LocalDate.now(), 
            LocalDate.now(), null, List.of());
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarAdicional() {
        var obj = new Adicional(1, BigDecimal.TEN, BigDecimal.TEN);
        assertThat(obj).isNotNull();
    }

    @Test
    void deveCriarAdicionais() {
        var obj = new Adicionais(1, BigDecimal.TEN, BigDecimal.TEN);
        assertThat(obj).isNotNull();
    }
}
