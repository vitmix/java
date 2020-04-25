package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Скласти регулярне вираження, що визначає чи є заданий рядок IP адресою, записаним у десятковому виді.
 * Приклад правильних виражень: 127.0.0.1, 255.255.255.0.
 * Приклад неправильних виражень: 1300.6.7.8, abc.def.gha.bcd.
 */
public class Task8 {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            start(reader);
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }

    public static void start(BufferedReader reader) {
        IpExtractor ipExtractor = new IpExtractorImpl(reader);
        ipExtractor.extractFromReader();
        String ip = ipExtractor.getIp();

        IpValidator ipValidator = new IpValidator(ip);
        try {
            ipValidator.validate();
        } catch (IPInvalidException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
}

interface IpExtractor {

    void extractFromReader();

    String getIp();
}

class IpExtractorImpl implements IpExtractor {

    private BufferedReader reader;
    private String ip;

    // without keyword "public" the visibility of constructor will be "package" [will be visible only in ipvalidation package]
    IpExtractorImpl(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void extractFromReader() {
        try {
            System.out.println("Enter the IP:");
            ip = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getIp() {
        return ip;
    }
}

class IpValidator {
    private static final String IPV4_REGEXP = "^(?:(?:^|\\.)(?:2(?:5[0-5]|[0-4]\\d)|1?\\d?\\d)){4}$";
    private static Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEXP);
    private String ip;

    IpValidator(String ip) {
        this.ip = ip;
    }

    public void validate() throws IPInvalidException {
        if (isValid(ip)) {
            System.out.println("IP is correct ... ");
        } else {
            throw new IPInvalidException("IP is not correct !..");
        }
    }

    private static boolean isValid(final String ip) {
        return IPV4_PATTERN.matcher(ip).matches();
    }
}
