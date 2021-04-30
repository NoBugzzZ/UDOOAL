package cn.edu.nju;

import cn.edu.nju.gateway.GatewayClient;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        GatewayClient gatewayClient=new GatewayClient("src/main/resources/config.json");
        gatewayClient.start();
    }
}
