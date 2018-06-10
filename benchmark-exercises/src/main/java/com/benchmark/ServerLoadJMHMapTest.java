package com.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Fork(1)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
// @OutputTimeUnit(TimeUnit.SECONDS)
@Threads(8)
@State(Scope.Benchmark)
public class ServerLoadJMHMapTest {
    @Param({ "0", "1", "10", "100", "1000", "10000", "100000", "1000000", "10000000" })
    static long mapSize;
    @Param({
            "com.infinitydb.map.air.AirConcurrentMap",
            "java.util.HashMap",
            "java.util.TreeMap",
            "java.util.concurrent.ConcurrentHashMap",
            "java.util.concurrent.ConcurrentSkipListMap"
    })
    static String mapClassName;
    static Map<Long, Long> map;
    // This will be optimized out by the JIT
    static boolean isCollectionsSynchronizedMap;
    @Setup(Level.Trial)
    static public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<Map<Long, Long>> mapClass =
                (Class<Map<Long, Long>>)Class.forName(mapClassName);
        map = mapClass.newInstance();
        if (!(map instanceof ConcurrentMap)) {
            isCollectionsSynchronizedMap = true;
            if (!(map instanceof NavigableMap))
                map = Collections.synchronizedMap(map);
            else
                map = Collections.synchronizedNavigableMap((NavigableMap)map);
        }
        // Random random = new Random(System.nanoTime());
        Random random = new Random(1);
        // Load up the Map
        for (long i = 0; i < mapSize; i++) {
            long o = random.nextLong();
            map.put(o, o);
        }
    }
    @Benchmark
    public static long testServerLoadGet() {
        long k = ThreadLocalRandom.current().nextLong();
        Long v = map.get(k);
        return v == null ? Integer.MIN_VALUE : v;
    }
    /*
     * We have to do both put() and remove() so the map doesn't change size and
     * so the remove() actually has an effect because the removed key actually
     * exists.
     */
    @Benchmark
    public static long testServerLoadPutRemove() {
        long k = ThreadLocalRandom.current().nextLong();;
        map.put(k, k);
        return map.remove(k);
    }
    @Benchmark
    public static long testServerLoadIterator() {
        long max = Long.MIN_VALUE;
        // This if condition is actually very fast and the JIT will remove it.
        if (isCollectionsSynchronizedMap) {
            // Must synch on the map, not the iterator or values!
            // This prevents concurrent modification
            synchronized (map) {
                for (Long v : map.values()) {
                    max = v > max ? v : max;
                }
            }
        } else {
            for (Long v : map.values()) {
                max = v > max ? v : max;
            }
        }
        return max;
    }
    @Benchmark
    public static long testServerLoadForEach() {
        class SummingBiConsumer implements BiConsumer<Object, Long> {
            long max = Long.MIN_VALUE;
            public void accept(Object k, Long v) {
                max = v > max ? v : max;
            }
        }
        SummingBiConsumer summingBiConsumer = new SummingBiConsumer();
        if (isCollectionsSynchronizedMap) {
            // Must synch on the map, not the iterator or values!
            // This prevents concurrent modification
            synchronized (map) {
                map.forEach(summingBiConsumer);
            }
        } else {
            map.forEach(summingBiConsumer);
        }
        return summingBiConsumer.max;
    }
    @Benchmark
    public static long testServerLoadSerialStream() {
        // This if condition is actually very fast and the JIT will remove it.
        if (isCollectionsSynchronizedMap) {
            // Must synch on the map, not the iterator or values!
            // This prevents concurrent modification
            synchronized (map) {
                return map.values().stream()
                        .mapToLong(o -> ((Long)o).longValue())
                        .reduce(Long.MIN_VALUE, (x, y) -> x > y ? x : y);
            }
        } else {
            return map.values().stream()
                    .mapToLong(o -> ((Long)o).longValue())
                    .reduce(Long.MIN_VALUE, (x, y) -> x > y ? x : y);
        }
    }
    @Benchmark
    public static long testServerLoadParallelStream() {
        // This if condition is actually very fast and the JIT will remove it.
        if (isCollectionsSynchronizedMap) {
            // Must synch on the map, not the iterator or set!
            // This prevents concurrent modification
            synchronized (map) {
                return map.values().stream().parallel()
                        .mapToLong(o -> ((Long)o).longValue())
                        .reduce(Long.MIN_VALUE, (x, y) -> x > y ? x : y);
            }
        } else {
            return map.values().stream().parallel()
                    .mapToLong(o -> ((Long)o).longValue())
                    .reduce(Long.MIN_VALUE, (x, y) -> x > y ? x : y);
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ServerLoadJMHMapTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}