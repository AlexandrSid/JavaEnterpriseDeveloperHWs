package main.java.com.alexsid.lesson4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MyNumberUtils<T extends Number> {
    //    private static Class[] numberTypes = new Class[]{
//            Integer.class,
//            Double.class,
//            Long.class,
//            Float.class,
//            Short.class,
//            Byte.class,
//            BigDecimal.class,
//            BigInteger.class,
//            AtomicInteger.class,
//            AtomicLong.class
//    };
    private Class<T> type;


    public <T> T getZero() {

        if (type.isInstance(Integer.class)) {
            return (T) Integer.valueOf(0);
        }
        if (type.isInstance(Double.class)) {
            return (T) Double.valueOf(0.0);
        }
        if (type.isInstance(Long.class)) {
            return (T) Long.valueOf(0);
        }
        if (type.isInstance(Float.class)) {
            return (T) Float.valueOf(0);
        }
        if (type.isInstance(Short.class)) {
            return (T) Short.valueOf((short) 0);
        }
        if (type.isInstance(Byte.class)) {
            return (T) Byte.valueOf((byte) 0);
        }
        if (type.isInstance(BigDecimal.class)) {
            return (T) Integer.valueOf(0);
        }
        if (type.isInstance(BigInteger.class)) {
            return (T) Integer.valueOf(0);
        }
        if (type.isInstance(AtomicInteger.class)) {
            return (T) Integer.valueOf(0);
        }
        if (type.isInstance(AtomicLong.class)) {
            return (T) Integer.valueOf(0);
        }
        return null;
    }

    public static Number sum(Number n1, Number n2) {
        BigDecimal bn1 = new BigDecimal(n1.toString());
        BigDecimal bn2 = new BigDecimal(n2.toString());
        return (Number) new BigDecimal(String.valueOf(bn1.add(bn2)));
    }

    public static Number divide(Number n, Number divider) {
        BigDecimal bn = new BigDecimal(n.toString());
        BigDecimal bdivider = new BigDecimal(divider.toString());
        return (Number) bn.divide(bdivider);
    }

    public static boolean notEqualsInt(Number n, Integer integer) {
        BigDecimal bn = new BigDecimal(n.toString());
        return !bn.equals(new BigDecimal(BigInteger.valueOf(integer)));
    }
}
