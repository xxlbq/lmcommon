package redis.clients.jedis.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Connection;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class ConnectionTest extends Assert {
    private Connection client;
    private final String IP="10.18.20.26";
    private final int PORT=6379;

    @Before
    public void setUp() throws Exception {
        client = new Connection();
    }

    @After
    public void tearDown() throws Exception {
        client.disconnect();
    }

    @Test(expected = JedisConnectionException.class)
    public void checkUnkownHost() {
        client.setHost(IP);
        client.connect();
    }

    @Test(expected = JedisConnectionException.class)
    public void checkWrongPort() {
        client.setHost(IP);
        client.setPort(PORT+10);
        client.connect();
    }
    
    @Test
    public void connectIfNotConnectedWhenSettingTimeoutInfinite() {
	client.setHost("IP");
        client.setPort(PORT);
	client.setTimeoutInfinite();
    }

}