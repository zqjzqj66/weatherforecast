package cn.edu.zzuli.weatherforecast.testPackage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: WeatherForecasrTest
 * @date 2018/10/31 8:51
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastTest {

    @Autowired
    DataSource dataSource;

	@Test
	public void tests() throws SQLException {
		System.out.println(dataSource.getConnection()
        );

	}

}
