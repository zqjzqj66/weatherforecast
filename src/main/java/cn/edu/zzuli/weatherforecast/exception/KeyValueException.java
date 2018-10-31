package cn.edu.zzuli.weatherforecast.exception;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: KeyValueException
 * @date 2018/10/31 13:42
 */
public class KeyValueException extends RuntimeException {

    public KeyValueException(String msg) {
        super(msg);
    }

    public KeyValueException() {
        super();
    }
}
