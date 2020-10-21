package com.alexsid.lesson13;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class OutOfMetaspaceMemoryProvider {

    private static Map<String, A> classMap = new HashMap();

    //Start with -XX:MaxMetaspaceSize=100m -verbose:gc
    //g1gc java14 preview features enabled
    public static void main(String[] args) throws MalformedURLException {//run with VM -verbose:gc option

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String iterateName = "file:" + i + ".jar";
            URL[] classLoaderURL = {new URL(iterateName)};
            URLClassLoader classLoader = new URLClassLoader(classLoaderURL);
            A poxyA = (A) Proxy.newProxyInstance(
                    classLoader,
                    new Class<?>[]{A.class},
                    new ClassAInvocationHandler(new Aimpl())
            );
            classMap.put(iterateName, poxyA);
        }
    }

    private static class ClassAInvocationHandler implements InvocationHandler {
        public ClassAInvocationHandler(Aimpl nothing) {}

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

}
