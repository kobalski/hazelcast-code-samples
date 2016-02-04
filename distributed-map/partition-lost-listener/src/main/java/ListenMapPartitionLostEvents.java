import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.instance.HazelcastInstanceFactory;
import com.hazelcast.map.MapPartitionLostEvent;
import com.hazelcast.map.listener.MapPartitionLostListener;

public class ListenMapPartitionLostEvents {

    public static void main(String[] args) {
        Config config = new Config();
        // keeps its data if a single node crashes
        config.getMapConfig("map0").setBackupCount(1);

        HazelcastInstance instance1 = HazelcastInstanceFactory.newHazelcastInstance(config);
        HazelcastInstance instance2 = HazelcastInstanceFactory.newHazelcastInstance(config);
        HazelcastInstance instance3 = HazelcastInstanceFactory.newHazelcastInstance(config);


        IMap<Object, Object> map0 = instance1.getMap("map0");
        map0.put(0, 0);

        map0.addPartitionLostListener(new MapPartitionLostListener() {
            @Override
            public void partitionLost(MapPartitionLostEvent event) {
                System.out.println(event);
            }
        });

        instance2.getLifecycleService().terminate();
    }
}
