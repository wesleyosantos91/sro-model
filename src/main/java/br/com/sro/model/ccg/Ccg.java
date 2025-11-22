package br.com.sro.model.ccg;

import java.time.LocalDate;
import java.util.List;

/**
 * Representa um Contrato de Contragarantia (CCG) no sistema SRO.
 *
 * <p>O CCG é um contrato entre seguradora e ressegurador que estabelece
 * as condições de contragarantia para operações de resseguro.</p>
 *
 * <p><b>Especificação:</b> SUSEP SRO v2.0.0 - Aba CCG</p>
 * <p><b>Bounded Context:</b> CCG (Contragarantia)</p>
 * <p><b>Aggregate Root:</b> Sim</p>
 *
 * @see <a href="https://www2.susep.gov.br/">SUSEP</a>
 */
public record Ccg(
    /**
     * Data de fim de vigência do contrato de contragarantia
     *
     * <p><b>Cardinalidade:</b> [0..1]</p>
     * <p><b>Tag:</b> data_termino</p>
     * <p><b>Tipo:</b> Date</p>
     * <p><b>Formato:</b> AAAA-MM-DD</p>
     * <p><b>Tamanho:</b> 10</p>
     */
    LocalDate dataTermino,

    /**
     * Lista de Tomador associados ao CCG
     *
     * <p><b>Tipo:</b> Lista de Tomador</p>
     */
    List<Tomador> tomadors,

    /**
     * Lista de Colateral associados ao CCG
     *
     * <p><b>Tipo:</b> Lista de Colateral</p>
     */
    List<Colateral> colaterals,

    /**
     * Lista de Fiador associados ao CCG
     *
     * <p><b>Tipo:</b> Lista de Fiador</p>
     */
    List<Fiador> fiadors
) {}