import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTT_Simple_Test {
    public static void main( String[] args ) throws Exception{
        String message = "Hello!";
        MqttClient client = new MqttClient("wss://iot.eclipse.org:443", "publisher", new MemoryPersistence());
        System.out.println("Broker: " + client.getServerURI());
        MqttConnectOptions connectOptions = new MqttConnectOptions();

        connectOptions.setKeepAliveInterval(120);
        connectOptions.setWill("my/status", "offline".getBytes(), 2, true);
        connectOptions.setCleanSession(false);
        connectOptions.setUserName("user");
        connectOptions.setPassword("pass".toCharArray());

        client.connect(connectOptions);
        System.out.println("Connected");

        client.publish("my/topic/123", message.getBytes(), 1, false);
        System.out.println("Published successful. Message: " + message);

        client.disconnect();
        System.out.println("Disconnected.");
        System.exit(0);
    }
}

