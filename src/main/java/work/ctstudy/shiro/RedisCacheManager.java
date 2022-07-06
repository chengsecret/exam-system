package work.ctstudy.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

//自定义缓存管理器
public class RedisCacheManager implements CacheManager {

    //参数cacheName：认证缓存或授权缓存的统一名称（在Realm中设置的名称）
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new RedisCache<K,V>(cacheName);
    }
}