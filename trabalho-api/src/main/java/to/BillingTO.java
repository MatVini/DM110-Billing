package to;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillingTO implements Serializable {
	
	private int billCode;
	private String description;
	private double value;
	private Date expirationDate;
	private Date paidDate;

}
