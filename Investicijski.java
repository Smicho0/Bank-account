package tvz.hr.priprema.x;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public interface Investicijski {

    public void promijeniImovinu(String datum, double postotnaGlavnica) throws ParseException;
}
