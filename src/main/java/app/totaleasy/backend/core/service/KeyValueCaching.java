package app.totaleasy.backend.core.service;

public interface KeyValueCaching<K, V> {

    V get(K key);
}
