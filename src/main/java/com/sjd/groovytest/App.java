package com.sjd.groovytest;

import java.time.Duration;
import java.time.Instant;
import java.util.Hashtable;

import org.apache.commons.io.IOUtils;

import groovy.lang.GroovyClassLoader;

/**
 * Hello world!
 *
 */
public class App {

	public static Hashtable<String, Class<?>> cache = new Hashtable<>();

	public static GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

	public static void main(String[] args) throws Exception {

		String script = loadScript(App.class, "scripts/Simple1.groovy");

		for (int i = 0; i < 10; i++)
			compileAndExecute("simple", script);

		String dsl = loadScript(App.class, "scripts/Dsl.groovy");

		String main = loadScript(App.class, "scripts/MainScript.groovy");
		Class<?> m = createCachedClass("mainScript", main);

		String useMain = loadScript(App.class, "scripts/UseMainScript.groovy");
		Class<?> um = createCachedClass("useMainScript", useMain);

		Payload p = new Payload();
		p.setLatitude(100);
		p.setLongitude(200);

		ITestScript ts = (ITestScript) um.newInstance();
		String message = ts.getMessage(p);

		System.out.println("Dependent script : " + message);

	}

	public static String loadScript(Class clazz, String name) throws Exception {

		return IOUtils.toString(clazz.getClassLoader().getResourceAsStream(name));

	}

	public static Class<?> createCachedClass(String name, String script) throws Exception {

		Class<?> theParsedClass = groovyClassLoader.parseClass(script);
		groovyClassLoader.close();

		// Put it in the cache
		cache.put(name, theParsedClass);

		return theParsedClass;
	}

	public static void compileAndExecute(String name, String script) throws Exception {

		Instant starts = Instant.now();

		Class<?> theParsedClass;

		// See if we have it
		if (cache.containsKey(name)) {

			// Get from cache
			theParsedClass = cache.get(name);

		} else {

			// Compile and add to the cache
			theParsedClass = createCachedClass(name, script);

		}

		Payload p = new Payload();
		p.setLatitude(1);
		p.setLongitude(2);

		ITestScript ts = (ITestScript) theParsedClass.newInstance();
		String message = ts.getMessage(p);

		Instant ends = Instant.now();

		System.out.println("Took : " + (Duration.between(starts, ends).getNano() / 1_000_000) + " ms");
		System.out.println(message);
		System.out.println("After lat/long : " + p);

	}
}
