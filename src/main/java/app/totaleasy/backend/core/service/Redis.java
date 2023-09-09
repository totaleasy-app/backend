package app.totaleasy.backend.core.service;

import app.totaleasy.backend.core.util.RedisConnection;

public abstract class Redis<K, V> implements KeyValueCaching<K, V> {

    protected static final RedisConnection redisConnection = RedisConnection.getInstance();

    public abstract V get(K key);
}
