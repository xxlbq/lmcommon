package redis.clients.jedis.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.tests.HostAndPortUtil.HostAndPort;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PipeliningTest extends Assert {
    private static HostAndPort hnp = HostAndPortUtil.getRedisServers().get(0);

    private Jedis jedis;

    @Before
    public void setUp() throws Exception {
        jedis = new Jedis(hnp.host, hnp.port, 500);
        jedis.connect();
        jedis.auth("bestwiz");
        jedis.flushAll();
    }

    @Test
    public void pipeline() throws UnsupportedEncodingException {
        List<Object> results = jedis.pipelined(new PipelineBlock() {
            public void execute() {
                set("foo", "bar");
                get("foo");
            }
        });

        assertEquals(2, results.size());
        assertEquals("OK", results.get(0));
        assertEquals("bar", results.get(1));

        Pipeline p = jedis.pipelined();
        p.set("foo", "bar");
        p.get("foo");
        results = p.syncAndReturnAll();

        assertEquals(2, results.size());
        assertEquals("OK", results.get(0));
        assertEquals("bar", results.get(1));

    }

    @Test
    public void pipelineResponse() {
        jedis.set("string", "foo");
        jedis.lpush("list", "foo");
        jedis.hset("hash", "foo", "bar");
        jedis.zadd("zset", 1, "foo");
        jedis.sadd("set", "foo");

        Pipeline p = jedis.pipelined();
        Response<String> string = p.get("string");
        Response<String> list = p.lpop("list");
        Response<String> hash = p.hget("hash", "foo");
        Response<Set<String>> zset = p.zrange("zset", 0, -1);
        Response<String> set = p.spop("set");
        Response<Boolean> blist = p.exists("list");
        Response<Double> zincrby = p.zincrby("zset", 1, "foo");
        Response<Long> zcard = p.zcard("zset");
        p.lpush("list", "bar");
        Response<List<String>> lrange = p.lrange("list", 0, -1);
        Response<Map<String, String>> hgetAll = p.hgetAll("hash");
        p.sadd("set", "foo");
        Response<Set<String>> smembers = p.smembers("set");
        Response<Set<Tuple>> zrangeWithScores = p.zrangeWithScores("zset", 0,
                -1);
        p.sync();

        assertEquals("foo", string.get());
        assertEquals("foo", list.get());
        assertEquals("bar", hash.get());
        assertEquals("foo", zset.get().iterator().next());
        assertEquals("foo", set.get());
        assertEquals(false, blist.get());
        assertEquals(Double.valueOf(2), zincrby.get());
        assertEquals(Long.valueOf(1), zcard.get());
        assertEquals(1, lrange.get().size());
        assertNotNull(hgetAll.get().get("foo"));
        assertEquals(1, smembers.get().size());
        assertEquals(1, zrangeWithScores.get().size());
    }
    
    @Test
    public void pipelineResponseWithData() {
        jedis.zadd("zset", 1, "foo");
        
        Pipeline p = jedis.pipelined();
        Response<Double> score = p.zscore("zset", "foo");
        p.sync();

        assertNotNull(score.get());    
    }
    
    @Test
    public void pipelineSelect() {
        Pipeline p = jedis.pipelined();
        p.select(1);
        p.sync();
    }
    
    @Test
    public void pipelineResponseWithoutData() {
        jedis.zadd("zset", 1, "foo");
        
        Pipeline p = jedis.pipelined();
        Response<Double> score = p.zscore("zset", "bar");
        p.sync();

        assertNull(score.get());
    }


    @Test(expected = JedisDataException.class)
    public void pipelineResponseWithinPipeline() {
        jedis.set("string", "foo");

        Pipeline p = jedis.pipelined();
        Response<String> string = p.get("string");
        string.get();
        p.sync();
    }

    @Test
    public void pipelineWithPubSub() {
        Pipeline pipelined = jedis.pipelined();
        Response<Long> p1 = pipelined.publish("foo", "bar");
        Response<Long> p2 = pipelined.publish("foo".getBytes(), "bar"
                .getBytes());
        pipelined.sync();
        assertEquals(0, p1.get().longValue());
        assertEquals(0, p2.get().longValue());
    }

    @Test
    public void canRetrieveUnsetKey() {
        Pipeline p = jedis.pipelined();
        Response<String> shouldNotExist = p.get(UUID.randomUUID().toString());
        p.sync();
        assertNull(shouldNotExist.get());
    }
    
    @Test
    public void piplineWithError(){
    	Pipeline p = jedis.pipelined();
    	p.set("foo", "bar");
        Response<Set<String>> error = p.smembers("foo");
        Response<String> r = p.get("foo");
        p.sync();
        try{
        	error.get();
        	fail();
        }catch(JedisDataException e){
        	//that is fine we should be here
        }
        assertEquals(r.get(), "bar");
    }

    @Test
    public void multi(){
        Pipeline p = jedis.pipelined();
        p.multi();
        Response<Long> r1 = p.hincrBy("a", "f1", -1);
        Response<Long> r2 = p.hincrBy("a", "f1", -2);
        Response<List<Object>> r3 = p.exec();
        List<Object> result = p.syncAndReturnAll();
        
        assertEquals(new Long(-1), r1.get());
        assertEquals(new Long(-3), r2.get());
        
        assertEquals(4, result.size());
        
        assertEquals("OK", result.get(0));
        assertEquals("QUEUED", result.get(1));
        assertEquals("QUEUED", result.get(2));
        
        //4th result is a list with the results from the multi
        @SuppressWarnings("unchecked")
		List<Object> multiResult = (List<Object>) result.get(3);
        assertEquals(new Long(-1), multiResult.get(0));
        assertEquals(new Long(-3), multiResult.get(1));
        
        assertEquals(new Long(-1), r3.get().get(0));
        assertEquals(new Long(-3), r3.get().get(1));
        
    }
}
