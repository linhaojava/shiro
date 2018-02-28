package cn.shiro.demo.util;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedUtil {
	private static MemCachedClient memCachedClient;

	static {
		String[] servers = { "192.168.115.31\\:11211" };

		memCachedClient = new MemCachedClient();

		// 设置服务器权重
		// Integer[] weights = {3, 2};

		// 创建一个Socked连接池实例
		SockIOPool pool = SockIOPool.getInstance();

		// 向连接池设置服务器和权重
		pool.setServers(servers);
		// pool.setWeights(weights);

		// set some TCP settings
		// disable nagle
		// set the read timeout to 3 secs
		// and don't set a connect timeout
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		// initialize the connection pool
		pool.initialize();
	}

	/**
	 * 获取MemCached实例
	 * 
	 * @return
	 * @author jack.zhang
	 * @date 2015年12月17日
	 */
	public static synchronized MemCachedClient getInstance() {
		return memCachedClient;
	}
}
