package defsu.sc;

import defsu.sc.core.*;
import defsu.sc.records.general.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"defsu.sc.maps"})
public class ScApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScApplication.class, args);

		MenuCore.init();

		Country c = new Country();
		c.setCommonName("Türkiye");
		c.setNativeName("Türkiye");
		c.setVisible(true);
		c.setUpdatedBy(0L);

		SuResponse r = c.save();
		System.out.println(c.getId());
		c.setNativeName("Turkiye2");
		r = c.save();


		SuField.FieldList props = ObjectCore.getFieldList(c);


		System.out.println(c.getId() + " " + c.getNativeName());
		c = new Country(2L);
		System.out.println(c.getId() + " - " + c.getNativeName());



		ObjectCore.ListResult result = ObjectCore.list(new Country(),2);
		System.out.println("");
		List<SuField.FieldList> rProps = ObjectCore.getFieldLists(result.records);
		System.out.println("");
	}

}
