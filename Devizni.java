package tvz.hr.priprema.x;

import java.math.BigDecimal;


public interface Devizni {
    BigDecimal konvertirajUKune(BigDecimal iznos);
    BigDecimal konvertirajUDevize(BigDecimal iznos);
}
