import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.DOMConfiguration;

public class Log {
    static Logger logger=Logger.getLogger(Log.class);
    public Log(){
        DOMConfigurator.configure("log4j.xml");

    }
    public void information(String message)
    {
        logger.info(message);
    }
    public  void warning(String message)
    {
        logger.warn(message);
    }
    public  void error(String message)
    {
        logger.error(message);
    }
}
